.class public final Lsage/mode/dexter/MultiDex$V14;
.super Ljava/lang/Object;
.source "MultiDex.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lsage/mode/dexter/MultiDex;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "V14"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lsage/mode/dexter/MultiDex$V14$JBMR2ElementConstructor;,
        Lsage/mode/dexter/MultiDex$V14$JBMR11ElementConstructor;,
        Lsage/mode/dexter/MultiDex$V14$ICSElementConstructor;,
        Lsage/mode/dexter/MultiDex$V14$ElementConstructor;
    }
.end annotation


# static fields
.field private static final EXTRACTED_SUFFIX_LENGTH:I


# instance fields
.field private final elementConstructor:Lsage/mode/dexter/MultiDex$V14$ElementConstructor;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 346
    const-string v0, ".zip"

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    sput v0, Lsage/mode/dexter/MultiDex$V14;->EXTRACTED_SUFFIX_LENGTH:I

    return-void
.end method

.method private constructor <init>()V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/ClassNotFoundException;,
            Ljava/lang/SecurityException;,
            Ljava/lang/NoSuchMethodException;
        }
    .end annotation

    .line 410
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 412
    const-string v0, "dalvik.system.DexPathList$Element"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 414
    .local v0, "elementClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :try_start_0
    new-instance v1, Lsage/mode/dexter/MultiDex$V14$ICSElementConstructor;

    invoke-direct {v1, v0}, Lsage/mode/dexter/MultiDex$V14$ICSElementConstructor;-><init>(Ljava/lang/Class;)V
    :try_end_0
    .catch Ljava/lang/NoSuchMethodException; {:try_start_0 .. :try_end_0} :catch_0

    .line 421
    .local v1, "constructor":Lsage/mode/dexter/MultiDex$V14$ElementConstructor;
    goto :goto_0

    .line 415
    .end local v1    # "constructor":Lsage/mode/dexter/MultiDex$V14$ElementConstructor;
    :catch_0
    move-exception v1

    .line 417
    .local v1, "e":Ljava/lang/NoSuchMethodException;
    :try_start_1
    new-instance v2, Lsage/mode/dexter/MultiDex$V14$JBMR11ElementConstructor;

    invoke-direct {v2, v0}, Lsage/mode/dexter/MultiDex$V14$JBMR11ElementConstructor;-><init>(Ljava/lang/Class;)V
    :try_end_1
    .catch Ljava/lang/NoSuchMethodException; {:try_start_1 .. :try_end_1} :catch_1

    .line 420
    .local v2, "constructor":Lsage/mode/dexter/MultiDex$V14$ElementConstructor;
    move-object v1, v2

    goto :goto_0

    .line 418
    .end local v2    # "constructor":Lsage/mode/dexter/MultiDex$V14$ElementConstructor;
    :catch_1
    move-exception v2

    .line 419
    .local v2, "e2":Ljava/lang/NoSuchMethodException;
    new-instance v3, Lsage/mode/dexter/MultiDex$V14$JBMR2ElementConstructor;

    invoke-direct {v3, v0}, Lsage/mode/dexter/MultiDex$V14$JBMR2ElementConstructor;-><init>(Ljava/lang/Class;)V

    move-object v1, v3

    .line 422
    .end local v2    # "e2":Ljava/lang/NoSuchMethodException;
    .local v1, "constructor":Lsage/mode/dexter/MultiDex$V14$ElementConstructor;
    :goto_0
    iput-object v1, p0, Lsage/mode/dexter/MultiDex$V14;->elementConstructor:Lsage/mode/dexter/MultiDex$V14$ElementConstructor;

    .line 423
    return-void
.end method

.method static install(Ljava/lang/ClassLoader;Ljava/util/List;)V
    .locals 5
    .param p0, "loader"    # Ljava/lang/ClassLoader;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/ClassLoader;",
            "Ljava/util/List<",
            "+",
            "Ljava/io/File;",
            ">;)V"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Ljava/lang/SecurityException;,
            Ljava/lang/IllegalArgumentException;,
            Ljava/lang/ClassNotFoundException;,
            Ljava/lang/NoSuchMethodException;,
            Ljava/lang/InstantiationException;,
            Ljava/lang/IllegalAccessException;,
            Ljava/lang/reflect/InvocationTargetException;,
            Ljava/lang/NoSuchFieldException;
        }
    .end annotation

    .line 400
    .local p1, "additionalClassPathEntries":Ljava/util/List;, "Ljava/util/List<+Ljava/io/File;>;"
    const-string v0, "pathList"

    invoke-static {p0, v0}, Lsage/mode/dexter/MultiDex;->findField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 401
    .local v0, "dexPathList":Ljava/lang/Object;
    new-instance v1, Lsage/mode/dexter/MultiDex$V14;

    invoke-direct {v1}, Lsage/mode/dexter/MultiDex$V14;-><init>()V

    invoke-direct {v1, p1}, Lsage/mode/dexter/MultiDex$V14;->makeDexElements(Ljava/util/List;)[Ljava/lang/Object;

    move-result-object v1

    .line 403
    .local v1, "elements":[Ljava/lang/Object;
    :try_start_0
    const-string v2, "dexElements"

    invoke-static {v0, v2, v1}, Lsage/mode/dexter/MultiDex;->expandFieldArray(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_0

    .line 407
    goto :goto_0

    .line 404
    :catch_0
    move-exception v2

    .line 405
    .local v2, "e":Ljava/lang/NoSuchFieldException;
    const-string v3, "MultiDex"

    const-string v4, "Failed find field \'dexElements\' attempting \'pathElements\'"

    invoke-static {v3, v4, v2}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 406
    const-string v3, "pathElements"

    invoke-static {v0, v3, v1}, Lsage/mode/dexter/MultiDex;->expandFieldArray(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V

    .line 408
    .end local v2    # "e":Ljava/lang/NoSuchFieldException;
    :goto_0
    return-void
.end method

.method private makeDexElements(Ljava/util/List;)[Ljava/lang/Object;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "+",
            "Ljava/io/File;",
            ">;)[",
            "Ljava/lang/Object;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Ljava/lang/SecurityException;,
            Ljava/lang/IllegalArgumentException;,
            Ljava/lang/InstantiationException;,
            Ljava/lang/IllegalAccessException;,
            Ljava/lang/reflect/InvocationTargetException;
        }
    .end annotation

    .line 426
    .local p1, "files":Ljava/util/List;, "Ljava/util/List<+Ljava/io/File;>;"
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    new-array v0, v0, [Ljava/lang/Object;

    .line 427
    .local v0, "elements":[Ljava/lang/Object;
    const/4 v1, 0x0

    .local v1, "i":I
    :goto_0
    array-length v2, v0

    if-ge v1, v2, :cond_0

    .line 428
    invoke-interface {p1, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/io/File;

    .line 429
    .local v2, "file":Ljava/io/File;
    iget-object v3, p0, Lsage/mode/dexter/MultiDex$V14;->elementConstructor:Lsage/mode/dexter/MultiDex$V14$ElementConstructor;

    invoke-virtual {v2}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v4

    invoke-static {v2}, Lsage/mode/dexter/MultiDex$V14;->optimizedPathFor(Ljava/io/File;)Ljava/lang/String;

    move-result-object v5

    const/4 v6, 0x0

    invoke-static {v4, v5, v6}, Ldalvik/system/DexFile;->loadDex(Ljava/lang/String;Ljava/lang/String;I)Ldalvik/system/DexFile;

    move-result-object v4

    invoke-interface {v3, v2, v4}, Lsage/mode/dexter/MultiDex$V14$ElementConstructor;->newInstance(Ljava/io/File;Ldalvik/system/DexFile;)Ljava/lang/Object;

    move-result-object v3

    aput-object v3, v0, v1

    .line 427
    .end local v2    # "file":Ljava/io/File;
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 431
    .end local v1    # "i":I
    :cond_0
    return-object v0
.end method

.method private static optimizedPathFor(Ljava/io/File;)Ljava/lang/String;
    .locals 6
    .param p0, "path"    # Ljava/io/File;

    .line 435
    invoke-virtual {p0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v0

    .line 436
    .local v0, "optimizedDirectory":Ljava/io/File;
    invoke-virtual {p0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v1

    .line 437
    .local v1, "fileName":Ljava/lang/String;
    new-instance v2, Ljava/io/File;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v4

    sget v5, Lsage/mode/dexter/MultiDex$V14;->EXTRACTED_SUFFIX_LENGTH:I

    sub-int/2addr v4, v5

    const/4 v5, 0x0

    invoke-virtual {v1, v5, v4}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, ".dex"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v0, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v2}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v2

    return-object v2
.end method
