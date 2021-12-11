package com.google.android.material.bottomnavigation;

import android.content.Context;
import com.google.android.material.C0552R;
import com.google.android.material.navigation.NavigationBarItemView;

public class BottomNavigationItemView extends NavigationBarItemView {
    public BottomNavigationItemView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.material.navigation.NavigationBarItemView
    public int getItemLayoutResId() {
        return C0552R.layout.design_bottom_navigation_item;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.material.navigation.NavigationBarItemView
    public int getItemDefaultMarginResId() {
        return C0552R.dimen.design_bottom_navigation_margin;
    }
}
