package androidx.customview.view;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class AbsSavedState implements Parcelable {
    public static final Parcelable.Creator<AbsSavedState> CREATOR = new Parcelable.ClassLoaderCreator<AbsSavedState>() {
        /* class androidx.customview.view.AbsSavedState.C02532 */

        @Override // android.os.Parcelable.ClassLoaderCreator
        public AbsSavedState createFromParcel(Parcel in, ClassLoader loader) {
            if (in.readParcelable(loader) == null) {
                return AbsSavedState.EMPTY_STATE;
            }
            throw new IllegalStateException("superState must be null");
        }

        @Override // android.os.Parcelable.Creator
        public AbsSavedState createFromParcel(Parcel in) {
            return createFromParcel(in, (ClassLoader) null);
        }

        @Override // android.os.Parcelable.Creator
        public AbsSavedState[] newArray(int size) {
            return new AbsSavedState[size];
        }
    };
    public static final AbsSavedState EMPTY_STATE = new AbsSavedState() {
        /* class androidx.customview.view.AbsSavedState.C02521 */
    };
    private final Parcelable mSuperState;

    private AbsSavedState() {
        this.mSuperState = null;
    }

    protected AbsSavedState(Parcelable superState) {
        if (superState != null) {
            this.mSuperState = superState != EMPTY_STATE ? superState : null;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    protected AbsSavedState(Parcel source) {
        this(source, null);
    }

    protected AbsSavedState(Parcel source, ClassLoader loader) {
        Parcelable superState = source.readParcelable(loader);
        this.mSuperState = superState != null ? superState : EMPTY_STATE;
    }

    public final Parcelable getSuperState() {
        return this.mSuperState;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mSuperState, flags);
    }
}
