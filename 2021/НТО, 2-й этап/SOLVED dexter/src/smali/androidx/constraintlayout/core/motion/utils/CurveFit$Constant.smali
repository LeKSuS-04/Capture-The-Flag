.class Landroidx/constraintlayout/core/motion/utils/CurveFit$Constant;
.super Landroidx/constraintlayout/core/motion/utils/CurveFit;
.source "CurveFit.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/constraintlayout/core/motion/utils/CurveFit;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "Constant"
.end annotation


# instance fields
.field mTime:D

.field mValue:[D


# direct methods
.method constructor <init>(D[D)V
    .locals 0

    invoke-direct {p0}, Landroidx/constraintlayout/core/motion/utils/CurveFit;-><init>()V

    iput-wide p1, p0, Landroidx/constraintlayout/core/motion/utils/CurveFit$Constant;->mTime:D

    iput-object p3, p0, Landroidx/constraintlayout/core/motion/utils/CurveFit$Constant;->mValue:[D

    return-void
.end method


# virtual methods
.method public getPos(DI)D
    .locals 3

    iget-object v0, p0, Landroidx/constraintlayout/core/motion/utils/CurveFit$Constant;->mValue:[D

    aget-wide v1, v0, p3

    return-wide v1
.end method

.method public getPos(D[D)V
    .locals 3

    iget-object v0, p0, Landroidx/constraintlayout/core/motion/utils/CurveFit$Constant;->mValue:[D

    array-length v1, v0

    const/4 v2, 0x0

    invoke-static {v0, v2, p3, v2, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    return-void
.end method

.method public getPos(D[F)V
    .locals 4

    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Landroidx/constraintlayout/core/motion/utils/CurveFit$Constant;->mValue:[D

    array-length v2, v1

    if-ge v0, v2, :cond_0

    aget-wide v2, v1, v0

    double-to-float v1, v2

    aput v1, p3, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method public getSlope(DI)D
    .locals 2

    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method public getSlope(D[D)V
    .locals 3

    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Landroidx/constraintlayout/core/motion/utils/CurveFit$Constant;->mValue:[D

    array-length v1, v1

    if-ge v0, v1, :cond_0

    const-wide/16 v1, 0x0

    aput-wide v1, p3, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method public getTimePoints()[D
    .locals 4

    const/4 v0, 0x1

    new-array v0, v0, [D

    iget-wide v1, p0, Landroidx/constraintlayout/core/motion/utils/CurveFit$Constant;->mTime:D

    const/4 v3, 0x0

    aput-wide v1, v0, v3

    return-object v0
.end method
