.class final Lcom/google/android/material/progressindicator/CircularDrawingDelegate;
.super Lcom/google/android/material/progressindicator/DrawingDelegate;
.source "CircularDrawingDelegate.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/google/android/material/progressindicator/DrawingDelegate<",
        "Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;",
        ">;"
    }
.end annotation


# instance fields
.field private adjustedRadius:F

.field private arcDirectionFactor:I

.field private displayedCornerRadius:F

.field private displayedTrackThickness:F


# direct methods
.method public constructor <init>(Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;)V
    .locals 1

    invoke-direct {p0, p1}, Lcom/google/android/material/progressindicator/DrawingDelegate;-><init>(Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;)V

    const/4 v0, 0x1

    iput v0, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->arcDirectionFactor:I

    return-void
.end method

.method private drawRoundedEnd(Landroid/graphics/Canvas;Landroid/graphics/Paint;FFFZLandroid/graphics/RectF;)V
    .locals 13

    move-object v0, p0

    move-object v7, p1

    if-eqz p6, :cond_0

    const/high16 v1, -0x40800000    # -1.0f

    goto :goto_0

    :cond_0
    const/high16 v1, 0x3f800000    # 1.0f

    :goto_0
    move v8, v1

    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move/from16 v9, p5

    invoke-virtual {p1, v9}, Landroid/graphics/Canvas;->rotate(F)V

    iget v1, v0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->adjustedRadius:F

    const/high16 v10, 0x40000000    # 2.0f

    div-float v2, p3, v10

    sub-float/2addr v1, v2

    add-float v2, v1, p4

    mul-float v1, v8, p4

    iget v3, v0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->arcDirectionFactor:I

    int-to-float v3, v3

    mul-float v1, v1, v3

    const/4 v11, 0x0

    invoke-static {v11, v1}, Ljava/lang/Math;->min(FF)F

    move-result v3

    iget v1, v0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->adjustedRadius:F

    div-float v4, p3, v10

    add-float/2addr v1, v4

    sub-float v4, v1, p4

    mul-float v1, v8, p4

    iget v5, v0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->arcDirectionFactor:I

    int-to-float v5, v5

    mul-float v1, v1, v5

    invoke-static {v11, v1}, Ljava/lang/Math;->max(FF)F

    move-result v5

    move-object v1, p1

    move-object v6, p2

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    iget v1, v0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->adjustedRadius:F

    div-float v2, p3, v10

    sub-float/2addr v1, v2

    add-float v1, v1, p4

    invoke-virtual {p1, v1, v11}, Landroid/graphics/Canvas;->translate(FF)V

    const/high16 v3, 0x43340000    # 180.0f

    neg-float v1, v8

    const/high16 v12, 0x42b40000    # 90.0f

    mul-float v1, v1, v12

    iget v2, v0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->arcDirectionFactor:I

    int-to-float v2, v2

    mul-float v4, v1, v2

    const/4 v5, 0x1

    move-object v1, p1

    move-object/from16 v2, p7

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    mul-float v1, p4, v10

    sub-float v1, p3, v1

    invoke-virtual {p1, v1, v11}, Landroid/graphics/Canvas;->translate(FF)V

    const/4 v3, 0x0

    mul-float v12, v12, v8

    iget v1, v0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->arcDirectionFactor:I

    int-to-float v1, v1

    mul-float v4, v12, v1

    move-object v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    return-void
.end method

.method private getSize()I
    .locals 2

    iget-object v0, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v0, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v0, v0, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->indicatorSize:I

    iget-object v1, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v1, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v1, v1, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->indicatorInset:I

    mul-int/lit8 v1, v1, 0x2

    add-int/2addr v0, v1

    return v0
.end method


# virtual methods
.method public adjustCanvas(Landroid/graphics/Canvas;F)V
    .locals 6

    iget-object v0, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v0, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v0, v0, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->indicatorSize:I

    int-to-float v0, v0

    const/high16 v1, 0x40000000    # 2.0f

    div-float/2addr v0, v1

    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v2, v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->indicatorInset:I

    int-to-float v2, v2

    add-float/2addr v0, v2

    invoke-virtual {p1, v0, v0}, Landroid/graphics/Canvas;->translate(FF)V

    const/high16 v2, -0x3d4c0000    # -90.0f

    invoke-virtual {p1, v2}, Landroid/graphics/Canvas;->rotate(F)V

    neg-float v2, v0

    neg-float v3, v0

    invoke-virtual {p1, v2, v3, v0, v0}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v2, v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->indicatorDirection:I

    const/4 v3, 0x1

    if-nez v2, :cond_0

    const/4 v2, 0x1

    goto :goto_0

    :cond_0
    const/4 v2, -0x1

    :goto_0
    iput v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->arcDirectionFactor:I

    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v2, v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->trackThickness:I

    int-to-float v2, v2

    mul-float v2, v2, p2

    iput v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->displayedTrackThickness:F

    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v2, v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->trackCornerRadius:I

    int-to-float v2, v2

    mul-float v2, v2, p2

    iput v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->displayedCornerRadius:F

    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v2, v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->indicatorSize:I

    iget-object v4, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v4, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v4, v4, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->trackThickness:I

    sub-int/2addr v2, v4

    int-to-float v2, v2

    div-float/2addr v2, v1

    iput v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->adjustedRadius:F

    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->drawable:Lcom/google/android/material/progressindicator/DrawableWithAnimatedVisibilityChange;

    invoke-virtual {v2}, Lcom/google/android/material/progressindicator/DrawableWithAnimatedVisibilityChange;->isShowing()Z

    move-result v2

    const/high16 v4, 0x3f800000    # 1.0f

    const/4 v5, 0x2

    if-eqz v2, :cond_1

    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v2, v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->showAnimationBehavior:I

    if-eq v2, v5, :cond_2

    :cond_1
    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->drawable:Lcom/google/android/material/progressindicator/DrawableWithAnimatedVisibilityChange;

    invoke-virtual {v2}, Lcom/google/android/material/progressindicator/DrawableWithAnimatedVisibilityChange;->isHiding()Z

    move-result v2

    if-eqz v2, :cond_3

    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v2, v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->hideAnimationBehavior:I

    if-ne v2, v3, :cond_3

    :cond_2
    iget v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->adjustedRadius:F

    sub-float/2addr v4, p2

    iget-object v3, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v3, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v3, v3, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->trackThickness:I

    int-to-float v3, v3

    mul-float v4, v4, v3

    div-float/2addr v4, v1

    add-float/2addr v2, v4

    iput v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->adjustedRadius:F

    goto :goto_1

    :cond_3
    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->drawable:Lcom/google/android/material/progressindicator/DrawableWithAnimatedVisibilityChange;

    invoke-virtual {v2}, Lcom/google/android/material/progressindicator/DrawableWithAnimatedVisibilityChange;->isShowing()Z

    move-result v2

    if-eqz v2, :cond_4

    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v2, v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->showAnimationBehavior:I

    if-eq v2, v3, :cond_5

    :cond_4
    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->drawable:Lcom/google/android/material/progressindicator/DrawableWithAnimatedVisibilityChange;

    invoke-virtual {v2}, Lcom/google/android/material/progressindicator/DrawableWithAnimatedVisibilityChange;->isHiding()Z

    move-result v2

    if-eqz v2, :cond_6

    iget-object v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v2, v2, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->hideAnimationBehavior:I

    if-ne v2, v5, :cond_6

    :cond_5
    iget v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->adjustedRadius:F

    sub-float/2addr v4, p2

    iget-object v3, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v3, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v3, v3, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->trackThickness:I

    int-to-float v3, v3

    mul-float v4, v4, v3

    div-float/2addr v4, v1

    sub-float/2addr v2, v4

    iput v2, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->adjustedRadius:F

    :cond_6
    :goto_1
    return-void
.end method

.method fillIndicator(Landroid/graphics/Canvas;Landroid/graphics/Paint;FFI)V
    .locals 24

    move-object/from16 v15, p0

    move-object/from16 v14, p2

    cmpl-float v0, p3, p4

    if-nez v0, :cond_0

    return-void

    :cond_0
    sget-object v0, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v14, v0}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    sget-object v0, Landroid/graphics/Paint$Cap;->BUTT:Landroid/graphics/Paint$Cap;

    invoke-virtual {v14, v0}, Landroid/graphics/Paint;->setStrokeCap(Landroid/graphics/Paint$Cap;)V

    const/4 v0, 0x1

    invoke-virtual {v14, v0}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    move/from16 v13, p5

    invoke-virtual {v14, v13}, Landroid/graphics/Paint;->setColor(I)V

    iget v0, v15, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->displayedTrackThickness:F

    invoke-virtual {v14, v0}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    const/high16 v0, 0x43b40000    # 360.0f

    mul-float v1, p3, v0

    iget v2, v15, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->arcDirectionFactor:I

    int-to-float v3, v2

    mul-float v22, v1, v3

    cmpl-float v1, p4, p3

    if-ltz v1, :cond_1

    sub-float v1, p4, p3

    goto :goto_0

    :cond_1
    const/high16 v1, 0x3f800000    # 1.0f

    add-float v1, p4, v1

    sub-float v1, v1, p3

    :goto_0
    mul-float v1, v1, v0

    int-to-float v2, v2

    mul-float v1, v1, v2

    move/from16 v23, v1

    new-instance v1, Landroid/graphics/RectF;

    iget v2, v15, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->adjustedRadius:F

    neg-float v3, v2

    neg-float v4, v2

    invoke-direct {v1, v3, v4, v2, v2}, Landroid/graphics/RectF;-><init>(FFFF)V

    move-object/from16 v17, v1

    const/16 v20, 0x0

    move-object/from16 v16, p1

    move/from16 v18, v22

    move/from16 v19, v23

    move-object/from16 v21, p2

    invoke-virtual/range {v16 .. v21}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    iget v1, v15, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->displayedCornerRadius:F

    const/4 v2, 0x0

    cmpl-float v1, v1, v2

    if-lez v1, :cond_2

    invoke-static/range {v23 .. v23}, Ljava/lang/Math;->abs(F)F

    move-result v1

    cmpg-float v0, v1, v0

    if-gez v0, :cond_2

    sget-object v0, Landroid/graphics/Paint$Style;->FILL:Landroid/graphics/Paint$Style;

    invoke-virtual {v14, v0}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    new-instance v7, Landroid/graphics/RectF;

    iget v0, v15, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->displayedCornerRadius:F

    neg-float v1, v0

    neg-float v2, v0

    invoke-direct {v7, v1, v2, v0, v0}, Landroid/graphics/RectF;-><init>(FFFF)V

    iget v3, v15, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->displayedTrackThickness:F

    iget v4, v15, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->displayedCornerRadius:F

    const/4 v6, 0x1

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move/from16 v5, v22

    invoke-direct/range {v0 .. v7}, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->drawRoundedEnd(Landroid/graphics/Canvas;Landroid/graphics/Paint;FFFZLandroid/graphics/RectF;)V

    iget v11, v15, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->displayedTrackThickness:F

    iget v12, v15, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->displayedCornerRadius:F

    add-float v0, v22, v23

    const/4 v1, 0x0

    move-object/from16 v8, p0

    move-object/from16 v9, p1

    move-object/from16 v10, p2

    move v13, v0

    move v14, v1

    move-object v15, v7

    invoke-direct/range {v8 .. v15}, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->drawRoundedEnd(Landroid/graphics/Canvas;Landroid/graphics/Paint;FFFZLandroid/graphics/RectF;)V

    :cond_2
    return-void
.end method

.method fillTrack(Landroid/graphics/Canvas;Landroid/graphics/Paint;)V
    .locals 8

    iget-object v0, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->spec:Lcom/google/android/material/progressindicator/BaseProgressIndicatorSpec;

    check-cast v0, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;

    iget v0, v0, Lcom/google/android/material/progressindicator/CircularProgressIndicatorSpec;->trackColor:I

    iget-object v1, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->drawable:Lcom/google/android/material/progressindicator/DrawableWithAnimatedVisibilityChange;

    invoke-virtual {v1}, Lcom/google/android/material/progressindicator/DrawableWithAnimatedVisibilityChange;->getAlpha()I

    move-result v1

    invoke-static {v0, v1}, Lcom/google/android/material/color/MaterialColors;->compositeARGBWithAlpha(II)I

    move-result v0

    sget-object v1, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {p2, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    sget-object v1, Landroid/graphics/Paint$Cap;->BUTT:Landroid/graphics/Paint$Cap;

    invoke-virtual {p2, v1}, Landroid/graphics/Paint;->setStrokeCap(Landroid/graphics/Paint$Cap;)V

    const/4 v1, 0x1

    invoke-virtual {p2, v1}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    invoke-virtual {p2, v0}, Landroid/graphics/Paint;->setColor(I)V

    iget v1, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->displayedTrackThickness:F

    invoke-virtual {p2, v1}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    new-instance v3, Landroid/graphics/RectF;

    iget v1, p0, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->adjustedRadius:F

    neg-float v2, v1

    neg-float v4, v1

    invoke-direct {v3, v2, v4, v1, v1}, Landroid/graphics/RectF;-><init>(FFFF)V

    const/4 v4, 0x0

    const/high16 v5, 0x43b40000    # 360.0f

    const/4 v6, 0x0

    move-object v2, p1

    move-object v7, p2

    invoke-virtual/range {v2 .. v7}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    return-void
.end method

.method public getPreferredHeight()I
    .locals 1

    invoke-direct {p0}, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->getSize()I

    move-result v0

    return v0
.end method

.method public getPreferredWidth()I
    .locals 1

    invoke-direct {p0}, Lcom/google/android/material/progressindicator/CircularDrawingDelegate;->getSize()I

    move-result v0

    return v0
.end method
