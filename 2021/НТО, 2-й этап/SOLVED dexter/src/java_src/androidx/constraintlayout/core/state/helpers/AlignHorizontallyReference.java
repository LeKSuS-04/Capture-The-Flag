package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State;
import java.util.Iterator;

public class AlignHorizontallyReference extends HelperReference {
    private float mBias = 0.5f;

    public AlignHorizontallyReference(State state) {
        super(state, State.Helper.ALIGN_VERTICALLY);
    }

    @Override // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.helpers.Facade, androidx.constraintlayout.core.state.Reference, androidx.constraintlayout.core.state.ConstraintReference
    public void apply() {
        Iterator it = this.mReferences.iterator();
        while (it.hasNext()) {
            ConstraintReference reference = this.mState.constraints(it.next());
            reference.clearHorizontal();
            if (this.mStartToStart != null) {
                reference.startToStart(this.mStartToStart);
            } else if (this.mStartToEnd != null) {
                reference.startToEnd(this.mStartToEnd);
            } else {
                reference.startToStart(State.PARENT);
            }
            if (this.mEndToStart != null) {
                reference.endToStart(this.mEndToStart);
            } else if (this.mEndToEnd != null) {
                reference.endToEnd(this.mEndToEnd);
            } else {
                reference.endToEnd(State.PARENT);
            }
            float f = this.mBias;
            if (f != 0.5f) {
                reference.horizontalBias(f);
            }
        }
    }
}
