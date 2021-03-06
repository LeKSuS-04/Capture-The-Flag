.class Lcom/google/android/material/timepicker/ClockFaceView;
.super Lcom/google/android/material/timepicker/RadialViewGroup;
.source "ClockFaceView.java"

# interfaces
.implements Lcom/google/android/material/timepicker/ClockHandView$OnRotateListener;


# static fields
.field private static final EPSILON:F = 0.001f

.field private static final INITIAL_CAPACITY:I = 0xc

.field private static final VALUE_PLACEHOLDER:Ljava/lang/String; = ""


# instance fields
.field private final clockHandPadding:I

.field private final clockHandView:Lcom/google/android/material/timepicker/ClockHandView;

.field private final clockSize:I

.field private currentHandRotation:F

.field private final gradientColors:[I

.field private final gradientPositions:[F

.field private final minimumHeight:I

.field private final minimumWidth:I

.field private final scratch:Landroid/graphics/RectF;

.field private final textColor:Landroid/content/res/ColorStateList;

.field private final textViewPool:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Landroid/widget/TextView;",
            ">;"
        }
    .end annotation
.end field

.field private final textViewRect:Landroid/graphics/Rect;

.field private final valueAccessibilityDelegate:Landroidx/core/view/AccessibilityDelegateCompat;

.field private values:[Ljava/lang/String;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/google/android/material/timepicker/ClockFaceView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    sget v0, Lcom/google/android/material/R$attr;->materialClockStyle:I

    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/material/timepicker/ClockFaceView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 9

    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/material/timepicker/RadialViewGroup;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewRect:Landroid/graphics/Rect;

    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->scratch:Landroid/graphics/RectF;

    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewPool:Landroid/util/SparseArray;

    const/4 v0, 0x3

    new-array v1, v0, [F

    fill-array-data v1, :array_0

    iput-object v1, p0, Lcom/google/android/material/timepicker/ClockFaceView;->gradientPositions:[F

    sget-object v1, Lcom/google/android/material/R$styleable;->ClockFaceView:[I

    sget v2, Lcom/google/android/material/R$style;->Widget_MaterialComponents_TimePicker_Clock:I

    invoke-virtual {p1, p2, v1, p3, v2}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v1

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockFaceView;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v3, Lcom/google/android/material/R$styleable;->ClockFaceView_clockNumberTextColor:I

    invoke-static {p1, v1, v3}, Lcom/google/android/material/resources/MaterialResources;->getColorStateList(Landroid/content/Context;Landroid/content/res/TypedArray;I)Landroid/content/res/ColorStateList;

    move-result-object v3

    iput-object v3, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textColor:Landroid/content/res/ColorStateList;

    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v4

    sget v5, Lcom/google/android/material/R$layout;->material_clockface_view:I

    const/4 v6, 0x1

    invoke-virtual {v4, v5, p0, v6}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    sget v4, Lcom/google/android/material/R$id;->material_clock_hand:I

    invoke-virtual {p0, v4}, Lcom/google/android/material/timepicker/ClockFaceView;->findViewById(I)Landroid/view/View;

    move-result-object v4

    check-cast v4, Lcom/google/android/material/timepicker/ClockHandView;

    iput-object v4, p0, Lcom/google/android/material/timepicker/ClockFaceView;->clockHandView:Lcom/google/android/material/timepicker/ClockHandView;

    sget v5, Lcom/google/android/material/R$dimen;->material_clock_hand_padding:I

    invoke-virtual {v2, v5}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v5

    iput v5, p0, Lcom/google/android/material/timepicker/ClockFaceView;->clockHandPadding:I

    new-array v5, v6, [I

    const v7, 0x10100a1

    const/4 v8, 0x0

    aput v7, v5, v8

    invoke-virtual {v3}, Landroid/content/res/ColorStateList;->getDefaultColor()I

    move-result v7

    invoke-virtual {v3, v5, v7}, Landroid/content/res/ColorStateList;->getColorForState([II)I

    move-result v5

    new-array v0, v0, [I

    aput v5, v0, v8

    aput v5, v0, v6

    invoke-virtual {v3}, Landroid/content/res/ColorStateList;->getDefaultColor()I

    move-result v3

    const/4 v7, 0x2

    aput v3, v0, v7

    iput-object v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->gradientColors:[I

    invoke-virtual {v4, p0}, Lcom/google/android/material/timepicker/ClockHandView;->addOnRotateListener(Lcom/google/android/material/timepicker/ClockHandView$OnRotateListener;)V

    sget v0, Lcom/google/android/material/R$color;->material_timepicker_clockface:I

    invoke-static {p1, v0}, Landroidx/appcompat/content/res/AppCompatResources;->getColorStateList(Landroid/content/Context;I)Landroid/content/res/ColorStateList;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/ColorStateList;->getDefaultColor()I

    move-result v0

    sget v3, Lcom/google/android/material/R$styleable;->ClockFaceView_clockFaceBackgroundColor:I

    invoke-static {p1, v1, v3}, Lcom/google/android/material/resources/MaterialResources;->getColorStateList(Landroid/content/Context;Landroid/content/res/TypedArray;I)Landroid/content/res/ColorStateList;

    move-result-object v3

    if-nez v3, :cond_0

    move v4, v0

    goto :goto_0

    :cond_0
    invoke-virtual {v3}, Landroid/content/res/ColorStateList;->getDefaultColor()I

    move-result v4

    :goto_0
    invoke-virtual {p0, v4}, Lcom/google/android/material/timepicker/ClockFaceView;->setBackgroundColor(I)V

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockFaceView;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v4

    new-instance v7, Lcom/google/android/material/timepicker/ClockFaceView$1;

    invoke-direct {v7, p0}, Lcom/google/android/material/timepicker/ClockFaceView$1;-><init>(Lcom/google/android/material/timepicker/ClockFaceView;)V

    invoke-virtual {v4, v7}, Landroid/view/ViewTreeObserver;->addOnPreDrawListener(Landroid/view/ViewTreeObserver$OnPreDrawListener;)V

    invoke-virtual {p0, v6}, Lcom/google/android/material/timepicker/ClockFaceView;->setFocusable(Z)V

    invoke-virtual {v1}, Landroid/content/res/TypedArray;->recycle()V

    new-instance v4, Lcom/google/android/material/timepicker/ClockFaceView$2;

    invoke-direct {v4, p0}, Lcom/google/android/material/timepicker/ClockFaceView$2;-><init>(Lcom/google/android/material/timepicker/ClockFaceView;)V

    iput-object v4, p0, Lcom/google/android/material/timepicker/ClockFaceView;->valueAccessibilityDelegate:Landroidx/core/view/AccessibilityDelegateCompat;

    const/16 v4, 0xc

    new-array v4, v4, [Ljava/lang/String;

    const-string v6, ""

    invoke-static {v4, v6}, Ljava/util/Arrays;->fill([Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-virtual {p0, v4, v8}, Lcom/google/android/material/timepicker/ClockFaceView;->setValues([Ljava/lang/String;I)V

    sget v6, Lcom/google/android/material/R$dimen;->material_time_picker_minimum_screen_height:I

    invoke-virtual {v2, v6}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v6

    iput v6, p0, Lcom/google/android/material/timepicker/ClockFaceView;->minimumHeight:I

    sget v6, Lcom/google/android/material/R$dimen;->material_time_picker_minimum_screen_width:I

    invoke-virtual {v2, v6}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v6

    iput v6, p0, Lcom/google/android/material/timepicker/ClockFaceView;->minimumWidth:I

    sget v6, Lcom/google/android/material/R$dimen;->material_clock_size:I

    invoke-virtual {v2, v6}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v6

    iput v6, p0, Lcom/google/android/material/timepicker/ClockFaceView;->clockSize:I

    return-void

    nop

    :array_0
    .array-data 4
        0x0
        0x3f666666    # 0.9f
        0x3f800000    # 1.0f
    .end array-data
.end method

.method static synthetic access$000(Lcom/google/android/material/timepicker/ClockFaceView;)Lcom/google/android/material/timepicker/ClockHandView;
    .locals 1

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->clockHandView:Lcom/google/android/material/timepicker/ClockHandView;

    return-object v0
.end method

.method static synthetic access$100(Lcom/google/android/material/timepicker/ClockFaceView;)I
    .locals 1

    iget v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->clockHandPadding:I

    return v0
.end method

.method static synthetic access$200(Lcom/google/android/material/timepicker/ClockFaceView;)Landroid/util/SparseArray;
    .locals 1

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewPool:Landroid/util/SparseArray;

    return-object v0
.end method

.method private findIntersectingTextView()V
    .locals 6

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->clockHandView:Lcom/google/android/material/timepicker/ClockHandView;

    invoke-virtual {v0}, Lcom/google/android/material/timepicker/ClockHandView;->getCurrentSelectorBox()Landroid/graphics/RectF;

    move-result-object v0

    const/4 v1, 0x0

    :goto_0
    iget-object v2, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewPool:Landroid/util/SparseArray;

    invoke-virtual {v2}, Landroid/util/SparseArray;->size()I

    move-result v2

    if-ge v1, v2, :cond_1

    iget-object v2, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewPool:Landroid/util/SparseArray;

    invoke-virtual {v2, v1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/widget/TextView;

    if-nez v2, :cond_0

    goto :goto_1

    :cond_0
    iget-object v3, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewRect:Landroid/graphics/Rect;

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->getDrawingRect(Landroid/graphics/Rect;)V

    iget-object v3, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewRect:Landroid/graphics/Rect;

    invoke-virtual {v2}, Landroid/widget/TextView;->getPaddingLeft()I

    move-result v4

    invoke-virtual {v2}, Landroid/widget/TextView;->getPaddingTop()I

    move-result v5

    invoke-virtual {v3, v4, v5}, Landroid/graphics/Rect;->offset(II)V

    iget-object v3, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewRect:Landroid/graphics/Rect;

    invoke-virtual {p0, v2, v3}, Lcom/google/android/material/timepicker/ClockFaceView;->offsetDescendantRectToMyCoords(Landroid/view/View;Landroid/graphics/Rect;)V

    iget-object v3, p0, Lcom/google/android/material/timepicker/ClockFaceView;->scratch:Landroid/graphics/RectF;

    iget-object v4, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewRect:Landroid/graphics/Rect;

    invoke-virtual {v3, v4}, Landroid/graphics/RectF;->set(Landroid/graphics/Rect;)V

    iget-object v3, p0, Lcom/google/android/material/timepicker/ClockFaceView;->scratch:Landroid/graphics/RectF;

    invoke-direct {p0, v0, v3}, Lcom/google/android/material/timepicker/ClockFaceView;->getGradientForTextView(Landroid/graphics/RectF;Landroid/graphics/RectF;)Landroid/graphics/RadialGradient;

    move-result-object v3

    invoke-virtual {v2}, Landroid/widget/TextView;->getPaint()Landroid/text/TextPaint;

    move-result-object v4

    invoke-virtual {v4, v3}, Landroid/text/TextPaint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    invoke-virtual {v2}, Landroid/widget/TextView;->invalidate()V

    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method private getGradientForTextView(Landroid/graphics/RectF;Landroid/graphics/RectF;)Landroid/graphics/RadialGradient;
    .locals 8

    invoke-static {p1, p2}, Landroid/graphics/RectF;->intersects(Landroid/graphics/RectF;Landroid/graphics/RectF;)Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return-object v0

    :cond_0
    new-instance v0, Landroid/graphics/RadialGradient;

    invoke-virtual {p1}, Landroid/graphics/RectF;->centerX()F

    move-result v1

    iget-object v2, p0, Lcom/google/android/material/timepicker/ClockFaceView;->scratch:Landroid/graphics/RectF;

    iget v2, v2, Landroid/graphics/RectF;->left:F

    sub-float v2, v1, v2

    invoke-virtual {p1}, Landroid/graphics/RectF;->centerY()F

    move-result v1

    iget-object v3, p0, Lcom/google/android/material/timepicker/ClockFaceView;->scratch:Landroid/graphics/RectF;

    iget v3, v3, Landroid/graphics/RectF;->top:F

    sub-float v3, v1, v3

    invoke-virtual {p1}, Landroid/graphics/RectF;->width()F

    move-result v1

    const/high16 v4, 0x3f000000    # 0.5f

    mul-float v4, v4, v1

    iget-object v5, p0, Lcom/google/android/material/timepicker/ClockFaceView;->gradientColors:[I

    iget-object v6, p0, Lcom/google/android/material/timepicker/ClockFaceView;->gradientPositions:[F

    sget-object v7, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    move-object v1, v0

    invoke-direct/range {v1 .. v7}, Landroid/graphics/RadialGradient;-><init>(FFF[I[FLandroid/graphics/Shader$TileMode;)V

    return-object v0
.end method

.method private static max3(FFF)F
    .locals 1

    invoke-static {p0, p1}, Ljava/lang/Math;->max(FF)F

    move-result v0

    invoke-static {v0, p2}, Ljava/lang/Math;->max(FF)F

    move-result v0

    return v0
.end method

.method private updateTextViews(I)V
    .locals 8

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockFaceView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewPool:Landroid/util/SparseArray;

    invoke-virtual {v1}, Landroid/util/SparseArray;->size()I

    move-result v1

    const/4 v2, 0x0

    :goto_0
    iget-object v3, p0, Lcom/google/android/material/timepicker/ClockFaceView;->values:[Ljava/lang/String;

    array-length v3, v3

    invoke-static {v3, v1}, Ljava/lang/Math;->max(II)I

    move-result v3

    if-ge v2, v3, :cond_3

    iget-object v3, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewPool:Landroid/util/SparseArray;

    invoke-virtual {v3, v2}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/widget/TextView;

    iget-object v4, p0, Lcom/google/android/material/timepicker/ClockFaceView;->values:[Ljava/lang/String;

    array-length v4, v4

    if-lt v2, v4, :cond_0

    invoke-virtual {p0, v3}, Lcom/google/android/material/timepicker/ClockFaceView;->removeView(Landroid/view/View;)V

    iget-object v4, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewPool:Landroid/util/SparseArray;

    invoke-virtual {v4, v2}, Landroid/util/SparseArray;->remove(I)V

    goto :goto_1

    :cond_0
    const/4 v4, 0x0

    if-nez v3, :cond_1

    sget v5, Lcom/google/android/material/R$layout;->material_clockface_textview:I

    invoke-virtual {v0, v5, p0, v4}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v5

    move-object v3, v5

    check-cast v3, Landroid/widget/TextView;

    iget-object v5, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textViewPool:Landroid/util/SparseArray;

    invoke-virtual {v5, v2, v3}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    invoke-virtual {p0, v3}, Lcom/google/android/material/timepicker/ClockFaceView;->addView(Landroid/view/View;)V

    :cond_1
    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setVisibility(I)V

    iget-object v5, p0, Lcom/google/android/material/timepicker/ClockFaceView;->values:[Ljava/lang/String;

    aget-object v5, v5, v2

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    sget v5, Lcom/google/android/material/R$id;->material_value_index:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-virtual {v3, v5, v6}, Landroid/widget/TextView;->setTag(ILjava/lang/Object;)V

    iget-object v5, p0, Lcom/google/android/material/timepicker/ClockFaceView;->valueAccessibilityDelegate:Landroidx/core/view/AccessibilityDelegateCompat;

    invoke-static {v3, v5}, Landroidx/core/view/ViewCompat;->setAccessibilityDelegate(Landroid/view/View;Landroidx/core/view/AccessibilityDelegateCompat;)V

    iget-object v5, p0, Lcom/google/android/material/timepicker/ClockFaceView;->textColor:Landroid/content/res/ColorStateList;

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setTextColor(Landroid/content/res/ColorStateList;)V

    if-eqz p1, :cond_2

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockFaceView;->getResources()Landroid/content/res/Resources;

    move-result-object v5

    const/4 v6, 0x1

    new-array v6, v6, [Ljava/lang/Object;

    iget-object v7, p0, Lcom/google/android/material/timepicker/ClockFaceView;->values:[Ljava/lang/String;

    aget-object v7, v7, v2

    aput-object v7, v6, v4

    invoke-virtual {v5, p1, v6}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/widget/TextView;->setContentDescription(Ljava/lang/CharSequence;)V

    :cond_2
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_3
    return-void
.end method


# virtual methods
.method public onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V
    .locals 4

    invoke-super {p0, p1}, Lcom/google/android/material/timepicker/RadialViewGroup;->onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V

    invoke-static {p1}, Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;->wrap(Landroid/view/accessibility/AccessibilityNodeInfo;)Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/material/timepicker/ClockFaceView;->values:[Ljava/lang/String;

    array-length v1, v1

    const/4 v2, 0x1

    const/4 v3, 0x0

    invoke-static {v2, v1, v3, v2}, Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat$CollectionInfoCompat;->obtain(IIZI)Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat$CollectionInfoCompat;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;->setCollectionInfo(Ljava/lang/Object;)V

    return-void
.end method

.method protected onLayout(ZIIII)V
    .locals 0

    invoke-super/range {p0 .. p5}, Lcom/google/android/material/timepicker/RadialViewGroup;->onLayout(ZIIII)V

    invoke-direct {p0}, Lcom/google/android/material/timepicker/ClockFaceView;->findIntersectingTextView()V

    return-void
.end method

.method protected onMeasure(II)V
    .locals 8

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockFaceView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v1

    iget v2, v1, Landroid/util/DisplayMetrics;->heightPixels:I

    int-to-float v2, v2

    iget v3, v1, Landroid/util/DisplayMetrics;->widthPixels:I

    int-to-float v3, v3

    iget v4, p0, Lcom/google/android/material/timepicker/ClockFaceView;->clockSize:I

    int-to-float v4, v4

    iget v5, p0, Lcom/google/android/material/timepicker/ClockFaceView;->minimumHeight:I

    int-to-float v5, v5

    div-float/2addr v5, v2

    iget v6, p0, Lcom/google/android/material/timepicker/ClockFaceView;->minimumWidth:I

    int-to-float v6, v6

    div-float/2addr v6, v3

    const/high16 v7, 0x3f800000    # 1.0f

    invoke-static {v5, v6, v7}, Lcom/google/android/material/timepicker/ClockFaceView;->max3(FFF)F

    move-result v5

    div-float/2addr v4, v5

    float-to-int v4, v4

    const/high16 v5, 0x40000000    # 2.0f

    invoke-static {v4, v5}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v5

    invoke-virtual {p0, v4, v4}, Lcom/google/android/material/timepicker/ClockFaceView;->setMeasuredDimension(II)V

    invoke-super {p0, v5, v5}, Lcom/google/android/material/timepicker/RadialViewGroup;->onMeasure(II)V

    return-void
.end method

.method public onRotate(FZ)V
    .locals 2

    iget v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->currentHandRotation:F

    sub-float/2addr v0, p1

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    const v1, 0x3a83126f    # 0.001f

    cmpl-float v0, v0, v1

    if-lez v0, :cond_0

    iput p1, p0, Lcom/google/android/material/timepicker/ClockFaceView;->currentHandRotation:F

    invoke-direct {p0}, Lcom/google/android/material/timepicker/ClockFaceView;->findIntersectingTextView()V

    :cond_0
    return-void
.end method

.method public setHandRotation(F)V
    .locals 1

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->clockHandView:Lcom/google/android/material/timepicker/ClockHandView;

    invoke-virtual {v0, p1}, Lcom/google/android/material/timepicker/ClockHandView;->setHandRotation(F)V

    invoke-direct {p0}, Lcom/google/android/material/timepicker/ClockFaceView;->findIntersectingTextView()V

    return-void
.end method

.method public setRadius(I)V
    .locals 2

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockFaceView;->getRadius()I

    move-result v0

    if-eq p1, v0, :cond_0

    invoke-super {p0, p1}, Lcom/google/android/material/timepicker/RadialViewGroup;->setRadius(I)V

    iget-object v0, p0, Lcom/google/android/material/timepicker/ClockFaceView;->clockHandView:Lcom/google/android/material/timepicker/ClockHandView;

    invoke-virtual {p0}, Lcom/google/android/material/timepicker/ClockFaceView;->getRadius()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/material/timepicker/ClockHandView;->setCircleRadius(I)V

    :cond_0
    return-void
.end method

.method public setValues([Ljava/lang/String;I)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/material/timepicker/ClockFaceView;->values:[Ljava/lang/String;

    invoke-direct {p0, p2}, Lcom/google/android/material/timepicker/ClockFaceView;->updateTextViews(I)V

    return-void
.end method
