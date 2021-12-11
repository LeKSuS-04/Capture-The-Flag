.class Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;
.super Landroidx/customview/widget/ViewDragHelper$Callback;
.source "BottomSheetBehavior.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/android/material/bottomsheet/BottomSheetBehavior;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;


# direct methods
.method constructor <init>(Lcom/google/android/material/bottomsheet/BottomSheetBehavior;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-direct {p0}, Landroidx/customview/widget/ViewDragHelper$Callback;-><init>()V

    return-void
.end method

.method private releasedLow(Landroid/view/View;)Z
    .locals 3

    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v0

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->parentHeight:I

    iget-object v2, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-virtual {v2}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->getExpandedOffset()I

    move-result v2

    add-int/2addr v1, v2

    div-int/lit8 v1, v1, 0x2

    if-le v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method


# virtual methods
.method public clampViewPositionHorizontal(Landroid/view/View;II)I
    .locals 1

    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v0

    return v0
.end method

.method public clampViewPositionVertical(Landroid/view/View;II)I
    .locals 2

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-virtual {v0}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->getExpandedOffset()I

    move-result v0

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget-boolean v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->hideable:Z

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->parentHeight:I

    goto :goto_0

    :cond_0
    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->collapsedOffset:I

    :goto_0
    invoke-static {p2, v0, v1}, Landroidx/core/math/MathUtils;->clamp(III)I

    move-result v0

    return v0
.end method

.method public getViewVerticalDragRange(Landroid/view/View;)I
    .locals 1

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget-boolean v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->hideable:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->parentHeight:I

    return v0

    :cond_0
    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->collapsedOffset:I

    return v0
.end method

.method public onViewDragStateChanged(I)V
    .locals 2

    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-static {v1}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->access$900(Lcom/google/android/material/bottomsheet/BottomSheetBehavior;)Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-virtual {v1, v0}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->setStateInternal(I)V

    :cond_0
    return-void
.end method

.method public onViewPositionChanged(Landroid/view/View;IIII)V
    .locals 1

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-virtual {v0, p3}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->dispatchOnSlide(I)V

    return-void
.end method

.method public onViewReleased(Landroid/view/View;FF)V
    .locals 4

    const/4 v0, 0x0

    cmpg-float v1, p3, v0

    if-gez v1, :cond_2

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-static {v0}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->access$1000(Lcom/google/android/material/bottomsheet/BottomSheetBehavior;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->fitToContentsOffset:I

    const/4 v1, 0x3

    goto/16 :goto_3

    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v0

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->halfExpandedOffset:I

    if-le v0, v1, :cond_1

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->halfExpandedOffset:I

    const/4 v2, 0x6

    move v0, v1

    move v1, v2

    goto :goto_0

    :cond_1
    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-virtual {v1}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->getExpandedOffset()I

    move-result v1

    const/4 v2, 0x3

    move v0, v1

    move v1, v2

    :goto_0
    goto/16 :goto_3

    :cond_2
    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget-boolean v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->hideable:Z

    if-eqz v1, :cond_8

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-virtual {v1, p1, p3}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->shouldHide(Landroid/view/View;F)Z

    move-result v1

    if-eqz v1, :cond_8

    invoke-static {p2}, Ljava/lang/Math;->abs(F)F

    move-result v0

    invoke-static {p3}, Ljava/lang/Math;->abs(F)F

    move-result v1

    cmpg-float v0, v0, v1

    if-gez v0, :cond_3

    const/high16 v0, 0x43fa0000    # 500.0f

    cmpl-float v0, p3, v0

    if-gtz v0, :cond_4

    :cond_3
    invoke-direct {p0, p1}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->releasedLow(Landroid/view/View;)Z

    move-result v0

    if-eqz v0, :cond_5

    :cond_4
    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->parentHeight:I

    const/4 v1, 0x5

    goto/16 :goto_3

    :cond_5
    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-static {v0}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->access$1000(Lcom/google/android/material/bottomsheet/BottomSheetBehavior;)Z

    move-result v0

    if-eqz v0, :cond_6

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->fitToContentsOffset:I

    const/4 v1, 0x3

    goto/16 :goto_3

    :cond_6
    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v0

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-virtual {v1}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->getExpandedOffset()I

    move-result v1

    sub-int/2addr v0, v1

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v0

    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v1

    iget-object v2, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v2, v2, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->halfExpandedOffset:I

    sub-int/2addr v1, v2

    invoke-static {v1}, Ljava/lang/Math;->abs(I)I

    move-result v1

    if-ge v0, v1, :cond_7

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-virtual {v0}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->getExpandedOffset()I

    move-result v0

    const/4 v1, 0x3

    goto/16 :goto_3

    :cond_7
    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->halfExpandedOffset:I

    const/4 v1, 0x6

    goto/16 :goto_3

    :cond_8
    cmpl-float v0, p3, v0

    if-eqz v0, :cond_c

    invoke-static {p2}, Ljava/lang/Math;->abs(F)F

    move-result v0

    invoke-static {p3}, Ljava/lang/Math;->abs(F)F

    move-result v1

    cmpl-float v0, v0, v1

    if-lez v0, :cond_9

    goto :goto_1

    :cond_9
    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-static {v0}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->access$1000(Lcom/google/android/material/bottomsheet/BottomSheetBehavior;)Z

    move-result v0

    if-eqz v0, :cond_a

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->collapsedOffset:I

    const/4 v1, 0x4

    goto/16 :goto_3

    :cond_a
    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v0

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->halfExpandedOffset:I

    sub-int v1, v0, v1

    invoke-static {v1}, Ljava/lang/Math;->abs(I)I

    move-result v1

    iget-object v2, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v2, v2, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->collapsedOffset:I

    sub-int v2, v0, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v2

    if-ge v1, v2, :cond_b

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->halfExpandedOffset:I

    const/4 v2, 0x6

    move v0, v1

    move v1, v2

    goto/16 :goto_3

    :cond_b
    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->collapsedOffset:I

    const/4 v2, 0x4

    move v0, v1

    move v1, v2

    goto/16 :goto_3

    :cond_c
    :goto_1
    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v0

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-static {v1}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->access$1000(Lcom/google/android/material/bottomsheet/BottomSheetBehavior;)Z

    move-result v1

    if-eqz v1, :cond_e

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->fitToContentsOffset:I

    sub-int v1, v0, v1

    invoke-static {v1}, Ljava/lang/Math;->abs(I)I

    move-result v1

    iget-object v2, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v2, v2, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->collapsedOffset:I

    sub-int v2, v0, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v2

    if-ge v1, v2, :cond_d

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->fitToContentsOffset:I

    const/4 v2, 0x3

    move v0, v1

    move v1, v2

    goto :goto_2

    :cond_d
    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->collapsedOffset:I

    const/4 v2, 0x4

    move v0, v1

    move v1, v2

    goto :goto_2

    :cond_e
    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->halfExpandedOffset:I

    if-ge v0, v1, :cond_10

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->collapsedOffset:I

    sub-int v1, v0, v1

    invoke-static {v1}, Ljava/lang/Math;->abs(I)I

    move-result v1

    if-ge v0, v1, :cond_f

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    invoke-virtual {v1}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->getExpandedOffset()I

    move-result v1

    const/4 v2, 0x3

    move v0, v1

    move v1, v2

    goto :goto_2

    :cond_f
    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->halfExpandedOffset:I

    const/4 v2, 0x6

    move v0, v1

    move v1, v2

    goto :goto_2

    :cond_10
    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->halfExpandedOffset:I

    sub-int v1, v0, v1

    invoke-static {v1}, Ljava/lang/Math;->abs(I)I

    move-result v1

    iget-object v2, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v2, v2, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->collapsedOffset:I

    sub-int v2, v0, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v2

    if-ge v1, v2, :cond_11

    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->halfExpandedOffset:I

    const/4 v2, 0x6

    move v0, v1

    move v1, v2

    goto :goto_2

    :cond_11
    iget-object v1, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v1, v1, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->collapsedOffset:I

    const/4 v2, 0x4

    move v0, v1

    move v1, v2

    :goto_2
    nop

    :goto_3
    iget-object v2, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    const/4 v3, 0x1

    invoke-virtual {v2, p1, v1, v0, v3}, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->startSettlingAnimation(Landroid/view/View;IIZ)V

    return-void
.end method

.method public tryCaptureView(Landroid/view/View;I)Z
    .locals 4

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->state:I

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-ne v0, v1, :cond_0

    return v2

    :cond_0
    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget-boolean v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->touchingScrollingChild:Z

    if-eqz v0, :cond_1

    return v2

    :cond_1
    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->state:I

    const/4 v3, 0x3

    if-ne v0, v3, :cond_3

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->activePointerId:I

    if-ne v0, p2, :cond_3

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget-object v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->nestedScrollingChildRef:Ljava/lang/ref/WeakReference;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget-object v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->nestedScrollingChildRef:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    goto :goto_0

    :cond_2
    const/4 v0, 0x0

    :goto_0
    if-eqz v0, :cond_3

    const/4 v3, -0x1

    invoke-virtual {v0, v3}, Landroid/view/View;->canScrollVertically(I)Z

    move-result v3

    if-eqz v3, :cond_3

    return v2

    :cond_3
    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget-object v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->viewRef:Ljava/lang/ref/WeakReference;

    if-eqz v0, :cond_4

    iget-object v0, p0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior$5;->this$0:Lcom/google/android/material/bottomsheet/BottomSheetBehavior;

    iget-object v0, v0, Lcom/google/android/material/bottomsheet/BottomSheetBehavior;->viewRef:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    if-ne v0, p1, :cond_4

    goto :goto_1

    :cond_4
    const/4 v1, 0x0

    :goto_1
    return v1
.end method
