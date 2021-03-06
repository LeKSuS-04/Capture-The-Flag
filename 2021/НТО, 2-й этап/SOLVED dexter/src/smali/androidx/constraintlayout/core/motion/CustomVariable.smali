.class public Landroidx/constraintlayout/core/motion/CustomVariable;
.super Ljava/lang/Object;
.source "CustomVariable.java"


# static fields
.field private static final TAG:Ljava/lang/String; = "TransitionLayout"


# instance fields
.field mBooleanValue:Z

.field private mFloatValue:F

.field private mIntegerValue:I

.field mName:Ljava/lang/String;

.field private mStringValue:Ljava/lang/String;

.field private mType:I


# direct methods
.method public constructor <init>(Landroidx/constraintlayout/core/motion/CustomVariable;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/high16 v0, -0x80000000

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    const/4 v0, 0x0

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    iget-object v0, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iget v0, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    iget v0, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    iget v0, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    iget-object v0, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    iget-boolean v0, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    iput-boolean v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    return-void
.end method

.method public constructor <init>(Landroidx/constraintlayout/core/motion/CustomVariable;Ljava/lang/Object;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/high16 v0, -0x80000000

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    const/4 v0, 0x0

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    iget-object v0, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iget v0, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    invoke-virtual {p0, p2}, Landroidx/constraintlayout/core/motion/CustomVariable;->setValue(Ljava/lang/Object;)V

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;I)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/high16 v0, -0x80000000

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    const/4 v0, 0x0

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    iput-object p1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iput p2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;IF)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/high16 v0, -0x80000000

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    const/4 v0, 0x0

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    iput-object p1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iput p2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    iput p3, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;II)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/high16 v0, -0x80000000

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    const/4 v0, 0x0

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    iput-object p1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iput p2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    const/16 v0, 0x385

    if-ne p2, v0, :cond_0

    int-to-float v0, p3

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    goto :goto_0

    :cond_0
    iput p3, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    :goto_0
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;ILjava/lang/Object;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/high16 v0, -0x80000000

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    const/4 v0, 0x0

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    iput-object p1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iput p2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    invoke-virtual {p0, p3}, Landroidx/constraintlayout/core/motion/CustomVariable;->setValue(Ljava/lang/Object;)V

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;ILjava/lang/String;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/high16 v0, -0x80000000

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    const/4 v0, 0x0

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    iput-object p1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iput p2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    iput-object p3, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;IZ)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/high16 v0, -0x80000000

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    const/4 v0, 0x0

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    iput-object p1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iput p2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    iput-boolean p3, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    return-void
.end method

.method private static clamp(I)I
    .locals 2

    const/16 v0, 0xff

    shr-int/lit8 v1, p0, 0x1f

    not-int v1, v1

    and-int/2addr p0, v1

    sub-int/2addr p0, v0

    shr-int/lit8 v1, p0, 0x1f

    and-int/2addr p0, v1

    add-int/2addr p0, v0

    return p0
.end method

.method public static colorString(I)Ljava/lang/String;
    .locals 3

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "00000000"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {p0}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "#"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, -0x8

    invoke-virtual {v0, v2}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method

.method public static hsvToRgb(FFF)I
    .locals 9

    const/high16 v0, 0x40c00000    # 6.0f

    mul-float v1, p0, v0

    float-to-int v1, v1

    mul-float v0, v0, p0

    int-to-float v2, v1

    sub-float/2addr v0, v2

    const/high16 v2, 0x437f0000    # 255.0f

    mul-float v3, p2, v2

    const/high16 v4, 0x3f800000    # 1.0f

    sub-float v5, v4, p1

    mul-float v3, v3, v5

    const/high16 v5, 0x3f000000    # 0.5f

    add-float/2addr v3, v5

    float-to-int v3, v3

    mul-float v6, p2, v2

    mul-float v7, v0, p1

    sub-float v7, v4, v7

    mul-float v6, v6, v7

    add-float/2addr v6, v5

    float-to-int v6, v6

    mul-float v7, p2, v2

    sub-float v8, v4, v0

    mul-float v8, v8, p1

    sub-float/2addr v4, v8

    mul-float v7, v7, v4

    add-float/2addr v7, v5

    float-to-int v4, v7

    mul-float v2, v2, p2

    add-float/2addr v2, v5

    float-to-int v2, v2

    const/high16 v5, -0x1000000

    packed-switch v1, :pswitch_data_0

    const/4 v5, 0x0

    return v5

    :pswitch_0
    shl-int/lit8 v7, v2, 0x10

    shl-int/lit8 v8, v3, 0x8

    add-int/2addr v7, v8

    add-int/2addr v7, v6

    or-int/2addr v5, v7

    return v5

    :pswitch_1
    shl-int/lit8 v7, v4, 0x10

    shl-int/lit8 v8, v3, 0x8

    add-int/2addr v7, v8

    add-int/2addr v7, v2

    or-int/2addr v5, v7

    return v5

    :pswitch_2
    shl-int/lit8 v7, v3, 0x10

    shl-int/lit8 v8, v6, 0x8

    add-int/2addr v7, v8

    add-int/2addr v7, v2

    or-int/2addr v5, v7

    return v5

    :pswitch_3
    shl-int/lit8 v7, v3, 0x10

    shl-int/lit8 v8, v2, 0x8

    add-int/2addr v7, v8

    add-int/2addr v7, v4

    or-int/2addr v5, v7

    return v5

    :pswitch_4
    shl-int/lit8 v7, v6, 0x10

    shl-int/lit8 v8, v2, 0x8

    add-int/2addr v7, v8

    add-int/2addr v7, v3

    or-int/2addr v5, v7

    return v5

    :pswitch_5
    shl-int/lit8 v7, v2, 0x10

    shl-int/lit8 v8, v4, 0x8

    add-int/2addr v7, v8

    add-int/2addr v7, v3

    or-int/2addr v5, v7

    return v5

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static rgbaTocColor(FFFF)I
    .locals 6

    const/high16 v0, 0x437f0000    # 255.0f

    mul-float v1, p0, v0

    float-to-int v1, v1

    invoke-static {v1}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v1

    mul-float v2, p1, v0

    float-to-int v2, v2

    invoke-static {v2}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v2

    mul-float v3, p2, v0

    float-to-int v3, v3

    invoke-static {v3}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v3

    mul-float v0, v0, p3

    float-to-int v0, v0

    invoke-static {v0}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v0

    shl-int/lit8 v4, v0, 0x18

    shl-int/lit8 v5, v1, 0x10

    or-int/2addr v4, v5

    shl-int/lit8 v5, v2, 0x8

    or-int/2addr v4, v5

    or-int/2addr v4, v3

    return v4
.end method


# virtual methods
.method public applyToWidget(Landroidx/constraintlayout/core/motion/MotionWidget;)V
    .locals 3

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    packed-switch v0, :pswitch_data_0

    goto :goto_0

    :pswitch_0
    iget-object v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iget-boolean v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    invoke-virtual {p1, v1, v0, v2}, Landroidx/constraintlayout/core/motion/MotionWidget;->setCustomAttribute(Ljava/lang/String;IZ)V

    goto :goto_0

    :pswitch_1
    iget-object v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iget-object v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    invoke-virtual {p1, v1, v0, v2}, Landroidx/constraintlayout/core/motion/MotionWidget;->setCustomAttribute(Ljava/lang/String;ILjava/lang/String;)V

    goto :goto_0

    :pswitch_2
    iget-object v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iget v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    invoke-virtual {p1, v1, v0, v2}, Landroidx/constraintlayout/core/motion/MotionWidget;->setCustomAttribute(Ljava/lang/String;IF)V

    goto :goto_0

    :pswitch_3
    iget-object v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iget v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    invoke-virtual {p1, v1, v0, v2}, Landroidx/constraintlayout/core/motion/MotionWidget;->setCustomAttribute(Ljava/lang/String;II)V

    nop

    :goto_0
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x384
        :pswitch_3
        :pswitch_2
        :pswitch_3
        :pswitch_1
        :pswitch_0
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method public copy()Landroidx/constraintlayout/core/motion/CustomVariable;
    .locals 1

    new-instance v0, Landroidx/constraintlayout/core/motion/CustomVariable;

    invoke-direct {v0, p0}, Landroidx/constraintlayout/core/motion/CustomVariable;-><init>(Landroidx/constraintlayout/core/motion/CustomVariable;)V

    return-object v0
.end method

.method public diff(Landroidx/constraintlayout/core/motion/CustomVariable;)Z
    .locals 4

    const/4 v0, 0x0

    if-eqz p1, :cond_7

    iget v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    iget v2, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    if-eq v1, v2, :cond_0

    goto :goto_0

    :cond_0
    const/4 v2, 0x1

    packed-switch v1, :pswitch_data_0

    return v0

    :pswitch_0
    iget v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    iget v3, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    cmpl-float v1, v1, v3

    if-nez v1, :cond_1

    const/4 v0, 0x1

    :cond_1
    return v0

    :pswitch_1
    iget-boolean v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    iget-boolean v3, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    if-ne v1, v3, :cond_2

    const/4 v0, 0x1

    :cond_2
    return v0

    :pswitch_2
    iget v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    iget v3, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    if-ne v1, v3, :cond_3

    const/4 v0, 0x1

    :cond_3
    return v0

    :pswitch_3
    iget v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    iget v3, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    if-ne v1, v3, :cond_4

    const/4 v0, 0x1

    :cond_4
    return v0

    :pswitch_4
    iget v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    iget v3, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    cmpl-float v1, v1, v3

    if-nez v1, :cond_5

    const/4 v0, 0x1

    :cond_5
    return v0

    :pswitch_5
    iget v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    iget v3, p1, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    if-ne v1, v3, :cond_6

    const/4 v0, 0x1

    :cond_6
    return v0

    :cond_7
    :goto_0
    return v0

    :pswitch_data_0
    .packed-switch 0x384
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_5
    .end packed-switch
.end method

.method public getBooleanValue()Z
    .locals 1

    iget-boolean v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    return v0
.end method

.method public getColorValue()I
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    return v0
.end method

.method public getFloatValue()F
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    return v0
.end method

.method public getIntegerValue()I
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    return v0
.end method

.method public getInterpolatedColor([F)I
    .locals 7

    const/4 v0, 0x0

    aget v0, p1, v0

    float-to-double v0, v0

    const-wide v2, 0x3fdd1745d1745d17L    # 0.45454545454545453

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v0

    double-to-float v0, v0

    const/high16 v1, 0x437f0000    # 255.0f

    mul-float v0, v0, v1

    float-to-int v0, v0

    invoke-static {v0}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v0

    const/4 v4, 0x1

    aget v4, p1, v4

    float-to-double v4, v4

    invoke-static {v4, v5, v2, v3}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v4

    double-to-float v4, v4

    mul-float v4, v4, v1

    float-to-int v4, v4

    invoke-static {v4}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v4

    const/4 v5, 0x2

    aget v5, p1, v5

    float-to-double v5, v5

    invoke-static {v5, v6, v2, v3}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v2

    double-to-float v2, v2

    mul-float v2, v2, v1

    float-to-int v2, v2

    invoke-static {v2}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v2

    const/4 v3, 0x3

    aget v3, p1, v3

    mul-float v3, v3, v1

    float-to-int v1, v3

    invoke-static {v1}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v1

    shl-int/lit8 v3, v1, 0x18

    shl-int/lit8 v5, v0, 0x10

    or-int/2addr v3, v5

    shl-int/lit8 v5, v4, 0x8

    or-int/2addr v3, v5

    or-int/2addr v3, v2

    return v3
.end method

.method public getName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    return-object v0
.end method

.method public getStringValue()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    return-object v0
.end method

.method public getType()I
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    return v0
.end method

.method public getValueToInterpolate()F
    .locals 2

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    packed-switch v0, :pswitch_data_0

    const/high16 v0, 0x7fc00000    # Float.NaN

    return v0

    :pswitch_0
    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    return v0

    :pswitch_1
    iget-boolean v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    if-eqz v0, :cond_0

    const/high16 v0, 0x3f800000    # 1.0f

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0

    :pswitch_2
    new-instance v0, Ljava/lang/RuntimeException;

    const-string v1, "Cannot interpolate String"

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0

    :pswitch_3
    new-instance v0, Ljava/lang/RuntimeException;

    const-string v1, "Color does not have a single color to interpolate"

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0

    :pswitch_4
    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    return v0

    :pswitch_5
    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    int-to-float v0, v0

    return v0

    nop

    :pswitch_data_0
    .packed-switch 0x384
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public getValuesToInterpolate([F)V
    .locals 13

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    const/4 v1, 0x0

    packed-switch v0, :pswitch_data_0

    goto :goto_1

    :pswitch_0
    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    aput v0, p1, v1

    goto :goto_1

    :pswitch_1
    iget-boolean v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    if-eqz v0, :cond_0

    const/high16 v0, 0x3f800000    # 1.0f

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    aput v0, p1, v1

    goto :goto_1

    :pswitch_2
    new-instance v0, Ljava/lang/RuntimeException;

    const-string v1, "Color does not have a single color to interpolate"

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0

    :pswitch_3
    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    shr-int/lit8 v2, v0, 0x18

    and-int/lit16 v2, v2, 0xff

    shr-int/lit8 v3, v0, 0x10

    and-int/lit16 v3, v3, 0xff

    shr-int/lit8 v4, v0, 0x8

    and-int/lit16 v4, v4, 0xff

    and-int/lit16 v0, v0, 0xff

    int-to-float v5, v3

    const/high16 v6, 0x437f0000    # 255.0f

    div-float/2addr v5, v6

    float-to-double v7, v5

    const-wide v9, 0x400199999999999aL    # 2.2

    invoke-static {v7, v8, v9, v10}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v7

    double-to-float v5, v7

    int-to-float v7, v4

    div-float/2addr v7, v6

    float-to-double v7, v7

    invoke-static {v7, v8, v9, v10}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v7

    double-to-float v7, v7

    int-to-float v8, v0

    div-float/2addr v8, v6

    float-to-double v11, v8

    invoke-static {v11, v12, v9, v10}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v8

    double-to-float v8, v8

    aput v5, p1, v1

    const/4 v1, 0x1

    aput v7, p1, v1

    const/4 v1, 0x2

    aput v8, p1, v1

    const/4 v1, 0x3

    int-to-float v9, v2

    div-float/2addr v9, v6

    aput v9, p1, v1

    goto :goto_1

    :pswitch_4
    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    aput v0, p1, v1

    goto :goto_1

    :pswitch_5
    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    int-to-float v0, v0

    aput v0, p1, v1

    nop

    :goto_1
    return-void

    :pswitch_data_0
    .packed-switch 0x384
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public isContinuous()Z
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    packed-switch v0, :pswitch_data_0

    :pswitch_0
    const/4 v0, 0x1

    return v0

    :pswitch_1
    const/4 v0, 0x0

    return v0

    nop

    :pswitch_data_0
    .packed-switch 0x387
        :pswitch_1
        :pswitch_1
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public numberOfInterpolatedValues()I
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    packed-switch v0, :pswitch_data_0

    const/4 v0, 0x1

    return v0

    :pswitch_0
    const/4 v0, 0x4

    return v0

    nop

    :pswitch_data_0
    .packed-switch 0x386
        :pswitch_0
    .end packed-switch
.end method

.method public setBooleanValue(Z)V
    .locals 0

    iput-boolean p1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    return-void
.end method

.method public setFloatValue(F)V
    .locals 0

    iput p1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    return-void
.end method

.method public setIntValue(I)V
    .locals 0

    iput p1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    return-void
.end method

.method public setInterpolatedValue(Landroidx/constraintlayout/core/motion/MotionWidget;[F)V
    .locals 8

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    const/4 v1, 0x1

    const/4 v2, 0x0

    packed-switch v0, :pswitch_data_0

    goto/16 :goto_1

    :pswitch_0
    iget-object v3, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    aget v4, p2, v2

    const/high16 v5, 0x3f000000    # 0.5f

    cmpl-float v4, v4, v5

    if-lez v4, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    invoke-virtual {p1, v3, v0, v1}, Landroidx/constraintlayout/core/motion/MotionWidget;->setCustomAttribute(Ljava/lang/String;IZ)V

    goto/16 :goto_1

    :pswitch_1
    new-instance v0, Ljava/lang/RuntimeException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "unable to interpolate "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0

    :pswitch_2
    aget v0, p2, v2

    float-to-double v2, v0

    const-wide v4, 0x3fdd1745d1745d17L    # 0.45454545454545453

    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v2

    double-to-float v0, v2

    const/high16 v2, 0x437f0000    # 255.0f

    mul-float v0, v0, v2

    float-to-int v0, v0

    invoke-static {v0}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v0

    aget v1, p2, v1

    float-to-double v6, v1

    invoke-static {v6, v7, v4, v5}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v6

    double-to-float v1, v6

    mul-float v1, v1, v2

    float-to-int v1, v1

    invoke-static {v1}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v1

    const/4 v3, 0x2

    aget v3, p2, v3

    float-to-double v6, v3

    invoke-static {v6, v7, v4, v5}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v3

    double-to-float v3, v3

    mul-float v3, v3, v2

    float-to-int v3, v3

    invoke-static {v3}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v3

    const/4 v4, 0x3

    aget v4, p2, v4

    mul-float v4, v4, v2

    float-to-int v2, v4

    invoke-static {v2}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v2

    shl-int/lit8 v4, v2, 0x18

    shl-int/lit8 v5, v0, 0x10

    or-int/2addr v4, v5

    shl-int/lit8 v5, v1, 0x8

    or-int/2addr v4, v5

    or-int/2addr v4, v3

    iget-object v5, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    iget v6, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    invoke-virtual {p1, v5, v6, v4}, Landroidx/constraintlayout/core/motion/MotionWidget;->setCustomAttribute(Ljava/lang/String;II)V

    goto :goto_1

    :pswitch_3
    iget-object v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    aget v2, p2, v2

    invoke-virtual {p1, v1, v0, v2}, Landroidx/constraintlayout/core/motion/MotionWidget;->setCustomAttribute(Ljava/lang/String;IF)V

    goto :goto_1

    :pswitch_4
    iget-object v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    aget v2, p2, v2

    float-to-int v2, v2

    invoke-virtual {p1, v1, v0, v2}, Landroidx/constraintlayout/core/motion/MotionWidget;->setCustomAttribute(Ljava/lang/String;II)V

    nop

    :goto_1
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x384
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_3
        :pswitch_1
    .end packed-switch
.end method

.method public setStringValue(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    return-void
.end method

.method public setValue(Ljava/lang/Object;)V
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    packed-switch v0, :pswitch_data_0

    goto :goto_0

    :pswitch_0
    move-object v0, p1

    check-cast v0, Ljava/lang/Float;

    invoke-virtual {v0}, Ljava/lang/Float;->floatValue()F

    move-result v0

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    goto :goto_0

    :pswitch_1
    move-object v0, p1

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    iput-boolean v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    goto :goto_0

    :pswitch_2
    move-object v0, p1

    check-cast v0, Ljava/lang/String;

    iput-object v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    goto :goto_0

    :pswitch_3
    move-object v0, p1

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    goto :goto_0

    :pswitch_4
    move-object v0, p1

    check-cast v0, Ljava/lang/Float;

    invoke-virtual {v0}, Ljava/lang/Float;->floatValue()F

    move-result v0

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    goto :goto_0

    :pswitch_5
    move-object v0, p1

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    nop

    :goto_0
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x384
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_5
    .end packed-switch
.end method

.method public setValue([F)V
    .locals 7

    iget v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    const/4 v1, 0x1

    const/4 v2, 0x0

    packed-switch v0, :pswitch_data_0

    goto :goto_1

    :pswitch_0
    aget v0, p1, v2

    float-to-double v3, v0

    const-wide/high16 v5, 0x3fe0000000000000L    # 0.5

    cmpl-double v0, v3, v5

    if-lez v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    iput-boolean v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    goto :goto_1

    :pswitch_1
    new-instance v0, Ljava/lang/RuntimeException;

    const-string v1, "Color does not have a single color to interpolate"

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0

    :pswitch_2
    aget v0, p1, v2

    aget v1, p1, v1

    const/4 v2, 0x2

    aget v2, p1, v2

    invoke-static {v0, v1, v2}, Landroidx/constraintlayout/core/motion/CustomVariable;->hsvToRgb(FFF)I

    move-result v0

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    const v1, 0xffffff

    and-int/2addr v0, v1

    const/high16 v1, 0x437f0000    # 255.0f

    const/4 v2, 0x3

    aget v2, p1, v2

    mul-float v2, v2, v1

    float-to-int v1, v2

    invoke-static {v1}, Landroidx/constraintlayout/core/motion/CustomVariable;->clamp(I)I

    move-result v1

    shl-int/lit8 v1, v1, 0x18

    or-int/2addr v0, v1

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    goto :goto_1

    :pswitch_3
    aget v0, p1, v2

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    goto :goto_1

    :pswitch_4
    aget v0, p1, v2

    float-to-int v0, v0

    iput v0, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    nop

    :goto_1
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x384
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_3
        :pswitch_4
    .end packed-switch
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mName:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v1, 0x3a

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    iget v1, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mType:I

    packed-switch v1, :pswitch_data_0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "????"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1

    :pswitch_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1

    :pswitch_1
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mBooleanValue:Z

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1

    :pswitch_2
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mStringValue:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1

    :pswitch_3
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    invoke-static {v2}, Landroidx/constraintlayout/core/motion/CustomVariable;->colorString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1

    :pswitch_4
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mFloatValue:F

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1

    :pswitch_5
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Landroidx/constraintlayout/core/motion/CustomVariable;->mIntegerValue:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1

    :pswitch_data_0
    .packed-switch 0x384
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
