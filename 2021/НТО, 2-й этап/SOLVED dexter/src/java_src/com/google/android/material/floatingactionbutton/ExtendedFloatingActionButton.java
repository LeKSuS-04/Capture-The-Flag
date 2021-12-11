package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.C0552R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.List;

public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    private static final int ANIM_STATE_HIDING = 1;
    private static final int ANIM_STATE_NONE = 0;
    private static final int ANIM_STATE_SHOWING = 2;
    private static final int DEF_STYLE_RES = C0552R.style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    static final Property<View, Float> HEIGHT = new Property<View, Float>(Float.class, "height") {
        /* class com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.C06445 */

        public void set(View object, Float value) {
            object.getLayoutParams().height = value.intValue();
            object.requestLayout();
        }

        public Float get(View object) {
            return Float.valueOf((float) object.getLayoutParams().height);
        }
    };
    static final Property<View, Float> PADDING_END = new Property<View, Float>(Float.class, "paddingEnd") {
        /* class com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.C06467 */

        public void set(View object, Float value) {
            ViewCompat.setPaddingRelative(object, ViewCompat.getPaddingStart(object), object.getPaddingTop(), value.intValue(), object.getPaddingBottom());
        }

        public Float get(View object) {
            return Float.valueOf((float) ViewCompat.getPaddingEnd(object));
        }
    };
    static final Property<View, Float> PADDING_START = new Property<View, Float>(Float.class, "paddingStart") {
        /* class com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.C06456 */

        public void set(View object, Float value) {
            ViewCompat.setPaddingRelative(object, value.intValue(), object.getPaddingTop(), ViewCompat.getPaddingEnd(object), object.getPaddingBottom());
        }

        public Float get(View object) {
            return Float.valueOf((float) ViewCompat.getPaddingStart(object));
        }
    };
    static final Property<View, Float> WIDTH = new Property<View, Float>(Float.class, "width") {
        /* class com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.C06434 */

        public void set(View object, Float value) {
            object.getLayoutParams().width = value.intValue();
            object.requestLayout();
        }

        public Float get(View object) {
            return Float.valueOf((float) object.getLayoutParams().width);
        }
    };
    private int animState;
    private boolean animateShowBeforeLayout;
    private final CoordinatorLayout.Behavior<ExtendedFloatingActionButton> behavior;
    private final AnimatorTracker changeVisibilityTracker;
    private final int collapsedSize;
    private final MotionStrategy extendStrategy;
    private int extendedPaddingEnd;
    private int extendedPaddingStart;
    private final MotionStrategy hideStrategy;
    private boolean isExtended;
    private boolean isTransforming;
    protected ColorStateList originalTextCsl;
    private final MotionStrategy showStrategy;
    private final MotionStrategy shrinkStrategy;

    interface Size {
        int getHeight();

        ViewGroup.LayoutParams getLayoutParams();

        int getPaddingEnd();

        int getPaddingStart();

        int getWidth();
    }

    public static abstract class OnChangedCallback {
        public void onShown(ExtendedFloatingActionButton extendedFab) {
        }

        public void onHidden(ExtendedFloatingActionButton extendedFab) {
        }

        public void onExtended(ExtendedFloatingActionButton extendedFab) {
        }

        public void onShrunken(ExtendedFloatingActionButton extendedFab) {
        }
    }

    public ExtendedFloatingActionButton(Context context) {
        this(context, null);
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, C0552R.attr.extendedFloatingActionButtonStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public ExtendedFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, r9), attrs, defStyleAttr);
        int i = DEF_STYLE_RES;
        this.animState = 0;
        AnimatorTracker animatorTracker = new AnimatorTracker();
        this.changeVisibilityTracker = animatorTracker;
        ShowStrategy showStrategy2 = new ShowStrategy(animatorTracker);
        this.showStrategy = showStrategy2;
        HideStrategy hideStrategy2 = new HideStrategy(animatorTracker);
        this.hideStrategy = hideStrategy2;
        this.isExtended = true;
        this.isTransforming = false;
        this.animateShowBeforeLayout = false;
        Context context2 = getContext();
        this.behavior = new ExtendedFloatingActionButtonBehavior(context2, attrs);
        TypedArray a = ThemeEnforcement.obtainStyledAttributes(context2, attrs, C0552R.styleable.ExtendedFloatingActionButton, defStyleAttr, i, new int[0]);
        MotionSpec showMotionSpec = MotionSpec.createFromAttribute(context2, a, C0552R.styleable.ExtendedFloatingActionButton_showMotionSpec);
        MotionSpec hideMotionSpec = MotionSpec.createFromAttribute(context2, a, C0552R.styleable.ExtendedFloatingActionButton_hideMotionSpec);
        MotionSpec extendMotionSpec = MotionSpec.createFromAttribute(context2, a, C0552R.styleable.ExtendedFloatingActionButton_extendMotionSpec);
        MotionSpec shrinkMotionSpec = MotionSpec.createFromAttribute(context2, a, C0552R.styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        this.collapsedSize = a.getDimensionPixelSize(C0552R.styleable.ExtendedFloatingActionButton_collapsedSize, -1);
        this.extendedPaddingStart = ViewCompat.getPaddingStart(this);
        this.extendedPaddingEnd = ViewCompat.getPaddingEnd(this);
        AnimatorTracker changeSizeTracker = new AnimatorTracker();
        ChangeSizeStrategy changeSizeStrategy = new ChangeSizeStrategy(changeSizeTracker, new Size() {
            /* class com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.C06401 */

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getWidth() {
                return (ExtendedFloatingActionButton.this.getMeasuredWidth() - (ExtendedFloatingActionButton.this.getCollapsedPadding() * 2)) + ExtendedFloatingActionButton.this.extendedPaddingStart + ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getHeight() {
                return ExtendedFloatingActionButton.this.getMeasuredHeight();
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getPaddingStart() {
                return ExtendedFloatingActionButton.this.extendedPaddingStart;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getPaddingEnd() {
                return ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public ViewGroup.LayoutParams getLayoutParams() {
                return new ViewGroup.LayoutParams(-2, -2);
            }
        }, true);
        this.extendStrategy = changeSizeStrategy;
        ChangeSizeStrategy changeSizeStrategy2 = new ChangeSizeStrategy(changeSizeTracker, new Size() {
            /* class com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.C06412 */

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getWidth() {
                return ExtendedFloatingActionButton.this.getCollapsedSize();
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getHeight() {
                return ExtendedFloatingActionButton.this.getCollapsedSize();
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getPaddingStart() {
                return ExtendedFloatingActionButton.this.getCollapsedPadding();
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getPaddingEnd() {
                return ExtendedFloatingActionButton.this.getCollapsedPadding();
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public ViewGroup.LayoutParams getLayoutParams() {
                return new ViewGroup.LayoutParams(getWidth(), getHeight());
            }
        }, false);
        this.shrinkStrategy = changeSizeStrategy2;
        showStrategy2.setMotionSpec(showMotionSpec);
        hideStrategy2.setMotionSpec(hideMotionSpec);
        changeSizeStrategy.setMotionSpec(extendMotionSpec);
        changeSizeStrategy2.setMotionSpec(shrinkMotionSpec);
        a.recycle();
        setShapeAppearanceModel(ShapeAppearanceModel.builder(context2, attrs, defStyleAttr, i, ShapeAppearanceModel.PILL).build());
        saveOriginalTextCsl();
    }

    @Override // android.widget.TextView
    public void setTextColor(int color) {
        super.setTextColor(color);
        saveOriginalTextCsl();
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);
        saveOriginalTextCsl();
    }

    private void saveOriginalTextCsl() {
        this.originalTextCsl = getTextColors();
    }

    /* access modifiers changed from: protected */
    public void silentlyUpdateTextColor(ColorStateList csl) {
        super.setTextColor(csl);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.material.button.MaterialButton
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isExtended && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.isExtended = false;
            this.shrinkStrategy.performNow();
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.behavior;
    }

    public void setExtended(boolean extended) {
        if (this.isExtended != extended) {
            MotionStrategy motionStrategy = extended ? this.extendStrategy : this.shrinkStrategy;
            if (!motionStrategy.shouldCancel()) {
                motionStrategy.performNow();
            }
        }
    }

    public final boolean isExtended() {
        return this.isExtended;
    }

    public void setAnimateShowBeforeLayout(boolean animateShowBeforeLayout2) {
        this.animateShowBeforeLayout = animateShowBeforeLayout2;
    }

    public void setPaddingRelative(int start, int top, int end, int bottom) {
        super.setPaddingRelative(start, top, end, bottom);
        if (this.isExtended && !this.isTransforming) {
            this.extendedPaddingStart = start;
            this.extendedPaddingEnd = end;
        }
    }

    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        if (this.isExtended && !this.isTransforming) {
            this.extendedPaddingStart = ViewCompat.getPaddingStart(this);
            this.extendedPaddingEnd = ViewCompat.getPaddingEnd(this);
        }
    }

    public void addOnShowAnimationListener(Animator.AnimatorListener listener) {
        this.showStrategy.addAnimationListener(listener);
    }

    public void removeOnShowAnimationListener(Animator.AnimatorListener listener) {
        this.showStrategy.removeAnimationListener(listener);
    }

    public void addOnHideAnimationListener(Animator.AnimatorListener listener) {
        this.hideStrategy.addAnimationListener(listener);
    }

    public void removeOnHideAnimationListener(Animator.AnimatorListener listener) {
        this.hideStrategy.removeAnimationListener(listener);
    }

    public void addOnShrinkAnimationListener(Animator.AnimatorListener listener) {
        this.shrinkStrategy.addAnimationListener(listener);
    }

    public void removeOnShrinkAnimationListener(Animator.AnimatorListener listener) {
        this.shrinkStrategy.removeAnimationListener(listener);
    }

    public void addOnExtendAnimationListener(Animator.AnimatorListener listener) {
        this.extendStrategy.addAnimationListener(listener);
    }

    public void removeOnExtendAnimationListener(Animator.AnimatorListener listener) {
        this.extendStrategy.removeAnimationListener(listener);
    }

    public void hide() {
        performMotion(this.hideStrategy, null);
    }

    public void hide(OnChangedCallback callback) {
        performMotion(this.hideStrategy, callback);
    }

    public void show() {
        performMotion(this.showStrategy, null);
    }

    public void show(OnChangedCallback callback) {
        performMotion(this.showStrategy, callback);
    }

    public void extend() {
        performMotion(this.extendStrategy, null);
    }

    public void extend(OnChangedCallback callback) {
        performMotion(this.extendStrategy, callback);
    }

    public void shrink() {
        performMotion(this.shrinkStrategy, null);
    }

    public void shrink(OnChangedCallback callback) {
        performMotion(this.shrinkStrategy, callback);
    }

    public MotionSpec getShowMotionSpec() {
        return this.showStrategy.getMotionSpec();
    }

    public void setShowMotionSpec(MotionSpec spec) {
        this.showStrategy.setMotionSpec(spec);
    }

    public void setShowMotionSpecResource(int id) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    public MotionSpec getHideMotionSpec() {
        return this.hideStrategy.getMotionSpec();
    }

    public void setHideMotionSpec(MotionSpec spec) {
        this.hideStrategy.setMotionSpec(spec);
    }

    public void setHideMotionSpecResource(int id) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    public MotionSpec getExtendMotionSpec() {
        return this.extendStrategy.getMotionSpec();
    }

    public void setExtendMotionSpec(MotionSpec spec) {
        this.extendStrategy.setMotionSpec(spec);
    }

    public void setExtendMotionSpecResource(int id) {
        setExtendMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    public MotionSpec getShrinkMotionSpec() {
        return this.shrinkStrategy.getMotionSpec();
    }

    public void setShrinkMotionSpec(MotionSpec spec) {
        this.shrinkStrategy.setMotionSpec(spec);
    }

    public void setShrinkMotionSpecResource(int id) {
        setShrinkMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void performMotion(final MotionStrategy strategy, final OnChangedCallback callback) {
        if (!strategy.shouldCancel()) {
            if (!shouldAnimateVisibilityChange()) {
                strategy.performNow();
                strategy.onChange(callback);
                return;
            }
            measure(0, 0);
            Animator animator = strategy.createAnimator();
            animator.addListener(new AnimatorListenerAdapter() {
                /* class com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.C06423 */
                private boolean cancelled;

                public void onAnimationStart(Animator animation) {
                    strategy.onAnimationStart(animation);
                    this.cancelled = false;
                }

                public void onAnimationCancel(Animator animation) {
                    this.cancelled = true;
                    strategy.onAnimationCancel();
                }

                public void onAnimationEnd(Animator animation) {
                    strategy.onAnimationEnd();
                    if (!this.cancelled) {
                        strategy.onChange(callback);
                    }
                }
            });
            for (Animator.AnimatorListener l : strategy.getListeners()) {
                animator.addListener(l);
            }
            animator.start();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isOrWillBeShown() {
        if (getVisibility() != 0) {
            if (this.animState == 2) {
                return true;
            }
            return false;
        } else if (this.animState != 1) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isOrWillBeHidden() {
        if (getVisibility() == 0) {
            if (this.animState == 1) {
                return true;
            }
            return false;
        } else if (this.animState != 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean shouldAnimateVisibilityChange() {
        return (ViewCompat.isLaidOut(this) || (!isOrWillBeShown() && this.animateShowBeforeLayout)) && !isInEditMode();
    }

    /* access modifiers changed from: package-private */
    public int getCollapsedSize() {
        int i = this.collapsedSize;
        if (i < 0) {
            return (Math.min(ViewCompat.getPaddingStart(this), ViewCompat.getPaddingEnd(this)) * 2) + getIconSize();
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        private static final boolean AUTO_HIDE_DEFAULT = false;
        private static final boolean AUTO_SHRINK_DEFAULT = true;
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        private OnChangedCallback internalAutoHideCallback;
        private OnChangedCallback internalAutoShrinkCallback;
        private Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = AUTO_SHRINK_DEFAULT;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, C0552R.styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = a.getBoolean(C0552R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = a.getBoolean(C0552R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, AUTO_SHRINK_DEFAULT);
            a.recycle();
        }

        public void setAutoHideEnabled(boolean autoHide) {
            this.autoHideEnabled = autoHide;
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        public void setAutoShrinkEnabled(boolean autoShrink) {
            this.autoShrinkEnabled = autoShrink;
        }

        public boolean isAutoShrinkEnabled() {
            return this.autoShrinkEnabled;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout parent, ExtendedFloatingActionButton child, Rect rect) {
            return super.getInsetDodgeRect(parent, (View) child, rect);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams lp) {
            if (lp.dodgeInsetEdges == 0) {
                lp.dodgeInsetEdges = 80;
            }
        }

        public boolean onDependentViewChanged(CoordinatorLayout parent, ExtendedFloatingActionButton child, View dependency) {
            if (dependency instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(parent, (AppBarLayout) dependency, child);
                return false;
            } else if (!isBottomSheet(dependency)) {
                return false;
            } else {
                updateFabVisibilityForBottomSheet(dependency, child);
                return false;
            }
        }

        private static boolean isBottomSheet(View view) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) lp).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void setInternalAutoHideCallback(OnChangedCallback callback) {
            this.internalAutoHideCallback = callback;
        }

        /* access modifiers changed from: package-private */
        public void setInternalAutoShrinkCallback(OnChangedCallback callback) {
            this.internalAutoShrinkCallback = callback;
        }

        private boolean shouldUpdateVisibility(View dependency, ExtendedFloatingActionButton child) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            if ((this.autoHideEnabled || this.autoShrinkEnabled) && lp.getAnchorId() == dependency.getId()) {
                return AUTO_SHRINK_DEFAULT;
            }
            return false;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout parent, AppBarLayout appBarLayout, ExtendedFloatingActionButton child) {
            if (!shouldUpdateVisibility(appBarLayout, child)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(parent, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                shrinkOrHide(child);
                return AUTO_SHRINK_DEFAULT;
            }
            extendOrShow(child);
            return AUTO_SHRINK_DEFAULT;
        }

        private boolean updateFabVisibilityForBottomSheet(View bottomSheet, ExtendedFloatingActionButton child) {
            if (!shouldUpdateVisibility(bottomSheet, child)) {
                return false;
            }
            if (bottomSheet.getTop() < (child.getHeight() / 2) + ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).topMargin) {
                shrinkOrHide(child);
                return AUTO_SHRINK_DEFAULT;
            }
            extendOrShow(child);
            return AUTO_SHRINK_DEFAULT;
        }

        /* access modifiers changed from: protected */
        public void shrinkOrHide(ExtendedFloatingActionButton fab) {
            MotionStrategy strategy;
            boolean z = this.autoShrinkEnabled;
            OnChangedCallback callback = z ? this.internalAutoShrinkCallback : this.internalAutoHideCallback;
            if (z) {
                strategy = fab.shrinkStrategy;
            } else {
                strategy = fab.hideStrategy;
            }
            fab.performMotion(strategy, callback);
        }

        /* access modifiers changed from: protected */
        public void extendOrShow(ExtendedFloatingActionButton fab) {
            MotionStrategy strategy;
            boolean z = this.autoShrinkEnabled;
            OnChangedCallback callback = z ? this.internalAutoShrinkCallback : this.internalAutoHideCallback;
            if (z) {
                strategy = fab.extendStrategy;
            } else {
                strategy = fab.showStrategy;
            }
            fab.performMotion(strategy, callback);
        }

        public boolean onLayoutChild(CoordinatorLayout parent, ExtendedFloatingActionButton child, int layoutDirection) {
            List<View> dependencies = parent.getDependencies(child);
            int count = dependencies.size();
            for (int i = 0; i < count; i++) {
                View dependency = dependencies.get(i);
                if (!(dependency instanceof AppBarLayout)) {
                    if (isBottomSheet(dependency) && updateFabVisibilityForBottomSheet(dependency, child)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(parent, (AppBarLayout) dependency, child)) {
                    break;
                }
            }
            parent.onLayoutChild(child, layoutDirection);
            return AUTO_SHRINK_DEFAULT;
        }
    }

    class ChangeSizeStrategy extends BaseMotionStrategy {
        private final boolean extending;
        private final Size size;

        ChangeSizeStrategy(AnimatorTracker animatorTracker, Size size2, boolean extending2) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
            this.size = size2;
            this.extending = extending2;
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void performNow() {
            ExtendedFloatingActionButton.this.isExtended = this.extending;
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = this.size.getLayoutParams().width;
                layoutParams.height = this.size.getLayoutParams().height;
                ViewCompat.setPaddingRelative(ExtendedFloatingActionButton.this, this.size.getPaddingStart(), ExtendedFloatingActionButton.this.getPaddingTop(), this.size.getPaddingEnd(), ExtendedFloatingActionButton.this.getPaddingBottom());
                ExtendedFloatingActionButton.this.requestLayout();
            }
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void onChange(OnChangedCallback callback) {
            if (callback != null) {
                if (this.extending) {
                    callback.onExtended(ExtendedFloatingActionButton.this);
                } else {
                    callback.onShrunken(ExtendedFloatingActionButton.this);
                }
            }
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public int getDefaultMotionSpecResource() {
            return this.extending ? C0552R.animator.mtrl_extended_fab_change_size_expand_motion_spec : C0552R.animator.mtrl_extended_fab_change_size_collapse_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public AnimatorSet createAnimator() {
            MotionSpec spec = getCurrentMotionSpec();
            if (spec.hasPropertyValues("width")) {
                PropertyValuesHolder[] widthValues = spec.getPropertyValues("width");
                widthValues[0].setFloatValues((float) ExtendedFloatingActionButton.this.getWidth(), (float) this.size.getWidth());
                spec.setPropertyValues("width", widthValues);
            }
            if (spec.hasPropertyValues("height")) {
                PropertyValuesHolder[] heightValues = spec.getPropertyValues("height");
                heightValues[0].setFloatValues((float) ExtendedFloatingActionButton.this.getHeight(), (float) this.size.getHeight());
                spec.setPropertyValues("height", heightValues);
            }
            if (spec.hasPropertyValues("paddingStart")) {
                PropertyValuesHolder[] paddingValues = spec.getPropertyValues("paddingStart");
                paddingValues[0].setFloatValues((float) ViewCompat.getPaddingStart(ExtendedFloatingActionButton.this), (float) this.size.getPaddingStart());
                spec.setPropertyValues("paddingStart", paddingValues);
            }
            if (spec.hasPropertyValues("paddingEnd")) {
                PropertyValuesHolder[] paddingValues2 = spec.getPropertyValues("paddingEnd");
                paddingValues2[0].setFloatValues((float) ViewCompat.getPaddingEnd(ExtendedFloatingActionButton.this), (float) this.size.getPaddingEnd());
                spec.setPropertyValues("paddingEnd", paddingValues2);
            }
            if (spec.hasPropertyValues("labelOpacity")) {
                PropertyValuesHolder[] labelOpacityValues = spec.getPropertyValues("labelOpacity");
                boolean z = this.extending;
                float endValue = 0.0f;
                float startValue = z ? 0.0f : 1.0f;
                if (z) {
                    endValue = 1.0f;
                }
                labelOpacityValues[0].setFloatValues(startValue, endValue);
                spec.setPropertyValues("labelOpacity", labelOpacityValues);
            }
            return super.createAnimator(spec);
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.isExtended = this.extending;
            ExtendedFloatingActionButton.this.isTransforming = true;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.this.isTransforming = false;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = this.size.getLayoutParams().width;
                layoutParams.height = this.size.getLayoutParams().height;
            }
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean shouldCancel() {
            return this.extending == ExtendedFloatingActionButton.this.isExtended || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }
    }

    class ShowStrategy extends BaseMotionStrategy {
        public ShowStrategy(AnimatorTracker animatorTracker) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void performNow() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void onChange(OnChangedCallback callback) {
            if (callback != null) {
                callback.onShown(ExtendedFloatingActionButton.this);
            }
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public int getDefaultMotionSpecResource() {
            return C0552R.animator.mtrl_extended_fab_show_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.animState = 2;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.this.animState = 0;
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean shouldCancel() {
            return ExtendedFloatingActionButton.this.isOrWillBeShown();
        }
    }

    class HideStrategy extends BaseMotionStrategy {
        private boolean isCancelled;

        public HideStrategy(AnimatorTracker animatorTracker) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void performNow() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void onChange(OnChangedCallback callback) {
            if (callback != null) {
                callback.onHidden(ExtendedFloatingActionButton.this);
            }
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean shouldCancel() {
            return ExtendedFloatingActionButton.this.isOrWillBeHidden();
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public int getDefaultMotionSpecResource() {
            return C0552R.animator.mtrl_extended_fab_hide_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.isCancelled = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.animState = 1;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationCancel() {
            super.onAnimationCancel();
            this.isCancelled = true;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.this.animState = 0;
            if (!this.isCancelled) {
                ExtendedFloatingActionButton.this.setVisibility(8);
            }
        }
    }
}
