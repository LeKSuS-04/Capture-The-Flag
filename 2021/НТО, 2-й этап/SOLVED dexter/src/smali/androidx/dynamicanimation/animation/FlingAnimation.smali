.class public final Landroidx/dynamicanimation/animation/FlingAnimation;
.super Landroidx/dynamicanimation/animation/DynamicAnimation;
.source "FlingAnimation.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroidx/dynamicanimation/animation/DynamicAnimation<",
        "Landroidx/dynamicanimation/animation/FlingAnimation;",
        ">;"
    }
.end annotation


# instance fields
.field private final mFlingForce:Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;


# direct methods
.method public constructor <init>(Landroidx/dynamicanimation/animation/FloatValueHolder;)V
    .locals 2

    invoke-direct {p0, p1}, Landroidx/dynamicanimation/animation/DynamicAnimation;-><init>(Landroidx/dynamicanimation/animation/FloatValueHolder;)V

    new-instance v0, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;

    invoke-direct {v0}, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;-><init>()V

    iput-object v0, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mFlingForce:Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;

    invoke-virtual {p0}, Landroidx/dynamicanimation/animation/FlingAnimation;->getValueThreshold()F

    move-result v1

    invoke-virtual {v0, v1}, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;->setValueThreshold(F)V

    return-void
.end method

.method public constructor <init>(Ljava/lang/Object;Landroidx/dynamicanimation/animation/FloatPropertyCompat;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<K:",
            "Ljava/lang/Object;",
            ">(TK;",
            "Landroidx/dynamicanimation/animation/FloatPropertyCompat<",
            "TK;>;)V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Landroidx/dynamicanimation/animation/DynamicAnimation;-><init>(Ljava/lang/Object;Landroidx/dynamicanimation/animation/FloatPropertyCompat;)V

    new-instance v0, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;

    invoke-direct {v0}, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;-><init>()V

    iput-object v0, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mFlingForce:Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;

    invoke-virtual {p0}, Landroidx/dynamicanimation/animation/FlingAnimation;->getValueThreshold()F

    move-result v1

    invoke-virtual {v0, v1}, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;->setValueThreshold(F)V

    return-void
.end method


# virtual methods
.method getAcceleration(FF)F
    .locals 1

    iget-object v0, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mFlingForce:Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;

    invoke-virtual {v0, p1, p2}, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;->getAcceleration(FF)F

    move-result v0

    return v0
.end method

.method public getFriction()F
    .locals 1

    iget-object v0, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mFlingForce:Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;

    invoke-virtual {v0}, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;->getFrictionScalar()F

    move-result v0

    return v0
.end method

.method isAtEquilibrium(FF)Z
    .locals 1

    iget v0, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mMaxValue:F

    cmpl-float v0, p1, v0

    if-gez v0, :cond_1

    iget v0, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mMinValue:F

    cmpg-float v0, p1, v0

    if-lez v0, :cond_1

    iget-object v0, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mFlingForce:Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;

    invoke-virtual {v0, p1, p2}, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;->isAtEquilibrium(FF)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    :goto_1
    return v0
.end method

.method public setFriction(F)Landroidx/dynamicanimation/animation/FlingAnimation;
    .locals 2

    const/4 v0, 0x0

    cmpg-float v0, p1, v0

    if-lez v0, :cond_0

    iget-object v0, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mFlingForce:Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;

    invoke-virtual {v0, p1}, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;->setFrictionScalar(F)V

    return-object p0

    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Friction must be positive"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public bridge synthetic setMaxValue(F)Landroidx/dynamicanimation/animation/DynamicAnimation;
    .locals 0

    invoke-virtual {p0, p1}, Landroidx/dynamicanimation/animation/FlingAnimation;->setMaxValue(F)Landroidx/dynamicanimation/animation/FlingAnimation;

    move-result-object p1

    return-object p1
.end method

.method public setMaxValue(F)Landroidx/dynamicanimation/animation/FlingAnimation;
    .locals 0

    invoke-super {p0, p1}, Landroidx/dynamicanimation/animation/DynamicAnimation;->setMaxValue(F)Landroidx/dynamicanimation/animation/DynamicAnimation;

    return-object p0
.end method

.method public bridge synthetic setMinValue(F)Landroidx/dynamicanimation/animation/DynamicAnimation;
    .locals 0

    invoke-virtual {p0, p1}, Landroidx/dynamicanimation/animation/FlingAnimation;->setMinValue(F)Landroidx/dynamicanimation/animation/FlingAnimation;

    move-result-object p1

    return-object p1
.end method

.method public setMinValue(F)Landroidx/dynamicanimation/animation/FlingAnimation;
    .locals 0

    invoke-super {p0, p1}, Landroidx/dynamicanimation/animation/DynamicAnimation;->setMinValue(F)Landroidx/dynamicanimation/animation/DynamicAnimation;

    return-object p0
.end method

.method public bridge synthetic setStartVelocity(F)Landroidx/dynamicanimation/animation/DynamicAnimation;
    .locals 0

    invoke-virtual {p0, p1}, Landroidx/dynamicanimation/animation/FlingAnimation;->setStartVelocity(F)Landroidx/dynamicanimation/animation/FlingAnimation;

    move-result-object p1

    return-object p1
.end method

.method public setStartVelocity(F)Landroidx/dynamicanimation/animation/FlingAnimation;
    .locals 0

    invoke-super {p0, p1}, Landroidx/dynamicanimation/animation/DynamicAnimation;->setStartVelocity(F)Landroidx/dynamicanimation/animation/DynamicAnimation;

    return-object p0
.end method

.method setValueThreshold(F)V
    .locals 1

    iget-object v0, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mFlingForce:Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;

    invoke-virtual {v0, p1}, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;->setValueThreshold(F)V

    return-void
.end method

.method updateValueAndVelocity(J)Z
    .locals 4

    iget-object v0, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mFlingForce:Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;

    iget v1, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mValue:F

    iget v2, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mVelocity:F

    invoke-virtual {v0, v1, v2, p1, p2}, Landroidx/dynamicanimation/animation/FlingAnimation$DragForce;->updateValueAndVelocity(FFJ)Landroidx/dynamicanimation/animation/DynamicAnimation$MassState;

    move-result-object v0

    iget v1, v0, Landroidx/dynamicanimation/animation/DynamicAnimation$MassState;->mValue:F

    iput v1, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mValue:F

    iget v1, v0, Landroidx/dynamicanimation/animation/DynamicAnimation$MassState;->mVelocity:F

    iput v1, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mVelocity:F

    iget v1, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mValue:F

    iget v2, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mMinValue:F

    const/4 v3, 0x1

    cmpg-float v1, v1, v2

    if-gez v1, :cond_0

    iget v1, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mMinValue:F

    iput v1, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mValue:F

    return v3

    :cond_0
    iget v1, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mValue:F

    iget v2, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mMaxValue:F

    cmpl-float v1, v1, v2

    if-lez v1, :cond_1

    iget v1, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mMaxValue:F

    iput v1, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mValue:F

    return v3

    :cond_1
    iget v1, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mValue:F

    iget v2, p0, Landroidx/dynamicanimation/animation/FlingAnimation;->mVelocity:F

    invoke-virtual {p0, v1, v2}, Landroidx/dynamicanimation/animation/FlingAnimation;->isAtEquilibrium(FF)Z

    move-result v1

    if-eqz v1, :cond_2

    return v3

    :cond_2
    const/4 v1, 0x0

    return v1
.end method
