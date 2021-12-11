package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.C0552R;
import com.google.android.material.animation.TransformationCallback;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {
    private static final long ANIMATION_DURATION = 300;
    private static final int DEF_STYLE_RES = C0552R.style.Widget_MaterialComponents_BottomAppBar;
    public static final int FAB_ALIGNMENT_MODE_CENTER = 0;
    public static final int FAB_ALIGNMENT_MODE_END = 1;
    public static final int FAB_ANIMATION_MODE_SCALE = 0;
    public static final int FAB_ANIMATION_MODE_SLIDE = 1;
    private static final int NO_MENU_RES_ID = 0;
    private int animatingModeChangeCounter;
    private ArrayList<AnimationListener> animationListeners;
    private Behavior behavior;
    private int bottomInset;
    private int fabAlignmentMode;
    AnimatorListenerAdapter fabAnimationListener;
    private int fabAnimationMode;
    private boolean fabAttached;
    private final int fabOffsetEndMode;
    TransformationCallback<FloatingActionButton> fabTransformationCallback;
    private boolean hideOnScroll;
    private int leftInset;
    private final MaterialShapeDrawable materialShapeDrawable;
    private boolean menuAnimatingWithFabAlignmentMode;
    private Animator menuAnimator;
    private Animator modeAnimator;
    private final boolean paddingBottomSystemWindowInsets;
    private final boolean paddingLeftSystemWindowInsets;
    private final boolean paddingRightSystemWindowInsets;
    private int pendingMenuResId;
    private int rightInset;

    /* access modifiers changed from: package-private */
    public interface AnimationListener {
        void onAnimationEnd(BottomAppBar bottomAppBar);

        void onAnimationStart(BottomAppBar bottomAppBar);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAlignmentMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAnimationMode {
    }

    public BottomAppBar(Context context) {
        this(context, null, 0);
    }

    public BottomAppBar(Context context, AttributeSet attrs) {
        this(context, attrs, C0552R.attr.bottomAppBarStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public BottomAppBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, r6), attrs, defStyleAttr);
        int i = DEF_STYLE_RES;
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable();
        this.materialShapeDrawable = materialShapeDrawable2;
        this.animatingModeChangeCounter = 0;
        this.pendingMenuResId = 0;
        this.menuAnimatingWithFabAlignmentMode = false;
        this.fabAttached = true;
        this.fabAnimationListener = new AnimatorListenerAdapter() {
            /* class com.google.android.material.bottomappbar.BottomAppBar.C05711 */

            public void onAnimationStart(Animator animation) {
                if (!BottomAppBar.this.menuAnimatingWithFabAlignmentMode) {
                    BottomAppBar bottomAppBar = BottomAppBar.this;
                    bottomAppBar.maybeAnimateMenuView(bottomAppBar.fabAlignmentMode, BottomAppBar.this.fabAttached);
                }
            }
        };
        this.fabTransformationCallback = new TransformationCallback<FloatingActionButton>() {
            /* class com.google.android.material.bottomappbar.BottomAppBar.C05722 */

            public void onScaleChanged(FloatingActionButton fab) {
                BottomAppBar.this.materialShapeDrawable.setInterpolation(fab.getVisibility() == 0 ? fab.getScaleY() : 0.0f);
            }

            public void onTranslationChanged(FloatingActionButton fab) {
                float horizontalOffset = fab.getTranslationX();
                if (BottomAppBar.this.getTopEdgeTreatment().getHorizontalOffset() != horizontalOffset) {
                    BottomAppBar.this.getTopEdgeTreatment().setHorizontalOffset(horizontalOffset);
                    BottomAppBar.this.materialShapeDrawable.invalidateSelf();
                }
                float f = 0.0f;
                float verticalOffset = Math.max(0.0f, -fab.getTranslationY());
                if (BottomAppBar.this.getTopEdgeTreatment().getCradleVerticalOffset() != verticalOffset) {
                    BottomAppBar.this.getTopEdgeTreatment().setCradleVerticalOffset(verticalOffset);
                    BottomAppBar.this.materialShapeDrawable.invalidateSelf();
                }
                MaterialShapeDrawable materialShapeDrawable = BottomAppBar.this.materialShapeDrawable;
                if (fab.getVisibility() == 0) {
                    f = fab.getScaleY();
                }
                materialShapeDrawable.setInterpolation(f);
            }
        };
        Context context2 = getContext();
        TypedArray a = ThemeEnforcement.obtainStyledAttributes(context2, attrs, C0552R.styleable.BottomAppBar, defStyleAttr, i, new int[0]);
        ColorStateList backgroundTint = MaterialResources.getColorStateList(context2, a, C0552R.styleable.BottomAppBar_backgroundTint);
        int elevation = a.getDimensionPixelSize(C0552R.styleable.BottomAppBar_elevation, 0);
        this.fabAlignmentMode = a.getInt(C0552R.styleable.BottomAppBar_fabAlignmentMode, 0);
        this.fabAnimationMode = a.getInt(C0552R.styleable.BottomAppBar_fabAnimationMode, 0);
        this.hideOnScroll = a.getBoolean(C0552R.styleable.BottomAppBar_hideOnScroll, false);
        this.paddingBottomSystemWindowInsets = a.getBoolean(C0552R.styleable.BottomAppBar_paddingBottomSystemWindowInsets, false);
        this.paddingLeftSystemWindowInsets = a.getBoolean(C0552R.styleable.BottomAppBar_paddingLeftSystemWindowInsets, false);
        this.paddingRightSystemWindowInsets = a.getBoolean(C0552R.styleable.BottomAppBar_paddingRightSystemWindowInsets, false);
        a.recycle();
        this.fabOffsetEndMode = getResources().getDimensionPixelOffset(C0552R.dimen.mtrl_bottomappbar_fabOffsetEndMode);
        materialShapeDrawable2.setShapeAppearanceModel(ShapeAppearanceModel.builder().setTopEdge(new BottomAppBarTopEdgeTreatment((float) a.getDimensionPixelOffset(C0552R.styleable.BottomAppBar_fabCradleMargin, 0), (float) a.getDimensionPixelOffset(C0552R.styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0), (float) a.getDimensionPixelOffset(C0552R.styleable.BottomAppBar_fabCradleVerticalOffset, 0))).build());
        materialShapeDrawable2.setShadowCompatibilityMode(2);
        materialShapeDrawable2.setPaintStyle(Paint.Style.FILL);
        materialShapeDrawable2.initializeElevationOverlay(context2);
        setElevation((float) elevation);
        DrawableCompat.setTintList(materialShapeDrawable2, backgroundTint);
        ViewCompat.setBackground(this, materialShapeDrawable2);
        ViewUtils.doOnApplyWindowInsets(this, attrs, defStyleAttr, i, new ViewUtils.OnApplyWindowInsetsListener() {
            /* class com.google.android.material.bottomappbar.BottomAppBar.C05733 */

            @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat insets, ViewUtils.RelativePadding initialPadding) {
                boolean leftInsetsChanged = false;
                boolean rightInsetsChanged = false;
                if (BottomAppBar.this.paddingBottomSystemWindowInsets) {
                    BottomAppBar.this.bottomInset = insets.getSystemWindowInsetBottom();
                }
                boolean z = true;
                if (BottomAppBar.this.paddingLeftSystemWindowInsets) {
                    leftInsetsChanged = BottomAppBar.this.leftInset != insets.getSystemWindowInsetLeft();
                    BottomAppBar.this.leftInset = insets.getSystemWindowInsetLeft();
                }
                if (BottomAppBar.this.paddingRightSystemWindowInsets) {
                    if (BottomAppBar.this.rightInset == insets.getSystemWindowInsetRight()) {
                        z = false;
                    }
                    rightInsetsChanged = z;
                    BottomAppBar.this.rightInset = insets.getSystemWindowInsetRight();
                }
                if (leftInsetsChanged || rightInsetsChanged) {
                    BottomAppBar.this.cancelAnimations();
                    BottomAppBar.this.setCutoutState();
                    BottomAppBar.this.setActionMenuViewPosition();
                }
                return insets;
            }
        });
    }

    public int getFabAlignmentMode() {
        return this.fabAlignmentMode;
    }

    public void setFabAlignmentMode(int fabAlignmentMode2) {
        setFabAlignmentModeAndReplaceMenu(fabAlignmentMode2, 0);
    }

    public void setFabAlignmentModeAndReplaceMenu(int fabAlignmentMode2, int newMenu) {
        this.pendingMenuResId = newMenu;
        this.menuAnimatingWithFabAlignmentMode = true;
        maybeAnimateMenuView(fabAlignmentMode2, this.fabAttached);
        maybeAnimateModeChange(fabAlignmentMode2);
        this.fabAlignmentMode = fabAlignmentMode2;
    }

    public int getFabAnimationMode() {
        return this.fabAnimationMode;
    }

    public void setFabAnimationMode(int fabAnimationMode2) {
        this.fabAnimationMode = fabAnimationMode2;
    }

    public void setBackgroundTint(ColorStateList backgroundTint) {
        DrawableCompat.setTintList(this.materialShapeDrawable, backgroundTint);
    }

    public ColorStateList getBackgroundTint() {
        return this.materialShapeDrawable.getTintList();
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().getFabCradleMargin();
    }

    public void setFabCradleMargin(float cradleMargin) {
        if (cradleMargin != getFabCradleMargin()) {
            getTopEdgeTreatment().setFabCradleMargin(cradleMargin);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().getFabCradleRoundedCornerRadius();
    }

    public void setFabCradleRoundedCornerRadius(float roundedCornerRadius) {
        if (roundedCornerRadius != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().setFabCradleRoundedCornerRadius(roundedCornerRadius);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().getCradleVerticalOffset();
    }

    public void setCradleVerticalOffset(float verticalOffset) {
        if (verticalOffset != getCradleVerticalOffset()) {
            getTopEdgeTreatment().setCradleVerticalOffset(verticalOffset);
            this.materialShapeDrawable.invalidateSelf();
            setCutoutState();
        }
    }

    public boolean getHideOnScroll() {
        return this.hideOnScroll;
    }

    public void setHideOnScroll(boolean hide) {
        this.hideOnScroll = hide;
    }

    public void performHide() {
        getBehavior().slideDown(this);
    }

    public void performShow() {
        getBehavior().slideUp(this);
    }

    public void setElevation(float elevation) {
        this.materialShapeDrawable.setElevation(elevation);
        getBehavior().setAdditionalHiddenOffsetY(this, this.materialShapeDrawable.getShadowRadius() - this.materialShapeDrawable.getShadowOffsetY());
    }

    public void replaceMenu(int newMenu) {
        if (newMenu != 0) {
            this.pendingMenuResId = 0;
            getMenu().clear();
            inflateMenu(newMenu);
        }
    }

    /* access modifiers changed from: package-private */
    public void addAnimationListener(AnimationListener listener) {
        if (this.animationListeners == null) {
            this.animationListeners = new ArrayList<>();
        }
        this.animationListeners.add(listener);
    }

    /* access modifiers changed from: package-private */
    public void removeAnimationListener(AnimationListener listener) {
        ArrayList<AnimationListener> arrayList = this.animationListeners;
        if (arrayList != null) {
            arrayList.remove(listener);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void dispatchAnimationStart() {
        ArrayList<AnimationListener> arrayList;
        int i = this.animatingModeChangeCounter;
        this.animatingModeChangeCounter = i + 1;
        if (i == 0 && (arrayList = this.animationListeners) != null) {
            Iterator<AnimationListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onAnimationStart(this);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void dispatchAnimationEnd() {
        ArrayList<AnimationListener> arrayList;
        int i = this.animatingModeChangeCounter - 1;
        this.animatingModeChangeCounter = i;
        if (i == 0 && (arrayList = this.animationListeners) != null) {
            Iterator<AnimationListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onAnimationEnd(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean setFabDiameter(int diameter) {
        if (((float) diameter) == getTopEdgeTreatment().getFabDiameter()) {
            return false;
        }
        getTopEdgeTreatment().setFabDiameter((float) diameter);
        this.materialShapeDrawable.invalidateSelf();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setFabCornerSize(float radius) {
        if (radius != getTopEdgeTreatment().getFabCornerRadius()) {
            getTopEdgeTreatment().setFabCornerSize(radius);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    private void maybeAnimateModeChange(int targetMode) {
        if (this.fabAlignmentMode != targetMode && ViewCompat.isLaidOut(this)) {
            Animator animator = this.modeAnimator;
            if (animator != null) {
                animator.cancel();
            }
            List<Animator> animators = new ArrayList<>();
            if (this.fabAnimationMode == 1) {
                createFabTranslationXAnimation(targetMode, animators);
            } else {
                createFabDefaultXAnimation(targetMode, animators);
            }
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animators);
            this.modeAnimator = set;
            set.addListener(new AnimatorListenerAdapter() {
                /* class com.google.android.material.bottomappbar.BottomAppBar.C05744 */

                public void onAnimationStart(Animator animation) {
                    BottomAppBar.this.dispatchAnimationStart();
                }

                public void onAnimationEnd(Animator animation) {
                    BottomAppBar.this.dispatchAnimationEnd();
                    BottomAppBar.this.modeAnimator = null;
                }
            });
            this.modeAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private FloatingActionButton findDependentFab() {
        View view = findDependentView();
        if (view instanceof FloatingActionButton) {
            return (FloatingActionButton) view;
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x001e  */
    private View findDependentView() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View v : ((CoordinatorLayout) getParent()).getDependents(this)) {
            if ((v instanceof FloatingActionButton) || (v instanceof ExtendedFloatingActionButton)) {
                return v;
            }
            while (r2.hasNext()) {
            }
        }
        return null;
    }

    private boolean isFabVisibleOrWillBeShown() {
        FloatingActionButton fab = findDependentFab();
        return fab != null && fab.isOrWillBeShown();
    }

    /* access modifiers changed from: protected */
    public void createFabDefaultXAnimation(final int targetMode, List<Animator> list) {
        FloatingActionButton fab = findDependentFab();
        if (fab != null && !fab.isOrWillBeHidden()) {
            dispatchAnimationStart();
            fab.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                /* class com.google.android.material.bottomappbar.BottomAppBar.C05755 */

                @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener
                public void onHidden(FloatingActionButton fab) {
                    fab.setTranslationX(BottomAppBar.this.getFabTranslationX(targetMode));
                    fab.show(new FloatingActionButton.OnVisibilityChangedListener() {
                        /* class com.google.android.material.bottomappbar.BottomAppBar.C05755.C05761 */

                        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener
                        public void onShown(FloatingActionButton fab) {
                            BottomAppBar.this.dispatchAnimationEnd();
                        }
                    });
                }
            });
        }
    }

    private void createFabTranslationXAnimation(int targetMode, List<Animator> animators) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(findDependentFab(), "translationX", getFabTranslationX(targetMode));
        animator.setDuration(ANIMATION_DURATION);
        animators.add(animator);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeAnimateMenuView(int targetMode, boolean newFabAttached) {
        if (!ViewCompat.isLaidOut(this)) {
            this.menuAnimatingWithFabAlignmentMode = false;
            replaceMenu(this.pendingMenuResId);
            return;
        }
        Animator animator = this.menuAnimator;
        if (animator != null) {
            animator.cancel();
        }
        List<Animator> animators = new ArrayList<>();
        if (!isFabVisibleOrWillBeShown()) {
            targetMode = 0;
            newFabAttached = false;
        }
        createMenuViewTranslationAnimation(targetMode, newFabAttached, animators);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animators);
        this.menuAnimator = set;
        set.addListener(new AnimatorListenerAdapter() {
            /* class com.google.android.material.bottomappbar.BottomAppBar.C05776 */

            public void onAnimationStart(Animator animation) {
                BottomAppBar.this.dispatchAnimationStart();
            }

            public void onAnimationEnd(Animator animation) {
                BottomAppBar.this.dispatchAnimationEnd();
                BottomAppBar.this.menuAnimatingWithFabAlignmentMode = false;
                BottomAppBar.this.menuAnimator = null;
            }
        });
        this.menuAnimator.start();
    }

    private void createMenuViewTranslationAnimation(final int targetMode, final boolean targetAttached, List<Animator> animators) {
        final ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            Animator fadeIn = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
            if (Math.abs(actionMenuView.getTranslationX() - ((float) getActionMenuViewTranslationX(actionMenuView, targetMode, targetAttached))) > 1.0f) {
                Animator fadeOut = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
                fadeOut.addListener(new AnimatorListenerAdapter() {
                    /* class com.google.android.material.bottomappbar.BottomAppBar.C05787 */
                    public boolean cancelled;

                    public void onAnimationCancel(Animator animation) {
                        this.cancelled = true;
                    }

                    public void onAnimationEnd(Animator animation) {
                        if (!this.cancelled) {
                            boolean replaced = BottomAppBar.this.pendingMenuResId != 0;
                            BottomAppBar bottomAppBar = BottomAppBar.this;
                            bottomAppBar.replaceMenu(bottomAppBar.pendingMenuResId);
                            BottomAppBar.this.translateActionMenuView(actionMenuView, targetMode, targetAttached, replaced);
                        }
                    }
                });
                AnimatorSet set = new AnimatorSet();
                set.setDuration(150L);
                set.playSequentially(fadeOut, fadeIn);
                animators.add(set);
            } else if (actionMenuView.getAlpha() < 1.0f) {
                animators.add(fadeIn);
            }
        }
    }

    private float getFabTranslationY() {
        return -getTopEdgeTreatment().getCradleVerticalOffset();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private float getFabTranslationX(int fabAlignmentMode2) {
        boolean isRtl = ViewUtils.isLayoutRtl(this);
        int i = 1;
        if (fabAlignmentMode2 != 1) {
            return 0.0f;
        }
        int measuredWidth = (getMeasuredWidth() / 2) - (this.fabOffsetEndMode + (isRtl ? this.leftInset : this.rightInset));
        if (isRtl) {
            i = -1;
        }
        return (float) (measuredWidth * i);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private float getFabTranslationX() {
        return getFabTranslationX(this.fabAlignmentMode);
    }

    private ActionMenuView getActionMenuView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof ActionMenuView) {
                return (ActionMenuView) view;
            }
        }
        return null;
    }

    private void translateActionMenuView(ActionMenuView actionMenuView, int fabAlignmentMode2, boolean fabAttached2) {
        translateActionMenuView(actionMenuView, fabAlignmentMode2, fabAttached2, false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void translateActionMenuView(final ActionMenuView actionMenuView, final int fabAlignmentMode2, final boolean fabAttached2, boolean shouldWaitForMenuReplacement) {
        Runnable runnable = new Runnable() {
            /* class com.google.android.material.bottomappbar.BottomAppBar.RunnableC05798 */

            public void run() {
                ActionMenuView actionMenuView = actionMenuView;
                actionMenuView.setTranslationX((float) BottomAppBar.this.getActionMenuViewTranslationX(actionMenuView, fabAlignmentMode2, fabAttached2));
            }
        };
        if (shouldWaitForMenuReplacement) {
            actionMenuView.post(runnable);
        } else {
            runnable.run();
        }
    }

    /* access modifiers changed from: protected */
    public int getActionMenuViewTranslationX(ActionMenuView actionMenuView, int fabAlignmentMode2, boolean fabAttached2) {
        int i;
        if (fabAlignmentMode2 != 1 || !fabAttached2) {
            return 0;
        }
        boolean isRtl = ViewUtils.isLayoutRtl(this);
        int toolbarLeftContentEnd = isRtl ? getMeasuredWidth() : 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View view = getChildAt(i2);
            if ((view.getLayoutParams() instanceof Toolbar.LayoutParams) && (((Toolbar.LayoutParams) view.getLayoutParams()).gravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 8388611) {
                if (isRtl) {
                    i = Math.min(toolbarLeftContentEnd, view.getLeft());
                } else {
                    i = Math.max(toolbarLeftContentEnd, view.getRight());
                }
                toolbarLeftContentEnd = i;
            }
        }
        return toolbarLeftContentEnd - ((isRtl ? actionMenuView.getRight() : actionMenuView.getLeft()) + (isRtl ? this.rightInset : -this.leftInset));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void cancelAnimations() {
        Animator animator = this.menuAnimator;
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = this.modeAnimator;
        if (animator2 != null) {
            animator2.cancel();
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.Toolbar
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            cancelAnimations();
            setCutoutState();
        }
        setActionMenuViewPosition();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private BottomAppBarTopEdgeTreatment getTopEdgeTreatment() {
        return (BottomAppBarTopEdgeTreatment) this.materialShapeDrawable.getShapeAppearanceModel().getTopEdge();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCutoutState() {
        getTopEdgeTreatment().setHorizontalOffset(getFabTranslationX());
        View fab = findDependentView();
        this.materialShapeDrawable.setInterpolation((!this.fabAttached || !isFabVisibleOrWillBeShown()) ? 0.0f : 1.0f);
        if (fab != null) {
            fab.setTranslationY(getFabTranslationY());
            fab.setTranslationX(getFabTranslationX());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActionMenuViewPosition() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null && this.menuAnimator == null) {
            actionMenuView.setAlpha(1.0f);
            if (!isFabVisibleOrWillBeShown()) {
                translateActionMenuView(actionMenuView, 0, false);
            } else {
                translateActionMenuView(actionMenuView, this.fabAlignmentMode, this.fabAttached);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFabAnimationListeners(FloatingActionButton fab) {
        fab.addOnHideAnimationListener(this.fabAnimationListener);
        fab.addOnShowAnimationListener(new AnimatorListenerAdapter() {
            /* class com.google.android.material.bottomappbar.BottomAppBar.C05809 */

            public void onAnimationStart(Animator animation) {
                BottomAppBar.this.fabAnimationListener.onAnimationStart(animation);
                FloatingActionButton fab = BottomAppBar.this.findDependentFab();
                if (fab != null) {
                    fab.setTranslationX(BottomAppBar.this.getFabTranslationX());
                }
            }
        });
        fab.addTransformationCallback(this.fabTransformationCallback);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getBottomInset() {
        return this.bottomInset;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getRightInset() {
        return this.rightInset;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getLeftInset() {
        return this.leftInset;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence title) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence subtitle) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public Behavior getBehavior() {
        if (this.behavior == null) {
            this.behavior = new Behavior();
        }
        return this.behavior;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.materialShapeDrawable);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        private final Rect fabContentRect = new Rect();
        private final View.OnLayoutChangeListener fabLayoutListener = new View.OnLayoutChangeListener() {
            /* class com.google.android.material.bottomappbar.BottomAppBar.Behavior.View$OnLayoutChangeListenerC05811 */

            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                BottomAppBar child = (BottomAppBar) Behavior.this.viewRef.get();
                if (child == null || !(v instanceof FloatingActionButton)) {
                    v.removeOnLayoutChangeListener(this);
                    return;
                }
                FloatingActionButton fab = (FloatingActionButton) v;
                fab.getMeasuredContentRect(Behavior.this.fabContentRect);
                int height = Behavior.this.fabContentRect.height();
                child.setFabDiameter(height);
                child.setFabCornerSize(fab.getShapeAppearanceModel().getTopLeftCornerSize().getCornerSize(new RectF(Behavior.this.fabContentRect)));
                CoordinatorLayout.LayoutParams fabLayoutParams = (CoordinatorLayout.LayoutParams) v.getLayoutParams();
                if (Behavior.this.originalBottomMargin == 0) {
                    fabLayoutParams.bottomMargin = child.getBottomInset() + (child.getResources().getDimensionPixelOffset(C0552R.dimen.mtrl_bottomappbar_fab_bottom_margin) - ((fab.getMeasuredHeight() - height) / 2));
                    fabLayoutParams.leftMargin = child.getLeftInset();
                    fabLayoutParams.rightMargin = child.getRightInset();
                    if (ViewUtils.isLayoutRtl(fab)) {
                        fabLayoutParams.leftMargin += child.fabOffsetEndMode;
                    } else {
                        fabLayoutParams.rightMargin += child.fabOffsetEndMode;
                    }
                }
            }
        };
        private int originalBottomMargin;
        private WeakReference<BottomAppBar> viewRef;

        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public boolean onLayoutChild(CoordinatorLayout parent, BottomAppBar child, int layoutDirection) {
            this.viewRef = new WeakReference<>(child);
            View dependentView = child.findDependentView();
            if (dependentView != null && !ViewCompat.isLaidOut(dependentView)) {
                CoordinatorLayout.LayoutParams fabLayoutParams = (CoordinatorLayout.LayoutParams) dependentView.getLayoutParams();
                fabLayoutParams.anchorGravity = 49;
                this.originalBottomMargin = fabLayoutParams.bottomMargin;
                if (dependentView instanceof FloatingActionButton) {
                    FloatingActionButton fab = (FloatingActionButton) dependentView;
                    fab.addOnLayoutChangeListener(this.fabLayoutListener);
                    child.addFabAnimationListeners(fab);
                }
                child.setCutoutState();
            }
            parent.onLayoutChild(child, layoutDirection);
            return super.onLayoutChild(parent, (View) child, layoutDirection);
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, BottomAppBar child, View directTargetChild, View target, int axes, int type) {
            return child.getHideOnScroll() && super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.Toolbar
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.fabAlignmentMode = this.fabAlignmentMode;
        savedState.fabAttached = this.fabAttached;
        return savedState;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.Toolbar
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.fabAlignmentMode = savedState.fabAlignmentMode;
        this.fabAttached = savedState.fabAttached;
    }

    /* access modifiers changed from: package-private */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            /* class com.google.android.material.bottomappbar.BottomAppBar.SavedState.C05821 */

            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in, null);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int fabAlignmentMode;
        boolean fabAttached;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel in, ClassLoader loader) {
            super(in, loader);
            this.fabAlignmentMode = in.readInt();
            this.fabAttached = in.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.fabAlignmentMode);
            out.writeInt(this.fabAttached ? 1 : 0);
        }
    }
}
