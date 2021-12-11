.class public Lsage/mode/dexter/MultiDexApplication;
.super Landroid/app/Application;
.source "MultiDexApplication.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 6
    invoke-direct {p0}, Landroid/app/Application;-><init>()V

    return-void
.end method


# virtual methods
.method public attachBaseContext(Landroid/content/Context;)V
    .locals 0
    .param p1, "base"    # Landroid/content/Context;

    .line 9
    invoke-super {p0, p1}, Landroid/app/Application;->attachBaseContext(Landroid/content/Context;)V

    .line 10
    invoke-static {p0}, Lsage/mode/dexter/MultiDex;->install(Landroid/content/Context;)V

    .line 11
    return-void
.end method
