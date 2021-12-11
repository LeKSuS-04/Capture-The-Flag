package androidx.constraintlayout.core.widgets;

public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f43x;

    /* renamed from: y */
    public int f44y;

    public void setBounds(int x, int y, int width2, int height2) {
        this.f43x = x;
        this.f44y = y;
        this.width = width2;
        this.height = height2;
    }

    /* access modifiers changed from: package-private */
    public void grow(int w, int h) {
        this.f43x -= w;
        this.f44y -= h;
        this.width += w * 2;
        this.height += h * 2;
    }

    /* access modifiers changed from: package-private */
    public boolean intersects(Rectangle bounds) {
        int i;
        int i2;
        int i3 = this.f43x;
        int i4 = bounds.f43x;
        return i3 >= i4 && i3 < i4 + bounds.width && (i = this.f44y) >= (i2 = bounds.f44y) && i < i2 + bounds.height;
    }

    public boolean contains(int x, int y) {
        int i;
        int i2 = this.f43x;
        return x >= i2 && x < i2 + this.width && y >= (i = this.f44y) && y < i + this.height;
    }

    public int getCenterX() {
        return (this.f43x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f44y + this.height) / 2;
    }
}
