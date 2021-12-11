package androidx.core.provider;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Deprecated
public class SelfDestructiveThread {
    private static final int MSG_DESTRUCTION = 0;
    private static final int MSG_INVOKE_RUNNABLE = 1;
    private Handler.Callback mCallback = new Handler.Callback() {
        /* class androidx.core.provider.SelfDestructiveThread.C02191 */

        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    SelfDestructiveThread.this.onDestruction();
                    return true;
                case 1:
                    SelfDestructiveThread.this.onInvokeRunnable((Runnable) msg.obj);
                    return true;
                default:
                    return true;
            }
        }
    };
    private final int mDestructAfterMillisec;
    private int mGeneration;
    private Handler mHandler;
    private final Object mLock = new Object();
    private final int mPriority;
    private HandlerThread mThread;
    private final String mThreadName;

    public interface ReplyCallback<T> {
        void onReply(T t);
    }

    public SelfDestructiveThread(String threadName, int priority, int destructAfterMillisec) {
        this.mThreadName = threadName;
        this.mPriority = priority;
        this.mDestructAfterMillisec = destructAfterMillisec;
        this.mGeneration = 0;
    }

    public boolean isRunning() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mThread != null;
        }
        return z;
    }

    public int getGeneration() {
        int i;
        synchronized (this.mLock) {
            i = this.mGeneration;
        }
        return i;
    }

    private void post(Runnable runnable) {
        synchronized (this.mLock) {
            if (this.mThread == null) {
                HandlerThread handlerThread = new HandlerThread(this.mThreadName, this.mPriority);
                this.mThread = handlerThread;
                handlerThread.start();
                this.mHandler = new Handler(this.mThread.getLooper(), this.mCallback);
                this.mGeneration++;
            }
            this.mHandler.removeMessages(0);
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    public <T> void postAndReply(final Callable<T> callable, final ReplyCallback<T> reply) {
        final Handler calleeHandler = CalleeHandler.create();
        post(new Runnable() {
            /* class androidx.core.provider.SelfDestructiveThread.RunnableC02202 */

            public void run() {
                final Object obj;
                try {
                    obj = callable.call();
                } catch (Exception e) {
                    obj = null;
                }
                calleeHandler.post(new Runnable() {
                    /* class androidx.core.provider.SelfDestructiveThread.RunnableC02202.RunnableC02211 */

                    public void run() {
                        reply.onReply(obj);
                    }
                });
            }
        });
    }

    public <T> T postAndWait(final Callable<T> callable, int timeoutMillis) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        final Condition cond = lock.newCondition();
        final AtomicReference<T> holder = new AtomicReference<>();
        final AtomicBoolean running = new AtomicBoolean(true);
        post(new Runnable() {
            /* class androidx.core.provider.SelfDestructiveThread.RunnableC02223 */

            public void run() {
                try {
                    holder.set(callable.call());
                } catch (Exception e) {
                }
                lock.lock();
                try {
                    running.set(false);
                    cond.signal();
                } finally {
                    lock.unlock();
                }
            }
        });
        lock.lock();
        try {
            if (!running.get()) {
                return holder.get();
            }
            long remaining = TimeUnit.MILLISECONDS.toNanos((long) timeoutMillis);
            do {
                try {
                    remaining = cond.awaitNanos(remaining);
                } catch (InterruptedException e) {
                }
                if (!running.get()) {
                    T t = holder.get();
                    lock.unlock();
                    return t;
                }
            } while (remaining > 0);
            throw new InterruptedException("timeout");
        } finally {
            lock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void onInvokeRunnable(Runnable runnable) {
        runnable.run();
        synchronized (this.mLock) {
            this.mHandler.removeMessages(0);
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(0), (long) this.mDestructAfterMillisec);
        }
    }

    /* access modifiers changed from: package-private */
    public void onDestruction() {
        synchronized (this.mLock) {
            if (!this.mHandler.hasMessages(1)) {
                this.mThread.quit();
                this.mThread = null;
                this.mHandler = null;
            }
        }
    }
}
