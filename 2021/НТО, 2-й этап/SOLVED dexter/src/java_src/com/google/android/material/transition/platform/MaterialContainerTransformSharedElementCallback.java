package com.google.android.material.transition.platform;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import com.google.android.material.C0552R;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class MaterialContainerTransformSharedElementCallback extends SharedElementCallback {
    private static WeakReference<View> capturedSharedElement;
    private boolean entering = true;
    private Rect returnEndBounds;
    private ShapeProvider shapeProvider = new ShapeableViewShapeProvider();
    private boolean sharedElementReenterTransitionEnabled = false;
    private boolean transparentWindowBackgroundEnabled = true;

    public interface ShapeProvider {
        ShapeAppearanceModel provideShape(View view);
    }

    public static class ShapeableViewShapeProvider implements ShapeProvider {
        @Override // com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.ShapeProvider
        public ShapeAppearanceModel provideShape(View sharedElement) {
            if (sharedElement instanceof Shapeable) {
                return ((Shapeable) sharedElement).getShapeAppearanceModel();
            }
            return null;
        }
    }

    public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
        capturedSharedElement = new WeakReference<>(sharedElement);
        return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
    }

    public View onCreateSnapshotView(Context context, Parcelable snapshot) {
        WeakReference<View> weakReference;
        View sharedElement;
        ShapeAppearanceModel shapeAppearanceModel;
        View snapshotView = super.onCreateSnapshotView(context, snapshot);
        if (!(snapshotView == null || (weakReference = capturedSharedElement) == null || this.shapeProvider == null || (sharedElement = weakReference.get()) == null || (shapeAppearanceModel = this.shapeProvider.provideShape(sharedElement)) == null)) {
            snapshotView.setTag(C0552R.C0555id.mtrl_motion_snapshot_view, shapeAppearanceModel);
        }
        return snapshotView;
    }

    @Override // android.app.SharedElementCallback
    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
        View sharedElement;
        Activity activity;
        if (!names.isEmpty() && !sharedElements.isEmpty() && (sharedElement = sharedElements.get(names.get(0))) != null && (activity = ContextUtils.getActivity(sharedElement.getContext())) != null) {
            Window window = activity.getWindow();
            if (this.entering) {
                setUpEnterTransform(window);
            } else {
                setUpReturnTransform(activity, window);
            }
        }
    }

    @Override // android.app.SharedElementCallback
    public void onSharedElementStart(List<String> list, List<View> sharedElements, List<View> sharedElementSnapshots) {
        if (!sharedElements.isEmpty() && !sharedElementSnapshots.isEmpty()) {
            sharedElements.get(0).setTag(C0552R.C0555id.mtrl_motion_snapshot_view, sharedElementSnapshots.get(0));
        }
        if (!this.entering && !sharedElements.isEmpty() && this.returnEndBounds != null) {
            View sharedElement = sharedElements.get(0);
            sharedElement.measure(View.MeasureSpec.makeMeasureSpec(this.returnEndBounds.width(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(this.returnEndBounds.height(), BasicMeasure.EXACTLY));
            sharedElement.layout(this.returnEndBounds.left, this.returnEndBounds.top, this.returnEndBounds.right, this.returnEndBounds.bottom);
        }
    }

    @Override // android.app.SharedElementCallback
    public void onSharedElementEnd(List<String> list, List<View> sharedElements, List<View> list2) {
        if (!sharedElements.isEmpty() && (sharedElements.get(0).getTag(C0552R.C0555id.mtrl_motion_snapshot_view) instanceof View)) {
            sharedElements.get(0).setTag(C0552R.C0555id.mtrl_motion_snapshot_view, null);
        }
        if (!this.entering && !sharedElements.isEmpty()) {
            this.returnEndBounds = TransitionUtils.getRelativeBoundsRect(sharedElements.get(0));
        }
        this.entering = false;
    }

    public ShapeProvider getShapeProvider() {
        return this.shapeProvider;
    }

    public void setShapeProvider(ShapeProvider shapeProvider2) {
        this.shapeProvider = shapeProvider2;
    }

    public boolean isTransparentWindowBackgroundEnabled() {
        return this.transparentWindowBackgroundEnabled;
    }

    public void setTransparentWindowBackgroundEnabled(boolean transparentWindowBackgroundEnabled2) {
        this.transparentWindowBackgroundEnabled = transparentWindowBackgroundEnabled2;
    }

    public boolean isSharedElementReenterTransitionEnabled() {
        return this.sharedElementReenterTransitionEnabled;
    }

    public void setSharedElementReenterTransitionEnabled(boolean sharedElementReenterTransitionEnabled2) {
        this.sharedElementReenterTransitionEnabled = sharedElementReenterTransitionEnabled2;
    }

    private void setUpEnterTransform(final Window window) {
        Transition transition = window.getSharedElementEnterTransition();
        if (transition instanceof MaterialContainerTransform) {
            MaterialContainerTransform transform = (MaterialContainerTransform) transition;
            if (!this.sharedElementReenterTransitionEnabled) {
                window.setSharedElementReenterTransition(null);
            }
            if (this.transparentWindowBackgroundEnabled) {
                updateBackgroundFadeDuration(window, transform);
                transform.addListener(new TransitionListenerAdapter() {
                    /* class com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.C08301 */

                    @Override // com.google.android.material.transition.platform.TransitionListenerAdapter
                    public void onTransitionStart(Transition transition) {
                        MaterialContainerTransformSharedElementCallback.removeWindowBackground(window);
                    }

                    @Override // com.google.android.material.transition.platform.TransitionListenerAdapter
                    public void onTransitionEnd(Transition transition) {
                        MaterialContainerTransformSharedElementCallback.restoreWindowBackground(window);
                    }
                });
            }
        }
    }

    private void setUpReturnTransform(final Activity activity, final Window window) {
        Transition transition = window.getSharedElementReturnTransition();
        if (transition instanceof MaterialContainerTransform) {
            MaterialContainerTransform transform = (MaterialContainerTransform) transition;
            transform.setHoldAtEndEnabled(true);
            transform.addListener(new TransitionListenerAdapter() {
                /* class com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.C08312 */

                @Override // com.google.android.material.transition.platform.TransitionListenerAdapter
                public void onTransitionEnd(Transition transition) {
                    View sharedElement;
                    if (!(MaterialContainerTransformSharedElementCallback.capturedSharedElement == null || (sharedElement = (View) MaterialContainerTransformSharedElementCallback.capturedSharedElement.get()) == null)) {
                        sharedElement.setAlpha(1.0f);
                        WeakReference unused = MaterialContainerTransformSharedElementCallback.capturedSharedElement = null;
                    }
                    activity.finish();
                    activity.overridePendingTransition(0, 0);
                }
            });
            if (this.transparentWindowBackgroundEnabled) {
                updateBackgroundFadeDuration(window, transform);
                transform.addListener(new TransitionListenerAdapter() {
                    /* class com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.C08323 */

                    @Override // com.google.android.material.transition.platform.TransitionListenerAdapter
                    public void onTransitionStart(Transition transition) {
                        MaterialContainerTransformSharedElementCallback.removeWindowBackground(window);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static void removeWindowBackground(Window window) {
        window.getDecorView().getBackground().mutate().setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(0, BlendModeCompat.CLEAR));
    }

    /* access modifiers changed from: private */
    public static void restoreWindowBackground(Window window) {
        window.getDecorView().getBackground().mutate().clearColorFilter();
    }

    private static void updateBackgroundFadeDuration(Window window, MaterialContainerTransform transform) {
        if (transform.getDuration() >= 0) {
            window.setTransitionBackgroundFadeDuration(transform.getDuration());
        }
    }
}
