.class Landroidx/core/content/pm/ShortcutManagerCompat$Api25Impl;
.super Ljava/lang/Object;
.source "ShortcutManagerCompat.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/core/content/pm/ShortcutManagerCompat;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "Api25Impl"
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static getShortcutInfoWithLowestRank(Ljava/util/List;)Ljava/lang/String;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroid/content/pm/ShortcutInfo;",
            ">;)",
            "Ljava/lang/String;"
        }
    .end annotation

    const/4 v0, -0x1

    const/4 v1, 0x0

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/content/pm/ShortcutInfo;

    invoke-virtual {v3}, Landroid/content/pm/ShortcutInfo;->getRank()I

    move-result v4

    if-le v4, v0, :cond_0

    invoke-virtual {v3}, Landroid/content/pm/ShortcutInfo;->getId()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v3}, Landroid/content/pm/ShortcutInfo;->getRank()I

    move-result v0

    :cond_0
    goto :goto_0

    :cond_1
    return-object v1
.end method
