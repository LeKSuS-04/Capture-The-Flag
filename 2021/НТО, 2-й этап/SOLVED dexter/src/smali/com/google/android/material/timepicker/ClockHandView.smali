.class Lcom/google/android/material/timepicker/ClockHandView;
.super Landroid/view/View;
.source "ClockHandView.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/google/android/material/timepicker/ClockHandView$OnActionUpListener;,
        Lcom/google/android/material/timepicker/ClockHandView$OnRotateListener;
    }
.end annotation


# static fields
.field private static final ANIMATION_DURATION:I = 0xc8


# instance fields
.field private animatingOnTouchUp:Z

.field private final centerDotRadius:F

.field private changedDuringTouch:Z

.field private circleRadius:I

.field private degRad:D

.field private downX:F

.field private downY:F

.field private isInTapRegion:Z

.field private final listeners:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/google/android/material/timepicker/ClockHandView$OnRotateListener;",
            ">;"
        }
    .end annotation
.end field

.field private onActionUpListener:Lcom/google/android/material/timepicker/ClockHandView$OnActionUpListener;

.field private originalDeg:F

.field private final paint:Landroid/graphics/Paint;

.field private rotationAnimator:Landroid/animation/ValueAnimator;

.field private scaledTouchSlop:I

.field private final selectorBox:Landroid/graphics/RectF;

.field private final selectorRadius:I

.field private final selectorStrokeWidth:I


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/google/android/material/timepicker/ClockHandView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    sget v0, Lcom/google/android/material/R$attr;->materialClockStyle:I

    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/material/timepicker/ClockHandView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 5

    invoke-direct {p0, p1, p2, p3}, Landroid/view/View;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->listeners:Ljava/util/List;

    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->paint:Landroid/graphics/Paint;

    new-instance v1, Landroid/graphics/RectF;

    invoke-direct {v1}, Landroid/graphics/RectF;-><init>()V

    iput-object v1, p0, Lcom/google/android/material/timepicker/ClockHandView;->selectorBox:Landroid/graphics/RectF;

    sget-object v1, Lcom/google/android/material/R$styleable;->ClockHandView:[I

    sget v2, Lcom/google/android/material/R$style;->Widget_MaterialComponents_TimePicker_Clock:I

    invoke-virtual {p1, p2, v1, p3, v2}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v1

    sget v2, Lcom/google/android/material/R$styleable;->ClockHandView_materialCircleRadius:I

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v2

    iput v2, p0, Lcom/google/android/material/timepicker/ClockHandView;->circleRadius:I

    sget v2, Lcom/google/android/material/R$styleable;->ClockHandView_selectorSize:I

    invoke-virtual {v1, v2, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v2

    iput v2, p0, Lcom/google/android/material/timepicker/ClockHandView;->selectorRadius:I

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockHandView;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v4, Lcom/google/android/material/R$dimen;->material_clock_hand_stroke_width:I

    invoke-virtual {v2, v4}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    iput v4, p0, Lcom/google/android/material/timepicker/ClockHandView;->selectorStrokeWidth:I

    sget v4, Lcom/google/android/material/R$dimen;->material_clock_hand_center_dot_radius:I

    invoke-virtual {v2, v4}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    int-to-float v4, v4

    iput v4, p0, Lcom/google/android/material/timepicker/ClockHandView;->centerDotRadius:F

    sget v4, Lcom/google/android/material/R$styleable;->ClockHandView_clockHandColor:I

    invoke-virtual {v1, v4, v3}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v3

    const/4 v4, 0x1

    invoke-virtual {v0, v4}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setColor(I)V

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/google/android/material/timepicker/ClockHandView;->setHandRotation(F)V

    invoke-static {p1}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewConfiguration;->getScaledTouchSlop()I

    move-result v0

    iput v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->scaledTouchSlop:I

    const/4 v0, 0x2

    invoke-static {p0, v0}, Landroidx/core/view/ViewCompat;->setImportantForAccessibility(Landroid/view/View;I)V

    invoke-virtual {v1}, Landroid/content/res/TypedArray;->recycle()V

    return-void
.end method

.method static synthetic access$000(Lcom/google/android/material/timepicker/ClockHandView;FZ)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/google/android/material/timepicker/ClockHandView;->setHandRotationInternal(FZ)V

    return-void
.end method

.method private drawSelector(Landroid/graphics/Canvas;)V
    .locals 20

    move-object/from16 v0, p0

    move-object/from16 v7, p1

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/material/timepicker/ClockHandView;->getHeight()I

    move-result v1

    div-int/lit8 v8, v1, 0x2

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/material/timepicker/ClockHandView;->getWidth()I

    move-result v1

    div-int/lit8 v9, v1, 0x2

    int-to-float v1, v9

    iget v2, v0, Lcom/google/android/material/timepicker/ClockHandView;->circleRadius:I

    int-to-float v2, v2

    iget-wide v3, v0, Lcom/google/android/material/timepicker/ClockHandView;->degRad:D

    invoke-static {v3, v4}, Ljava/lang/Math;->cos(D)D

    move-result-wide v3

    double-to-float v3, v3

    mul-float v2, v2, v3

    add-float v10, v1, v2

    int-to-float v1, v8

    iget v2, v0, Lcom/google/android/material/timepicker/ClockHandView;->circleRadius:I

    int-to-float v2, v2

    iget-wide v3, v0, Lcom/google/android/material/timepicker/ClockHandView;->degRad:D

    invoke-static {v3, v4}, Ljava/lang/Math;->sin(D)D

    move-result-wide v3

    double-to-float v3, v3

    mul-float v2, v2, v3

    add-float v11, v1, v2

    iget-object v1, v0, Lcom/google/android/material/timepicker/ClockHandView;->paint:Landroid/graphics/Paint;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    iget v1, v0, Lcom/google/android/material/timepicker/ClockHandView;->selectorRadius:I

    int-to-float v1, v1

    iget-object v2, v0, Lcom/google/android/material/timepicker/ClockHandView;->paint:Landroid/graphics/Paint;

    invoke-virtual {v7, v10, v11, v1, v2}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    iget-wide v1, v0, Lcom/google/android/material/timepicker/ClockHandView;->degRad:D

    invoke-static {v1, v2}, Ljava/lang/Math;->sin(D)D

    move-result-wide v12

    iget-wide v1, v0, Lcom/google/android/material/timepicker/ClockHandView;->degRad:D

    invoke-static {v1, v2}, Ljava/lang/Math;->cos(D)D

    move-result-wide v14

    iget v1, v0, Lcom/google/android/material/timepicker/ClockHandView;->circleRadius:I

    iget v2, v0, Lcom/google/android/material/timepicker/ClockHandView;->selectorRadius:I

    sub-int/2addr v1, v2

    int-to-float v6, v1

    float-to-double v1, v6

    mul-double v1, v1, v14

    double-to-int v1, v1

    add-int/2addr v1, v9

    int-to-float v5, v1

    float-to-double v1, v6

    mul-double v1, v1, v12

    double-to-int v1, v1

    add-int/2addr v1, v8

    int-to-float v4, v1

    iget-object v1, v0, Lcom/google/android/material/timepicker/ClockHandView;->paint:Landroid/graphics/Paint;

    iget v2, v0, Lcom/google/android/material/timepicker/ClockHandView;->selectorStrokeWidth:I

    int-to-float v2, v2

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    int-to-float v2, v9

    int-to-float v3, v8

    iget-object v1, v0, Lcom/google/android/material/timepicker/ClockHandView;->paint:Landroid/graphics/Paint;

    move-object/from16 v16, v1

    move-object/from16 v1, p1

    move/from16 v17, v4

    move v4, v5

    move/from16 v18, v5

    move/from16 v5, v17

    move/from16 v19, v6

    move-object/from16 v6, v16

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    int-to-float v1, v9

    int-to-float v2, v8

    iget v3, v0, Lcom/google/android/material/timepicker/ClockHandView;->centerDotRadius:F

    iget-object v4, v0, Lcom/google/android/material/timepicker/ClockHandView;->paint:Landroid/graphics/Paint;

    invoke-virtual {v7, v1, v2, v3, v4}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    return-void
.end method

.method private getDegreesFromXY(FF)I
    .locals 8

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockHandView;->getWidth()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockHandView;->getHeight()I

    move-result v1

    div-int/lit8 v1, v1, 0x2

    int-to-float v2, v0

    sub-float v2, p1, v2

    float-to-double v2, v2

    int-to-float v4, v1

    sub-float v4, p2, v4

    float-to-double v4, v4

    invoke-static {v4, v5, v2, v3}, Ljava/lang/Math;->atan2(DD)D

    move-result-wide v6

    invoke-static {v6, v7}, Ljava/lang/Math;->toDegrees(D)D

    move-result-wide v6

    double-to-int v6, v6

    add-int/lit8 v6, v6, 0x5a

    if-gez v6, :cond_0

    add-int/lit16 v6, v6, 0x168

    :cond_0
    return v6
.end method

.method private getValuesForAnimation(F)Landroid/util/Pair;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(F)",
            "Landroid/util/Pair<",
            "Ljava/lang/Float;",
            "Ljava/lang/Float;",
            ">;"
        }
    .end annotation

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockHandView;->getHandRotation()F

    move-result v0

    sub-float v1, v0, p1

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result v1

    const/high16 v2, 0x43340000    # 180.0f

    cmpl-float v1, v1, v2

    if-lez v1, :cond_1

    const/high16 v1, 0x43b40000    # 360.0f

    cmpl-float v3, v0, v2

    if-lez v3, :cond_0

    cmpg-float v3, p1, v2

    if-gez v3, :cond_0

    add-float/2addr p1, v1

    :cond_0
    cmpg-float v3, v0, v2

    if-gez v3, :cond_1

    cmpl-float v2, p1, v2

    if-lez v2, :cond_1

    add-float/2addr v0, v1

    :cond_1
    new-instance v1, Landroid/util/Pair;

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v2

    invoke-static {p1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v3

    invoke-direct {v1, v2, v3}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v1
.end method

.method private handleTouchInput(FFZZZ)Z
    .locals 6

    invoke-direct {p0, p1, p2}, Lcom/google/android/material/timepicker/ClockHandView;->getDegreesFromXY(FF)I

    move-result v0

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockHandView;->getHandRotation()F

    move-result v1

    int-to-float v2, v0

    const/4 v3, 0x0

    const/4 v4, 0x1

    cmpl-float v1, v1, v2

    if-eqz v1, :cond_0

    const/4 v1, 0x1

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    if-eqz p4, :cond_1

    if-eqz v1, :cond_1

    return v4

    :cond_1
    if-nez v1, :cond_3

    if-eqz p3, :cond_2

    goto :goto_1

    :cond_2
    return v3

    :cond_3
    :goto_1
    int-to-float v2, v0

    if-eqz p5, :cond_4

    iget-boolean v5, p0, Lcom/google/android/material/timepicker/ClockHandView;->animatingOnTouchUp:Z

    if-eqz v5, :cond_4

    const/4 v3, 0x1

    :cond_4
    invoke-virtual {p0, v2, v3}, Lcom/google/android/material/timepicker/ClockHandView;->setHandRotation(FZ)V

    return v4
.end method

.method private setHandRotationInternal(FZ)V
    .locals 10

    const/high16 v0, 0x43b40000    # 360.0f

    rem-float/2addr p1, v0

    iput p1, p0, Lcom/google/android/material/timepicker/ClockHandView;->originalDeg:F

    const/high16 v0, 0x42b40000    # 90.0f

    sub-float v0, p1, v0

    float-to-double v1, v0

    invoke-static {v1, v2}, Ljava/lang/Math;->toRadians(D)D

    move-result-wide v1

    iput-wide v1, p0, Lcom/google/android/material/timepicker/ClockHandView;->degRad:D

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockHandView;->getHeight()I

    move-result v1

    div-int/lit8 v1, v1, 0x2

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockHandView;->getWidth()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    int-to-float v3, v2

    iget v4, p0, Lcom/google/android/material/timepicker/ClockHandView;->circleRadius:I

    int-to-float v4, v4

    iget-wide v5, p0, Lcom/google/android/material/timepicker/ClockHandView;->degRad:D

    invoke-static {v5, v6}, Ljava/lang/Math;->cos(D)D

    move-result-wide v5

    double-to-float v5, v5

    mul-float v4, v4, v5

    add-float/2addr v3, v4

    int-to-float v4, v1

    iget v5, p0, Lcom/google/android/material/timepicker/ClockHandView;->circleRadius:I

    int-to-float v5, v5

    iget-wide v6, p0, Lcom/google/android/material/timepicker/ClockHandView;->degRad:D

    invoke-static {v6, v7}, Ljava/lang/Math;->sin(D)D

    move-result-wide v6

    double-to-float v6, v6

    mul-float v5, v5, v6

    add-float/2addr v4, v5

    iget-object v5, p0, Lcom/google/android/material/timepicker/ClockHandView;->selectorBox:Landroid/graphics/RectF;

    iget v6, p0, Lcom/google/android/material/timepicker/ClockHandView;->selectorRadius:I

    int-to-float v7, v6

    sub-float v7, v3, v7

    int-to-float v8, v6

    sub-float v8, v4, v8

    int-to-float v9, v6

    add-float/2addr v9, v3

    int-to-float v6, v6

    add-float/2addr v6, v4

    invoke-virtual {v5, v7, v8, v9, v6}, Landroid/graphics/RectF;->set(FFFF)V

    iget-object v5, p0, Lcom/google/android/material/timepicker/ClockHandView;->listeners:Ljava/util/List;

    invoke-interface {v5}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :goto_0
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_0

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/google/android/material/timepicker/ClockHandView$OnRotateListener;

    invoke-interface {v6, p1, p2}, Lcom/google/android/material/timepicker/ClockHandView$OnRotateListener;->onRotate(FZ)V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockHandView;->invalidate()V

    return-void
.end method


# virtual methods
.method public addOnRotateListener(Lcom/google/android/material/timepicker/ClockHandView$OnRotateListener;)V
    .locals 1

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->listeners:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getCurrentSelectorBox()Landroid/graphics/RectF;
    .locals 1

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->selectorBox:Landroid/graphics/RectF;

    return-object v0
.end method

.method public getHandRotation()F
    .locals 1

    iget v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->originalDeg:F

    return v0
.end method

.method public getSelectorRadius()I
    .locals 1

    iget v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->selectorRadius:I

    return v0
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 0

    invoke-super {p0, p1}, Landroid/view/View;->onDraw(Landroid/graphics/Canvas;)V

    invoke-direct {p0, p1}, Lcom/google/android/material/timepicker/ClockHandView;->drawSelector(Landroid/graphics/Canvas;)V

    return-void
.end method

.method protected onLayout(ZIIII)V
    .locals 1

    invoke-super/range {p0 .. p5}, Landroid/view/View;->onLayout(ZIIII)V

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockHandView;->getHandRotation()F

    move-result v0

    invoke-virtual {p0, v0}, Lcom/google/android/material/timepicker/ClockHandView;->setHandRotation(F)V

    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 14

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v10

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v11

    const/4 v4, 0x0

    const/4 v12, 0x1

    packed-switch v0, :pswitch_data_0

    goto :goto_1

    :pswitch_0
    iget v5, p0, Lcom/google/android/material/timepicker/ClockHandView;->downX:F

    sub-float v5, v10, v5

    float-to-int v5, v5

    iget v6, p0, Lcom/google/android/material/timepicker/ClockHandView;->downY:F

    sub-float v6, v11, v6

    float-to-int v6, v6

    mul-int v7, v5, v5

    mul-int v8, v6, v6

    add-int/2addr v7, v8

    iget v8, p0, Lcom/google/android/material/timepicker/ClockHandView;->scaledTouchSlop:I

    if-le v7, v8, :cond_0

    const/4 v8, 0x1

    goto :goto_0

    :cond_0
    const/4 v8, 0x0

    :goto_0
    iput-boolean v8, p0, Lcom/google/android/material/timepicker/ClockHandView;->isInTapRegion:Z

    iget-boolean v8, p0, Lcom/google/android/material/timepicker/ClockHandView;->changedDuringTouch:Z

    if-eqz v8, :cond_1

    const/4 v1, 0x1

    :cond_1
    if-ne v0, v12, :cond_2

    const/4 v4, 0x1

    :cond_2
    move v3, v4

    goto :goto_1

    :pswitch_1
    iput v10, p0, Lcom/google/android/material/timepicker/ClockHandView;->downX:F

    iput v11, p0, Lcom/google/android/material/timepicker/ClockHandView;->downY:F

    iput-boolean v12, p0, Lcom/google/android/material/timepicker/ClockHandView;->isInTapRegion:Z

    iput-boolean v4, p0, Lcom/google/android/material/timepicker/ClockHandView;->changedDuringTouch:Z

    const/4 v2, 0x1

    nop

    :goto_1
    iget-boolean v13, p0, Lcom/google/android/material/timepicker/ClockHandView;->changedDuringTouch:Z

    move-object v4, p0

    move v5, v10

    move v6, v11

    move v7, v1

    move v8, v2

    move v9, v3

    invoke-direct/range {v4 .. v9}, Lcom/google/android/material/timepicker/ClockHandView;->handleTouchInput(FFZZZ)Z

    move-result v4

    or-int/2addr v4, v13

    iput-boolean v4, p0, Lcom/google/android/material/timepicker/ClockHandView;->changedDuringTouch:Z

    if-eqz v4, :cond_3

    if-eqz v3, :cond_3

    iget-object v4, p0, Lcom/google/android/material/timepicker/ClockHandView;->onActionUpListener:Lcom/google/android/material/timepicker/ClockHandView$OnActionUpListener;

    if-eqz v4, :cond_3

    invoke-direct {p0, v10, v11}, Lcom/google/android/material/timepicker/ClockHandView;->getDegreesFromXY(FF)I

    move-result v5

    int-to-float v5, v5

    iget-boolean v6, p0, Lcom/google/android/material/timepicker/ClockHandView;->isInTapRegion:Z

    invoke-interface {v4, v5, v6}, Lcom/google/android/material/timepicker/ClockHandView$OnActionUpListener;->onActionUp(FZ)V

    :cond_3
    return v12

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_0
        :pswitch_0
    .end packed-switch
.end method

.method public setAnimateOnTouchUp(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/google/android/material/timepicker/ClockHandView;->animatingOnTouchUp:Z

    return-void
.end method

.method public setCircleRadius(I)V
    .locals 0

    iput p1, p0, Lcom/google/android/material/timepicker/ClockHandView;->circleRadius:I

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockHandView;->invalidate()V

    return-void
.end method

.method public setHandRotation(F)V
    .locals 1

    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lcom/google/android/material/timepicker/ClockHandView;->setHandRotation(FZ)V

    return-void
.end method

.method public setHandRotation(FZ)V
    .locals 4

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->rotationAnimator:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    :cond_0
    const/4 v0, 0x0

    if-nez p2, :cond_1

    invoke-direct {p0, p1, v0}, Lcom/google/android/material/timepicker/ClockHandView;->setHandRotationInternal(FZ)V

    return-void

    :cond_1
    invoke-direct {p0, p1}, Lcom/google/android/material/timepicker/ClockHandView;->getValuesForAnimation(F)Landroid/util/Pair;

    move-result-object v1

    const/4 v2, 0x2

    new-array v2, v2, [F

    iget-object v3, v1, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast v3, Ljava/lang/Float;

    invoke-virtual {v3}, Ljava/lang/Float;->floatValue()F

    move-result v3

    aput v3, v2, v0

    const/4 v0, 0x1

    iget-object v3, v1, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast v3, Ljava/lang/Float;

    invoke-virtual {v3}, Ljava/lang/Float;->floatValue()F

    move-result v3

    aput v3, v2, v0

    invoke-static {v2}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->rotationAnimator:Landroid/animation/ValueAnimator;

    const-wide/16 v2, 0xc8

    invoke-virtual {v0, v2, v3}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->rotationAnimator:Landroid/animation/ValueAnimator;

    new-instance v2, Lcom/google/android/material/timepicker/ClockHandView$1;

    invoke-direct {v2, p0}, Lcom/google/android/material/timepicker/ClockHandView$1;-><init>(Lcom/google/android/material/timepicker/ClockHandView;)V

    invoke-virtual {v0, v2}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->rotationAnimator:Landroid/animation/ValueAnimator;

    new-instance v2, Lcom/google/android/material/timepicker/ClockHandView$2;

    invoke-direct {v2, p0}, Lcom/google/android/material/timepicker/ClockHandView$2;-><init>(Lcom/google/android/material/timepicker/ClockHandView;)V

    invoke-virtual {v0, v2}, Landroid/animation/ValueAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockHandView;->rotationAnimator:Landroid/animation/ValueAnimator;

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->start()V

    return-void
.end method

.method public setOnActionUpListener(Lcom/google/android/material/timepicker/ClockHandView$OnActionUpListener;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/material/timepicker/ClockHandView;->onActionUpListener:Lcom/google/android/material/timepicker/ClockHandView$OnActionUpListener;

    return-void
.end method
