package androidx.lifecycle;

import androidx.arch.core.util.Function;

public class Transformations {
    private Transformations() {
    }

    public static <X, Y> LiveData<Y> map(LiveData<X> source, final Function<X, Y> mapFunction) {
        final MediatorLiveData<Y> result = new MediatorLiveData<>();
        result.addSource(source, new Observer<X>() {
            /* class androidx.lifecycle.Transformations.C03791 */

            @Override // androidx.lifecycle.Observer
            public void onChanged(X x) {
                result.setValue(mapFunction.apply(x));
            }
        });
        return result;
    }

    public static <X, Y> LiveData<Y> switchMap(LiveData<X> source, final Function<X, LiveData<Y>> switchMapFunction) {
        final MediatorLiveData<Y> result = new MediatorLiveData<>();
        result.addSource(source, new Observer<X>() {
            /* class androidx.lifecycle.Transformations.C03802 */
            LiveData<Y> mSource;

            @Override // androidx.lifecycle.Observer
            public void onChanged(X x) {
                LiveData<Y> newLiveData = (LiveData) switchMapFunction.apply(x);
                LiveData<Y> liveData = this.mSource;
                if (liveData != newLiveData) {
                    if (liveData != null) {
                        result.removeSource(liveData);
                    }
                    this.mSource = newLiveData;
                    if (newLiveData != null) {
                        result.addSource(newLiveData, new Observer<Y>() {
                            /* class androidx.lifecycle.Transformations.C03802.C03811 */

                            @Override // androidx.lifecycle.Observer
                            public void onChanged(Y y) {
                                result.setValue(y);
                            }
                        });
                    }
                }
            }
        });
        return result;
    }
}
