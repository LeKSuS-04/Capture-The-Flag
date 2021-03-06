package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* access modifiers changed from: package-private */
public final class ScrollEventAdapter extends RecyclerView.OnScrollListener {
    private static final int NO_POSITION = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_IN_PROGRESS_FAKE_DRAG = 4;
    private static final int STATE_IN_PROGRESS_IMMEDIATE_SCROLL = 3;
    private static final int STATE_IN_PROGRESS_MANUAL_DRAG = 1;
    private static final int STATE_IN_PROGRESS_SMOOTH_SCROLL = 2;
    private int mAdapterState;
    private ViewPager2.OnPageChangeCallback mCallback;
    private boolean mDataSetChangeHappened;
    private boolean mDispatchSelected;
    private int mDragStartPosition;
    private boolean mFakeDragging;
    private final LinearLayoutManager mLayoutManager;
    private final RecyclerView mRecyclerView;
    private boolean mScrollHappened;
    private int mScrollState;
    private ScrollEventValues mScrollValues = new ScrollEventValues();
    private int mTarget;
    private final ViewPager2 mViewPager;

    ScrollEventAdapter(ViewPager2 viewPager) {
        this.mViewPager = viewPager;
        RecyclerView recyclerView = viewPager.mRecyclerView;
        this.mRecyclerView = recyclerView;
        this.mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        resetState();
    }

    private void resetState() {
        this.mAdapterState = 0;
        this.mScrollState = 0;
        this.mScrollValues.reset();
        this.mDragStartPosition = -1;
        this.mTarget = -1;
        this.mDispatchSelected = false;
        this.mScrollHappened = false;
        this.mFakeDragging = false;
        this.mDataSetChangeHappened = false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (!(this.mAdapterState == 1 && this.mScrollState == 1) && newState == 1) {
            startDrag(false);
        } else if (!isInAnyDraggingState() || newState != 2) {
            if (isInAnyDraggingState() && newState == 0) {
                boolean dispatchIdle = false;
                updateScrollEventValues();
                if (!this.mScrollHappened) {
                    if (this.mScrollValues.mPosition != -1) {
                        dispatchScrolled(this.mScrollValues.mPosition, 0.0f, 0);
                    }
                    dispatchIdle = true;
                } else if (this.mScrollValues.mOffsetPx == 0) {
                    dispatchIdle = true;
                    if (this.mDragStartPosition != this.mScrollValues.mPosition) {
                        dispatchSelected(this.mScrollValues.mPosition);
                    }
                }
                if (dispatchIdle) {
                    dispatchStateChanged(0);
                    resetState();
                }
            }
            if (this.mAdapterState == 2 && newState == 0 && this.mDataSetChangeHappened) {
                updateScrollEventValues();
                if (this.mScrollValues.mOffsetPx == 0) {
                    if (this.mTarget != this.mScrollValues.mPosition) {
                        dispatchSelected(this.mScrollValues.mPosition == -1 ? 0 : this.mScrollValues.mPosition);
                    }
                    dispatchStateChanged(0);
                    resetState();
                }
            }
        } else if (this.mScrollHappened) {
            dispatchStateChanged(2);
            this.mDispatchSelected = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if ((r8 < 0) == r6.mViewPager.isRtl()) goto L_0x0022;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b  */
    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        boolean scrollingForward;
        int i;
        this.mScrollHappened = true;
        updateScrollEventValues();
        if (this.mDispatchSelected) {
            this.mDispatchSelected = false;
            if (dy <= 0) {
                if (dy == 0) {
                }
                scrollingForward = false;
                i = (scrollingForward || this.mScrollValues.mOffsetPx == 0) ? this.mScrollValues.mPosition : this.mScrollValues.mPosition + 1;
                this.mTarget = i;
                if (this.mDragStartPosition != i) {
                    dispatchSelected(i);
                }
            }
            scrollingForward = true;
            if (scrollingForward) {
            }
            this.mTarget = i;
            if (this.mDragStartPosition != i) {
            }
        } else if (this.mAdapterState == 0) {
            int position = this.mScrollValues.mPosition;
            dispatchSelected(position == -1 ? 0 : position);
        }
        dispatchScrolled(this.mScrollValues.mPosition == -1 ? 0 : this.mScrollValues.mPosition, this.mScrollValues.mOffset, this.mScrollValues.mOffsetPx);
        int i2 = this.mScrollValues.mPosition;
        int i3 = this.mTarget;
        if ((i2 == i3 || i3 == -1) && this.mScrollValues.mOffsetPx == 0 && this.mScrollState != 1) {
            dispatchStateChanged(0);
            resetState();
        }
    }

    private void updateScrollEventValues() {
        int start;
        int sizePx;
        ScrollEventValues values = this.mScrollValues;
        values.mPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        if (values.mPosition == -1) {
            values.reset();
            return;
        }
        View firstVisibleView = this.mLayoutManager.findViewByPosition(values.mPosition);
        if (firstVisibleView == null) {
            values.reset();
            return;
        }
        int leftDecorations = this.mLayoutManager.getLeftDecorationWidth(firstVisibleView);
        int rightDecorations = this.mLayoutManager.getRightDecorationWidth(firstVisibleView);
        int topDecorations = this.mLayoutManager.getTopDecorationHeight(firstVisibleView);
        int bottomDecorations = this.mLayoutManager.getBottomDecorationHeight(firstVisibleView);
        ViewGroup.LayoutParams params = firstVisibleView.getLayoutParams();
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams margin = (ViewGroup.MarginLayoutParams) params;
            leftDecorations += margin.leftMargin;
            rightDecorations += margin.rightMargin;
            topDecorations += margin.topMargin;
            bottomDecorations += margin.bottomMargin;
        }
        int decoratedHeight = firstVisibleView.getHeight() + topDecorations + bottomDecorations;
        int decoratedWidth = firstVisibleView.getWidth() + leftDecorations + rightDecorations;
        if (this.mLayoutManager.getOrientation() == 0) {
            sizePx = decoratedWidth;
            start = (firstVisibleView.getLeft() - leftDecorations) - this.mRecyclerView.getPaddingLeft();
            if (this.mViewPager.isRtl()) {
                start = -start;
            }
        } else {
            sizePx = decoratedHeight;
            start = (firstVisibleView.getTop() - topDecorations) - this.mRecyclerView.getPaddingTop();
        }
        values.mOffsetPx = -start;
        if (values.mOffsetPx >= 0) {
            values.mOffset = sizePx == 0 ? 0.0f : ((float) values.mOffsetPx) / ((float) sizePx);
        } else if (new AnimateLayoutChangeDetector(this.mLayoutManager).mayHaveInterferingAnimations()) {
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        } else {
            throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(values.mOffsetPx)));
        }
    }

    private void startDrag(boolean isFakeDrag) {
        this.mFakeDragging = isFakeDrag;
        this.mAdapterState = isFakeDrag ? 4 : 1;
        int i = this.mTarget;
        if (i != -1) {
            this.mDragStartPosition = i;
            this.mTarget = -1;
        } else if (this.mDragStartPosition == -1) {
            this.mDragStartPosition = getPosition();
        }
        dispatchStateChanged(1);
    }

    /* access modifiers changed from: package-private */
    public void notifyDataSetChangeHappened() {
        this.mDataSetChangeHappened = true;
    }

    /* access modifiers changed from: package-private */
    public void notifyProgrammaticScroll(int target, boolean smooth) {
        this.mAdapterState = smooth ? 2 : 3;
        boolean hasNewTarget = false;
        this.mFakeDragging = false;
        if (this.mTarget != target) {
            hasNewTarget = true;
        }
        this.mTarget = target;
        dispatchStateChanged(2);
        if (hasNewTarget) {
            dispatchSelected(target);
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyBeginFakeDrag() {
        this.mAdapterState = 4;
        startDrag(true);
    }

    /* access modifiers changed from: package-private */
    public void notifyEndFakeDrag() {
        if (!isDragging() || this.mFakeDragging) {
            this.mFakeDragging = false;
            updateScrollEventValues();
            if (this.mScrollValues.mOffsetPx == 0) {
                if (this.mScrollValues.mPosition != this.mDragStartPosition) {
                    dispatchSelected(this.mScrollValues.mPosition);
                }
                dispatchStateChanged(0);
                resetState();
                return;
            }
            dispatchStateChanged(2);
        }
    }

    /* access modifiers changed from: package-private */
    public void setOnPageChangeCallback(ViewPager2.OnPageChangeCallback callback) {
        this.mCallback = callback;
    }

    /* access modifiers changed from: package-private */
    public int getScrollState() {
        return this.mScrollState;
    }

    /* access modifiers changed from: package-private */
    public boolean isIdle() {
        return this.mScrollState == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isDragging() {
        return this.mScrollState == 1;
    }

    /* access modifiers changed from: package-private */
    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    private boolean isInAnyDraggingState() {
        int i = this.mAdapterState;
        return i == 1 || i == 4;
    }

    /* access modifiers changed from: package-private */
    public double getRelativeScrollPosition() {
        updateScrollEventValues();
        return ((double) this.mScrollValues.mPosition) + ((double) this.mScrollValues.mOffset);
    }

    private void dispatchStateChanged(int state) {
        if ((this.mAdapterState != 3 || this.mScrollState != 0) && this.mScrollState != state) {
            this.mScrollState = state;
            ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
            if (onPageChangeCallback != null) {
                onPageChangeCallback.onPageScrollStateChanged(state);
            }
        }
    }

    private void dispatchSelected(int target) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageSelected(target);
        }
    }

    private void dispatchScrolled(int position, float offset, int offsetPx) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrolled(position, offset, offsetPx);
        }
    }

    private int getPosition() {
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }

    /* access modifiers changed from: private */
    public static final class ScrollEventValues {
        float mOffset;
        int mOffsetPx;
        int mPosition;

        ScrollEventValues() {
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mPosition = -1;
            this.mOffset = 0.0f;
            this.mOffsetPx = 0;
        }
    }
}
