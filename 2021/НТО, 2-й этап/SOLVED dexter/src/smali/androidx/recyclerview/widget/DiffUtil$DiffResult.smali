.class public Landroidx/recyclerview/widget/DiffUtil$DiffResult;
.super Ljava/lang/Object;
.source "DiffUtil.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/recyclerview/widget/DiffUtil;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "DiffResult"
.end annotation


# static fields
.field private static final FLAG_CHANGED:I = 0x2

.field private static final FLAG_IGNORE:I = 0x10

.field private static final FLAG_MASK:I = 0x1f

.field private static final FLAG_MOVED_CHANGED:I = 0x4

.field private static final FLAG_MOVED_NOT_CHANGED:I = 0x8

.field private static final FLAG_NOT_CHANGED:I = 0x1

.field private static final FLAG_OFFSET:I = 0x5

.field public static final NO_POSITION:I = -0x1


# instance fields
.field private final mCallback:Landroidx/recyclerview/widget/DiffUtil$Callback;

.field private final mDetectMoves:Z

.field private final mNewItemStatuses:[I

.field private final mNewListSize:I

.field private final mOldItemStatuses:[I

.field private final mOldListSize:I

.field private final mSnakes:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroidx/recyclerview/widget/DiffUtil$Snake;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Landroidx/recyclerview/widget/DiffUtil$Callback;Ljava/util/List;[I[IZ)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroidx/recyclerview/widget/DiffUtil$Callback;",
            "Ljava/util/List<",
            "Landroidx/recyclerview/widget/DiffUtil$Snake;",
            ">;[I[IZ)V"
        }
    .end annotation

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mSnakes:Ljava/util/List;

    iput-object p3, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldItemStatuses:[I

    iput-object p4, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewItemStatuses:[I

    const/4 v0, 0x0

    invoke-static {p3, v0}, Ljava/util/Arrays;->fill([II)V

    invoke-static {p4, v0}, Ljava/util/Arrays;->fill([II)V

    iput-object p1, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mCallback:Landroidx/recyclerview/widget/DiffUtil$Callback;

    invoke-virtual {p1}, Landroidx/recyclerview/widget/DiffUtil$Callback;->getOldListSize()I

    move-result v0

    iput v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldListSize:I

    invoke-virtual {p1}, Landroidx/recyclerview/widget/DiffUtil$Callback;->getNewListSize()I

    move-result v0

    iput v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewListSize:I

    iput-boolean p5, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mDetectMoves:Z

    invoke-direct {p0}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->addRootSnake()V

    invoke-direct {p0}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->findMatchingItems()V

    return-void
.end method

.method private addRootSnake()V
    .locals 4

    iget-object v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mSnakes:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mSnakes:Ljava/util/List;

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroidx/recyclerview/widget/DiffUtil$Snake;

    :goto_0
    if-eqz v0, :cond_1

    iget v2, v0, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    if-nez v2, :cond_1

    iget v2, v0, Landroidx/recyclerview/widget/DiffUtil$Snake;->y:I

    if-eqz v2, :cond_2

    :cond_1
    new-instance v2, Landroidx/recyclerview/widget/DiffUtil$Snake;

    invoke-direct {v2}, Landroidx/recyclerview/widget/DiffUtil$Snake;-><init>()V

    iput v1, v2, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    iput v1, v2, Landroidx/recyclerview/widget/DiffUtil$Snake;->y:I

    iput-boolean v1, v2, Landroidx/recyclerview/widget/DiffUtil$Snake;->removal:Z

    iput v1, v2, Landroidx/recyclerview/widget/DiffUtil$Snake;->size:I

    iput-boolean v1, v2, Landroidx/recyclerview/widget/DiffUtil$Snake;->reverse:Z

    iget-object v3, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mSnakes:Ljava/util/List;

    invoke-interface {v3, v1, v2}, Ljava/util/List;->add(ILjava/lang/Object;)V

    :cond_2
    return-void
.end method

.method private dispatchAdditions(Ljava/util/List;Landroidx/recyclerview/widget/ListUpdateCallback;III)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;",
            ">;",
            "Landroidx/recyclerview/widget/ListUpdateCallback;",
            "III)V"
        }
    .end annotation

    iget-boolean v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mDetectMoves:Z

    if-nez v0, :cond_0

    invoke-interface {p2, p3, p4}, Landroidx/recyclerview/widget/ListUpdateCallback;->onInserted(II)V

    return-void

    :cond_0
    add-int/lit8 v0, p4, -0x1

    :goto_0
    if-ltz v0, :cond_3

    iget-object v1, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewItemStatuses:[I

    add-int v2, p5, v0

    aget v2, v1, v2

    and-int/lit8 v2, v2, 0x1f

    const/4 v3, 0x1

    sparse-switch v2, :sswitch_data_0

    new-instance v1, Ljava/lang/IllegalStateException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "unknown flag for pos "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    add-int v4, p5, v0

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v4, " "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    int-to-long v4, v2

    invoke-static {v4, v5}, Ljava/lang/Long;->toBinaryString(J)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v3}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v1

    :sswitch_0
    new-instance v1, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;

    add-int v3, p5, v0

    const/4 v4, 0x0

    invoke-direct {v1, v3, p3, v4}, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;-><init>(IIZ)V

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_2

    :sswitch_1
    add-int v4, p5, v0

    aget v1, v1, v4

    shr-int/lit8 v1, v1, 0x5

    invoke-static {p1, v1, v3}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->removePostponedUpdate(Ljava/util/List;IZ)Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;

    move-result-object v4

    iget v5, v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->currentPos:I

    invoke-interface {p2, v5, p3}, Landroidx/recyclerview/widget/ListUpdateCallback;->onMoved(II)V

    const/4 v5, 0x4

    if-ne v2, v5, :cond_2

    iget-object v5, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mCallback:Landroidx/recyclerview/widget/DiffUtil$Callback;

    add-int v6, p5, v0

    invoke-virtual {v5, v1, v6}, Landroidx/recyclerview/widget/DiffUtil$Callback;->getChangePayload(II)Ljava/lang/Object;

    move-result-object v5

    invoke-interface {p2, p3, v3, v5}, Landroidx/recyclerview/widget/ListUpdateCallback;->onChanged(IILjava/lang/Object;)V

    goto :goto_2

    :sswitch_2
    invoke-interface {p2, p3, v3}, Landroidx/recyclerview/widget/ListUpdateCallback;->onInserted(II)V

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;

    iget v5, v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->currentPos:I

    add-int/2addr v5, v3

    iput v5, v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->currentPos:I

    goto :goto_1

    :cond_1
    nop

    :cond_2
    :goto_2
    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    :cond_3
    return-void

    :sswitch_data_0
    .sparse-switch
        0x0 -> :sswitch_2
        0x4 -> :sswitch_1
        0x8 -> :sswitch_1
        0x10 -> :sswitch_0
    .end sparse-switch
.end method

.method private dispatchRemovals(Ljava/util/List;Landroidx/recyclerview/widget/ListUpdateCallback;III)V
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;",
            ">;",
            "Landroidx/recyclerview/widget/ListUpdateCallback;",
            "III)V"
        }
    .end annotation

    iget-boolean v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mDetectMoves:Z

    if-nez v0, :cond_0

    invoke-interface {p2, p3, p4}, Landroidx/recyclerview/widget/ListUpdateCallback;->onRemoved(II)V

    return-void

    :cond_0
    add-int/lit8 v0, p4, -0x1

    :goto_0
    if-ltz v0, :cond_3

    iget-object v1, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldItemStatuses:[I

    add-int v2, p5, v0

    aget v2, v1, v2

    and-int/lit8 v2, v2, 0x1f

    const/4 v3, 0x1

    sparse-switch v2, :sswitch_data_0

    new-instance v1, Ljava/lang/IllegalStateException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "unknown flag for pos "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    add-int v4, p5, v0

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v4, " "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    int-to-long v4, v2

    invoke-static {v4, v5}, Ljava/lang/Long;->toBinaryString(J)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v3}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v1

    :sswitch_0
    new-instance v1, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;

    add-int v4, p5, v0

    add-int v5, p3, v0

    invoke-direct {v1, v4, v5, v3}, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;-><init>(IIZ)V

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_2

    :sswitch_1
    add-int v4, p5, v0

    aget v1, v1, v4

    shr-int/lit8 v1, v1, 0x5

    const/4 v4, 0x0

    invoke-static {p1, v1, v4}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->removePostponedUpdate(Ljava/util/List;IZ)Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;

    move-result-object v4

    add-int v5, p3, v0

    iget v6, v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->currentPos:I

    sub-int/2addr v6, v3

    invoke-interface {p2, v5, v6}, Landroidx/recyclerview/widget/ListUpdateCallback;->onMoved(II)V

    const/4 v5, 0x4

    if-ne v2, v5, :cond_2

    iget v5, v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->currentPos:I

    sub-int/2addr v5, v3

    iget-object v6, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mCallback:Landroidx/recyclerview/widget/DiffUtil$Callback;

    add-int v7, p5, v0

    invoke-virtual {v6, v7, v1}, Landroidx/recyclerview/widget/DiffUtil$Callback;->getChangePayload(II)Ljava/lang/Object;

    move-result-object v6

    invoke-interface {p2, v5, v3, v6}, Landroidx/recyclerview/widget/ListUpdateCallback;->onChanged(IILjava/lang/Object;)V

    goto :goto_2

    :sswitch_2
    add-int v1, p3, v0

    invoke-interface {p2, v1, v3}, Landroidx/recyclerview/widget/ListUpdateCallback;->onRemoved(II)V

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;

    iget v5, v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->currentPos:I

    sub-int/2addr v5, v3

    iput v5, v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->currentPos:I

    goto :goto_1

    :cond_1
    nop

    :cond_2
    :goto_2
    add-int/lit8 v0, v0, -0x1

    goto/16 :goto_0

    :cond_3
    return-void

    nop

    :sswitch_data_0
    .sparse-switch
        0x0 -> :sswitch_2
        0x4 -> :sswitch_1
        0x8 -> :sswitch_1
        0x10 -> :sswitch_0
    .end sparse-switch
.end method

.method private findAddition(III)V
    .locals 2

    iget-object v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldItemStatuses:[I

    add-int/lit8 v1, p1, -0x1

    aget v0, v0, v1

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, p3, v0}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->findMatchingItem(IIIZ)Z

    return-void
.end method

.method private findMatchingItem(IIIZ)Z
    .locals 15

    move-object v0, p0

    if-eqz p4, :cond_0

    add-int/lit8 v1, p2, -0x1

    move/from16 v2, p1

    add-int/lit8 v3, p2, -0x1

    goto :goto_0

    :cond_0
    add-int/lit8 v1, p1, -0x1

    add-int/lit8 v2, p1, -0x1

    move/from16 v3, p2

    :goto_0
    move/from16 v4, p3

    :goto_1
    if-ltz v4, :cond_8

    iget-object v5, v0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mSnakes:Ljava/util/List;

    invoke-interface {v5, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Landroidx/recyclerview/widget/DiffUtil$Snake;

    iget v6, v5, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    iget v7, v5, Landroidx/recyclerview/widget/DiffUtil$Snake;->size:I

    add-int/2addr v6, v7

    iget v7, v5, Landroidx/recyclerview/widget/DiffUtil$Snake;->y:I

    iget v8, v5, Landroidx/recyclerview/widget/DiffUtil$Snake;->size:I

    add-int/2addr v7, v8

    const/16 v8, 0x8

    const/4 v9, 0x4

    const/4 v10, 0x1

    if-eqz p4, :cond_4

    add-int/lit8 v11, v2, -0x1

    :goto_2
    if-lt v11, v6, :cond_3

    iget-object v12, v0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mCallback:Landroidx/recyclerview/widget/DiffUtil$Callback;

    invoke-virtual {v12, v11, v1}, Landroidx/recyclerview/widget/DiffUtil$Callback;->areItemsTheSame(II)Z

    move-result v12

    if-eqz v12, :cond_2

    iget-object v12, v0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mCallback:Landroidx/recyclerview/widget/DiffUtil$Callback;

    invoke-virtual {v12, v11, v1}, Landroidx/recyclerview/widget/DiffUtil$Callback;->areContentsTheSame(II)Z

    move-result v12

    if-eqz v12, :cond_1

    goto :goto_3

    :cond_1
    const/4 v8, 0x4

    :goto_3
    iget-object v9, v0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewItemStatuses:[I

    shl-int/lit8 v13, v11, 0x5

    or-int/lit8 v13, v13, 0x10

    aput v13, v9, v1

    iget-object v9, v0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldItemStatuses:[I

    shl-int/lit8 v13, v1, 0x5

    or-int/2addr v13, v8

    aput v13, v9, v11

    return v10

    :cond_2
    add-int/lit8 v11, v11, -0x1

    goto :goto_2

    :cond_3
    goto :goto_6

    :cond_4
    add-int/lit8 v11, v3, -0x1

    :goto_4
    if-lt v11, v7, :cond_7

    iget-object v12, v0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mCallback:Landroidx/recyclerview/widget/DiffUtil$Callback;

    invoke-virtual {v12, v1, v11}, Landroidx/recyclerview/widget/DiffUtil$Callback;->areItemsTheSame(II)Z

    move-result v12

    if-eqz v12, :cond_6

    iget-object v12, v0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mCallback:Landroidx/recyclerview/widget/DiffUtil$Callback;

    invoke-virtual {v12, v1, v11}, Landroidx/recyclerview/widget/DiffUtil$Callback;->areContentsTheSame(II)Z

    move-result v12

    if-eqz v12, :cond_5

    goto :goto_5

    :cond_5
    const/4 v8, 0x4

    :goto_5
    iget-object v9, v0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldItemStatuses:[I

    add-int/lit8 v13, p1, -0x1

    shl-int/lit8 v14, v11, 0x5

    or-int/lit8 v14, v14, 0x10

    aput v14, v9, v13

    iget-object v9, v0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewItemStatuses:[I

    add-int/lit8 v13, p1, -0x1

    shl-int/lit8 v13, v13, 0x5

    or-int/2addr v13, v8

    aput v13, v9, v11

    return v10

    :cond_6
    add-int/lit8 v11, v11, -0x1

    goto :goto_4

    :cond_7
    :goto_6
    iget v2, v5, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    iget v3, v5, Landroidx/recyclerview/widget/DiffUtil$Snake;->y:I

    add-int/lit8 v4, v4, -0x1

    goto :goto_1

    :cond_8
    const/4 v4, 0x0

    return v4
.end method

.method private findMatchingItems()V
    .locals 14

    iget v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldListSize:I

    iget v1, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewListSize:I

    iget-object v2, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mSnakes:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    const/4 v3, 0x1

    sub-int/2addr v2, v3

    :goto_0
    if-ltz v2, :cond_4

    iget-object v4, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mSnakes:Ljava/util/List;

    invoke-interface {v4, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroidx/recyclerview/widget/DiffUtil$Snake;

    iget v5, v4, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    iget v6, v4, Landroidx/recyclerview/widget/DiffUtil$Snake;->size:I

    add-int/2addr v5, v6

    iget v6, v4, Landroidx/recyclerview/widget/DiffUtil$Snake;->y:I

    iget v7, v4, Landroidx/recyclerview/widget/DiffUtil$Snake;->size:I

    add-int/2addr v6, v7

    iget-boolean v7, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mDetectMoves:Z

    if-eqz v7, :cond_1

    :goto_1
    if-le v0, v5, :cond_0

    invoke-direct {p0, v0, v1, v2}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->findAddition(III)V

    add-int/lit8 v0, v0, -0x1

    goto :goto_1

    :cond_0
    :goto_2
    if-le v1, v6, :cond_1

    invoke-direct {p0, v0, v1, v2}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->findRemoval(III)V

    add-int/lit8 v1, v1, -0x1

    goto :goto_2

    :cond_1
    const/4 v7, 0x0

    :goto_3
    iget v8, v4, Landroidx/recyclerview/widget/DiffUtil$Snake;->size:I

    if-ge v7, v8, :cond_3

    iget v8, v4, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    add-int/2addr v8, v7

    iget v9, v4, Landroidx/recyclerview/widget/DiffUtil$Snake;->y:I

    add-int/2addr v9, v7

    iget-object v10, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mCallback:Landroidx/recyclerview/widget/DiffUtil$Callback;

    invoke-virtual {v10, v8, v9}, Landroidx/recyclerview/widget/DiffUtil$Callback;->areContentsTheSame(II)Z

    move-result v10

    if-eqz v10, :cond_2

    const/4 v11, 0x1

    goto :goto_4

    :cond_2
    const/4 v11, 0x2

    :goto_4
    iget-object v12, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldItemStatuses:[I

    shl-int/lit8 v13, v9, 0x5

    or-int/2addr v13, v11

    aput v13, v12, v8

    iget-object v12, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewItemStatuses:[I

    shl-int/lit8 v13, v8, 0x5

    or-int/2addr v13, v11

    aput v13, v12, v9

    add-int/lit8 v7, v7, 0x1

    goto :goto_3

    :cond_3
    iget v0, v4, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    iget v1, v4, Landroidx/recyclerview/widget/DiffUtil$Snake;->y:I

    add-int/lit8 v2, v2, -0x1

    goto :goto_0

    :cond_4
    return-void
.end method

.method private findRemoval(III)V
    .locals 2

    iget-object v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewItemStatuses:[I

    add-int/lit8 v1, p2, -0x1

    aget v0, v0, v1

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    invoke-direct {p0, p1, p2, p3, v0}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->findMatchingItem(IIIZ)Z

    return-void
.end method

.method private static removePostponedUpdate(Ljava/util/List;IZ)Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;",
            ">;IZ)",
            "Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;"
        }
    .end annotation

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    :goto_0
    if-ltz v0, :cond_3

    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;

    iget v3, v2, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->posInOwnerList:I

    if-ne v3, p1, :cond_2

    iget-boolean v3, v2, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->removal:Z

    if-ne v3, p2, :cond_2

    invoke-interface {p0, v0}, Ljava/util/List;->remove(I)Ljava/lang/Object;

    move v3, v0

    :goto_1
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v4

    if-ge v3, v4, :cond_1

    invoke-interface {p0, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;

    iget v5, v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->currentPos:I

    if-eqz p2, :cond_0

    const/4 v6, 0x1

    goto :goto_2

    :cond_0
    const/4 v6, -0x1

    :goto_2
    add-int/2addr v5, v6

    iput v5, v4, Landroidx/recyclerview/widget/DiffUtil$PostponedUpdate;->currentPos:I

    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    :cond_1
    return-object v2

    :cond_2
    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    :cond_3
    const/4 v0, 0x0

    return-object v0
.end method


# virtual methods
.method public convertNewPositionToOld(I)I
    .locals 3

    if-ltz p1, :cond_1

    iget v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewListSize:I

    if-ge p1, v0, :cond_1

    iget-object v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewItemStatuses:[I

    aget v0, v0, p1

    and-int/lit8 v1, v0, 0x1f

    if-nez v1, :cond_0

    const/4 v1, -0x1

    return v1

    :cond_0
    shr-int/lit8 v1, v0, 0x5

    return v1

    :cond_1
    new-instance v0, Ljava/lang/IndexOutOfBoundsException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Index out of bounds - passed position = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, ", new list size = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewListSize:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IndexOutOfBoundsException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public convertOldPositionToNew(I)I
    .locals 3

    if-ltz p1, :cond_1

    iget v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldListSize:I

    if-ge p1, v0, :cond_1

    iget-object v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldItemStatuses:[I

    aget v0, v0, p1

    and-int/lit8 v1, v0, 0x1f

    if-nez v1, :cond_0

    const/4 v1, -0x1

    return v1

    :cond_0
    shr-int/lit8 v1, v0, 0x5

    return v1

    :cond_1
    new-instance v0, Ljava/lang/IndexOutOfBoundsException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Index out of bounds - passed position = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, ", old list size = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldListSize:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IndexOutOfBoundsException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public dispatchUpdatesTo(Landroidx/recyclerview/widget/ListUpdateCallback;)V
    .locals 17

    move-object/from16 v8, p0

    move-object/from16 v0, p1

    instance-of v1, v0, Landroidx/recyclerview/widget/BatchingListUpdateCallback;

    if-eqz v1, :cond_0

    move-object v1, v0

    check-cast v1, Landroidx/recyclerview/widget/BatchingListUpdateCallback;

    move-object v9, v0

    move-object v10, v1

    goto :goto_0

    :cond_0
    new-instance v1, Landroidx/recyclerview/widget/BatchingListUpdateCallback;

    invoke-direct {v1, v0}, Landroidx/recyclerview/widget/BatchingListUpdateCallback;-><init>(Landroidx/recyclerview/widget/ListUpdateCallback;)V

    move-object v0, v1

    move-object v9, v0

    move-object v10, v1

    :goto_0
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iget v0, v8, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldListSize:I

    iget v2, v8, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mNewListSize:I

    iget-object v3, v8, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mSnakes:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/List;->size()I

    move-result v3

    const/4 v11, 0x1

    sub-int/2addr v3, v11

    move v12, v0

    move v13, v2

    move v14, v3

    :goto_1
    if-ltz v14, :cond_5

    iget-object v0, v8, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mSnakes:Ljava/util/List;

    invoke-interface {v0, v14}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    move-object v15, v0

    check-cast v15, Landroidx/recyclerview/widget/DiffUtil$Snake;

    iget v7, v15, Landroidx/recyclerview/widget/DiffUtil$Snake;->size:I

    iget v0, v15, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    add-int v6, v0, v7

    iget v0, v15, Landroidx/recyclerview/widget/DiffUtil$Snake;->y:I

    add-int v5, v0, v7

    if-ge v6, v12, :cond_1

    sub-int v4, v12, v6

    move-object/from16 v0, p0

    move-object v2, v10

    move v3, v6

    move v11, v5

    move v5, v6

    invoke-direct/range {v0 .. v5}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->dispatchRemovals(Ljava/util/List;Landroidx/recyclerview/widget/ListUpdateCallback;III)V

    goto :goto_2

    :cond_1
    move v11, v5

    :goto_2
    if-ge v11, v13, :cond_2

    sub-int v0, v13, v11

    move-object/from16 v2, p0

    move-object v3, v1

    move-object v4, v10

    move v5, v6

    move/from16 v16, v6

    move v6, v0

    move v0, v7

    move v7, v11

    invoke-direct/range {v2 .. v7}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->dispatchAdditions(Ljava/util/List;Landroidx/recyclerview/widget/ListUpdateCallback;III)V

    goto :goto_3

    :cond_2
    move/from16 v16, v6

    move v0, v7

    :goto_3
    add-int/lit8 v7, v0, -0x1

    :goto_4
    if-ltz v7, :cond_4

    iget-object v2, v8, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mOldItemStatuses:[I

    iget v3, v15, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    add-int/2addr v3, v7

    aget v2, v2, v3

    and-int/lit8 v2, v2, 0x1f

    const/4 v3, 0x2

    if-ne v2, v3, :cond_3

    iget v2, v15, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    add-int/2addr v2, v7

    iget-object v3, v8, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mCallback:Landroidx/recyclerview/widget/DiffUtil$Callback;

    iget v4, v15, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    add-int/2addr v4, v7

    iget v5, v15, Landroidx/recyclerview/widget/DiffUtil$Snake;->y:I

    add-int/2addr v5, v7

    invoke-virtual {v3, v4, v5}, Landroidx/recyclerview/widget/DiffUtil$Callback;->getChangePayload(II)Ljava/lang/Object;

    move-result-object v3

    const/4 v4, 0x1

    invoke-virtual {v10, v2, v4, v3}, Landroidx/recyclerview/widget/BatchingListUpdateCallback;->onChanged(IILjava/lang/Object;)V

    goto :goto_5

    :cond_3
    const/4 v4, 0x1

    :goto_5
    add-int/lit8 v7, v7, -0x1

    goto :goto_4

    :cond_4
    const/4 v4, 0x1

    iget v12, v15, Landroidx/recyclerview/widget/DiffUtil$Snake;->x:I

    iget v13, v15, Landroidx/recyclerview/widget/DiffUtil$Snake;->y:I

    add-int/lit8 v14, v14, -0x1

    const/4 v11, 0x1

    goto :goto_1

    :cond_5
    invoke-virtual {v10}, Landroidx/recyclerview/widget/BatchingListUpdateCallback;->dispatchLastEvent()V

    return-void
.end method

.method public dispatchUpdatesTo(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V
    .locals 1

    new-instance v0, Landroidx/recyclerview/widget/AdapterListUpdateCallback;

    invoke-direct {v0, p1}, Landroidx/recyclerview/widget/AdapterListUpdateCallback;-><init>(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    invoke-virtual {p0, v0}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->dispatchUpdatesTo(Landroidx/recyclerview/widget/ListUpdateCallback;)V

    return-void
.end method

.method getSnakes()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Landroidx/recyclerview/widget/DiffUtil$Snake;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->mSnakes:Ljava/util/List;

    return-object v0
.end method
