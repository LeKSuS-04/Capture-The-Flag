.class final Lcom/google/android/material/transition/FadeThroughProvider$2;
.super Landroid/animation/AnimatorListenerAdapter;
.source "FadeThroughProvider.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/android/material/transition/FadeThroughProvider;->createFadeThroughAnimator(Landroid/view/View;FFFFF)Landroid/animation/Animator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation


# instance fields
.field final synthetic val$originalAlpha:F

.field final synthetic val$view:Landroid/view/View;


# direct methods
.method constructor <init>(Landroid/view/View;F)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/material/transition/FadeThroughProvider$2;->val$view:Landroid/view/View;

    iput p2, p0, Lcom/google/android/material/transition/FadeThroughProvider$2;->val$originalAlpha:F

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2

    iget-object v0, p0, Lcom/google/android/material/transition/FadeThroughProvider$2;->val$view:Landroid/view/View;

    iget v1, p0, Lcom/google/android/material/transition/FadeThroughProvider$2;->val$originalAlpha:F

    invoke-virtual {v0, v1}, Landroid/view/View;->setAlpha(F)V

    return-void
.end method
