.class public Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;
.super Lcom/google/android/material/navigation/NavigationBarMenuView;
.source "BottomNavigationMenuView.java"


# instance fields
.field private final activeItemMaxWidth:I

.field private final activeItemMinWidth:I

.field private final inactiveItemMaxWidth:I

.field private final inactiveItemMinWidth:I

.field private final itemHeight:I

.field private itemHorizontalTranslationEnabled:Z

.field private tempChildWidths:[I


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 3

    invoke-direct {p0, p1}, Lcom/google/android/material/navigation/NavigationBarMenuView;-><init>(Landroid/content/Context;)V

    new-instance v0, Landroid/widget/FrameLayout$LayoutParams;

    const/4 v1, -0x2

    invoke-direct {v0, v1, v1}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    const/16 v1, 0x11

    iput v1, v0, Landroid/widget/FrameLayout$LayoutParams;->gravity:I

    invoke-virtual {p0, v0}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    invoke-virtual {p0}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/google/android/material/R$dimen;->design_bottom_navigation_item_max_width:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    iput v2, p0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->inactiveItemMaxWidth:I

    sget v2, Lcom/google/android/material/R$dimen;->design_bottom_navigation_item_min_width:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    iput v2, p0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->inactiveItemMinWidth:I

    sget v2, Lcom/google/android/material/R$dimen;->design_bottom_navigation_active_item_max_width:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    iput v2, p0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->activeItemMaxWidth:I

    sget v2, Lcom/google/android/material/R$dimen;->design_bottom_navigation_active_item_min_width:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    iput v2, p0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->activeItemMinWidth:I

    sget v2, Lcom/google/android/material/R$dimen;->design_bottom_navigation_height:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    iput v2, p0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->itemHeight:I

    const/4 v2, 0x5

    new-array v2, v2, [I

    iput-object v2, p0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->tempChildWidths:[I

    return-void
.end method


# virtual methods
.method protected createNavigationBarItemView(Landroid/content/Context;)Lcom/google/android/material/navigation/NavigationBarItemView;
    .locals 1

    new-instance v0, Lcom/google/android/material/bottomnavigation/BottomNavigationItemView;

    invoke-direct {v0, p1}, Lcom/google/android/material/bottomnavigation/BottomNavigationItemView;-><init>(Landroid/content/Context;)V

    return-object v0
.end method

.method public isItemHorizontalTranslationEnabled()Z
    .locals 1

    iget-boolean v0, p0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->itemHorizontalTranslationEnabled:Z

    return v0
.end method

.method protected onLayout(ZIIII)V
    .locals 9

    invoke-virtual {p0}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getChildCount()I

    move-result v0

    sub-int v1, p4, p2

    sub-int v2, p5, p3

    const/4 v3, 0x0

    const/4 v4, 0x0

    :goto_0
    if-ge v4, v0, :cond_2

    invoke-virtual {p0, v4}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getChildAt(I)Landroid/view/View;

    move-result-object v5

    invoke-virtual {v5}, Landroid/view/View;->getVisibility()I

    move-result v6

    const/16 v7, 0x8

    if-ne v6, v7, :cond_0

    goto :goto_2

    :cond_0
    invoke-static {p0}, Landroidx/core/view/ViewCompat;->getLayoutDirection(Landroid/view/View;)I

    move-result v6

    const/4 v7, 0x0

    const/4 v8, 0x1

    if-ne v6, v8, :cond_1

    sub-int v6, v1, v3

    invoke-virtual {v5}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    sub-int/2addr v6, v8

    sub-int v8, v1, v3

    invoke-virtual {v5, v6, v7, v8, v2}, Landroid/view/View;->layout(IIII)V

    goto :goto_1

    :cond_1
    invoke-virtual {v5}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    add-int/2addr v6, v3

    invoke-virtual {v5, v3, v7, v6, v2}, Landroid/view/View;->layout(IIII)V

    :goto_1
    invoke-virtual {v5}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    add-int/2addr v3, v6

    :goto_2
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_2
    return-void
.end method

.method protected onMeasure(II)V
    .locals 21

    move-object/from16 v0, p0

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getMenu()Landroidx/appcompat/view/menu/MenuBuilder;

    move-result-object v1

    invoke-static/range {p1 .. p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v2

    invoke-virtual {v1}, Landroidx/appcompat/view/menu/MenuBuilder;->getVisibleItems()Ljava/util/ArrayList;

    move-result-object v3

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getChildCount()I

    move-result v4

    iget v5, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->itemHeight:I

    const/high16 v6, 0x40000000    # 2.0f

    invoke-static {v5, v6}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v5

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getLabelVisibilityMode()I

    move-result v7

    invoke-virtual {v0, v7, v3}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->isShifting(II)Z

    move-result v7

    const/16 v9, 0x8

    if-eqz v7, :cond_7

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->isItemHorizontalTranslationEnabled()Z

    move-result v7

    if-eqz v7, :cond_7

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getSelectedItemPosition()I

    move-result v7

    invoke-virtual {v0, v7}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getChildAt(I)Landroid/view/View;

    move-result-object v7

    iget v11, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->activeItemMinWidth:I

    invoke-virtual {v7}, Landroid/view/View;->getVisibility()I

    move-result v12

    if-eq v12, v9, :cond_0

    iget v12, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->activeItemMaxWidth:I

    const/high16 v13, -0x80000000

    invoke-static {v12, v13}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v12

    invoke-virtual {v7, v12, v5}, Landroid/view/View;->measure(II)V

    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v12

    invoke-static {v11, v12}, Ljava/lang/Math;->max(II)I

    move-result v11

    :cond_0
    invoke-virtual {v7}, Landroid/view/View;->getVisibility()I

    move-result v12

    if-eq v12, v9, :cond_1

    const/4 v12, 0x1

    goto :goto_0

    :cond_1
    const/4 v12, 0x0

    :goto_0
    sub-int v12, v3, v12

    iget v13, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->inactiveItemMinWidth:I

    mul-int v13, v13, v12

    sub-int v13, v2, v13

    iget v14, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->activeItemMaxWidth:I

    invoke-static {v11, v14}, Ljava/lang/Math;->min(II)I

    move-result v14

    invoke-static {v13, v14}, Ljava/lang/Math;->min(II)I

    move-result v14

    sub-int v15, v2, v14

    if-nez v12, :cond_2

    const/16 v16, 0x1

    goto :goto_1

    :cond_2
    move/from16 v16, v12

    :goto_1
    div-int v15, v15, v16

    iget v6, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->inactiveItemMaxWidth:I

    invoke-static {v15, v6}, Ljava/lang/Math;->min(II)I

    move-result v6

    sub-int v17, v2, v14

    mul-int v18, v6, v12

    sub-int v17, v17, v18

    const/16 v18, 0x0

    move/from16 v8, v18

    :goto_2
    if-ge v8, v4, :cond_6

    invoke-virtual {v0, v8}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getChildAt(I)Landroid/view/View;

    move-result-object v19

    invoke-virtual/range {v19 .. v19}, Landroid/view/View;->getVisibility()I

    move-result v10

    if-eq v10, v9, :cond_4

    iget-object v10, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->tempChildWidths:[I

    invoke-virtual/range {p0 .. p0}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getSelectedItemPosition()I

    move-result v9

    if-ne v8, v9, :cond_3

    move v9, v14

    goto :goto_3

    :cond_3
    move v9, v6

    :goto_3
    aput v9, v10, v8

    if-lez v17, :cond_5

    iget-object v9, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->tempChildWidths:[I

    aget v10, v9, v8

    const/16 v20, 0x1

    add-int/lit8 v10, v10, 0x1

    aput v10, v9, v8

    add-int/lit8 v17, v17, -0x1

    goto :goto_4

    :cond_4
    iget-object v9, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->tempChildWidths:[I

    const/4 v10, 0x0

    aput v10, v9, v8

    :cond_5
    :goto_4
    add-int/lit8 v8, v8, 0x1

    const/16 v9, 0x8

    goto :goto_2

    :cond_6
    goto :goto_8

    :cond_7
    if-nez v3, :cond_8

    const/4 v6, 0x1

    goto :goto_5

    :cond_8
    move v6, v3

    :goto_5
    div-int v6, v2, v6

    iget v7, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->activeItemMaxWidth:I

    invoke-static {v6, v7}, Ljava/lang/Math;->min(II)I

    move-result v7

    mul-int v8, v7, v3

    sub-int v8, v2, v8

    const/4 v9, 0x0

    :goto_6
    if-ge v9, v4, :cond_b

    invoke-virtual {v0, v9}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getChildAt(I)Landroid/view/View;

    move-result-object v10

    invoke-virtual {v10}, Landroid/view/View;->getVisibility()I

    move-result v10

    const/16 v11, 0x8

    if-eq v10, v11, :cond_a

    iget-object v10, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->tempChildWidths:[I

    aput v7, v10, v9

    if-lez v8, :cond_9

    aget v11, v10, v9

    const/4 v12, 0x1

    add-int/2addr v11, v12

    aput v11, v10, v9

    add-int/lit8 v8, v8, -0x1

    goto :goto_7

    :cond_9
    const/4 v12, 0x1

    goto :goto_7

    :cond_a
    const/4 v12, 0x1

    iget-object v10, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->tempChildWidths:[I

    const/4 v11, 0x0

    aput v11, v10, v9

    :goto_7
    add-int/lit8 v9, v9, 0x1

    goto :goto_6

    :cond_b
    :goto_8
    const/4 v6, 0x0

    const/4 v7, 0x0

    :goto_9
    if-ge v7, v4, :cond_d

    invoke-virtual {v0, v7}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->getChildAt(I)Landroid/view/View;

    move-result-object v8

    invoke-virtual {v8}, Landroid/view/View;->getVisibility()I

    move-result v9

    const/16 v10, 0x8

    if-ne v9, v10, :cond_c

    goto :goto_a

    :cond_c
    iget-object v9, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->tempChildWidths:[I

    aget v9, v9, v7

    const/high16 v11, 0x40000000    # 2.0f

    invoke-static {v9, v11}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v9

    invoke-virtual {v8, v9, v5}, Landroid/view/View;->measure(II)V

    invoke-virtual {v8}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v9

    invoke-virtual {v8}, Landroid/view/View;->getMeasuredWidth()I

    move-result v11

    iput v11, v9, Landroid/view/ViewGroup$LayoutParams;->width:I

    invoke-virtual {v8}, Landroid/view/View;->getMeasuredWidth()I

    move-result v11

    add-int/2addr v6, v11

    :goto_a
    add-int/lit8 v7, v7, 0x1

    goto :goto_9

    :cond_d
    nop

    const/high16 v7, 0x40000000    # 2.0f

    invoke-static {v6, v7}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v7

    const/4 v8, 0x0

    invoke-static {v6, v7, v8}, Landroid/view/View;->resolveSizeAndState(III)I

    move-result v7

    iget v9, v0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->itemHeight:I

    invoke-static {v9, v5, v8}, Landroid/view/View;->resolveSizeAndState(III)I

    move-result v8

    invoke-virtual {v0, v7, v8}, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->setMeasuredDimension(II)V

    return-void
.end method

.method public setItemHorizontalTranslationEnabled(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/google/android/material/bottomnavigation/BottomNavigationMenuView;->itemHorizontalTranslationEnabled:Z

    return-void
.end method
