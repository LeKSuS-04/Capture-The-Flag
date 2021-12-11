.class public final Lsage/mode/dexter/MultiDex$V19;
.super Ljava/lang/Object;
.source "MultiDex.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lsage/mode/dexter/MultiDex;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "V19"
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 306
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 307
    return-void
.end method

.method static install(Ljava/lang/ClassLoader;Ljava/util/List;Ljava/io/File;)V
    .locals 9
    .param p0, "loader"    # Ljava/lang/ClassLoader;
    .param p2, "optimizedDirectory"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/ClassLoader;",
            "Ljava/util/List<",
            "+",
            "Ljava/io/File;",
            ">;",
            "Ljava/io/File;",
            ")V"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/IllegalArgumentException;,
            Ljava/lang/IllegalAccessException;,
            Ljava/lang/NoSuchFieldException;,
            Ljava/lang/reflect/InvocationTargetException;,
            Ljava/lang/NoSuchMethodException;,
            Ljava/io/IOException;
        }
    .end annotation

    .line 311
    .local p1, "additionalClassPathEntries":Ljava/util/List;, "Ljava/util/List<+Ljava/io/File;>;"
    const-string v0, "pathList"

    invoke-static {p0, v0}, Lsage/mode/dexter/MultiDex;->findField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 312
    .local v0, "dexPathList":Ljava/lang/Object;
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 313
    .local v1, "suppressedExceptions":Ljava/util/ArrayList;, "Ljava/util/ArrayList<Ljava/io/IOException;>;"
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2, p1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-static {v0, v2, p2, v1}, Lsage/mode/dexter/MultiDex$V19;->makeDexElements(Ljava/lang/Object;Ljava/util/ArrayList;Ljava/io/File;Ljava/util/ArrayList;)[Ljava/lang/Object;

    move-result-object v2

    const-string v3, "dexElements"

    invoke-static {v0, v3, v2}, Lsage/mode/dexter/MultiDex;->expandFieldArray(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V

    .line 314
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-lez v2, :cond_2

    .line 315
    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    .line 316
    .local v2, "it":Ljava/util/Iterator;, "Ljava/util/Iterator<Ljava/io/IOException;>;"
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 317
    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Throwable;

    const-string v4, "MultiDex"

    const-string v5, "Exception in makeDexElement"

    invoke-static {v4, v5, v3}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0

    .line 319
    :cond_0
    const-string v3, "dexElementsSuppressedExceptions"

    invoke-static {v0, v3}, Lsage/mode/dexter/MultiDex;->findField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v3

    .line 320
    .local v3, "suppressedExceptionsField":Ljava/lang/reflect/Field;
    invoke-virtual {v3, v0}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, [Ljava/io/IOException;

    .line 321
    .local v4, "dexElementsSuppressedExceptions2":[Ljava/io/IOException;
    const/4 v5, 0x0

    if-nez v4, :cond_1

    .line 322
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v6

    new-array v6, v6, [Ljava/io/IOException;

    invoke-virtual {v1, v6}, Ljava/util/ArrayList;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v6

    check-cast v6, [Ljava/io/IOException;

    .local v6, "dexElementsSuppressedExceptions":[Ljava/io/IOException;
    goto :goto_1

    .line 324
    .end local v6    # "dexElementsSuppressedExceptions":[Ljava/io/IOException;
    :cond_1
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v6

    array-length v7, v4

    add-int/2addr v6, v7

    new-array v6, v6, [Ljava/io/IOException;

    .line 325
    .local v6, "combined":[Ljava/io/IOException;
    invoke-virtual {v1, v6}, Ljava/util/ArrayList;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    .line 326
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v7

    array-length v8, v4

    invoke-static {v4, v5, v6, v7, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 327
    move-object v7, v6

    .line 329
    .local v6, "dexElementsSuppressedExceptions":[Ljava/io/IOException;
    :goto_1
    invoke-virtual {v3, v0, v6}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 330
    new-instance v7, Ljava/io/IOException;

    const-string v8, "I/O exception during makeDexElement"

    invoke-direct {v7, v8}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    .line 331
    .local v7, "exception":Ljava/io/IOException;
    invoke-virtual {v1, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Throwable;

    invoke-virtual {v7, v5}, Ljava/io/IOException;->initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;

    .line 332
    throw v7

    .line 334
    .end local v2    # "it":Ljava/util/Iterator;, "Ljava/util/Iterator<Ljava/io/IOException;>;"
    .end local v3    # "suppressedExceptionsField":Ljava/lang/reflect/Field;
    .end local v4    # "dexElementsSuppressedExceptions2":[Ljava/io/IOException;
    .end local v6    # "dexElementsSuppressedExceptions":[Ljava/io/IOException;
    .end local v7    # "exception":Ljava/io/IOException;
    :cond_2
    return-void
.end method

.method private static makeDexElements(Ljava/lang/Object;Ljava/util/ArrayList;Ljava/io/File;Ljava/util/ArrayList;)[Ljava/lang/Object;
    .locals 6
    .param p0, "dexPathList"    # Ljava/lang/Object;
    .param p2, "optimizedDirectory"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "Ljava/util/ArrayList<",
            "Ljava/io/File;",
            ">;",
            "Ljava/io/File;",
            "Ljava/util/ArrayList<",
            "Ljava/io/IOException;",
            ">;)[",
            "Ljava/lang/Object;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/IllegalAccessException;,
            Ljava/lang/reflect/InvocationTargetException;,
            Ljava/lang/NoSuchMethodException;
        }
    .end annotation

    .line 337
    .local p1, "files":Ljava/util/ArrayList;, "Ljava/util/ArrayList<Ljava/io/File;>;"
    .local p3, "suppressedExceptions":Ljava/util/ArrayList;, "Ljava/util/ArrayList<Ljava/io/IOException;>;"
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/4 v1, 0x2

    const/4 v2, 0x1

    const/4 v3, 0x0

    const/4 v4, 0x3

    const/16 v5, 0x17

    if-lt v0, v5, :cond_0

    .line 338
    new-array v0, v4, [Ljava/lang/Class;

    const-class v5, Ljava/util/List;

    aput-object v5, v0, v3

    const-class v5, Ljava/io/File;

    aput-object v5, v0, v2

    const-class v5, Ljava/util/List;

    aput-object v5, v0, v1

    const-string v5, "makePathElements"

    invoke-static {p0, v5, v0}, Lsage/mode/dexter/MultiDex;->findMethod(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    goto :goto_0

    .line 339
    :cond_0
    new-array v0, v4, [Ljava/lang/Class;

    const-class v5, Ljava/util/ArrayList;

    aput-object v5, v0, v3

    const-class v5, Ljava/io/File;

    aput-object v5, v0, v2

    const-class v5, Ljava/util/ArrayList;

    aput-object v5, v0, v1

    const-string v5, "makeDexElements"

    invoke-static {p0, v5, v0}, Lsage/mode/dexter/MultiDex;->findMethod(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    :goto_0
    nop

    .line 340
    .local v0, "makeDexElements":Ljava/lang/reflect/Method;
    new-array v4, v4, [Ljava/lang/Object;

    aput-object p1, v4, v3

    aput-object p2, v4, v2

    aput-object p3, v4, v1

    invoke-virtual {v0, p0, v4}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Ljava/lang/Object;

    return-object v1
.end method
