package com.google.android.material.navigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.internal.ParcelableSparseArray;

public class NavigationBarPresenter implements MenuPresenter {

    /* renamed from: id */
    private int f109id;
    private MenuBuilder menu;
    private NavigationBarMenuView menuView;
    private boolean updateSuspended = false;

    public void setMenuView(NavigationBarMenuView menuView2) {
        this.menuView = menuView2;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menu2) {
        this.menu = menu2;
        this.menuView.initialize(menu2);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup root) {
        return this.menuView;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean cleared) {
        if (!this.updateSuspended) {
            if (cleared) {
                this.menuView.buildMenuView();
            } else {
                this.menuView.updateMenuView();
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback cb) {
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menu2, boolean allMenusAreClosing) {
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menu2, MenuItemImpl item) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menu2, MenuItemImpl item) {
        return false;
    }

    public void setId(int id) {
        this.f109id = id;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.f109id;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.selectedItemId = this.menuView.getSelectedItemId();
        savedState.badgeSavedStates = BadgeUtils.createParcelableBadgeStates(this.menuView.getBadgeDrawables());
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            this.menuView.tryRestoreSelectedItemId(((SavedState) state).selectedItemId);
            this.menuView.setBadgeDrawables(BadgeUtils.createBadgeDrawablesFromSavedStates(this.menuView.getContext(), ((SavedState) state).badgeSavedStates));
        }
    }

    public void setUpdateSuspended(boolean updateSuspended2) {
        this.updateSuspended = updateSuspended2;
    }

    /* access modifiers changed from: package-private */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* class com.google.android.material.navigation.NavigationBarPresenter.SavedState.C06721 */

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        ParcelableSparseArray badgeSavedStates;
        int selectedItemId;

        SavedState() {
        }

        SavedState(Parcel in) {
            this.selectedItemId = in.readInt();
            this.badgeSavedStates = (ParcelableSparseArray) in.readParcelable(getClass().getClassLoader());
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(this.selectedItemId);
            out.writeParcelable(this.badgeSavedStates, 0);
        }
    }
}
