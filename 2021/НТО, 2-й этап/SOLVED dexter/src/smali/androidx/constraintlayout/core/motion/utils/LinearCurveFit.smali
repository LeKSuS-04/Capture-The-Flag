.class public Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;
.super Landroidx/constraintlayout/core/motion/utils/CurveFit;
.source "LinearCurveFit.java"


# static fields
.field private static final TAG:Ljava/lang/String; = "LinearCurveFit"


# instance fields
.field private mExtrapolate:Z

.field mSlopeTemp:[D

.field private mT:[D

.field private mTotalLength:D

.field private mY:[[D


# direct methods
.method public constructor <init>([D[[D)V
    .locals 21

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    invoke-direct/range {p0 .. p0}, Landroidx/constraintlayout/core/motion/utils/CurveFit;-><init>()V

    const-wide/high16 v3, 0x7ff8000000000000L    # Double.NaN

    iput-wide v3, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mTotalLength:D

    const/4 v3, 0x1

    iput-boolean v3, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mExtrapolate:Z

    array-length v3, v1

    const/4 v4, 0x0

    aget-object v5, v2, v4

    array-length v5, v5

    new-array v6, v5, [D

    iput-object v6, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mSlopeTemp:[D

    iput-object v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    iput-object v2, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    const/4 v6, 0x2

    if-le v5, v6, :cond_2

    const-wide/16 v6, 0x0

    const-wide/16 v8, 0x0

    const-wide/16 v10, 0x0

    const/4 v12, 0x0

    :goto_0
    array-length v13, v1

    if-ge v12, v13, :cond_1

    aget-object v13, v2, v12

    aget-wide v14, v13, v4

    aget-object v13, v2, v12

    aget-wide v16, v13, v4

    if-lez v12, :cond_0

    move/from16 v18, v5

    sub-double v4, v14, v8

    move-wide/from16 v19, v14

    sub-double v13, v16, v10

    invoke-static {v4, v5, v13, v14}, Ljava/lang/Math;->hypot(DD)D

    move-result-wide v4

    add-double/2addr v6, v4

    goto :goto_1

    :cond_0
    move/from16 v18, v5

    move-wide/from16 v19, v14

    :goto_1
    move-wide/from16 v8, v19

    move-wide/from16 v10, v16

    add-int/lit8 v12, v12, 0x1

    move/from16 v5, v18

    const/4 v4, 0x0

    goto :goto_0

    :cond_1
    move/from16 v18, v5

    const-wide/16 v4, 0x0

    iput-wide v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mTotalLength:D

    goto :goto_2

    :cond_2
    move/from16 v18, v5

    :goto_2
    return-void
.end method

.method private getLength2D(D)D
    .locals 34

    move-object/from16 v0, p0

    iget-wide v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mTotalLength:D

    invoke-static {v1, v2}, Ljava/lang/Double;->isNaN(D)Z

    move-result v1

    const-wide/16 v2, 0x0

    if-eqz v1, :cond_0

    return-wide v2

    :cond_0
    iget-object v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    array-length v4, v1

    const/4 v5, 0x0

    aget-wide v6, v1, v5

    cmpg-double v8, p1, v6

    if-gtz v8, :cond_1

    return-wide v2

    :cond_1
    add-int/lit8 v6, v4, -0x1

    aget-wide v6, v1, v6

    cmpl-double v1, p1, v6

    if-ltz v1, :cond_2

    iget-wide v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mTotalLength:D

    return-wide v1

    :cond_2
    const-wide/16 v6, 0x0

    const-wide/16 v8, 0x0

    const-wide/16 v10, 0x0

    const/4 v1, 0x0

    :goto_0
    add-int/lit8 v12, v4, -0x1

    if-ge v1, v12, :cond_6

    iget-object v12, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v13, v12, v1

    aget-wide v14, v13, v5

    aget-object v12, v12, v1

    const/4 v13, 0x1

    aget-wide v16, v12, v13

    if-lez v1, :cond_3

    sub-double v2, v14, v8

    move-wide/from16 v20, v14

    sub-double v13, v16, v10

    invoke-static {v2, v3, v13, v14}, Ljava/lang/Math;->hypot(DD)D

    move-result-wide v2

    add-double/2addr v6, v2

    goto :goto_1

    :cond_3
    move-wide/from16 v20, v14

    :goto_1
    move-wide/from16 v8, v20

    move-wide/from16 v10, v16

    iget-object v2, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    aget-wide v13, v2, v1

    cmpl-double v3, p1, v13

    if-nez v3, :cond_4

    return-wide v6

    :cond_4
    add-int/lit8 v3, v1, 0x1

    aget-wide v13, v2, v3

    cmpg-double v3, p1, v13

    if-gez v3, :cond_5

    add-int/lit8 v3, v1, 0x1

    aget-wide v13, v2, v3

    aget-wide v18, v2, v1

    sub-double v13, v13, v18

    aget-wide v18, v2, v1

    sub-double v2, p1, v18

    div-double/2addr v2, v13

    iget-object v15, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v18, v15, v1

    aget-wide v22, v18, v5

    add-int/lit8 v18, v1, 0x1

    aget-object v18, v15, v18

    aget-wide v24, v18, v5

    aget-object v5, v15, v1

    const/4 v12, 0x1

    aget-wide v18, v5, v12

    add-int/lit8 v5, v1, 0x1

    aget-object v5, v15, v5

    aget-wide v26, v5, v12

    const-wide/high16 v28, 0x3ff0000000000000L    # 1.0

    sub-double v30, v28, v2

    mul-double v30, v30, v18

    mul-double v32, v26, v2

    add-double v30, v30, v32

    move v12, v4

    sub-double v4, v16, v30

    sub-double v28, v28, v2

    mul-double v28, v28, v22

    mul-double v15, v24, v2

    add-double v28, v28, v15

    move-wide/from16 v30, v2

    sub-double v2, v20, v28

    invoke-static {v4, v5, v2, v3}, Ljava/lang/Math;->hypot(DD)D

    move-result-wide v15

    add-double/2addr v6, v15

    return-wide v6

    :cond_5
    move v12, v4

    add-int/lit8 v1, v1, 0x1

    const-wide/16 v2, 0x0

    goto :goto_0

    :cond_6
    const-wide/16 v1, 0x0

    return-wide v1
.end method


# virtual methods
.method public getPos(DI)D
    .locals 18

    move-object/from16 v0, p0

    move/from16 v1, p3

    iget-object v2, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    array-length v3, v2

    iget-boolean v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mExtrapolate:Z

    const/4 v5, 0x0

    if-eqz v4, :cond_1

    aget-wide v6, v2, v5

    cmpg-double v4, p1, v6

    if-gtz v4, :cond_0

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v4, v4, v5

    aget-wide v6, v4, v1

    aget-wide v8, v2, v5

    sub-double v8, p1, v8

    aget-wide v4, v2, v5

    invoke-virtual {v0, v4, v5, v1}, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->getSlope(DI)D

    move-result-wide v4

    mul-double v8, v8, v4

    add-double/2addr v6, v8

    return-wide v6

    :cond_0
    add-int/lit8 v4, v3, -0x1

    aget-wide v4, v2, v4

    cmpl-double v6, p1, v4

    if-ltz v6, :cond_3

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    add-int/lit8 v5, v3, -0x1

    aget-object v4, v4, v5

    aget-wide v5, v4, v1

    add-int/lit8 v4, v3, -0x1

    aget-wide v7, v2, v4

    sub-double v7, p1, v7

    add-int/lit8 v4, v3, -0x1

    aget-wide v9, v2, v4

    invoke-virtual {v0, v9, v10, v1}, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->getSlope(DI)D

    move-result-wide v9

    mul-double v7, v7, v9

    add-double/2addr v5, v7

    return-wide v5

    :cond_1
    aget-wide v6, v2, v5

    cmpg-double v4, p1, v6

    if-gtz v4, :cond_2

    iget-object v2, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v2, v2, v5

    aget-wide v4, v2, v1

    return-wide v4

    :cond_2
    add-int/lit8 v4, v3, -0x1

    aget-wide v4, v2, v4

    cmpl-double v2, p1, v4

    if-ltz v2, :cond_3

    iget-object v2, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    add-int/lit8 v4, v3, -0x1

    aget-object v2, v2, v4

    aget-wide v4, v2, v1

    return-wide v4

    :cond_3
    const/4 v2, 0x0

    :goto_0
    add-int/lit8 v4, v3, -0x1

    if-ge v2, v4, :cond_6

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    aget-wide v5, v4, v2

    cmpl-double v7, p1, v5

    if-nez v7, :cond_4

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v4, v4, v2

    aget-wide v5, v4, v1

    return-wide v5

    :cond_4
    add-int/lit8 v5, v2, 0x1

    aget-wide v5, v4, v5

    cmpg-double v7, p1, v5

    if-gez v7, :cond_5

    add-int/lit8 v5, v2, 0x1

    aget-wide v5, v4, v5

    aget-wide v7, v4, v2

    sub-double/2addr v5, v7

    aget-wide v7, v4, v2

    sub-double v7, p1, v7

    div-double/2addr v7, v5

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v9, v4, v2

    aget-wide v10, v9, v1

    add-int/lit8 v9, v2, 0x1

    aget-object v4, v4, v9

    aget-wide v12, v4, v1

    const-wide/high16 v14, 0x3ff0000000000000L    # 1.0

    sub-double/2addr v14, v7

    mul-double v14, v14, v10

    mul-double v16, v12, v7

    add-double v14, v14, v16

    return-wide v14

    :cond_5
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_6
    const-wide/16 v4, 0x0

    return-wide v4
.end method

.method public getPos(D[D)V
    .locals 17

    move-object/from16 v0, p0

    iget-object v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    array-length v2, v1

    iget-object v3, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    const/4 v4, 0x0

    aget-object v3, v3, v4

    array-length v3, v3

    iget-boolean v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mExtrapolate:Z

    if-eqz v5, :cond_3

    aget-wide v5, v1, v4

    cmpg-double v7, p1, v5

    if-gtz v7, :cond_1

    aget-wide v5, v1, v4

    iget-object v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mSlopeTemp:[D

    invoke-virtual {v0, v5, v6, v1}, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->getSlope(D[D)V

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v3, :cond_0

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v5, v5, v4

    aget-wide v6, v5, v1

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    aget-wide v8, v5, v4

    sub-double v8, p1, v8

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mSlopeTemp:[D

    aget-wide v10, v5, v1

    mul-double v8, v8, v10

    add-double/2addr v6, v8

    aput-wide v6, p3, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    return-void

    :cond_1
    add-int/lit8 v4, v2, -0x1

    aget-wide v4, v1, v4

    cmpl-double v6, p1, v4

    if-ltz v6, :cond_7

    add-int/lit8 v4, v2, -0x1

    aget-wide v4, v1, v4

    iget-object v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mSlopeTemp:[D

    invoke-virtual {v0, v4, v5, v1}, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->getSlope(D[D)V

    const/4 v1, 0x0

    :goto_1
    if-ge v1, v3, :cond_2

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    add-int/lit8 v5, v2, -0x1

    aget-object v4, v4, v5

    aget-wide v5, v4, v1

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    add-int/lit8 v7, v2, -0x1

    aget-wide v7, v4, v7

    sub-double v7, p1, v7

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mSlopeTemp:[D

    aget-wide v9, v4, v1

    mul-double v7, v7, v9

    add-double/2addr v5, v7

    aput-wide v5, p3, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    :cond_2
    return-void

    :cond_3
    aget-wide v5, v1, v4

    cmpg-double v7, p1, v5

    if-gtz v7, :cond_5

    const/4 v1, 0x0

    :goto_2
    if-ge v1, v3, :cond_4

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v5, v5, v4

    aget-wide v6, v5, v1

    aput-wide v6, p3, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_2

    :cond_4
    return-void

    :cond_5
    add-int/lit8 v4, v2, -0x1

    aget-wide v4, v1, v4

    cmpl-double v1, p1, v4

    if-ltz v1, :cond_7

    const/4 v1, 0x0

    :goto_3
    if-ge v1, v3, :cond_6

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    add-int/lit8 v5, v2, -0x1

    aget-object v4, v4, v5

    aget-wide v5, v4, v1

    aput-wide v5, p3, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_3

    :cond_6
    return-void

    :cond_7
    const/4 v1, 0x0

    :goto_4
    add-int/lit8 v4, v2, -0x1

    if-ge v1, v4, :cond_b

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    aget-wide v5, v4, v1

    cmpl-double v4, p1, v5

    if-nez v4, :cond_8

    const/4 v4, 0x0

    :goto_5
    if-ge v4, v3, :cond_8

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v5, v5, v1

    aget-wide v6, v5, v4

    aput-wide v6, p3, v4

    add-int/lit8 v4, v4, 0x1

    goto :goto_5

    :cond_8
    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    add-int/lit8 v5, v1, 0x1

    aget-wide v5, v4, v5

    cmpg-double v7, p1, v5

    if-gez v7, :cond_a

    add-int/lit8 v5, v1, 0x1

    aget-wide v5, v4, v5

    aget-wide v7, v4, v1

    sub-double/2addr v5, v7

    aget-wide v7, v4, v1

    sub-double v7, p1, v7

    div-double/2addr v7, v5

    const/4 v4, 0x0

    :goto_6
    if-ge v4, v3, :cond_9

    iget-object v9, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v10, v9, v1

    aget-wide v11, v10, v4

    add-int/lit8 v10, v1, 0x1

    aget-object v9, v9, v10

    aget-wide v13, v9, v4

    const-wide/high16 v9, 0x3ff0000000000000L    # 1.0

    sub-double/2addr v9, v7

    mul-double v9, v9, v11

    mul-double v15, v13, v7

    add-double/2addr v9, v15

    aput-wide v9, p3, v4

    add-int/lit8 v4, v4, 0x1

    goto :goto_6

    :cond_9
    return-void

    :cond_a
    add-int/lit8 v1, v1, 0x1

    goto :goto_4

    :cond_b
    return-void
.end method

.method public getPos(D[F)V
    .locals 17

    move-object/from16 v0, p0

    iget-object v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    array-length v2, v1

    iget-object v3, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    const/4 v4, 0x0

    aget-object v3, v3, v4

    array-length v3, v3

    iget-boolean v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mExtrapolate:Z

    if-eqz v5, :cond_3

    aget-wide v5, v1, v4

    cmpg-double v7, p1, v5

    if-gtz v7, :cond_1

    aget-wide v5, v1, v4

    iget-object v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mSlopeTemp:[D

    invoke-virtual {v0, v5, v6, v1}, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->getSlope(D[D)V

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v3, :cond_0

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v5, v5, v4

    aget-wide v6, v5, v1

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    aget-wide v8, v5, v4

    sub-double v8, p1, v8

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mSlopeTemp:[D

    aget-wide v10, v5, v1

    mul-double v8, v8, v10

    add-double/2addr v6, v8

    double-to-float v5, v6

    aput v5, p3, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    return-void

    :cond_1
    add-int/lit8 v4, v2, -0x1

    aget-wide v4, v1, v4

    cmpl-double v6, p1, v4

    if-ltz v6, :cond_7

    add-int/lit8 v4, v2, -0x1

    aget-wide v4, v1, v4

    iget-object v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mSlopeTemp:[D

    invoke-virtual {v0, v4, v5, v1}, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->getSlope(D[D)V

    const/4 v1, 0x0

    :goto_1
    if-ge v1, v3, :cond_2

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    add-int/lit8 v5, v2, -0x1

    aget-object v4, v4, v5

    aget-wide v5, v4, v1

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    add-int/lit8 v7, v2, -0x1

    aget-wide v7, v4, v7

    sub-double v7, p1, v7

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mSlopeTemp:[D

    aget-wide v9, v4, v1

    mul-double v7, v7, v9

    add-double/2addr v5, v7

    double-to-float v4, v5

    aput v4, p3, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    :cond_2
    return-void

    :cond_3
    aget-wide v5, v1, v4

    cmpg-double v7, p1, v5

    if-gtz v7, :cond_5

    const/4 v1, 0x0

    :goto_2
    if-ge v1, v3, :cond_4

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v5, v5, v4

    aget-wide v6, v5, v1

    double-to-float v5, v6

    aput v5, p3, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_2

    :cond_4
    return-void

    :cond_5
    add-int/lit8 v4, v2, -0x1

    aget-wide v4, v1, v4

    cmpl-double v1, p1, v4

    if-ltz v1, :cond_7

    const/4 v1, 0x0

    :goto_3
    if-ge v1, v3, :cond_6

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    add-int/lit8 v5, v2, -0x1

    aget-object v4, v4, v5

    aget-wide v5, v4, v1

    double-to-float v4, v5

    aput v4, p3, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_3

    :cond_6
    return-void

    :cond_7
    const/4 v1, 0x0

    :goto_4
    add-int/lit8 v4, v2, -0x1

    if-ge v1, v4, :cond_b

    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    aget-wide v5, v4, v1

    cmpl-double v4, p1, v5

    if-nez v4, :cond_8

    const/4 v4, 0x0

    :goto_5
    if-ge v4, v3, :cond_8

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v5, v5, v1

    aget-wide v6, v5, v4

    double-to-float v5, v6

    aput v5, p3, v4

    add-int/lit8 v4, v4, 0x1

    goto :goto_5

    :cond_8
    iget-object v4, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    add-int/lit8 v5, v1, 0x1

    aget-wide v5, v4, v5

    cmpg-double v7, p1, v5

    if-gez v7, :cond_a

    add-int/lit8 v5, v1, 0x1

    aget-wide v5, v4, v5

    aget-wide v7, v4, v1

    sub-double/2addr v5, v7

    aget-wide v7, v4, v1

    sub-double v7, p1, v7

    div-double/2addr v7, v5

    const/4 v4, 0x0

    :goto_6
    if-ge v4, v3, :cond_9

    iget-object v9, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v10, v9, v1

    aget-wide v11, v10, v4

    add-int/lit8 v10, v1, 0x1

    aget-object v9, v9, v10

    aget-wide v13, v9, v4

    const-wide/high16 v9, 0x3ff0000000000000L    # 1.0

    sub-double/2addr v9, v7

    mul-double v9, v9, v11

    mul-double v15, v13, v7

    add-double/2addr v9, v15

    double-to-float v9, v9

    aput v9, p3, v4

    add-int/lit8 v4, v4, 0x1

    goto :goto_6

    :cond_9
    return-void

    :cond_a
    add-int/lit8 v1, v1, 0x1

    goto :goto_4

    :cond_b
    return-void
.end method

.method public getSlope(DI)D
    .locals 17

    move-object/from16 v0, p0

    iget-object v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    array-length v2, v1

    const/4 v3, 0x0

    aget-wide v4, v1, v3

    cmpg-double v6, p1, v4

    if-gez v6, :cond_0

    aget-wide v3, v1, v3

    goto :goto_0

    :cond_0
    add-int/lit8 v3, v2, -0x1

    aget-wide v3, v1, v3

    cmpl-double v5, p1, v3

    if-ltz v5, :cond_1

    add-int/lit8 v3, v2, -0x1

    aget-wide v3, v1, v3

    goto :goto_0

    :cond_1
    move-wide/from16 v3, p1

    :goto_0
    const/4 v1, 0x0

    :goto_1
    add-int/lit8 v5, v2, -0x1

    if-ge v1, v5, :cond_3

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    add-int/lit8 v6, v1, 0x1

    aget-wide v6, v5, v6

    cmpg-double v8, v3, v6

    if-gtz v8, :cond_2

    add-int/lit8 v6, v1, 0x1

    aget-wide v6, v5, v6

    aget-wide v8, v5, v1

    sub-double/2addr v6, v8

    aget-wide v8, v5, v1

    sub-double v8, v3, v8

    div-double/2addr v8, v6

    iget-object v5, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v10, v5, v1

    aget-wide v11, v10, p3

    add-int/lit8 v10, v1, 0x1

    aget-object v5, v5, v10

    aget-wide v13, v5, p3

    sub-double v15, v13, v11

    div-double/2addr v15, v6

    return-wide v15

    :cond_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    :cond_3
    const-wide/16 v5, 0x0

    return-wide v5
.end method

.method public getSlope(D[D)V
    .locals 17

    move-object/from16 v0, p0

    iget-object v1, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    array-length v2, v1

    iget-object v3, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    const/4 v4, 0x0

    aget-object v3, v3, v4

    array-length v3, v3

    aget-wide v5, v1, v4

    cmpg-double v7, p1, v5

    if-gtz v7, :cond_0

    aget-wide v4, v1, v4

    goto :goto_0

    :cond_0
    add-int/lit8 v4, v2, -0x1

    aget-wide v4, v1, v4

    cmpl-double v6, p1, v4

    if-ltz v6, :cond_1

    add-int/lit8 v4, v2, -0x1

    aget-wide v4, v1, v4

    goto :goto_0

    :cond_1
    move-wide/from16 v4, p1

    :goto_0
    const/4 v1, 0x0

    :goto_1
    add-int/lit8 v6, v2, -0x1

    if-ge v1, v6, :cond_4

    iget-object v6, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    add-int/lit8 v7, v1, 0x1

    aget-wide v7, v6, v7

    cmpg-double v9, v4, v7

    if-gtz v9, :cond_3

    add-int/lit8 v7, v1, 0x1

    aget-wide v7, v6, v7

    aget-wide v9, v6, v1

    sub-double/2addr v7, v9

    aget-wide v9, v6, v1

    sub-double v9, v4, v9

    div-double/2addr v9, v7

    const/4 v6, 0x0

    :goto_2
    if-ge v6, v3, :cond_2

    iget-object v11, v0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mY:[[D

    aget-object v12, v11, v1

    aget-wide v13, v12, v6

    add-int/lit8 v12, v1, 0x1

    aget-object v11, v11, v12

    aget-wide v15, v11, v6

    sub-double v11, v15, v13

    div-double/2addr v11, v7

    aput-wide v11, p3, v6

    add-int/lit8 v6, v6, 0x1

    goto :goto_2

    :cond_2
    goto :goto_3

    :cond_3
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    :cond_4
    :goto_3
    return-void
.end method

.method public getTimePoints()[D
    .locals 1

    iget-object v0, p0, Landroidx/constraintlayout/core/motion/utils/LinearCurveFit;->mT:[D

    return-object v0
.end method
