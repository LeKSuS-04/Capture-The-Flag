package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.C0552R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MaterialButtonToggleGroup extends LinearLayout {
    private static final int DEF_STYLE_RES = C0552R.style.Widget_MaterialComponents_MaterialButtonToggleGroup;
    private static final String LOG_TAG = MaterialButtonToggleGroup.class.getSimpleName();
    private int checkedId;
    private final CheckedStateTracker checkedStateTracker;
    private Integer[] childOrder;
    private final Comparator<MaterialButton> childOrderComparator;
    private final LinkedHashSet<OnButtonCheckedListener> onButtonCheckedListeners;
    private final List<CornerData> originalCornerData;
    private final PressedStateTracker pressedStateTracker;
    private boolean selectionRequired;
    private boolean singleSelection;
    private boolean skipCheckedStateTracker;

    public interface OnButtonCheckedListener {
        void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z);
    }

    public MaterialButtonToggleGroup(Context context) {
        this(context, null);
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attrs) {
        this(context, attrs, C0552R.attr.materialButtonToggleGroupStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, r4), attrs, defStyleAttr);
        int i = DEF_STYLE_RES;
        this.originalCornerData = new ArrayList();
        this.checkedStateTracker = new CheckedStateTracker();
        this.pressedStateTracker = new PressedStateTracker();
        this.onButtonCheckedListeners = new LinkedHashSet<>();
        this.childOrderComparator = new Comparator<MaterialButton>() {
            /* class com.google.android.material.button.MaterialButtonToggleGroup.C05971 */

            public int compare(MaterialButton v1, MaterialButton v2) {
                int checked = Boolean.valueOf(v1.isChecked()).compareTo(Boolean.valueOf(v2.isChecked()));
                if (checked != 0) {
                    return checked;
                }
                int stateful = Boolean.valueOf(v1.isPressed()).compareTo(Boolean.valueOf(v2.isPressed()));
                if (stateful != 0) {
                    return stateful;
                }
                return Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(v1)).compareTo(Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(v2)));
            }
        };
        this.skipCheckedStateTracker = false;
        TypedArray attributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attrs, C0552R.styleable.MaterialButtonToggleGroup, defStyleAttr, i, new int[0]);
        setSingleSelection(attributes.getBoolean(C0552R.styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.checkedId = attributes.getResourceId(C0552R.styleable.MaterialButtonToggleGroup_checkedButton, -1);
        this.selectionRequired = attributes.getBoolean(C0552R.styleable.MaterialButtonToggleGroup_selectionRequired, false);
        setChildrenDrawingOrderEnabled(true);
        attributes.recycle();
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        int i = this.checkedId;
        if (i != -1) {
            checkForced(i, true);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        updateChildOrder();
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!(child instanceof MaterialButton)) {
            Log.e(LOG_TAG, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(child, index, params);
        MaterialButton buttonChild = (MaterialButton) child;
        setGeneratedIdIfNeeded(buttonChild);
        setupButtonChild(buttonChild);
        if (buttonChild.isChecked()) {
            updateCheckedStates(buttonChild.getId(), true);
            setCheckedId(buttonChild.getId());
        }
        ShapeAppearanceModel shapeAppearanceModel = buttonChild.getShapeAppearanceModel();
        this.originalCornerData.add(new CornerData(shapeAppearanceModel.getTopLeftCornerSize(), shapeAppearanceModel.getBottomLeftCornerSize(), shapeAppearanceModel.getTopRightCornerSize(), shapeAppearanceModel.getBottomRightCornerSize()));
        ViewCompat.setAccessibilityDelegate(buttonChild, new AccessibilityDelegateCompat() {
            /* class com.google.android.material.button.MaterialButtonToggleGroup.C05982 */

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, MaterialButtonToggleGroup.this.getIndexWithinVisibleButtons(host), 1, false, ((MaterialButton) host).isChecked()));
            }
        });
    }

    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if (child instanceof MaterialButton) {
            ((MaterialButton) child).removeOnCheckedChangeListener(this.checkedStateTracker);
            ((MaterialButton) child).setOnPressedChangeListenerInternal(null);
        }
        int indexOfChild = indexOfChild(child);
        if (indexOfChild >= 0) {
            this.originalCornerData.remove(indexOfChild);
        }
        updateChildShapes();
        adjustChildMarginsAndUpdateLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        updateChildShapes();
        adjustChildMarginsAndUpdateLayout();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public CharSequence getAccessibilityClassName() {
        return MaterialButtonToggleGroup.class.getName();
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        AccessibilityNodeInfoCompat.wrap(info).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, getVisibleButtonCount(), false, isSingleSelection() ? 1 : 2));
    }

    public void check(int id) {
        if (id != this.checkedId) {
            checkForced(id, true);
        }
    }

    public void uncheck(int id) {
        checkForced(id, false);
    }

    public void clearChecked() {
        this.skipCheckedStateTracker = true;
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton child = getChildButton(i);
            child.setChecked(false);
            dispatchOnButtonChecked(child.getId(), false);
        }
        this.skipCheckedStateTracker = false;
        setCheckedId(-1);
    }

    public int getCheckedButtonId() {
        if (this.singleSelection) {
            return this.checkedId;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        List<Integer> checkedIds = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton child = getChildButton(i);
            if (child.isChecked()) {
                checkedIds.add(Integer.valueOf(child.getId()));
            }
        }
        return checkedIds;
    }

    public void addOnButtonCheckedListener(OnButtonCheckedListener listener) {
        this.onButtonCheckedListeners.add(listener);
    }

    public void removeOnButtonCheckedListener(OnButtonCheckedListener listener) {
        this.onButtonCheckedListeners.remove(listener);
    }

    public void clearOnButtonCheckedListeners() {
        this.onButtonCheckedListeners.clear();
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    public void setSingleSelection(boolean singleSelection2) {
        if (this.singleSelection != singleSelection2) {
            this.singleSelection = singleSelection2;
            clearChecked();
        }
    }

    public void setSelectionRequired(boolean selectionRequired2) {
        this.selectionRequired = selectionRequired2;
    }

    public boolean isSelectionRequired() {
        return this.selectionRequired;
    }

    public void setSingleSelection(int id) {
        setSingleSelection(getResources().getBoolean(id));
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView instanceof MaterialButton) {
            this.skipCheckedStateTracker = true;
            ((MaterialButton) checkedView).setChecked(checked);
            this.skipCheckedStateTracker = false;
        }
    }

    private void setCheckedId(int checkedId2) {
        this.checkedId = checkedId2;
        dispatchOnButtonChecked(checkedId2, true);
    }

    private void adjustChildMarginsAndUpdateLayout() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex != -1) {
            for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
                MaterialButton currentButton = getChildButton(i);
                int smallestStrokeWidth = Math.min(currentButton.getStrokeWidth(), getChildButton(i - 1).getStrokeWidth());
                LinearLayout.LayoutParams params = buildLayoutParams(currentButton);
                if (getOrientation() == 0) {
                    MarginLayoutParamsCompat.setMarginEnd(params, 0);
                    MarginLayoutParamsCompat.setMarginStart(params, -smallestStrokeWidth);
                    params.topMargin = 0;
                } else {
                    params.bottomMargin = 0;
                    params.topMargin = -smallestStrokeWidth;
                    MarginLayoutParamsCompat.setMarginStart(params, 0);
                }
                currentButton.setLayoutParams(params);
            }
            resetChildMargins(firstVisibleChildIndex);
        }
    }

    private MaterialButton getChildButton(int index) {
        return (MaterialButton) getChildAt(index);
    }

    private void resetChildMargins(int childIndex) {
        if (getChildCount() != 0 && childIndex != -1) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getChildButton(childIndex).getLayoutParams();
            if (getOrientation() == 1) {
                params.topMargin = 0;
                params.bottomMargin = 0;
                return;
            }
            MarginLayoutParamsCompat.setMarginEnd(params, 0);
            MarginLayoutParamsCompat.setMarginStart(params, 0);
            params.leftMargin = 0;
            params.rightMargin = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateChildShapes() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i = 0; i < childCount; i++) {
            MaterialButton button = getChildButton(i);
            if (button.getVisibility() != 8) {
                ShapeAppearanceModel.Builder builder = button.getShapeAppearanceModel().toBuilder();
                updateBuilderWithCornerData(builder, getNewCornerData(i, firstVisibleChildIndex, lastVisibleChildIndex));
                button.setShapeAppearanceModel(builder.build());
            }
        }
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (isChildVisible(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int i = getChildCount() - 1; i >= 0; i--) {
            if (isChildVisible(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isChildVisible(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    private int getVisibleButtonCount() {
        int count = 0;
        for (int i = 0; i < getChildCount(); i++) {
            if ((getChildAt(i) instanceof MaterialButton) && isChildVisible(i)) {
                count++;
            }
        }
        return count;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getIndexWithinVisibleButtons(View child) {
        if (!(child instanceof MaterialButton)) {
            return -1;
        }
        int index = 0;
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) == child) {
                return index;
            }
            if ((getChildAt(i) instanceof MaterialButton) && isChildVisible(i)) {
                index++;
            }
        }
        return -1;
    }

    private CornerData getNewCornerData(int index, int firstVisibleChildIndex, int lastVisibleChildIndex) {
        CornerData cornerData = this.originalCornerData.get(index);
        if (firstVisibleChildIndex == lastVisibleChildIndex) {
            return cornerData;
        }
        boolean isHorizontal = getOrientation() == 0;
        if (index == firstVisibleChildIndex) {
            return isHorizontal ? CornerData.start(cornerData, this) : CornerData.top(cornerData);
        }
        if (index == lastVisibleChildIndex) {
            return isHorizontal ? CornerData.end(cornerData, this) : CornerData.bottom(cornerData);
        }
        return null;
    }

    private static void updateBuilderWithCornerData(ShapeAppearanceModel.Builder shapeAppearanceModelBuilder, CornerData cornerData) {
        if (cornerData == null) {
            shapeAppearanceModelBuilder.setAllCornerSizes(0.0f);
        } else {
            shapeAppearanceModelBuilder.setTopLeftCornerSize(cornerData.topLeft).setBottomLeftCornerSize(cornerData.bottomLeft).setTopRightCornerSize(cornerData.topRight).setBottomRightCornerSize(cornerData.bottomRight);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean updateCheckedStates(int childId, boolean childIsChecked) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (!this.selectionRequired || !checkedButtonIds.isEmpty()) {
            if (childIsChecked && this.singleSelection) {
                checkedButtonIds.remove(Integer.valueOf(childId));
                for (Integer num : checkedButtonIds) {
                    int buttonId = num.intValue();
                    setCheckedStateForView(buttonId, false);
                    dispatchOnButtonChecked(buttonId, false);
                }
            }
            return true;
        }
        setCheckedStateForView(childId, true);
        this.checkedId = childId;
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void dispatchOnButtonChecked(int buttonId, boolean checked) {
        Iterator<OnButtonCheckedListener> it = this.onButtonCheckedListeners.iterator();
        while (it.hasNext()) {
            it.next().onButtonChecked(this, buttonId, checked);
        }
    }

    private void checkForced(int checkedId2, boolean checked) {
        MaterialButton button = (MaterialButton) findViewById(checkedId2);
        if (button != null) {
            button.setChecked(checked);
        }
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(ViewCompat.generateViewId());
        }
    }

    private void setupButtonChild(MaterialButton buttonChild) {
        buttonChild.setMaxLines(1);
        buttonChild.setEllipsize(TextUtils.TruncateAt.END);
        buttonChild.setCheckable(true);
        buttonChild.addOnCheckedChangeListener(this.checkedStateTracker);
        buttonChild.setOnPressedChangeListenerInternal(this.pressedStateTracker);
        buttonChild.setShouldDrawSurfaceColorStroke(true);
    }

    private LinearLayout.LayoutParams buildLayoutParams(View child) {
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return (LinearLayout.LayoutParams) layoutParams;
        }
        return new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int childCount, int i) {
        Integer[] numArr = this.childOrder;
        if (numArr != null && i < numArr.length) {
            return numArr[i].intValue();
        }
        Log.w(LOG_TAG, "Child order wasn't updated");
        return i;
    }

    private void updateChildOrder() {
        SortedMap<MaterialButton, Integer> viewToIndexMap = new TreeMap<>(this.childOrderComparator);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            viewToIndexMap.put(getChildButton(i), Integer.valueOf(i));
        }
        this.childOrder = (Integer[]) viewToIndexMap.values().toArray(new Integer[0]);
    }

    /* access modifiers changed from: private */
    public class CheckedStateTracker implements MaterialButton.OnCheckedChangeListener {
        private CheckedStateTracker() {
        }

        @Override // com.google.android.material.button.MaterialButton.OnCheckedChangeListener
        public void onCheckedChanged(MaterialButton button, boolean isChecked) {
            if (!MaterialButtonToggleGroup.this.skipCheckedStateTracker) {
                if (MaterialButtonToggleGroup.this.singleSelection) {
                    MaterialButtonToggleGroup.this.checkedId = isChecked ? button.getId() : -1;
                }
                if (MaterialButtonToggleGroup.this.updateCheckedStates(button.getId(), isChecked)) {
                    MaterialButtonToggleGroup.this.dispatchOnButtonChecked(button.getId(), button.isChecked());
                }
                MaterialButtonToggleGroup.this.invalidate();
            }
        }
    }

    /* access modifiers changed from: private */
    public class PressedStateTracker implements MaterialButton.OnPressedChangeListener {
        private PressedStateTracker() {
        }

        @Override // com.google.android.material.button.MaterialButton.OnPressedChangeListener
        public void onPressedChanged(MaterialButton button, boolean isPressed) {
            MaterialButtonToggleGroup.this.invalidate();
        }
    }

    /* access modifiers changed from: private */
    public static class CornerData {
        private static final CornerSize noCorner = new AbsoluteCornerSize(0.0f);
        CornerSize bottomLeft;
        CornerSize bottomRight;
        CornerSize topLeft;
        CornerSize topRight;

        CornerData(CornerSize topLeft2, CornerSize bottomLeft2, CornerSize topRight2, CornerSize bottomRight2) {
            this.topLeft = topLeft2;
            this.topRight = topRight2;
            this.bottomRight = bottomRight2;
            this.bottomLeft = bottomLeft2;
        }

        public static CornerData start(CornerData orig, View view) {
            return ViewUtils.isLayoutRtl(view) ? right(orig) : left(orig);
        }

        public static CornerData end(CornerData orig, View view) {
            return ViewUtils.isLayoutRtl(view) ? left(orig) : right(orig);
        }

        public static CornerData left(CornerData orig) {
            CornerSize cornerSize = orig.topLeft;
            CornerSize cornerSize2 = orig.bottomLeft;
            CornerSize cornerSize3 = noCorner;
            return new CornerData(cornerSize, cornerSize2, cornerSize3, cornerSize3);
        }

        public static CornerData right(CornerData orig) {
            CornerSize cornerSize = noCorner;
            return new CornerData(cornerSize, cornerSize, orig.topRight, orig.bottomRight);
        }

        public static CornerData top(CornerData orig) {
            CornerSize cornerSize = orig.topLeft;
            CornerSize cornerSize2 = noCorner;
            return new CornerData(cornerSize, cornerSize2, orig.topRight, cornerSize2);
        }

        public static CornerData bottom(CornerData orig) {
            CornerSize cornerSize = noCorner;
            return new CornerData(cornerSize, orig.bottomLeft, cornerSize, orig.bottomRight);
        }
    }
}
