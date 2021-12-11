package androidx.lifecycle;

import android.view.View;
import android.view.ViewParent;
import androidx.lifecycle.runtime.C0384R;

public class ViewTreeLifecycleOwner {
    private ViewTreeLifecycleOwner() {
    }

    public static void set(View view, LifecycleOwner lifecycleOwner) {
        view.setTag(C0384R.C0385id.view_tree_lifecycle_owner, lifecycleOwner);
    }

    public static LifecycleOwner get(View view) {
        LifecycleOwner found = (LifecycleOwner) view.getTag(C0384R.C0385id.view_tree_lifecycle_owner);
        if (found != null) {
            return found;
        }
        ViewParent parent = view.getParent();
        while (found == null && (parent instanceof View)) {
            View parentView = (View) parent;
            found = (LifecycleOwner) parentView.getTag(C0384R.C0385id.view_tree_lifecycle_owner);
            parent = parentView.getParent();
        }
        return found;
    }
}
