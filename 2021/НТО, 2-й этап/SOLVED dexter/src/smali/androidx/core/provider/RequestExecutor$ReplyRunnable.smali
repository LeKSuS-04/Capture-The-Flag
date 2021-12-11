.class Landroidx/core/provider/RequestExecutor$ReplyRunnable;
.super Ljava/lang/Object;
.source "RequestExecutor.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/core/provider/RequestExecutor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "ReplyRunnable"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Ljava/lang/Runnable;"
    }
.end annotation


# instance fields
.field private mCallable:Ljava/util/concurrent/Callable;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/Callable<",
            "TT;>;"
        }
    .end annotation
.end field

.field private mConsumer:Landroidx/core/util/Consumer;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/core/util/Consumer<",
            "TT;>;"
        }
    .end annotation
.end field

.field private mHandler:Landroid/os/Handler;


# direct methods
.method constructor <init>(Landroid/os/Handler;Ljava/util/concurrent/Callable;Landroidx/core/util/Consumer;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/os/Handler;",
            "Ljava/util/concurrent/Callable<",
            "TT;>;",
            "Landroidx/core/util/Consumer<",
            "TT;>;)V"
        }
    .end annotation

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Landroidx/core/provider/RequestExecutor$ReplyRunnable;->mCallable:Ljava/util/concurrent/Callable;

    iput-object p3, p0, Landroidx/core/provider/RequestExecutor$ReplyRunnable;->mConsumer:Landroidx/core/util/Consumer;

    iput-object p1, p0, Landroidx/core/provider/RequestExecutor$ReplyRunnable;->mHandler:Landroid/os/Handler;

    return-void
.end method


# virtual methods
.method public run()V
    .locals 5

    :try_start_0
    iget-object v0, p0, Landroidx/core/provider/RequestExecutor$ReplyRunnable;->mCallable:Ljava/util/concurrent/Callable;

    invoke-interface {v0}, Ljava/util/concurrent/Callable;->call()Ljava/lang/Object;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    const/4 v1, 0x0

    move-object v0, v1

    :goto_0
    move-object v1, v0

    iget-object v2, p0, Landroidx/core/provider/RequestExecutor$ReplyRunnable;->mConsumer:Landroidx/core/util/Consumer;

    iget-object v3, p0, Landroidx/core/provider/RequestExecutor$ReplyRunnable;->mHandler:Landroid/os/Handler;

    new-instance v4, Landroidx/core/provider/RequestExecutor$ReplyRunnable$1;

    invoke-direct {v4, p0, v2, v1}, Landroidx/core/provider/RequestExecutor$ReplyRunnable$1;-><init>(Landroidx/core/provider/RequestExecutor$ReplyRunnable;Landroidx/core/util/Consumer;Ljava/lang/Object;)V

    invoke-virtual {v3, v4}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method
