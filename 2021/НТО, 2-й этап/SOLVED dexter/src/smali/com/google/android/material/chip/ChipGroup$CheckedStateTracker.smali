.class Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;
.super Ljava/lang/Object;
.source "ChipGroup.java"

# interfaces
.implements Landroid/widget/CompoundButton$OnCheckedChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/android/material/chip/ChipGroup;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "CheckedStateTracker"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/google/android/material/chip/ChipGroup;


# direct methods
.method private constructor <init>(Lcom/google/android/material/chip/ChipGroup;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/google/android/material/chip/ChipGroup;Lcom/google/android/material/chip/ChipGroup$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;-><init>(Lcom/google/android/material/chip/ChipGroup;)V

    return-void
.end method


# virtual methods
.method public onCheckedChanged(Landroid/widget/CompoundButton;Z)V
    .locals 5

    iget-object v0, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-static {v0}, Lcom/google/android/material/chip/ChipGroup;->access$300(Lcom/google/android/material/chip/ChipGroup;)Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-virtual {v0}, Lcom/google/android/material/chip/ChipGroup;->getCheckedChipIds()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-static {v1}, Lcom/google/android/material/chip/ChipGroup;->access$400(Lcom/google/android/material/chip/ChipGroup;)Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-virtual {p1}, Landroid/widget/CompoundButton;->getId()I

    move-result v3

    const/4 v4, 0x1

    invoke-static {v1, v3, v4}, Lcom/google/android/material/chip/ChipGroup;->access$500(Lcom/google/android/material/chip/ChipGroup;IZ)V

    iget-object v1, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-virtual {p1}, Landroid/widget/CompoundButton;->getId()I

    move-result v3

    invoke-static {v1, v3, v2}, Lcom/google/android/material/chip/ChipGroup;->access$600(Lcom/google/android/material/chip/ChipGroup;IZ)V

    return-void

    :cond_1
    invoke-virtual {p1}, Landroid/widget/CompoundButton;->getId()I

    move-result v1

    const/4 v3, -0x1

    if-eqz p2, :cond_3

    iget-object v4, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-static {v4}, Lcom/google/android/material/chip/ChipGroup;->access$700(Lcom/google/android/material/chip/ChipGroup;)I

    move-result v4

    if-eq v4, v3, :cond_2

    iget-object v3, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-static {v3}, Lcom/google/android/material/chip/ChipGroup;->access$700(Lcom/google/android/material/chip/ChipGroup;)I

    move-result v3

    if-eq v3, v1, :cond_2

    iget-object v3, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-static {v3}, Lcom/google/android/material/chip/ChipGroup;->access$800(Lcom/google/android/material/chip/ChipGroup;)Z

    move-result v3

    if-eqz v3, :cond_2

    iget-object v3, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-static {v3}, Lcom/google/android/material/chip/ChipGroup;->access$700(Lcom/google/android/material/chip/ChipGroup;)I

    move-result v4

    invoke-static {v3, v4, v2}, Lcom/google/android/material/chip/ChipGroup;->access$500(Lcom/google/android/material/chip/ChipGroup;IZ)V

    :cond_2
    iget-object v2, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-static {v2, v1}, Lcom/google/android/material/chip/ChipGroup;->access$900(Lcom/google/android/material/chip/ChipGroup;I)V

    goto :goto_0

    :cond_3
    iget-object v2, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-static {v2}, Lcom/google/android/material/chip/ChipGroup;->access$700(Lcom/google/android/material/chip/ChipGroup;)I

    move-result v2

    if-ne v2, v1, :cond_4

    iget-object v2, p0, Lcom/google/android/material/chip/ChipGroup$CheckedStateTracker;->this$0:Lcom/google/android/material/chip/ChipGroup;

    invoke-static {v2, v3}, Lcom/google/android/material/chip/ChipGroup;->access$900(Lcom/google/android/material/chip/ChipGroup;I)V

    :cond_4
    :goto_0
    return-void
.end method
