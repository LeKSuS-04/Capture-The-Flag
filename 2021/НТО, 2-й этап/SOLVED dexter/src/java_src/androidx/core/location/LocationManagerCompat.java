package androidx.core.location;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.location.GnssStatusCompat;
import androidx.core.p003os.ExecutorCompat;
import androidx.core.util.Preconditions;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class LocationManagerCompat {
    private static final long PRE_N_LOOPER_TIMEOUT_S = 4;
    private static Field sContextField;
    private static final SimpleArrayMap<Object, Object> sGnssStatusListeners = new SimpleArrayMap<>();

    public static boolean isLocationEnabled(LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.isLocationEnabled(locationManager);
        }
        if (Build.VERSION.SDK_INT <= 19) {
            try {
                if (sContextField == null) {
                    sContextField = LocationManager.class.getDeclaredField("mContext");
                }
                sContextField.setAccessible(true);
                Context context = (Context) sContextField.get(locationManager);
                if (Build.VERSION.SDK_INT != 19) {
                    return !TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed"));
                }
                if (Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) != 0) {
                    return true;
                }
                return false;
            } catch (ClassCastException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            }
        }
        if (locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps")) {
            return true;
        }
        return false;
    }

    public static boolean registerGnssStatusCallback(LocationManager locationManager, GnssStatusCompat.Callback callback, Handler handler) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, ExecutorCompat.create(handler), callback);
        }
        return registerGnssStatusCallback(locationManager, new InlineHandlerExecutor(handler), callback);
    }

    public static boolean registerGnssStatusCallback(LocationManager locationManager, Executor executor, GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, null, executor, callback);
        }
        Looper looper = Looper.myLooper();
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        return registerGnssStatusCallback(locationManager, new Handler(looper), executor, callback);
    }

    private static boolean registerGnssStatusCallback(final LocationManager locationManager, Handler baseHandler, Executor executor, GnssStatusCompat.Callback callback) {
        final GpsStatusTransport transport;
        if (Build.VERSION.SDK_INT >= 30) {
            SimpleArrayMap<Object, Object> simpleArrayMap = sGnssStatusListeners;
            synchronized (simpleArrayMap) {
                GnssStatusTransport transport2 = (GnssStatusTransport) simpleArrayMap.get(callback);
                if (transport2 == null) {
                    transport2 = new GnssStatusTransport(callback);
                }
                if (!locationManager.registerGnssStatusCallback(executor, transport2)) {
                    return false;
                }
                simpleArrayMap.put(callback, transport2);
                return true;
            }
        } else if (Build.VERSION.SDK_INT >= 24) {
            Preconditions.checkArgument(baseHandler != null);
            SimpleArrayMap<Object, Object> simpleArrayMap2 = sGnssStatusListeners;
            synchronized (simpleArrayMap2) {
                PreRGnssStatusTransport transport3 = (PreRGnssStatusTransport) simpleArrayMap2.get(callback);
                if (transport3 == null) {
                    transport3 = new PreRGnssStatusTransport(callback);
                } else {
                    transport3.unregister();
                }
                transport3.register(executor);
                if (!locationManager.registerGnssStatusCallback(transport3, baseHandler)) {
                    return false;
                }
                simpleArrayMap2.put(callback, transport3);
                return true;
            }
        } else {
            Preconditions.checkArgument(baseHandler != null);
            SimpleArrayMap<Object, Object> simpleArrayMap3 = sGnssStatusListeners;
            synchronized (simpleArrayMap3) {
                GpsStatusTransport transport4 = (GpsStatusTransport) simpleArrayMap3.get(callback);
                if (transport4 == null) {
                    transport = new GpsStatusTransport(locationManager, callback);
                } else {
                    transport4.unregister();
                    transport = transport4;
                }
                transport.register(executor);
                FutureTask<Boolean> task = new FutureTask<>(new Callable<Boolean>() {
                    /* class androidx.core.location.LocationManagerCompat.CallableC02021 */

                    @Override // java.util.concurrent.Callable
                    public Boolean call() {
                        return Boolean.valueOf(locationManager.addGpsStatusListener(transport));
                    }
                });
                if (Looper.myLooper() == baseHandler.getLooper()) {
                    task.run();
                } else if (!baseHandler.post(task)) {
                    throw new IllegalStateException(baseHandler + " is shutting down");
                }
                boolean interrupted = false;
                try {
                    long remainingNanos = TimeUnit.SECONDS.toNanos(PRE_N_LOOPER_TIMEOUT_S);
                    while (true) {
                        try {
                            break;
                        } catch (InterruptedException e) {
                            interrupted = true;
                            remainingNanos = (System.nanoTime() + remainingNanos) - System.nanoTime();
                        }
                    }
                    if (task.get(remainingNanos, TimeUnit.NANOSECONDS).booleanValue()) {
                        sGnssStatusListeners.put(callback, transport);
                        if (interrupted) {
                            Thread.currentThread().interrupt();
                        }
                        return true;
                    }
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    return false;
                } catch (ExecutionException e2) {
                    if (e2.getCause() instanceof RuntimeException) {
                        throw ((RuntimeException) e2.getCause());
                    } else if (e2.getCause() instanceof Error) {
                        throw ((Error) e2.getCause());
                    } else {
                        throw new IllegalStateException(e2);
                    }
                } catch (TimeoutException e3) {
                    throw new IllegalStateException(baseHandler + " appears to be blocked, please run registerGnssStatusCallback() directly on a Looper thread or ensure the main Looper is not blocked by this thread", e3);
                } catch (Throwable th) {
                    if (0 != 0) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
        }
    }

    public static void unregisterGnssStatusCallback(LocationManager locationManager, GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            SimpleArrayMap<Object, Object> simpleArrayMap = sGnssStatusListeners;
            synchronized (simpleArrayMap) {
                GnssStatusTransport transport = (GnssStatusTransport) simpleArrayMap.remove(callback);
                if (transport != null) {
                    locationManager.unregisterGnssStatusCallback(transport);
                }
            }
        } else if (Build.VERSION.SDK_INT >= 24) {
            SimpleArrayMap<Object, Object> simpleArrayMap2 = sGnssStatusListeners;
            synchronized (simpleArrayMap2) {
                PreRGnssStatusTransport transport2 = (PreRGnssStatusTransport) simpleArrayMap2.remove(callback);
                if (transport2 != null) {
                    transport2.unregister();
                    locationManager.unregisterGnssStatusCallback(transport2);
                }
            }
        } else {
            SimpleArrayMap<Object, Object> simpleArrayMap3 = sGnssStatusListeners;
            synchronized (simpleArrayMap3) {
                GpsStatusTransport transport3 = (GpsStatusTransport) simpleArrayMap3.remove(callback);
                if (transport3 != null) {
                    transport3.unregister();
                    locationManager.removeGpsStatusListener(transport3);
                }
            }
        }
    }

    private LocationManagerCompat() {
    }

    /* access modifiers changed from: private */
    public static class GnssStatusTransport extends GnssStatus.Callback {
        final GnssStatusCompat.Callback mCallback;

        GnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        public void onStarted() {
            this.mCallback.onStarted();
        }

        public void onStopped() {
            this.mCallback.onStopped();
        }

        public void onFirstFix(int ttffMillis) {
            this.mCallback.onFirstFix(ttffMillis);
        }

        public void onSatelliteStatusChanged(GnssStatus status) {
            this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(status));
        }
    }

    /* access modifiers changed from: private */
    public static class PreRGnssStatusTransport extends GnssStatus.Callback {
        final GnssStatusCompat.Callback mCallback;
        volatile Executor mExecutor;

        PreRGnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        public void register(Executor executor) {
            boolean z = true;
            Preconditions.checkArgument(executor != null, "invalid null executor");
            if (this.mExecutor != null) {
                z = false;
            }
            Preconditions.checkState(z);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        public void onStarted() {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    /* class androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.RunnableC02071 */

                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onStarted();
                        }
                    }
                });
            }
        }

        public void onStopped() {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    /* class androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.RunnableC02082 */

                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onStopped();
                        }
                    }
                });
            }
        }

        public void onFirstFix(final int ttffMillis) {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    /* class androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.RunnableC02093 */

                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onFirstFix(ttffMillis);
                        }
                    }
                });
            }
        }

        public void onSatelliteStatusChanged(final GnssStatus status) {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    /* class androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.RunnableC02104 */

                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(status));
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static class GpsStatusTransport implements GpsStatus.Listener {
        final GnssStatusCompat.Callback mCallback;
        volatile Executor mExecutor;
        private final LocationManager mLocationManager;

        GpsStatusTransport(LocationManager locationManager, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mLocationManager = locationManager;
            this.mCallback = callback;
        }

        public void register(Executor executor) {
            Preconditions.checkState(this.mExecutor == null);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        public void onGpsStatusChanged(int event) {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                switch (event) {
                    case 1:
                        executor.execute(new Runnable() {
                            /* class androidx.core.location.LocationManagerCompat.GpsStatusTransport.RunnableC02031 */

                            public void run() {
                                if (GpsStatusTransport.this.mExecutor == executor) {
                                    GpsStatusTransport.this.mCallback.onStarted();
                                }
                            }
                        });
                        return;
                    case 2:
                        executor.execute(new Runnable() {
                            /* class androidx.core.location.LocationManagerCompat.GpsStatusTransport.RunnableC02042 */

                            public void run() {
                                if (GpsStatusTransport.this.mExecutor == executor) {
                                    GpsStatusTransport.this.mCallback.onStopped();
                                }
                            }
                        });
                        return;
                    case 3:
                        GpsStatus gpsStatus = this.mLocationManager.getGpsStatus(null);
                        if (gpsStatus != null) {
                            final int ttff = gpsStatus.getTimeToFirstFix();
                            executor.execute(new Runnable() {
                                /* class androidx.core.location.LocationManagerCompat.GpsStatusTransport.RunnableC02053 */

                                public void run() {
                                    if (GpsStatusTransport.this.mExecutor == executor) {
                                        GpsStatusTransport.this.mCallback.onFirstFix(ttff);
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    case 4:
                        GpsStatus gpsStatus2 = this.mLocationManager.getGpsStatus(null);
                        if (gpsStatus2 != null) {
                            final GnssStatusCompat gnssStatus = GnssStatusCompat.wrap(gpsStatus2);
                            executor.execute(new Runnable() {
                                /* class androidx.core.location.LocationManagerCompat.GpsStatusTransport.RunnableC02064 */

                                public void run() {
                                    if (GpsStatusTransport.this.mExecutor == executor) {
                                        GpsStatusTransport.this.mCallback.onSatelliteStatusChanged(gnssStatus);
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static class InlineHandlerExecutor implements Executor {
        private final Handler mHandler;

        InlineHandlerExecutor(Handler handler) {
            this.mHandler = (Handler) Preconditions.checkNotNull(handler);
        }

        public void execute(Runnable command) {
            if (Looper.myLooper() == this.mHandler.getLooper()) {
                command.run();
            } else if (!this.mHandler.post((Runnable) Preconditions.checkNotNull(command))) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }

    private static class Api28Impl {
        private Api28Impl() {
        }

        public static boolean isLocationEnabled(LocationManager locationManager) {
            return locationManager.isLocationEnabled();
        }
    }
}
