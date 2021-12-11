package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;

abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    private static final int INVALID_POINTER = -1;
    private int activePointerId = -1;
    private Runnable flingRunnable;
    private boolean isBeingDragged;
    private int lastMotionY;
    OverScroller scroller;
    private int touchSlop = -1;
    private VelocityTracker velocityTracker;

    public HeaderBehavior() {
    }

    public HeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, V child, MotionEvent ev) {
        int pointerIndex;
        if (this.touchSlop < 0) {
            this.touchSlop = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
        }
        if (ev.getActionMasked() == 2 && this.isBeingDragged) {
            int i = this.activePointerId;
            if (i == -1 || (pointerIndex = ev.findPointerIndex(i)) == -1) {
                return false;
            }
            int y = (int) ev.getY(pointerIndex);
            if (Math.abs(y - this.lastMotionY) > this.touchSlop) {
                this.lastMotionY = y;
                return true;
            }
        }
        if (ev.getActionMasked() == 0) {
            this.activePointerId = -1;
            int x = (int) ev.getX();
            int y2 = (int) ev.getY();
            boolean z = canDragView(child) && parent.isPointInChildBounds(child, x, y2);
            this.isBeingDragged = z;
            if (z) {
                this.lastMotionY = y2;
                this.activePointerId = ev.getPointerId(0);
                ensureVelocityTracker();
                OverScroller overScroller = this.scroller;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.scroller.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(ev);
        }
        return false;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0073  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout parent, V child, MotionEvent ev) {
        VelocityTracker velocityTracker2;
        boolean consumeUp = false;
        switch (ev.getActionMasked()) {
            case 1:
                VelocityTracker velocityTracker3 = this.velocityTracker;
                if (velocityTracker3 != null) {
                    consumeUp = true;
                    velocityTracker3.addMovement(ev);
                    this.velocityTracker.computeCurrentVelocity(1000);
                    fling(parent, child, -getScrollRangeForDragFling(child), 0, this.velocityTracker.getYVelocity(this.activePointerId));
                }
                this.isBeingDragged = false;
                this.activePointerId = -1;
                velocityTracker2 = this.velocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.velocityTracker = null;
                    break;
                }
                break;
            case 2:
                int activePointerIndex = ev.findPointerIndex(this.activePointerId);
                if (activePointerIndex != -1) {
                    int y = (int) ev.getY(activePointerIndex);
                    this.lastMotionY = y;
                    scroll(parent, child, this.lastMotionY - y, getMaxDragOffset(child), 0);
                    break;
                } else {
                    return false;
                }
            case 3:
                this.isBeingDragged = false;
                this.activePointerId = -1;
                velocityTracker2 = this.velocityTracker;
                if (velocityTracker2 != null) {
                }
                break;
            case 6:
                int newIndex = ev.getActionIndex() == 0 ? 1 : 0;
                this.activePointerId = ev.getPointerId(newIndex);
                this.lastMotionY = (int) (ev.getY(newIndex) + 0.5f);
                break;
        }
        VelocityTracker velocityTracker4 = this.velocityTracker;
        if (velocityTracker4 != null) {
            velocityTracker4.addMovement(ev);
        }
        if (this.isBeingDragged || consumeUp) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int setHeaderTopBottomOffset(CoordinatorLayout parent, V header, int newOffset) {
        return setHeaderTopBottomOffset(parent, header, newOffset, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    /* access modifiers changed from: package-private */
    public int setHeaderTopBottomOffset(CoordinatorLayout parent, V v, int newOffset, int minOffset, int maxOffset) {
        int newOffset2;
        int curOffset = getTopAndBottomOffset();
        if (minOffset == 0 || curOffset < minOffset || curOffset > maxOffset || curOffset == (newOffset2 = MathUtils.clamp(newOffset, minOffset, maxOffset))) {
            return 0;
        }
        setTopAndBottomOffset(newOffset2);
        return curOffset - newOffset2;
    }

    /* access modifiers changed from: package-private */
    public int getTopBottomOffsetForScrollingSibling() {
        return getTopAndBottomOffset();
    }

    /* access modifiers changed from: package-private */
    public final int scroll(CoordinatorLayout coordinatorLayout, V header, int dy, int minOffset, int maxOffset) {
        return setHeaderTopBottomOffset(coordinatorLayout, header, getTopBottomOffsetForScrollingSibling() - dy, minOffset, maxOffset);
    }

    /* access modifiers changed from: package-private */
    public final boolean fling(CoordinatorLayout coordinatorLayout, V layout, int minOffset, int maxOffset, float velocityY) {
        Runnable runnable = this.flingRunnable;
        if (runnable != null) {
            layout.removeCallbacks(runnable);
            this.flingRunnable = null;
        }
        if (this.scroller == null) {
            this.scroller = new OverScroller(layout.getContext());
        }
        this.scroller.fling(0, getTopAndBottomOffset(), 0, Math.round(velocityY), 0, 0, minOffset, maxOffset);
        if (this.scroller.computeScrollOffset()) {
            FlingRunnable flingRunnable2 = new FlingRunnable(coordinatorLayout, layout);
            this.flingRunnable = flingRunnable2;
            ViewCompat.postOnAnimation(layout, flingRunnable2);
            return true;
        }
        onFlingFinished(coordinatorLayout, layout);
        return false;
    }

    /* access modifiers changed from: package-private */
    public void onFlingFinished(CoordinatorLayout parent, V v) {
    }

    /* access modifiers changed from: package-private */
    public boolean canDragView(V v) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getMaxDragOffset(V view) {
        return -view.getHeight();
    }

    /* access modifiers changed from: package-private */
    public int getScrollRangeForDragFling(V view) {
        return view.getHeight();
    }

    private void ensureVelocityTracker() {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
    }

    /* access modifiers changed from: private */
    public class FlingRunnable implements Runnable {
        private final V layout;
        private final CoordinatorLayout parent;

        FlingRunnable(CoordinatorLayout parent2, V layout2) {
            this.parent = parent2;
            this.layout = layout2;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v6, resolved type: com.google.android.material.appbar.HeaderBehavior */
        /* JADX DEBUG: Multi-variable search result rejected for r0v7, resolved type: com.google.android.material.appbar.HeaderBehavior */
        /* JADX WARN: Multi-variable type inference failed */
        public void run() {
            if (this.layout != null && HeaderBehavior.this.scroller != null) {
                if (HeaderBehavior.this.scroller.computeScrollOffset()) {
                    HeaderBehavior headerBehavior = HeaderBehavior.this;
                    headerBehavior.setHeaderTopBottomOffset(this.parent, this.layout, headerBehavior.scroller.getCurrY());
                    ViewCompat.postOnAnimation(this.layout, this);
                    return;
                }
                HeaderBehavior.this.onFlingFinished(this.parent, this.layout);
            }
        }
    }
}
