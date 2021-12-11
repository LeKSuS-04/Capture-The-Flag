package androidx.transition;

import android.graphics.Matrix;
import android.view.View;

class ViewUtilsApi29 extends ViewUtilsApi23 {
    ViewUtilsApi29() {
    }

    @Override // androidx.transition.ViewUtilsApi19, androidx.transition.ViewUtilsBase
    public void setTransitionAlpha(View view, float alpha) {
        view.setTransitionAlpha(alpha);
    }

    @Override // androidx.transition.ViewUtilsApi19, androidx.transition.ViewUtilsBase
    public float getTransitionAlpha(View view) {
        return view.getTransitionAlpha();
    }

    @Override // androidx.transition.ViewUtilsBase, androidx.transition.ViewUtilsApi23
    public void setTransitionVisibility(View view, int visibility) {
        view.setTransitionVisibility(visibility);
    }

    @Override // androidx.transition.ViewUtilsBase, androidx.transition.ViewUtilsApi22
    public void setLeftTopRightBottom(View v, int left, int top, int right, int bottom) {
        v.setLeftTopRightBottom(left, top, right, bottom);
    }

    @Override // androidx.transition.ViewUtilsBase, androidx.transition.ViewUtilsApi21
    public void transformMatrixToGlobal(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // androidx.transition.ViewUtilsBase, androidx.transition.ViewUtilsApi21
    public void transformMatrixToLocal(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }

    @Override // androidx.transition.ViewUtilsBase, androidx.transition.ViewUtilsApi21
    public void setAnimationMatrix(View view, Matrix matrix) {
        view.setAnimationMatrix(matrix);
    }
}
