package androidx.savedstate;

import android.view.View;
import android.view.ViewParent;

public final class ViewTreeSavedStateRegistryOwner {
    private ViewTreeSavedStateRegistryOwner() {
    }

    public static void set(View view, SavedStateRegistryOwner owner) {
        view.setTag(C0457R.C0458id.view_tree_saved_state_registry_owner, owner);
    }

    public static SavedStateRegistryOwner get(View view) {
        SavedStateRegistryOwner found = (SavedStateRegistryOwner) view.getTag(C0457R.C0458id.view_tree_saved_state_registry_owner);
        if (found != null) {
            return found;
        }
        ViewParent parent = view.getParent();
        while (found == null && (parent instanceof View)) {
            View parentView = (View) parent;
            found = (SavedStateRegistryOwner) parentView.getTag(C0457R.C0458id.view_tree_saved_state_registry_owner);
            parent = parentView.getParent();
        }
        return found;
    }
}
