.class Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;
.super Ljava/lang/Object;
.source "ConstraintSet.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/constraintlayout/widget/ConstraintSet;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "WriteXmlEngine"
.end annotation


# static fields
.field private static final SPACE:Ljava/lang/String; = "\n       "


# instance fields
.field final BASELINE:Ljava/lang/String;

.field final BOTTOM:Ljava/lang/String;

.field final END:Ljava/lang/String;

.field final LEFT:Ljava/lang/String;

.field final RIGHT:Ljava/lang/String;

.field final START:Ljava/lang/String;

.field final TOP:Ljava/lang/String;

.field context:Landroid/content/Context;

.field flags:I

.field idMap:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/Integer;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field layout:Landroidx/constraintlayout/widget/ConstraintLayout;

.field final synthetic this$0:Landroidx/constraintlayout/widget/ConstraintSet;

.field unknownCount:I

.field writer:Ljava/io/Writer;


# direct methods
.method constructor <init>(Landroidx/constraintlayout/widget/ConstraintSet;Ljava/io/Writer;Landroidx/constraintlayout/widget/ConstraintLayout;I)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010,
            0x0,
            0x0,
            0x0
        }
        names = {
            "this$0",
            "writer",
            "layout",
            "flags"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    iput-object p1, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->this$0:Landroidx/constraintlayout/widget/ConstraintSet;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->unknownCount:I

    const-string v0, "\'left\'"

    iput-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->LEFT:Ljava/lang/String;

    const-string v0, "\'right\'"

    iput-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->RIGHT:Ljava/lang/String;

    const-string v0, "\'baseline\'"

    iput-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->BASELINE:Ljava/lang/String;

    const-string v0, "\'bottom\'"

    iput-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->BOTTOM:Ljava/lang/String;

    const-string v0, "\'top\'"

    iput-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->TOP:Ljava/lang/String;

    const-string v0, "\'start\'"

    iput-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->START:Ljava/lang/String;

    const-string v0, "\'end\'"

    iput-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->END:Ljava/lang/String;

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->idMap:Ljava/util/HashMap;

    iput-object p2, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    iput-object p3, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->layout:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {p3}, Landroidx/constraintlayout/widget/ConstraintLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    iput-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->context:Landroid/content/Context;

    iput p4, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->flags:I

    return-void
.end method

.method private writeBaseDimension(Ljava/lang/String;II)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "dimString",
            "dim",
            "def"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    if-eq p2, p3, :cond_2

    const/4 v0, -0x2

    const-string v1, "\n       "

    if-ne p2, v0, :cond_0

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "=\"wrap_content\""

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    goto :goto_0

    :cond_0
    const/4 v0, -0x1

    if-ne p2, v0, :cond_1

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "=\"match_parent\""

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "=\""

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, "dp\""

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    :cond_2
    :goto_0
    return-void
.end method

.method private writeBoolen(Ljava/lang/String;ZZ)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "dimString",
            "val",
            "def"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    if-eq p2, p3, :cond_0

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n       "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "=\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v2, "dp\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method private writeDimension(Ljava/lang/String;II)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "dimString",
            "dim",
            "def"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    if-eq p2, p3, :cond_0

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n       "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "=\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, "dp\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method private writeEnum(Ljava/lang/String;I[Ljava/lang/String;I)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0,
            0x0
        }
        names = {
            "dimString",
            "val",
            "types",
            "def"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    if-eq p2, p4, :cond_0

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n       "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "=\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    aget-object v2, p3, p2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    :cond_0
    return-void
.end method


# virtual methods
.method getName(I)Ljava/lang/String;
    .locals 5
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "id"
        }
    .end annotation

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->idMap:Ljava/util/HashMap;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    const-string v1, ""

    const-string v2, "@+id/"

    if-eqz v0, :cond_0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->idMap:Ljava/util/HashMap;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_0
    if-nez p1, :cond_1

    const-string v0, "parent"

    return-object v0

    :cond_1
    invoke-virtual {p0, p1}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->lookup(I)Ljava/lang/String;

    move-result-object v0

    iget-object v3, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->idMap:Ljava/util/HashMap;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, v4, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method

.method lookup(I)Ljava/lang/String;
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "id"
        }
    .end annotation

    const/4 v0, -0x1

    const-string v1, "unknown"

    if-eq p1, v0, :cond_0

    :try_start_0
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->context:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getResourceEntryName(I)Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->unknownCount:I

    add-int/lit8 v2, v2, 0x1

    iput v2, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->unknownCount:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->unknownCount:I

    add-int/lit8 v1, v1, 0x1

    iput v1, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->unknownCount:I

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method

.method writeCircle(IFI)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "circleConstraint",
            "circleAngle",
            "circleRadius"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    const/4 v0, -0x1

    if-ne p1, v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v1, "circle"

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v1, ":["

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    invoke-virtual {p0, p1}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->getName(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, ", "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, "]"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    return-void
.end method

.method writeConstraint(Ljava/lang/String;ILjava/lang/String;II)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0,
            0x0,
            0x0
        }
        names = {
            "my",
            "leftToLeft",
            "other",
            "margin",
            "goneMargin"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    const/4 v0, -0x1

    if-ne p2, v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n       "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v1, ":["

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    invoke-virtual {p0, p2}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->getName(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v1, " , "

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    invoke-virtual {v0, p3}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    if-eqz p4, :cond_1

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    :cond_1
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v1, "],\n"

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    return-void
.end method

.method writeLayout()V
    .locals 19
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    move-object/from16 v0, p0

    iget-object v1, v0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v2, "\n<ConstraintSet>\n"

    invoke-virtual {v1, v2}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v1, v0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->this$0:Landroidx/constraintlayout/widget/ConstraintSet;

    invoke-static {v1}, Landroidx/constraintlayout/widget/ConstraintSet;->access$1300(Landroidx/constraintlayout/widget/ConstraintSet;)Ljava/util/HashMap;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/HashMap;->keySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    iget-object v3, v0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->this$0:Landroidx/constraintlayout/widget/ConstraintSet;

    invoke-static {v3}, Landroidx/constraintlayout/widget/ConstraintSet;->access$1300(Landroidx/constraintlayout/widget/ConstraintSet;)Ljava/util/HashMap;

    move-result-object v3

    invoke-virtual {v3, v2}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroidx/constraintlayout/widget/ConstraintSet$Constraint;

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v4

    invoke-virtual {v0, v4}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->getName(I)Ljava/lang/String;

    move-result-object v4

    iget-object v5, v0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v6, "  <Constraint"

    invoke-virtual {v5, v6}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v5, v0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "\n       android:id=\""

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "\""

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v5, v3, Landroidx/constraintlayout/widget/ConstraintSet$Constraint;->layout:Landroidx/constraintlayout/widget/ConstraintSet$Layout;

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->mWidth:I

    const/4 v7, -0x5

    const-string v8, "android:layout_width"

    invoke-direct {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeBaseDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->mHeight:I

    const-string v8, "android:layout_height"

    invoke-direct {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeBaseDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->guideBegin:I

    int-to-float v6, v6

    const/high16 v7, -0x40800000    # -1.0f

    const-string v8, "app:layout_constraintGuide_begin"

    invoke-virtual {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->guideEnd:I

    int-to-float v6, v6

    const-string v8, "app:layout_constraintGuide_end"

    invoke-virtual {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->guidePercent:F

    const-string v8, "app:layout_constraintGuide_percent"

    invoke-virtual {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->horizontalBias:F

    const/high16 v7, 0x3f000000    # 0.5f

    const-string v8, "app:layout_constraintHorizontal_bias"

    invoke-virtual {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->verticalBias:F

    const-string v8, "app:layout_constraintVertical_bias"

    invoke-virtual {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget-object v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->dimensionRatio:Ljava/lang/String;

    const/4 v7, 0x0

    const-string v8, "app:layout_constraintDimensionRatio"

    invoke-virtual {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->circleConstraint:I

    const-string v7, "app:layout_constraintCircle"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->circleRadius:I

    int-to-float v6, v6

    const/4 v7, 0x0

    const-string v8, "app:layout_constraintCircleRadius"

    invoke-virtual {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->circleAngle:F

    const-string v8, "app:layout_constraintCircleAngle"

    invoke-virtual {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->orientation:I

    int-to-float v6, v6

    const/high16 v7, -0x40800000    # -1.0f

    const-string v8, "android:orientation"

    invoke-virtual {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->verticalWeight:F

    const-string v8, "app:layout_constraintVertical_weight"

    invoke-virtual {v0, v8, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->horizontalWeight:F

    const-string v9, "app:layout_constraintHorizontal_weight"

    invoke-virtual {v0, v9, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->horizontalChainStyle:I

    int-to-float v6, v6

    const/4 v7, 0x0

    const-string v10, "app:layout_constraintHorizontal_chainStyle"

    invoke-virtual {v0, v10, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->verticalChainStyle:I

    int-to-float v6, v6

    const-string v11, "app:layout_constraintVertical_chainStyle"

    invoke-virtual {v0, v11, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->mBarrierDirection:I

    int-to-float v6, v6

    const/high16 v7, -0x40800000    # -1.0f

    const-string v12, "app:barrierDirection"

    invoke-virtual {v0, v12, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->mBarrierMargin:I

    int-to-float v6, v6

    const/4 v7, 0x0

    const-string v13, "app:barrierMargin"

    invoke-virtual {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->leftMargin:I

    const/4 v7, 0x0

    const-string v13, "app:layout_marginLeft"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->goneLeftMargin:I

    const/high16 v7, -0x80000000

    const-string v13, "app:layout_goneMarginLeft"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->rightMargin:I

    const/4 v7, 0x0

    const-string v13, "app:layout_marginRight"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->goneRightMargin:I

    const/high16 v7, -0x80000000

    const-string v13, "app:layout_goneMarginRight"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->startMargin:I

    const/4 v7, 0x0

    const-string v13, "app:layout_marginStart"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->goneStartMargin:I

    const/high16 v7, -0x80000000

    const-string v13, "app:layout_goneMarginStart"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->endMargin:I

    const/4 v7, 0x0

    const-string v13, "app:layout_marginEnd"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->goneEndMargin:I

    const/high16 v7, -0x80000000

    const-string v13, "app:layout_goneMarginEnd"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->topMargin:I

    const/4 v7, 0x0

    const-string v13, "app:layout_marginTop"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->goneTopMargin:I

    const/high16 v7, -0x80000000

    const-string v13, "app:layout_goneMarginTop"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->bottomMargin:I

    const/4 v7, 0x0

    const-string v13, "app:layout_marginBottom"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->goneBottomMargin:I

    const/high16 v7, -0x80000000

    const-string v13, "app:layout_goneMarginBottom"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->goneBaselineMargin:I

    const-string v13, "app:goneBaselineMargin"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->baselineMargin:I

    const/4 v7, 0x0

    const-string v13, "app:baselineMargin"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget-boolean v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->constrainedWidth:Z

    const-string v13, "app:layout_constrainedWidth"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeBoolen(Ljava/lang/String;ZZ)V

    iget-boolean v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->constrainedHeight:Z

    const-string v13, "app:layout_constrainedHeight"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeBoolen(Ljava/lang/String;ZZ)V

    iget-boolean v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->mBarrierAllowsGoneWidgets:Z

    const/4 v7, 0x1

    const-string v13, "app:barrierAllowsGoneWidgets"

    invoke-direct {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeBoolen(Ljava/lang/String;ZZ)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->mWrapBehavior:I

    int-to-float v6, v6

    const/4 v7, 0x0

    const-string v13, "app:layout_wrapBehaviorInParent"

    invoke-virtual {v0, v13, v6, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->baselineToBaseline:I

    const-string v7, "app:baselineToBaseline"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->baselineToBottom:I

    const-string v7, "app:baselineToBottom"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->baselineToTop:I

    const-string v7, "app:baselineToTop"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->bottomToBottom:I

    const-string v7, "app:layout_constraintBottom_toBottomOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->bottomToTop:I

    const-string v7, "app:layout_constraintBottom_toTopOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->endToEnd:I

    const-string v7, "app:layout_constraintEnd_toEndOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->endToStart:I

    const-string v7, "app:layout_constraintEnd_toStartOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->leftToLeft:I

    const-string v7, "app:layout_constraintLeft_toLeftOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->leftToRight:I

    const-string v7, "app:layout_constraintLeft_toRightOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->rightToLeft:I

    const-string v7, "app:layout_constraintRight_toLeftOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->rightToRight:I

    const-string v7, "app:layout_constraintRight_toRightOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->startToEnd:I

    const-string v7, "app:layout_constraintStart_toEndOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->startToStart:I

    const-string v7, "app:layout_constraintStart_toStartOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->topToBottom:I

    const-string v7, "app:layout_constraintTop_toBottomOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    iget v6, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->topToTop:I

    const-string v7, "app:layout_constraintTop_toTopOf"

    invoke-virtual {v0, v7, v6}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeXmlConstraint(Ljava/lang/String;I)V

    const-string v6, "spread"

    const-string v7, "wrap"

    const-string v13, "percent"

    filled-new-array {v6, v7, v13}, [Ljava/lang/String;

    move-result-object v6

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->heightDefault:I

    const/4 v13, 0x0

    const-string v14, "app:layout_constraintHeight_default"

    invoke-direct {v0, v14, v7, v6, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeEnum(Ljava/lang/String;I[Ljava/lang/String;I)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->heightPercent:F

    const/high16 v13, 0x3f800000    # 1.0f

    const-string v14, "app:layout_constraintHeight_percent"

    invoke-virtual {v0, v14, v7, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->heightMin:I

    const/4 v13, 0x0

    const-string v14, "app:layout_constraintHeight_min"

    invoke-direct {v0, v14, v7, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->heightMax:I

    const-string v14, "app:layout_constraintHeight_max"

    invoke-direct {v0, v14, v7, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget-boolean v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->constrainedHeight:Z

    const-string v14, "android:layout_constrainedHeight"

    invoke-direct {v0, v14, v7, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeBoolen(Ljava/lang/String;ZZ)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->widthDefault:I

    const-string v14, "app:layout_constraintWidth_default"

    invoke-direct {v0, v14, v7, v6, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeEnum(Ljava/lang/String;I[Ljava/lang/String;I)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->widthPercent:F

    const/high16 v13, 0x3f800000    # 1.0f

    const-string v14, "app:layout_constraintWidth_percent"

    invoke-virtual {v0, v14, v7, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->widthMin:I

    const/4 v13, 0x0

    const-string v14, "app:layout_constraintWidth_min"

    invoke-direct {v0, v14, v7, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->widthMax:I

    const-string v14, "app:layout_constraintWidth_max"

    invoke-direct {v0, v14, v7, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeDimension(Ljava/lang/String;II)V

    iget-boolean v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->constrainedWidth:Z

    const-string v14, "android:layout_constrainedWidth"

    invoke-direct {v0, v14, v7, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeBoolen(Ljava/lang/String;ZZ)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->verticalWeight:F

    const/high16 v13, -0x40800000    # -1.0f

    invoke-virtual {v0, v8, v7, v13}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->horizontalWeight:F

    const/high16 v8, -0x40800000    # -1.0f

    invoke-virtual {v0, v9, v7, v8}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;FF)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->horizontalChainStyle:I

    invoke-virtual {v0, v10, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;I)V

    iget v7, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->verticalChainStyle:I

    invoke-virtual {v0, v11, v7}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;I)V

    const-string v13, "left"

    const-string v14, "right"

    const-string v15, "top"

    const-string v16, "bottom"

    const-string v17, "start"

    const-string v18, "end"

    filled-new-array/range {v13 .. v18}, [Ljava/lang/String;

    move-result-object v7

    iget v8, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->mBarrierDirection:I

    const/4 v9, -0x1

    invoke-direct {v0, v12, v8, v7, v9}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeEnum(Ljava/lang/String;I[Ljava/lang/String;I)V

    iget-object v8, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->mConstraintTag:Ljava/lang/String;

    const/4 v9, 0x0

    const-string v10, "app:layout_constraintTag"

    invoke-virtual {v0, v10, v8, v9}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    iget-object v8, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->mReferenceIds:[I

    if-eqz v8, :cond_0

    iget-object v8, v5, Landroidx/constraintlayout/widget/ConstraintSet$Layout;->mReferenceIds:[I

    const-string v9, "\'ReferenceIds\'"

    invoke-virtual {v0, v9, v8}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writeVariable(Ljava/lang/String;[I)V

    :cond_0
    iget-object v8, v0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v9, " />\n"

    invoke-virtual {v8, v9}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    goto/16 :goto_0

    :cond_1
    iget-object v1, v0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v2, "</ConstraintSet>\n"

    invoke-virtual {v1, v2}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    return-void
.end method

.method writeVariable(Ljava/lang/String;FF)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "name",
            "value",
            "def"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    cmpl-float v0, p2, p3

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n       "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "=\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v2, "\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    return-void
.end method

.method writeVariable(Ljava/lang/String;I)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0
        }
        names = {
            "name",
            "value"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    if-eqz p2, :cond_1

    const/4 v0, -0x1

    if-ne p2, v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n       "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "=\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, "\"\n"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    return-void

    :cond_1
    :goto_0
    return-void
.end method

.method writeVariable(Ljava/lang/String;Ljava/lang/String;)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0
        }
        names = {
            "name",
            "value"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    if-nez p2, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    invoke-virtual {v0, p1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v1, ":"

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, ", "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v1, "\n"

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    return-void
.end method

.method writeVariable(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "name",
            "value",
            "def"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    if-eqz p2, :cond_1

    invoke-virtual {p2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n       "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "=\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    return-void

    :cond_1
    :goto_0
    return-void
.end method

.method writeVariable(Ljava/lang/String;[I)V
    .locals 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0
        }
        names = {
            "name",
            "value"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    if-nez p2, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n       "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v1, ":"

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    const/4 v0, 0x0

    :goto_0
    array-length v1, p2

    if-ge v0, v1, :cond_2

    iget-object v1, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    if-nez v0, :cond_1

    const-string v3, "["

    goto :goto_1

    :cond_1
    const-string v3, ", "

    :goto_1
    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    aget v3, p2, v0

    invoke-virtual {p0, v3}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->getName(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_2
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    const-string v1, "],\n"

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    return-void
.end method

.method writeXmlConstraint(Ljava/lang/String;I)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0
        }
        names = {
            "str",
            "leftToLeft"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    const/4 v0, -0x1

    if-ne p2, v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\n       "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->writer:Ljava/io/Writer;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "=\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p2}, Landroidx/constraintlayout/widget/ConstraintSet$WriteXmlEngine;->getName(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    return-void
.end method
