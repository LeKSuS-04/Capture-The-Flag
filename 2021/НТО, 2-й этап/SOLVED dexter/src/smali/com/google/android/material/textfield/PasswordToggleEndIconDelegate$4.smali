.class Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate$4;
.super Ljava/lang/Object;
.source "PasswordToggleEndIconDelegate.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;->initialize()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;


# direct methods
.method constructor <init>(Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate$4;->this$0:Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 3

    iget-object v0, p0, Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate$4;->this$0:Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;

    iget-object v0, v0, Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;->textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;

    invoke-virtual {v0}, Lcom/google/android/material/textfield/TextInputLayout;->getEditText()Landroid/widget/EditText;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {v0}, Landroid/widget/EditText;->getSelectionEnd()I

    move-result v1

    iget-object v2, p0, Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate$4;->this$0:Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;

    invoke-static {v2}, Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;->access$000(Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;)Z

    move-result v2

    if-eqz v2, :cond_1

    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Landroid/widget/EditText;->setTransformationMethod(Landroid/text/method/TransformationMethod;)V

    goto :goto_0

    :cond_1
    invoke-static {}, Landroid/text/method/PasswordTransformationMethod;->getInstance()Landroid/text/method/PasswordTransformationMethod;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/widget/EditText;->setTransformationMethod(Landroid/text/method/TransformationMethod;)V

    :goto_0
    if-ltz v1, :cond_2

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setSelection(I)V

    :cond_2
    iget-object v2, p0, Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate$4;->this$0:Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;

    iget-object v2, v2, Lcom/google/android/material/textfield/PasswordToggleEndIconDelegate;->textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;

    invoke-virtual {v2}, Lcom/google/android/material/textfield/TextInputLayout;->refreshEndIconDrawableState()V

    return-void
.end method
