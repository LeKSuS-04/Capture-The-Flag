package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* access modifiers changed from: package-private */
public final class PageTransformerAdapter extends ViewPager2.OnPageChangeCallback {
    private final LinearLayoutManager mLayoutManager;
    private ViewPager2.PageTransformer mPageTransformer;

    PageTransformerAdapter(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    /* access modifiers changed from: package-private */
    public ViewPager2.PageTransformer getPageTransformer() {
        return this.mPageTransformer;
    }

    /* access modifiers changed from: package-private */
    public void setPageTransformer(ViewPager2.PageTransformer transformer) {
        this.mPageTransformer = transformer;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (this.mPageTransformer != null) {
            float transformOffset = -positionOffset;
            for (int i = 0; i < this.mLayoutManager.getChildCount(); i++) {
                View view = this.mLayoutManager.getChildAt(i);
                if (view != null) {
                    this.mPageTransformer.transformPage(view, ((float) (this.mLayoutManager.getPosition(view) - position)) + transformOffset);
                } else {
                    throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i), Integer.valueOf(this.mLayoutManager.getChildCount())));
                }
            }
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageSelected(int position) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int state) {
    }
}
