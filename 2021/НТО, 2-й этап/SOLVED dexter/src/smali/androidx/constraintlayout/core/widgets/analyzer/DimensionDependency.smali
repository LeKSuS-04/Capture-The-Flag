.class Landroidx/constraintlayout/core/widgets/analyzer/DimensionDependency;
.super Landroidx/constraintlayout/core/widgets/analyzer/DependencyNode;
.source "DimensionDependency.java"


# instance fields
.field public wrapValue:I


# direct methods
.method public constructor <init>(Landroidx/constraintlayout/core/widgets/analyzer/WidgetRun;)V
    .locals 1

    invoke-direct {p0, p1}, Landroidx/constraintlayout/core/widgets/analyzer/DependencyNode;-><init>(Landroidx/constraintlayout/core/widgets/analyzer/WidgetRun;)V

    instance-of v0, p1, Landroidx/constraintlayout/core/widgets/analyzer/HorizontalWidgetRun;

    if-eqz v0, :cond_0

    sget-object v0, Landroidx/constraintlayout/core/widgets/analyzer/DependencyNode$Type;->HORIZONTAL_DIMENSION:Landroidx/constraintlayout/core/widgets/analyzer/DependencyNode$Type;

    iput-object v0, p0, Landroidx/constraintlayout/core/widgets/analyzer/DimensionDependency;->type:Landroidx/constraintlayout/core/widgets/analyzer/DependencyNode$Type;

    goto :goto_0

    :cond_0
    sget-object v0, Landroidx/constraintlayout/core/widgets/analyzer/DependencyNode$Type;->VERTICAL_DIMENSION:Landroidx/constraintlayout/core/widgets/analyzer/DependencyNode$Type;

    iput-object v0, p0, Landroidx/constraintlayout/core/widgets/analyzer/DimensionDependency;->type:Landroidx/constraintlayout/core/widgets/analyzer/DependencyNode$Type;

    :goto_0
    return-void
.end method


# virtual methods
.method public resolve(I)V
    .locals 2

    iget-boolean v0, p0, Landroidx/constraintlayout/core/widgets/analyzer/DimensionDependency;->resolved:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    iput-boolean v0, p0, Landroidx/constraintlayout/core/widgets/analyzer/DimensionDependency;->resolved:Z

    iput p1, p0, Landroidx/constraintlayout/core/widgets/analyzer/DimensionDependency;->value:I

    iget-object v0, p0, Landroidx/constraintlayout/core/widgets/analyzer/DimensionDependency;->dependencies:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/constraintlayout/core/widgets/analyzer/Dependency;

    invoke-interface {v1, v1}, Landroidx/constraintlayout/core/widgets/analyzer/Dependency;->update(Landroidx/constraintlayout/core/widgets/analyzer/Dependency;)V

    goto :goto_0

    :cond_1
    return-void
.end method
