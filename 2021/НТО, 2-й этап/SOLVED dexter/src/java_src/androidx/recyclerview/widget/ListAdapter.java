package androidx.recyclerview.widget;

import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

public abstract class ListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    final AsyncListDiffer<T> mDiffer;
    private final AsyncListDiffer.ListListener<T> mListener;

    protected ListAdapter(DiffUtil.ItemCallback<T> diffCallback) {
        C04351 r0 = new AsyncListDiffer.ListListener<T>() {
            /* class androidx.recyclerview.widget.ListAdapter.C04351 */

            @Override // androidx.recyclerview.widget.AsyncListDiffer.ListListener
            public void onCurrentListChanged(List<T> previousList, List<T> currentList) {
                ListAdapter.this.onCurrentListChanged(previousList, currentList);
            }
        };
        this.mListener = r0;
        AsyncListDiffer<T> asyncListDiffer = new AsyncListDiffer<>(new AdapterListUpdateCallback(this), new AsyncDifferConfig.Builder(diffCallback).build());
        this.mDiffer = asyncListDiffer;
        asyncListDiffer.addListListener(r0);
    }

    protected ListAdapter(AsyncDifferConfig<T> config) {
        C04351 r0 = new AsyncListDiffer.ListListener<T>() {
            /* class androidx.recyclerview.widget.ListAdapter.C04351 */

            @Override // androidx.recyclerview.widget.AsyncListDiffer.ListListener
            public void onCurrentListChanged(List<T> previousList, List<T> currentList) {
                ListAdapter.this.onCurrentListChanged(previousList, currentList);
            }
        };
        this.mListener = r0;
        AsyncListDiffer<T> asyncListDiffer = new AsyncListDiffer<>(new AdapterListUpdateCallback(this), config);
        this.mDiffer = asyncListDiffer;
        asyncListDiffer.addListListener(r0);
    }

    public void submitList(List<T> list) {
        this.mDiffer.submitList(list);
    }

    public void submitList(List<T> list, Runnable commitCallback) {
        this.mDiffer.submitList(list, commitCallback);
    }

    /* access modifiers changed from: protected */
    public T getItem(int position) {
        return this.mDiffer.getCurrentList().get(position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDiffer.getCurrentList().size();
    }

    public List<T> getCurrentList() {
        return this.mDiffer.getCurrentList();
    }

    public void onCurrentListChanged(List<T> list, List<T> list2) {
    }
}
