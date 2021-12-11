package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.List;

public class ShapePath {
    protected static final float ANGLE_LEFT = 180.0f;
    private static final float ANGLE_UP = 270.0f;
    private boolean containsIncompatibleShadowOp;
    @Deprecated
    public float currentShadowAngle;
    @Deprecated
    public float endShadowAngle;
    @Deprecated
    public float endX;
    @Deprecated
    public float endY;
    private final List<PathOperation> operations = new ArrayList();
    private final List<ShadowCompatOperation> shadowCompatOperations = new ArrayList();
    @Deprecated
    public float startX;
    @Deprecated
    public float startY;

    public static abstract class PathOperation {
        protected final Matrix matrix = new Matrix();

        public abstract void applyToPath(Matrix matrix2, Path path);
    }

    public ShapePath() {
        reset(0.0f, 0.0f);
    }

    public ShapePath(float startX2, float startY2) {
        reset(startX2, startY2);
    }

    public void reset(float startX2, float startY2) {
        reset(startX2, startY2, ANGLE_UP, 0.0f);
    }

    public void reset(float startX2, float startY2, float shadowStartAngle, float shadowSweepAngle) {
        setStartX(startX2);
        setStartY(startY2);
        setEndX(startX2);
        setEndY(startY2);
        setCurrentShadowAngle(shadowStartAngle);
        setEndShadowAngle((shadowStartAngle + shadowSweepAngle) % 360.0f);
        this.operations.clear();
        this.shadowCompatOperations.clear();
        this.containsIncompatibleShadowOp = false;
    }

    public void lineTo(float x, float y) {
        PathLineOperation operation = new PathLineOperation();
        operation.f110x = x;
        operation.f111y = y;
        this.operations.add(operation);
        LineShadowOperation shadowOperation = new LineShadowOperation(operation, getEndX(), getEndY());
        addShadowCompatOperation(shadowOperation, shadowOperation.getAngle() + ANGLE_UP, shadowOperation.getAngle() + ANGLE_UP);
        setEndX(x);
        setEndY(y);
    }

    public void quadToPoint(float controlX, float controlY, float toX, float toY) {
        PathQuadOperation operation = new PathQuadOperation();
        operation.setControlX(controlX);
        operation.setControlY(controlY);
        operation.setEndX(toX);
        operation.setEndY(toY);
        this.operations.add(operation);
        this.containsIncompatibleShadowOp = true;
        setEndX(toX);
        setEndY(toY);
    }

    public void cubicToPoint(float controlX1, float controlY1, float controlX2, float controlY2, float toX, float toY) {
        this.operations.add(new PathCubicOperation(controlX1, controlY1, controlX2, controlY2, toX, toY));
        this.containsIncompatibleShadowOp = true;
        setEndX(toX);
        setEndY(toY);
    }

    public void addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle) {
        PathArcOperation operation = new PathArcOperation(left, top, right, bottom);
        operation.setStartAngle(startAngle);
        operation.setSweepAngle(sweepAngle);
        this.operations.add(operation);
        ArcShadowOperation arcShadowOperation = new ArcShadowOperation(operation);
        float endAngle = startAngle + sweepAngle;
        boolean drawShadowInsideBounds = sweepAngle < 0.0f;
        addShadowCompatOperation(arcShadowOperation, drawShadowInsideBounds ? (startAngle + ANGLE_LEFT) % 360.0f : startAngle, drawShadowInsideBounds ? (ANGLE_LEFT + endAngle) % 360.0f : endAngle);
        setEndX(((left + right) * 0.5f) + (((right - left) / 2.0f) * ((float) Math.cos(Math.toRadians((double) (startAngle + sweepAngle))))));
        setEndY(((top + bottom) * 0.5f) + (((bottom - top) / 2.0f) * ((float) Math.sin(Math.toRadians((double) (startAngle + sweepAngle))))));
    }

    public void applyToPath(Matrix transform, Path path) {
        int size = this.operations.size();
        for (int i = 0; i < size; i++) {
            this.operations.get(i).applyToPath(transform, path);
        }
    }

    /* access modifiers changed from: package-private */
    public ShadowCompatOperation createShadowCompatOperation(Matrix transform) {
        addConnectingShadowIfNecessary(getEndShadowAngle());
        final Matrix transformCopy = new Matrix(transform);
        final List<ShadowCompatOperation> operations2 = new ArrayList<>(this.shadowCompatOperations);
        return new ShadowCompatOperation() {
            /* class com.google.android.material.shape.ShapePath.C07021 */

            @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
            public void draw(Matrix matrix, ShadowRenderer shadowRenderer, int shadowElevation, Canvas canvas) {
                for (ShadowCompatOperation op : operations2) {
                    op.draw(transformCopy, shadowRenderer, shadowElevation, canvas);
                }
            }
        };
    }

    private void addShadowCompatOperation(ShadowCompatOperation shadowOperation, float startShadowAngle, float endShadowAngle2) {
        addConnectingShadowIfNecessary(startShadowAngle);
        this.shadowCompatOperations.add(shadowOperation);
        setCurrentShadowAngle(endShadowAngle2);
    }

    /* access modifiers changed from: package-private */
    public boolean containsIncompatibleShadowOp() {
        return this.containsIncompatibleShadowOp;
    }

    private void addConnectingShadowIfNecessary(float nextShadowAngle) {
        if (getCurrentShadowAngle() != nextShadowAngle) {
            float shadowSweep = ((nextShadowAngle - getCurrentShadowAngle()) + 360.0f) % 360.0f;
            if (shadowSweep <= ANGLE_LEFT) {
                PathArcOperation pathArcOperation = new PathArcOperation(getEndX(), getEndY(), getEndX(), getEndY());
                pathArcOperation.setStartAngle(getCurrentShadowAngle());
                pathArcOperation.setSweepAngle(shadowSweep);
                this.shadowCompatOperations.add(new ArcShadowOperation(pathArcOperation));
                setCurrentShadowAngle(nextShadowAngle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public float getStartX() {
        return this.startX;
    }

    /* access modifiers changed from: package-private */
    public float getStartY() {
        return this.startY;
    }

    /* access modifiers changed from: package-private */
    public float getEndX() {
        return this.endX;
    }

    /* access modifiers changed from: package-private */
    public float getEndY() {
        return this.endY;
    }

    private float getCurrentShadowAngle() {
        return this.currentShadowAngle;
    }

    private float getEndShadowAngle() {
        return this.endShadowAngle;
    }

    private void setStartX(float startX2) {
        this.startX = startX2;
    }

    private void setStartY(float startY2) {
        this.startY = startY2;
    }

    private void setEndX(float endX2) {
        this.endX = endX2;
    }

    private void setEndY(float endY2) {
        this.endY = endY2;
    }

    private void setCurrentShadowAngle(float currentShadowAngle2) {
        this.currentShadowAngle = currentShadowAngle2;
    }

    private void setEndShadowAngle(float endShadowAngle2) {
        this.endShadowAngle = endShadowAngle2;
    }

    /* access modifiers changed from: package-private */
    public static abstract class ShadowCompatOperation {
        static final Matrix IDENTITY_MATRIX = new Matrix();

        public abstract void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i, Canvas canvas);

        ShadowCompatOperation() {
        }

        public final void draw(ShadowRenderer shadowRenderer, int shadowElevation, Canvas canvas) {
            draw(IDENTITY_MATRIX, shadowRenderer, shadowElevation, canvas);
        }
    }

    /* access modifiers changed from: package-private */
    public static class LineShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation;
        private final float startX;
        private final float startY;

        public LineShadowOperation(PathLineOperation operation2, float startX2, float startY2) {
            this.operation = operation2;
            this.startX = startX2;
            this.startY = startY2;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix transform, ShadowRenderer shadowRenderer, int shadowElevation, Canvas canvas) {
            RectF rect = new RectF(0.0f, 0.0f, (float) Math.hypot((double) (this.operation.f111y - this.startY), (double) (this.operation.f110x - this.startX)), 0.0f);
            Matrix edgeTransform = new Matrix(transform);
            edgeTransform.preTranslate(this.startX, this.startY);
            edgeTransform.preRotate(getAngle());
            shadowRenderer.drawEdgeShadow(canvas, edgeTransform, rect, shadowElevation);
        }

        /* access modifiers changed from: package-private */
        public float getAngle() {
            return (float) Math.toDegrees(Math.atan((double) ((this.operation.f111y - this.startY) / (this.operation.f110x - this.startX))));
        }
    }

    /* access modifiers changed from: package-private */
    public static class ArcShadowOperation extends ShadowCompatOperation {
        private final PathArcOperation operation;

        public ArcShadowOperation(PathArcOperation operation2) {
            this.operation = operation2;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix transform, ShadowRenderer shadowRenderer, int shadowElevation, Canvas canvas) {
            shadowRenderer.drawCornerShadow(canvas, transform, new RectF(this.operation.getLeft(), this.operation.getTop(), this.operation.getRight(), this.operation.getBottom()), shadowElevation, this.operation.getStartAngle(), this.operation.getSweepAngle());
        }
    }

    public static class PathLineOperation extends PathOperation {

        /* renamed from: x */
        private float f110x;

        /* renamed from: y */
        private float f111y;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix transform, Path path) {
            Matrix inverse = this.matrix;
            transform.invert(inverse);
            path.transform(inverse);
            path.lineTo(this.f110x, this.f111y);
            path.transform(transform);
        }
    }

    public static class PathQuadOperation extends PathOperation {
        @Deprecated
        public float controlX;
        @Deprecated
        public float controlY;
        @Deprecated
        public float endX;
        @Deprecated
        public float endY;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix transform, Path path) {
            Matrix inverse = this.matrix;
            transform.invert(inverse);
            path.transform(inverse);
            path.quadTo(getControlX(), getControlY(), getEndX(), getEndY());
            path.transform(transform);
        }

        private float getEndX() {
            return this.endX;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEndX(float endX2) {
            this.endX = endX2;
        }

        private float getControlY() {
            return this.controlY;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setControlY(float controlY2) {
            this.controlY = controlY2;
        }

        private float getEndY() {
            return this.endY;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEndY(float endY2) {
            this.endY = endY2;
        }

        private float getControlX() {
            return this.controlX;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setControlX(float controlX2) {
            this.controlX = controlX2;
        }
    }

    public static class PathArcOperation extends PathOperation {
        private static final RectF rectF = new RectF();
        @Deprecated
        public float bottom;
        @Deprecated
        public float left;
        @Deprecated
        public float right;
        @Deprecated
        public float startAngle;
        @Deprecated
        public float sweepAngle;
        @Deprecated
        public float top;

        public PathArcOperation(float left2, float top2, float right2, float bottom2) {
            setLeft(left2);
            setTop(top2);
            setRight(right2);
            setBottom(bottom2);
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix transform, Path path) {
            Matrix inverse = this.matrix;
            transform.invert(inverse);
            path.transform(inverse);
            RectF rectF2 = rectF;
            rectF2.set(getLeft(), getTop(), getRight(), getBottom());
            path.arcTo(rectF2, getStartAngle(), getSweepAngle(), false);
            path.transform(transform);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private float getLeft() {
            return this.left;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private float getTop() {
            return this.top;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private float getRight() {
            return this.right;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private float getBottom() {
            return this.bottom;
        }

        private void setLeft(float left2) {
            this.left = left2;
        }

        private void setTop(float top2) {
            this.top = top2;
        }

        private void setRight(float right2) {
            this.right = right2;
        }

        private void setBottom(float bottom2) {
            this.bottom = bottom2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private float getStartAngle() {
            return this.startAngle;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private float getSweepAngle() {
            return this.sweepAngle;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStartAngle(float startAngle2) {
            this.startAngle = startAngle2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSweepAngle(float sweepAngle2) {
            this.sweepAngle = sweepAngle2;
        }
    }

    public static class PathCubicOperation extends PathOperation {
        private float controlX1;
        private float controlX2;
        private float controlY1;
        private float controlY2;
        private float endX;
        private float endY;

        public PathCubicOperation(float controlX12, float controlY12, float controlX22, float controlY22, float endX2, float endY2) {
            setControlX1(controlX12);
            setControlY1(controlY12);
            setControlX2(controlX22);
            setControlY2(controlY22);
            setEndX(endX2);
            setEndY(endY2);
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix transform, Path path) {
            Matrix inverse = this.matrix;
            transform.invert(inverse);
            path.transform(inverse);
            path.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX, this.endY);
            path.transform(transform);
        }

        private float getControlX1() {
            return this.controlX1;
        }

        private void setControlX1(float controlX12) {
            this.controlX1 = controlX12;
        }

        private float getControlY1() {
            return this.controlY1;
        }

        private void setControlY1(float controlY12) {
            this.controlY1 = controlY12;
        }

        private float getControlX2() {
            return this.controlX2;
        }

        private void setControlX2(float controlX22) {
            this.controlX2 = controlX22;
        }

        private float getControlY2() {
            return this.controlY1;
        }

        private void setControlY2(float controlY22) {
            this.controlY2 = controlY22;
        }

        private float getEndX() {
            return this.endX;
        }

        private void setEndX(float endX2) {
            this.endX = endX2;
        }

        private float getEndY() {
            return this.endY;
        }

        private void setEndY(float endY2) {
            this.endY = endY2;
        }
    }
}
