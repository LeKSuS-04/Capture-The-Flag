.class public Landroidx/constraintlayout/utils/widget/MotionButton;
.super Landroidx/appcompat/widget/AppCompatButton;
.source "MotionButton.java"


# instance fields
.field private mPath:Landroid/graphics/Path;

.field mRect:Landroid/graphics/RectF;

.field private mRound:F

.field private mRoundPercent:F

.field mViewOutlineProvider:Landroid/view/ViewOutlineProvider;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "context"
        }
    .end annotation

    invoke-direct {p0, p1}, Landroidx/appcompat/widget/AppCompatButton;-><init>(Landroid/content/Context;)V

    const/4 v0, 0x0

    iput v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRoundPercent:F

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRound:F

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Landroidx/constraintlayout/utils/widget/MotionButton;->init(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0
        }
        names = {
            "context",
            "attrs"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Landroidx/appcompat/widget/AppCompatButton;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    const/4 v0, 0x0

    iput v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRoundPercent:F

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRound:F

    invoke-direct {p0, p1, p2}, Landroidx/constraintlayout/utils/widget/MotionButton;->init(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "context",
            "attrs",
            "defStyleAttr"
        }
    .end annotation

    invoke-direct {p0, p1, p2, p3}, Landroidx/appcompat/widget/AppCompatButton;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    const/4 v0, 0x0

    iput v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRoundPercent:F

    const/high16 v0, 0x7fc00000    # Float.NaN

    iput v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRound:F

    invoke-direct {p0, p1, p2}, Landroidx/constraintlayout/utils/widget/MotionButton;->init(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method static synthetic access$000(Landroidx/constraintlayout/utils/widget/MotionButton;)F
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRoundPercent:F

    return v0
.end method

.method static synthetic access$100(Landroidx/constraintlayout/utils/widget/MotionButton;)F
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRound:F

    return v0
.end method

.method private init(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 7
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0
        }
        names = {
            "context",
            "attrs"
        }
    .end annotation

    const/4 v0, 0x0

    invoke-virtual {p0, v0, v0, v0, v0}, Landroidx/constraintlayout/utils/widget/MotionButton;->setPadding(IIII)V

    if-eqz p2, :cond_3

    invoke-virtual {p0}, Landroidx/constraintlayout/utils/widget/MotionButton;->getContext()Landroid/content/Context;

    move-result-object v0

    sget-object v1, Landroidx/constraintlayout/widget/R$styleable;->ImageFilterView:[I

    invoke-virtual {v0, p2, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/TypedArray;->getIndexCount()I

    move-result v1

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_2

    invoke-virtual {v0, v2}, Landroid/content/res/TypedArray;->getIndex(I)I

    move-result v3

    sget v4, Landroidx/constraintlayout/widget/R$styleable;->ImageFilterView_round:I

    const/4 v5, 0x0

    const/16 v6, 0x15

    if-ne v3, v4, :cond_0

    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v4, v6, :cond_1

    invoke-virtual {v0, v3, v5}, Landroid/content/res/TypedArray;->getDimension(IF)F

    move-result v4

    invoke-virtual {p0, v4}, Landroidx/constraintlayout/utils/widget/MotionButton;->setRound(F)V

    goto :goto_1

    :cond_0
    sget v4, Landroidx/constraintlayout/widget/R$styleable;->ImageFilterView_roundPercent:I

    if-ne v3, v4, :cond_1

    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v4, v6, :cond_1

    invoke-virtual {v0, v3, v5}, Landroid/content/res/TypedArray;->getFloat(IF)F

    move-result v4

    invoke-virtual {p0, v4}, Landroidx/constraintlayout/utils/widget/MotionButton;->setRoundPercent(F)V

    :cond_1
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    :cond_3
    return-void
.end method


# virtual methods
.method public draw(Landroid/graphics/Canvas;)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "canvas"
        }
    .end annotation

    const/4 v0, 0x0

    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x15

    if-ge v1, v2, :cond_0

    iget v1, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRound:F

    const/4 v2, 0x0

    cmpl-float v1, v1, v2

    if-eqz v1, :cond_0

    iget-object v1, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mPath:Landroid/graphics/Path;

    if-eqz v1, :cond_0

    const/4 v0, 0x1

    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    iget-object v1, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mPath:Landroid/graphics/Path;

    invoke-virtual {p1, v1}, Landroid/graphics/Canvas;->clipPath(Landroid/graphics/Path;)Z

    :cond_0
    invoke-super {p0, p1}, Landroidx/appcompat/widget/AppCompatButton;->draw(Landroid/graphics/Canvas;)V

    if-eqz v0, :cond_1

    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    :cond_1
    return-void
.end method

.method public getRound()F
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRound:F

    return v0
.end method

.method public getRoundPercent()F
    .locals 1

    iget v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRoundPercent:F

    return v0
.end method

.method public setRound(F)V
    .locals 8
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "round"
        }
    .end annotation

    invoke-static {p1}, Ljava/lang/Float;->isNaN(F)Z

    move-result v0

    if-eqz v0, :cond_0

    iput p1, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRound:F

    iget v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRoundPercent:F

    const/high16 v1, -0x40800000    # -1.0f

    iput v1, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRoundPercent:F

    invoke-virtual {p0, v0}, Landroidx/constraintlayout/utils/widget/MotionButton;->setRoundPercent(F)V

    return-void

    :cond_0
    iget v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRound:F

    const/4 v1, 0x1

    const/4 v2, 0x0

    cmpl-float v0, v0, p1

    if-eqz v0, :cond_1

    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    :goto_0
    iput p1, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRound:F

    const/16 v3, 0x15

    const/4 v4, 0x0

    cmpl-float v5, p1, v4

    if-eqz v5, :cond_6

    iget-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mPath:Landroid/graphics/Path;

    if-nez v2, :cond_2

    new-instance v2, Landroid/graphics/Path;

    invoke-direct {v2}, Landroid/graphics/Path;-><init>()V

    iput-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mPath:Landroid/graphics/Path;

    :cond_2
    iget-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRect:Landroid/graphics/RectF;

    if-nez v2, :cond_3

    new-instance v2, Landroid/graphics/RectF;

    invoke-direct {v2}, Landroid/graphics/RectF;-><init>()V

    iput-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRect:Landroid/graphics/RectF;

    :cond_3
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v2, v3, :cond_5

    iget-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mViewOutlineProvider:Landroid/view/ViewOutlineProvider;

    if-nez v2, :cond_4

    new-instance v2, Landroidx/constraintlayout/utils/widget/MotionButton$2;

    invoke-direct {v2, p0}, Landroidx/constraintlayout/utils/widget/MotionButton$2;-><init>(Landroidx/constraintlayout/utils/widget/MotionButton;)V

    iput-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mViewOutlineProvider:Landroid/view/ViewOutlineProvider;

    invoke-virtual {p0, v2}, Landroidx/constraintlayout/utils/widget/MotionButton;->setOutlineProvider(Landroid/view/ViewOutlineProvider;)V

    :cond_4
    invoke-virtual {p0, v1}, Landroidx/constraintlayout/utils/widget/MotionButton;->setClipToOutline(Z)V

    :cond_5
    invoke-virtual {p0}, Landroidx/constraintlayout/utils/widget/MotionButton;->getWidth()I

    move-result v1

    invoke-virtual {p0}, Landroidx/constraintlayout/utils/widget/MotionButton;->getHeight()I

    move-result v2

    iget-object v5, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRect:Landroid/graphics/RectF;

    int-to-float v6, v1

    int-to-float v7, v2

    invoke-virtual {v5, v4, v4, v6, v7}, Landroid/graphics/RectF;->set(FFFF)V

    iget-object v4, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mPath:Landroid/graphics/Path;

    invoke-virtual {v4}, Landroid/graphics/Path;->reset()V

    iget-object v4, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mPath:Landroid/graphics/Path;

    iget-object v5, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRect:Landroid/graphics/RectF;

    iget v6, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRound:F

    sget-object v7, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    invoke-virtual {v4, v5, v6, v6, v7}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    goto :goto_1

    :cond_6
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v1, v3, :cond_7

    invoke-virtual {p0, v2}, Landroidx/constraintlayout/utils/widget/MotionButton;->setClipToOutline(Z)V

    :cond_7
    :goto_1
    if-eqz v0, :cond_8

    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v1, v3, :cond_8

    invoke-virtual {p0}, Landroidx/constraintlayout/utils/widget/MotionButton;->invalidateOutline()V

    :cond_8
    return-void
.end method

.method public setRoundPercent(F)V
    .locals 9
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "round"
        }
    .end annotation

    iget v0, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRoundPercent:F

    const/4 v1, 0x1

    const/4 v2, 0x0

    cmpl-float v0, v0, p1

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    iput p1, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRoundPercent:F

    const/16 v3, 0x15

    const/4 v4, 0x0

    cmpl-float v5, p1, v4

    if-eqz v5, :cond_5

    iget-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mPath:Landroid/graphics/Path;

    if-nez v2, :cond_1

    new-instance v2, Landroid/graphics/Path;

    invoke-direct {v2}, Landroid/graphics/Path;-><init>()V

    iput-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mPath:Landroid/graphics/Path;

    :cond_1
    iget-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRect:Landroid/graphics/RectF;

    if-nez v2, :cond_2

    new-instance v2, Landroid/graphics/RectF;

    invoke-direct {v2}, Landroid/graphics/RectF;-><init>()V

    iput-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRect:Landroid/graphics/RectF;

    :cond_2
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v2, v3, :cond_4

    iget-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mViewOutlineProvider:Landroid/view/ViewOutlineProvider;

    if-nez v2, :cond_3

    new-instance v2, Landroidx/constraintlayout/utils/widget/MotionButton$1;

    invoke-direct {v2, p0}, Landroidx/constraintlayout/utils/widget/MotionButton$1;-><init>(Landroidx/constraintlayout/utils/widget/MotionButton;)V

    iput-object v2, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mViewOutlineProvider:Landroid/view/ViewOutlineProvider;

    invoke-virtual {p0, v2}, Landroidx/constraintlayout/utils/widget/MotionButton;->setOutlineProvider(Landroid/view/ViewOutlineProvider;)V

    :cond_3
    invoke-virtual {p0, v1}, Landroidx/constraintlayout/utils/widget/MotionButton;->setClipToOutline(Z)V

    :cond_4
    invoke-virtual {p0}, Landroidx/constraintlayout/utils/widget/MotionButton;->getWidth()I

    move-result v1

    invoke-virtual {p0}, Landroidx/constraintlayout/utils/widget/MotionButton;->getHeight()I

    move-result v2

    invoke-static {v1, v2}, Ljava/lang/Math;->min(II)I

    move-result v5

    int-to-float v5, v5

    iget v6, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRoundPercent:F

    mul-float v5, v5, v6

    const/high16 v6, 0x40000000    # 2.0f

    div-float/2addr v5, v6

    iget-object v6, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRect:Landroid/graphics/RectF;

    int-to-float v7, v1

    int-to-float v8, v2

    invoke-virtual {v6, v4, v4, v7, v8}, Landroid/graphics/RectF;->set(FFFF)V

    iget-object v4, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mPath:Landroid/graphics/Path;

    invoke-virtual {v4}, Landroid/graphics/Path;->reset()V

    iget-object v4, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mPath:Landroid/graphics/Path;

    iget-object v6, p0, Landroidx/constraintlayout/utils/widget/MotionButton;->mRect:Landroid/graphics/RectF;

    sget-object v7, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    invoke-virtual {v4, v6, v5, v5, v7}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    goto :goto_1

    :cond_5
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v1, v3, :cond_6

    invoke-virtual {p0, v2}, Landroidx/constraintlayout/utils/widget/MotionButton;->setClipToOutline(Z)V

    :cond_6
    :goto_1
    if-eqz v0, :cond_7

    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v1, v3, :cond_7

    invoke-virtual {p0}, Landroidx/constraintlayout/utils/widget/MotionButton;->invalidateOutline()V

    :cond_7
    return-void
.end method
