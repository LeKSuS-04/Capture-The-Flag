.class abstract Lcom/google/android/material/datepicker/DateFormatTextWatcher;
.super Lcom/google/android/material/internal/TextWatcherAdapter;
.source "DateFormatTextWatcher.java"


# static fields
.field private static final VALIDATION_DELAY:I = 0x3e8


# instance fields
.field private final constraints:Lcom/google/android/material/datepicker/CalendarConstraints;

.field private final dateFormat:Ljava/text/DateFormat;

.field private final outOfRange:Ljava/lang/String;

.field private final setErrorCallback:Ljava/lang/Runnable;

.field private setRangeErrorCallback:Ljava/lang/Runnable;

.field private final textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;


# direct methods
.method constructor <init>(Ljava/lang/String;Ljava/text/DateFormat;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/datepicker/CalendarConstraints;)V
    .locals 2

    invoke-direct {p0}, Lcom/google/android/material/internal/TextWatcherAdapter;-><init>()V

    iput-object p2, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->dateFormat:Ljava/text/DateFormat;

    iput-object p3, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;

    iput-object p4, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->constraints:Lcom/google/android/material/datepicker/CalendarConstraints;

    invoke-virtual {p3}, Lcom/google/android/material/textfield/TextInputLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    sget v1, Lcom/google/android/material/R$string;->mtrl_picker_out_of_range:I

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->outOfRange:Ljava/lang/String;

    new-instance v0, Lcom/google/android/material/datepicker/DateFormatTextWatcher$1;

    invoke-direct {v0, p0, p1}, Lcom/google/android/material/datepicker/DateFormatTextWatcher$1;-><init>(Lcom/google/android/material/datepicker/DateFormatTextWatcher;Ljava/lang/String;)V

    iput-object v0, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->setErrorCallback:Ljava/lang/Runnable;

    return-void
.end method

.method static synthetic access$000(Lcom/google/android/material/datepicker/DateFormatTextWatcher;)Lcom/google/android/material/textfield/TextInputLayout;
    .locals 1

    iget-object v0, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;

    return-object v0
.end method

.method static synthetic access$100(Lcom/google/android/material/datepicker/DateFormatTextWatcher;)Ljava/text/DateFormat;
    .locals 1

    iget-object v0, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->dateFormat:Ljava/text/DateFormat;

    return-object v0
.end method

.method static synthetic access$200(Lcom/google/android/material/datepicker/DateFormatTextWatcher;)Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->outOfRange:Ljava/lang/String;

    return-object v0
.end method

.method private createRangeErrorCallback(J)Ljava/lang/Runnable;
    .locals 1

    new-instance v0, Lcom/google/android/material/datepicker/DateFormatTextWatcher$2;

    invoke-direct {v0, p0, p1, p2}, Lcom/google/android/material/datepicker/DateFormatTextWatcher$2;-><init>(Lcom/google/android/material/datepicker/DateFormatTextWatcher;J)V

    return-object v0
.end method


# virtual methods
.method onInvalidDate()V
    .locals 0

    return-void
.end method

.method public onTextChanged(Ljava/lang/CharSequence;III)V
    .locals 5

    iget-object v0, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;

    iget-object v1, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->setErrorCallback:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Lcom/google/android/material/textfield/TextInputLayout;->removeCallbacks(Ljava/lang/Runnable;)Z

    iget-object v0, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;

    iget-object v1, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->setRangeErrorCallback:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Lcom/google/android/material/textfield/TextInputLayout;->removeCallbacks(Ljava/lang/Runnable;)Z

    iget-object v0, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/google/android/material/textfield/TextInputLayout;->setError(Ljava/lang/CharSequence;)V

    invoke-virtual {p0, v1}, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->onValidDate(Ljava/lang/Long;)V

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    :cond_0
    :try_start_0
    iget-object v0, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->dateFormat:Ljava/text/DateFormat;

    invoke-interface {p1}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/text/DateFormat;->parse(Ljava/lang/String;)Ljava/util/Date;

    move-result-object v0

    iget-object v2, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;

    invoke-virtual {v2, v1}, Lcom/google/android/material/textfield/TextInputLayout;->setError(Ljava/lang/CharSequence;)V

    invoke-virtual {v0}, Ljava/util/Date;->getTime()J

    move-result-wide v1

    iget-object v3, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->constraints:Lcom/google/android/material/datepicker/CalendarConstraints;

    invoke-virtual {v3}, Lcom/google/android/material/datepicker/CalendarConstraints;->getDateValidator()Lcom/google/android/material/datepicker/CalendarConstraints$DateValidator;

    move-result-object v3

    invoke-interface {v3, v1, v2}, Lcom/google/android/material/datepicker/CalendarConstraints$DateValidator;->isValid(J)Z

    move-result v3

    if-eqz v3, :cond_1

    iget-object v3, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->constraints:Lcom/google/android/material/datepicker/CalendarConstraints;

    invoke-virtual {v3, v1, v2}, Lcom/google/android/material/datepicker/CalendarConstraints;->isWithinBounds(J)Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-virtual {v0}, Ljava/util/Date;->getTime()J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {p0, v3}, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->onValidDate(Ljava/lang/Long;)V

    return-void

    :cond_1
    invoke-direct {p0, v1, v2}, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->createRangeErrorCallback(J)Ljava/lang/Runnable;

    move-result-object v3

    iput-object v3, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->setRangeErrorCallback:Ljava/lang/Runnable;

    iget-object v4, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;

    invoke-virtual {p0, v4, v3}, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->runValidation(Landroid/view/View;Ljava/lang/Runnable;)V
    :try_end_0
    .catch Ljava/text/ParseException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    iget-object v1, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->textInputLayout:Lcom/google/android/material/textfield/TextInputLayout;

    iget-object v2, p0, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->setErrorCallback:Ljava/lang/Runnable;

    invoke-virtual {p0, v1, v2}, Lcom/google/android/material/datepicker/DateFormatTextWatcher;->runValidation(Landroid/view/View;Ljava/lang/Runnable;)V

    :goto_0
    return-void
.end method

.method abstract onValidDate(Ljava/lang/Long;)V
.end method

.method public runValidation(Landroid/view/View;Ljava/lang/Runnable;)V
    .locals 2

    const-wide/16 v0, 0x3e8

    invoke-virtual {p1, p2, v0, v1}, Landroid/view/View;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method
