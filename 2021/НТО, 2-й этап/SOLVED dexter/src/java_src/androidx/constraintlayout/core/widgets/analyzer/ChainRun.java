package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList<WidgetRun> widgets = new ArrayList<>();

    public ChainRun(ConstraintWidget widget, int orientation) {
        super(widget);
        this.orientation = orientation;
        build();
    }

    public String toString() {
        StringBuilder log = new StringBuilder("ChainRun ");
        log.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            log.append("<");
            log.append(it.next());
            log.append("> ");
        }
        return log.toString();
    }

    /* access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        int count = this.widgets.size();
        for (int i = 0; i < count; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int count = this.widgets.size();
        long wrapDimension = 0;
        for (int i = 0; i < count; i++) {
            WidgetRun run = this.widgets.get(i);
            wrapDimension = wrapDimension + ((long) run.start.margin) + run.getWrapDimension() + ((long) run.end.margin);
        }
        return wrapDimension;
    }

    private void build() {
        ConstraintWidget current = this.widget;
        ConstraintWidget previous = current.getPreviousChainMember(this.orientation);
        while (previous != null) {
            current = previous;
            previous = current.getPreviousChainMember(this.orientation);
        }
        this.widget = current;
        this.widgets.add(current.getRun(this.orientation));
        ConstraintWidget next = current.getNextChainMember(this.orientation);
        while (next != null) {
            this.widgets.add(next.getRun(this.orientation));
            next = next.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun run = it.next();
            if (this.orientation == 0) {
                run.widget.horizontalChainRun = this;
            } else if (this.orientation == 1) {
                run.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl()) && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.widgets;
            this.widget = arrayList.get(arrayList.size() - 1).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    /* access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00f5  */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        int i;
        boolean isInRtl;
        int position;
        int i2;
        float bias;
        int position2;
        float bias2;
        int gap;
        int position3;
        int gap2;
        boolean isInRtl2;
        int matchConstraintsDimension;
        float weights;
        int size;
        int min;
        int max;
        ConstraintWidget parent;
        boolean treatAsFixed;
        boolean treatAsFixed2;
        ChainRun chainRun = this;
        if (chainRun.start.resolved && chainRun.end.resolved) {
            ConstraintWidget parent2 = chainRun.widget.getParent();
            boolean isInRtl3 = false;
            if (parent2 instanceof ConstraintWidgetContainer) {
                isInRtl3 = ((ConstraintWidgetContainer) parent2).isRtl();
            }
            int distance = chainRun.end.value - chainRun.start.value;
            int size2 = 0;
            int numMatchConstraints = 0;
            float weights2 = 0.0f;
            int numVisibleWidgets = 0;
            int count = chainRun.widgets.size();
            int firstVisibleWidget = -1;
            int i3 = 0;
            while (true) {
                i = 8;
                if (i3 >= count) {
                    break;
                }
                if (chainRun.widgets.get(i3).widget.getVisibility() != 8) {
                    firstVisibleWidget = i3;
                    break;
                }
                i3++;
            }
            int lastVisibleWidget = -1;
            int i4 = count - 1;
            while (true) {
                if (i4 < 0) {
                    break;
                }
                if (chainRun.widgets.get(i4).widget.getVisibility() != 8) {
                    lastVisibleWidget = i4;
                    break;
                }
                i4--;
            }
            int j = 0;
            while (true) {
                if (j >= 2) {
                    break;
                }
                int i5 = 0;
                while (i5 < count) {
                    WidgetRun run = chainRun.widgets.get(i5);
                    if (run.widget.getVisibility() == i) {
                        parent = parent2;
                    } else {
                        numVisibleWidgets++;
                        if (i5 > 0 && i5 >= firstVisibleWidget) {
                            size2 += run.start.margin;
                        }
                        int dimension = run.dimension.value;
                        parent = parent2;
                        boolean treatAsFixed3 = run.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        if (!treatAsFixed3) {
                            treatAsFixed2 = treatAsFixed3;
                            if (run.matchConstraintsType == 1 && j == 0) {
                                treatAsFixed = true;
                                dimension = run.dimension.wrapValue;
                                numMatchConstraints++;
                                if (!treatAsFixed) {
                                }
                                size2 += -run.end.margin;
                            } else if (run.dimension.resolved) {
                                treatAsFixed = true;
                                if (!treatAsFixed) {
                                    numMatchConstraints++;
                                    float weight = run.widget.mWeight[chainRun.orientation];
                                    if (weight >= 0.0f) {
                                        weights2 += weight;
                                    }
                                } else {
                                    size2 += dimension;
                                }
                                if (i5 < count - 1 && i5 < lastVisibleWidget) {
                                    size2 += -run.end.margin;
                                }
                            }
                        } else if (chainRun.orientation != 0 || run.widget.horizontalRun.dimension.resolved) {
                            treatAsFixed2 = treatAsFixed3;
                            if (chainRun.orientation == 1 && !run.widget.verticalRun.dimension.resolved) {
                                return;
                            }
                        } else {
                            return;
                        }
                        treatAsFixed = treatAsFixed2;
                        if (!treatAsFixed) {
                        }
                        size2 += -run.end.margin;
                    }
                    i5++;
                    parent2 = parent;
                    i = 8;
                }
                if (size2 < distance || numMatchConstraints == 0) {
                    break;
                }
                numVisibleWidgets = 0;
                numMatchConstraints = 0;
                size2 = 0;
                weights2 = 0.0f;
                j++;
                parent2 = parent2;
                i = 8;
            }
            int position4 = chainRun.start.value;
            if (isInRtl3) {
                position4 = chainRun.end.value;
            }
            if (size2 > distance) {
                if (isInRtl3) {
                    position4 += (int) ((((float) (size2 - distance)) / 2.0f) + 0.5f);
                } else {
                    position4 -= (int) ((((float) (size2 - distance)) / 2.0f) + 0.5f);
                }
            }
            if (numMatchConstraints > 0) {
                int matchConstraintsDimension2 = (int) ((((float) (distance - size2)) / ((float) numMatchConstraints)) + 0.5f);
                int appliedLimits = 0;
                int i6 = 0;
                while (i6 < count) {
                    WidgetRun run2 = chainRun.widgets.get(i6);
                    if (run2.widget.getVisibility() == 8) {
                        isInRtl2 = isInRtl3;
                        size = size2;
                        weights = weights2;
                        matchConstraintsDimension = matchConstraintsDimension2;
                    } else if (run2.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || run2.dimension.resolved) {
                        isInRtl2 = isInRtl3;
                        size = size2;
                        weights = weights2;
                        matchConstraintsDimension = matchConstraintsDimension2;
                    } else {
                        int dimension2 = matchConstraintsDimension2;
                        if (weights2 > 0.0f) {
                            dimension2 = (int) (((((float) (distance - size2)) * run2.widget.mWeight[chainRun.orientation]) / weights2) + 0.5f);
                        }
                        int value = dimension2;
                        size = size2;
                        if (chainRun.orientation == 0) {
                            int max2 = run2.widget.mMatchConstraintMaxWidth;
                            min = run2.widget.mMatchConstraintMinWidth;
                            max = max2;
                            weights = weights2;
                        } else {
                            int max3 = run2.widget.mMatchConstraintMaxHeight;
                            min = run2.widget.mMatchConstraintMinHeight;
                            max = max3;
                            weights = weights2;
                        }
                        matchConstraintsDimension = matchConstraintsDimension2;
                        isInRtl2 = isInRtl3;
                        if (run2.matchConstraintsType == 1) {
                            value = Math.min(value, run2.dimension.wrapValue);
                        }
                        int value2 = Math.max(min, value);
                        if (max > 0) {
                            value2 = Math.min(max, value2);
                        }
                        if (value2 != dimension2) {
                            appliedLimits++;
                            dimension2 = value2;
                        }
                        run2.dimension.resolve(dimension2);
                    }
                    i6++;
                    position4 = position4;
                    size2 = size;
                    weights2 = weights;
                    matchConstraintsDimension2 = matchConstraintsDimension;
                    isInRtl3 = isInRtl2;
                }
                position = position4;
                isInRtl = isInRtl3;
                if (appliedLimits > 0) {
                    numMatchConstraints -= appliedLimits;
                    int size3 = 0;
                    for (int i7 = 0; i7 < count; i7++) {
                        WidgetRun run3 = chainRun.widgets.get(i7);
                        if (run3.widget.getVisibility() != 8) {
                            if (i7 > 0 && i7 >= firstVisibleWidget) {
                                size3 += run3.start.margin;
                            }
                            size3 += run3.dimension.value;
                            if (i7 < count - 1 && i7 < lastVisibleWidget) {
                                size3 += -run3.end.margin;
                            }
                        }
                    }
                    size2 = size3;
                } else {
                    size2 = size2;
                }
                if (chainRun.chainStyle == 2 && appliedLimits == 0) {
                    chainRun.chainStyle = 0;
                }
            } else {
                position = position4;
                isInRtl = isInRtl3;
            }
            if (size2 > distance) {
                i2 = 2;
                chainRun.chainStyle = 2;
            } else {
                i2 = 2;
            }
            if (numVisibleWidgets > 0 && numMatchConstraints == 0 && firstVisibleWidget == lastVisibleWidget) {
                chainRun.chainStyle = i2;
            }
            int i8 = chainRun.chainStyle;
            if (i8 == 1) {
                int gap3 = 0;
                if (numVisibleWidgets > 1) {
                    gap3 = (distance - size2) / (numVisibleWidgets - 1);
                } else if (numVisibleWidgets == 1) {
                    gap3 = (distance - size2) / 2;
                }
                if (numMatchConstraints > 0) {
                    gap3 = 0;
                }
                int i9 = 0;
                int position5 = position;
                while (i9 < count) {
                    int index = i9;
                    if (isInRtl) {
                        index = count - (i9 + 1);
                    }
                    WidgetRun run4 = chainRun.widgets.get(index);
                    if (run4.widget.getVisibility() == 8) {
                        run4.start.resolve(position5);
                        run4.end.resolve(position5);
                        gap2 = gap3;
                    } else {
                        if (i9 > 0) {
                            if (isInRtl) {
                                position5 -= gap3;
                            } else {
                                position5 += gap3;
                            }
                        }
                        if (i9 > 0 && i9 >= firstVisibleWidget) {
                            if (isInRtl) {
                                position5 -= run4.start.margin;
                            } else {
                                position5 += run4.start.margin;
                            }
                        }
                        if (isInRtl) {
                            run4.end.resolve(position5);
                        } else {
                            run4.start.resolve(position5);
                        }
                        int dimension3 = run4.dimension.value;
                        gap2 = gap3;
                        if (run4.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && run4.matchConstraintsType == 1) {
                            dimension3 = run4.dimension.wrapValue;
                        }
                        if (isInRtl) {
                            position5 -= dimension3;
                        } else {
                            position5 += dimension3;
                        }
                        if (isInRtl) {
                            run4.start.resolve(position5);
                        } else {
                            run4.end.resolve(position5);
                        }
                        run4.resolved = true;
                        if (i9 < count - 1 && i9 < lastVisibleWidget) {
                            if (isInRtl) {
                                position5 -= -run4.end.margin;
                            } else {
                                position5 += -run4.end.margin;
                            }
                        }
                    }
                    i9++;
                    gap3 = gap2;
                }
            } else if (i8 == 0) {
                int gap4 = (distance - size2) / (numVisibleWidgets + 1);
                if (numMatchConstraints > 0) {
                    gap4 = 0;
                }
                int i10 = 0;
                int position6 = position;
                while (i10 < count) {
                    int index2 = i10;
                    if (isInRtl) {
                        index2 = count - (i10 + 1);
                    }
                    WidgetRun run5 = chainRun.widgets.get(index2);
                    if (run5.widget.getVisibility() == 8) {
                        run5.start.resolve(position6);
                        run5.end.resolve(position6);
                        gap = gap4;
                    } else {
                        if (isInRtl) {
                            position3 = position6 - gap4;
                        } else {
                            position3 = position6 + gap4;
                        }
                        if (i10 > 0 && i10 >= firstVisibleWidget) {
                            if (isInRtl) {
                                position3 -= run5.start.margin;
                            } else {
                                position3 += run5.start.margin;
                            }
                        }
                        if (isInRtl) {
                            run5.end.resolve(position3);
                        } else {
                            run5.start.resolve(position3);
                        }
                        int dimension4 = run5.dimension.value;
                        gap = gap4;
                        if (run5.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && run5.matchConstraintsType == 1) {
                            dimension4 = Math.min(dimension4, run5.dimension.wrapValue);
                        }
                        if (isInRtl) {
                            position6 = position3 - dimension4;
                        } else {
                            position6 = position3 + dimension4;
                        }
                        if (isInRtl) {
                            run5.start.resolve(position6);
                        } else {
                            run5.end.resolve(position6);
                        }
                        if (i10 < count - 1 && i10 < lastVisibleWidget) {
                            if (isInRtl) {
                                position6 -= -run5.end.margin;
                            } else {
                                position6 += -run5.end.margin;
                            }
                        }
                    }
                    i10++;
                    gap4 = gap;
                }
            } else if (i8 == 2) {
                if (chainRun.orientation == 0) {
                    bias = chainRun.widget.getHorizontalBiasPercent();
                } else {
                    bias = chainRun.widget.getVerticalBiasPercent();
                }
                if (isInRtl) {
                    bias = 1.0f - bias;
                }
                int gap5 = (int) ((((float) (distance - size2)) * bias) + 0.5f);
                if (gap5 < 0 || numMatchConstraints > 0) {
                    gap5 = 0;
                }
                if (isInRtl) {
                    position2 = position - gap5;
                } else {
                    position2 = position + gap5;
                }
                int i11 = 0;
                while (i11 < count) {
                    int index3 = i11;
                    if (isInRtl) {
                        index3 = count - (i11 + 1);
                    }
                    WidgetRun run6 = chainRun.widgets.get(index3);
                    if (run6.widget.getVisibility() == 8) {
                        run6.start.resolve(position2);
                        run6.end.resolve(position2);
                        bias2 = bias;
                    } else {
                        if (i11 > 0 && i11 >= firstVisibleWidget) {
                            if (isInRtl) {
                                position2 -= run6.start.margin;
                            } else {
                                position2 += run6.start.margin;
                            }
                        }
                        if (isInRtl) {
                            run6.end.resolve(position2);
                        } else {
                            run6.start.resolve(position2);
                        }
                        int dimension5 = run6.dimension.value;
                        bias2 = bias;
                        if (run6.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                            if (run6.matchConstraintsType == 1) {
                                dimension5 = run6.dimension.wrapValue;
                            }
                        }
                        if (isInRtl) {
                            position2 -= dimension5;
                        } else {
                            position2 += dimension5;
                        }
                        if (isInRtl) {
                            run6.start.resolve(position2);
                        } else {
                            run6.end.resolve(position2);
                        }
                        if (i11 < count - 1 && i11 < lastVisibleWidget) {
                            if (isInRtl) {
                                position2 -= -run6.end.margin;
                            } else {
                                position2 += -run6.end.margin;
                            }
                        }
                    }
                    i11++;
                    chainRun = this;
                    bias = bias2;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun run = this.widgets.get(i);
            if (run.widget.getVisibility() != 8) {
                return run.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int i = this.widgets.size() - 1; i >= 0; i--) {
            WidgetRun run = this.widgets.get(i);
            if (run.widget.getVisibility() != 8) {
                return run.widget;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int count = this.widgets.size();
        if (count >= 1) {
            ConstraintWidget firstWidget = this.widgets.get(0).widget;
            ConstraintWidget lastWidget = this.widgets.get(count - 1).widget;
            if (this.orientation == 0) {
                ConstraintAnchor startAnchor = firstWidget.mLeft;
                ConstraintAnchor endAnchor = lastWidget.mRight;
                DependencyNode startTarget = getTarget(startAnchor, 0);
                int startMargin = startAnchor.getMargin();
                ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
                if (firstVisibleWidget != null) {
                    startMargin = firstVisibleWidget.mLeft.getMargin();
                }
                if (startTarget != null) {
                    addTarget(this.start, startTarget, startMargin);
                }
                DependencyNode endTarget = getTarget(endAnchor, 0);
                int endMargin = endAnchor.getMargin();
                ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
                if (lastVisibleWidget != null) {
                    endMargin = lastVisibleWidget.mRight.getMargin();
                }
                if (endTarget != null) {
                    addTarget(this.end, endTarget, -endMargin);
                }
            } else {
                ConstraintAnchor startAnchor2 = firstWidget.mTop;
                ConstraintAnchor endAnchor2 = lastWidget.mBottom;
                DependencyNode startTarget2 = getTarget(startAnchor2, 1);
                int startMargin2 = startAnchor2.getMargin();
                ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
                if (firstVisibleWidget2 != null) {
                    startMargin2 = firstVisibleWidget2.mTop.getMargin();
                }
                if (startTarget2 != null) {
                    addTarget(this.start, startTarget2, startMargin2);
                }
                DependencyNode endTarget2 = getTarget(endAnchor2, 1);
                int endMargin2 = endAnchor2.getMargin();
                ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
                if (lastVisibleWidget2 != null) {
                    endMargin2 = lastVisibleWidget2.mBottom.getMargin();
                }
                if (endTarget2 != null) {
                    addTarget(this.end, endTarget2, -endMargin2);
                }
            }
            this.start.updateDelegate = this;
            this.end.updateDelegate = this;
        }
    }
}
