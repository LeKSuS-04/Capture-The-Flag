.class public Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;
.super Ljava/lang/Object;
.source "SpringStopEngine.java"

# interfaces
.implements Landroidx/constraintlayout/core/motion/utils/StopEngine;


# static fields
.field private static final UNSET:D = 1.7976931348623157E308


# instance fields
.field private mBoundaryMode:I

.field mDamping:D

.field private mInitialized:Z

.field private mLastTime:F

.field private mLastVelocity:D

.field private mMass:F

.field private mPos:F

.field private mStiffness:D

.field private mStopThreshold:F

.field private mTargetPos:D

.field private mV:F


# direct methods
.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-wide/high16 v0, 0x3fe0000000000000L    # 0.5

    iput-wide v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mDamping:D

    const/4 v0, 0x0

    iput-boolean v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mInitialized:Z

    iput v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mBoundaryMode:I

    return-void
.end method

.method private compute(D)V
    .locals 25

    move-object/from16 v0, p0

    iget-wide v1, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mStiffness:D

    iget-wide v3, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mDamping:D

    iget-wide v5, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mStiffness:D

    iget v7, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mMass:F

    float-to-double v7, v7

    div-double/2addr v5, v7

    invoke-static {v5, v6}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v5

    mul-double v5, v5, p1

    const-wide/high16 v7, 0x4010000000000000L    # 4.0

    mul-double v5, v5, v7

    const-wide/high16 v7, 0x4022000000000000L    # 9.0

    div-double/2addr v7, v5

    const-wide/high16 v5, 0x3ff0000000000000L    # 1.0

    add-double/2addr v7, v5

    double-to-int v5, v7

    int-to-double v6, v5

    div-double v6, p1, v6

    const/4 v8, 0x0

    :goto_0
    if-ge v8, v5, :cond_2

    iget v9, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mPos:F

    float-to-double v10, v9

    iget-wide v12, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mTargetPos:D

    sub-double/2addr v10, v12

    neg-double v14, v1

    mul-double v14, v14, v10

    move/from16 v16, v5

    iget v5, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mV:F

    move-wide/from16 p1, v10

    float-to-double v10, v5

    mul-double v10, v10, v3

    sub-double/2addr v14, v10

    iget v10, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mMass:F

    move-wide/from16 v17, v3

    float-to-double v3, v10

    div-double/2addr v14, v3

    float-to-double v3, v5

    mul-double v19, v14, v6

    const-wide/high16 v21, 0x4000000000000000L    # 2.0

    div-double v19, v19, v21

    add-double v3, v3, v19

    move-wide/from16 v19, v14

    float-to-double v14, v9

    mul-double v23, v6, v3

    div-double v23, v23, v21

    add-double v14, v14, v23

    sub-double/2addr v14, v12

    neg-double v11, v14

    mul-double v11, v11, v1

    mul-double v23, v3, v17

    sub-double v11, v11, v23

    move-wide/from16 v23, v1

    float-to-double v1, v10

    div-double/2addr v11, v1

    mul-double v1, v11, v6

    move-wide/from16 v19, v3

    float-to-double v3, v5

    div-double v21, v1, v21

    add-double v3, v3, v21

    move-wide/from16 v19, v11

    float-to-double v10, v5

    add-double/2addr v10, v1

    double-to-float v5, v10

    iput v5, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mV:F

    float-to-double v9, v9

    mul-double v11, v3, v6

    add-double/2addr v9, v11

    double-to-float v9, v9

    iput v9, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mPos:F

    iget v10, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mBoundaryMode:I

    if-lez v10, :cond_1

    const/4 v11, 0x0

    cmpg-float v11, v9, v11

    if-gez v11, :cond_0

    and-int/lit8 v11, v10, 0x1

    const/4 v12, 0x1

    if-ne v11, v12, :cond_0

    neg-float v9, v9

    iput v9, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mPos:F

    neg-float v5, v5

    iput v5, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mV:F

    :cond_0
    iget v5, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mPos:F

    const/high16 v9, 0x3f800000    # 1.0f

    cmpl-float v9, v5, v9

    if-lez v9, :cond_1

    and-int/lit8 v9, v10, 0x2

    const/4 v10, 0x2

    if-ne v9, v10, :cond_1

    const/high16 v9, 0x40000000    # 2.0f

    sub-float/2addr v9, v5

    iput v9, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mPos:F

    iget v5, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mV:F

    neg-float v5, v5

    iput v5, v0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mV:F

    :cond_1
    add-int/lit8 v8, v8, 0x1

    move/from16 v5, v16

    move-wide/from16 v3, v17

    move-wide/from16 v1, v23

    goto/16 :goto_0

    :cond_2
    return-void
.end method


# virtual methods
.method public debug(Ljava/lang/String;F)Ljava/lang/String;
    .locals 1

    const/4 v0, 0x0

    return-object v0
.end method

.method public getAcceleration()F
    .locals 10

    iget-wide v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mStiffness:D

    iget-wide v2, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mDamping:D

    iget v4, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mPos:F

    float-to-double v4, v4

    iget-wide v6, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mTargetPos:D

    sub-double/2addr v4, v6

    neg-double v6, v0

    mul-double v6, v6, v4

    iget v8, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mV:F

    float-to-double v8, v8

    mul-double v8, v8, v2

    sub-double/2addr v6, v8

    double-to-float v6, v6

    iget v7, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mMass:F

    div-float/2addr v6, v7

    return v6
.end method

.method public getInterpolation(F)F
    .locals 2

    iget v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mLastTime:F

    sub-float v0, p1, v0

    float-to-double v0, v0

    invoke-direct {p0, v0, v1}, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->compute(D)V

    iput p1, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mLastTime:F

    iget v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mPos:F

    return v0
.end method

.method public getVelocity()F
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public getVelocity(F)F
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mV:F

    return v0
.end method

.method public isStopped()Z
    .locals 15

    iget v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mPos:F

    float-to-double v0, v0

    iget-wide v2, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mTargetPos:D

    sub-double/2addr v0, v2

    iget-wide v2, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mStiffness:D

    iget v4, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mV:F

    float-to-double v4, v4

    iget v6, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mMass:F

    float-to-double v6, v6

    mul-double v8, v4, v4

    mul-double v8, v8, v6

    mul-double v10, v2, v0

    mul-double v10, v10, v0

    add-double/2addr v8, v10

    div-double v10, v8, v2

    invoke-static {v10, v11}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v10

    iget v12, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mStopThreshold:F

    float-to-double v12, v12

    cmpg-double v14, v10, v12

    if-gtz v14, :cond_0

    const/4 v12, 0x1

    goto :goto_0

    :cond_0
    const/4 v12, 0x0

    :goto_0
    return v12
.end method

.method log(Ljava/lang/String;)V
    .locals 4

    new-instance v0, Ljava/lang/Throwable;

    invoke-direct {v0}, Ljava/lang/Throwable;-><init>()V

    invoke-virtual {v0}, Ljava/lang/Throwable;->getStackTrace()[Ljava/lang/StackTraceElement;

    move-result-object v0

    const/4 v1, 0x1

    aget-object v0, v0, v1

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, ".("

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StackTraceElement;->getFileName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ":"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StackTraceElement;->getLineNumber()I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, ") "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StackTraceElement;->getMethodName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "() "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    sget-object v2, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    return-void
.end method

.method public springConfig(FFFFFFFI)V
    .locals 2

    float-to-double v0, p2

    iput-wide v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mTargetPos:D

    float-to-double v0, p6

    iput-wide v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mDamping:D

    const/4 v0, 0x0

    iput-boolean v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mInitialized:Z

    iput p1, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mPos:F

    float-to-double v0, p3

    iput-wide v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mLastVelocity:D

    float-to-double v0, p5

    iput-wide v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mStiffness:D

    iput p4, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mMass:F

    iput p7, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mStopThreshold:F

    iput p8, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mBoundaryMode:I

    const/4 v0, 0x0

    iput v0, p0, Landroidx/constraintlayout/core/motion/utils/SpringStopEngine;->mLastTime:F

    return-void
.end method
