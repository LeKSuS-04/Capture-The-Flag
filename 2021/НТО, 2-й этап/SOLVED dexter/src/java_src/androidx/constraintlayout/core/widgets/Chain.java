package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.ArrayRow;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;

public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem system, ArrayList<ConstraintWidget> widgets, int orientation) {
        ChainHead[] chainsArray;
        int chainsSize;
        int offset;
        if (orientation == 0) {
            offset = 0;
            chainsSize = constraintWidgetContainer.mHorizontalChainsSize;
            chainsArray = constraintWidgetContainer.mHorizontalChainsArray;
        } else {
            offset = 2;
            chainsSize = constraintWidgetContainer.mVerticalChainsSize;
            chainsArray = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i = 0; i < chainsSize; i++) {
            ChainHead first = chainsArray[i];
            first.define();
            if (widgets == null || (widgets != null && widgets.contains(first.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:313:0x069c  */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x06a8  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x06ad  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x06b3  */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x06b8  */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06bb  */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06d3  */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06d7  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x06e4  */
    /* JADX WARNING: Removed duplicated region for block: B:333:0x06e8 A[ADDED_TO_REGION] */
    static void applyChainConstraints(ConstraintWidgetContainer container, LinearSystem system, int orientation, int offset, ChainHead chainHead) {
        boolean isChainSpread;
        boolean done;
        boolean isChainPacked;
        boolean isChainSpreadInside;
        ConstraintWidget widget;
        ArrayList<ConstraintWidget> listMatchConstraints;
        ConstraintWidget widget2;
        ConstraintWidget head;
        ConstraintWidget lastVisibleWidget;
        SolverVariable beginTarget;
        SolverVariable endTarget;
        ConstraintAnchor begin;
        ConstraintAnchor end;
        ConstraintAnchor end2;
        ConstraintAnchor endTarget2;
        int endPointsStrength;
        ConstraintWidget previousVisibleWidget;
        ConstraintWidget widget3;
        ConstraintWidget next;
        int i;
        ConstraintWidget next2;
        SolverVariable beginNext;
        ConstraintAnchor beginNextAnchor;
        SolverVariable beginNextTarget;
        int nextMargin;
        int strength;
        ConstraintWidget next3;
        ConstraintWidget previousVisibleWidget2;
        ConstraintWidget widget4;
        ConstraintWidget next4;
        ConstraintWidget previousVisibleWidget3;
        SolverVariable beginTarget2;
        SolverVariable beginNext2;
        ConstraintAnchor beginNextAnchor2;
        int nextMargin2;
        int margin1;
        int margin2;
        int strength2;
        ConstraintAnchor end3;
        float bias;
        ConstraintWidget head2;
        float totalWeights;
        ArrayList<ConstraintWidget> listMatchConstraints2;
        ConstraintWidget widget5;
        ConstraintWidget head3;
        int margin;
        ConstraintWidget firstMatchConstraintsWidget;
        ConstraintWidget previousMatchConstraintsWidget;
        ConstraintWidget next5;
        int strength3;
        int i2 = orientation;
        ChainHead chainHead2 = chainHead;
        ConstraintWidget first = chainHead2.mFirst;
        ConstraintWidget last = chainHead2.mLast;
        ConstraintWidget firstVisibleWidget = chainHead2.mFirstVisibleWidget;
        ConstraintWidget lastVisibleWidget2 = chainHead2.mLastVisibleWidget;
        ConstraintWidget head4 = chainHead2.mHead;
        float totalWeights2 = chainHead2.mTotalWeight;
        ConstraintWidget firstMatchConstraintsWidget2 = chainHead2.mFirstMatchConstraintWidget;
        ConstraintWidget previousMatchConstraintsWidget2 = chainHead2.mLastMatchConstraintWidget;
        boolean isWrapContent = container.mListDimensionBehaviors[i2] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i2 == 0) {
            isChainSpread = head4.mHorizontalChainStyle == 0;
            done = false;
            widget = first;
            isChainSpreadInside = head4.mHorizontalChainStyle == 1;
            isChainPacked = head4.mHorizontalChainStyle == 2;
        } else {
            isChainSpread = head4.mVerticalChainStyle == 0;
            done = false;
            widget = first;
            isChainSpreadInside = head4.mVerticalChainStyle == 1;
            isChainPacked = head4.mVerticalChainStyle == 2;
        }
        while (!done) {
            ConstraintAnchor begin2 = widget.mListAnchors[offset];
            int strength4 = 4;
            if (isChainPacked) {
                strength4 = 1;
            }
            int margin3 = begin2.getMargin();
            boolean isSpreadOnly = widget.mListDimensionBehaviors[i2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mResolvedMatchConstraintDefault[i2] == 0;
            if (begin2.mTarget == null || widget == first) {
                margin = margin3;
            } else {
                margin = margin3 + begin2.mTarget.getMargin();
            }
            if (!(!isChainPacked || widget == first || widget == firstVisibleWidget)) {
                strength4 = 8;
            }
            if (begin2.mTarget != null) {
                if (widget == firstVisibleWidget) {
                    previousMatchConstraintsWidget = previousMatchConstraintsWidget2;
                    firstMatchConstraintsWidget = firstMatchConstraintsWidget2;
                    system.addGreaterThan(begin2.mSolverVariable, begin2.mTarget.mSolverVariable, margin, 6);
                } else {
                    previousMatchConstraintsWidget = previousMatchConstraintsWidget2;
                    firstMatchConstraintsWidget = firstMatchConstraintsWidget2;
                    system.addGreaterThan(begin2.mSolverVariable, begin2.mTarget.mSolverVariable, margin, 8);
                }
                if (isSpreadOnly && !isChainPacked) {
                    strength4 = 5;
                }
                if (widget != firstVisibleWidget || !isChainPacked || !widget.isInBarrier(i2)) {
                    strength3 = strength4;
                } else {
                    strength3 = 5;
                }
                system.addEquality(begin2.mSolverVariable, begin2.mTarget.mSolverVariable, margin, strength3);
            } else {
                previousMatchConstraintsWidget = previousMatchConstraintsWidget2;
                firstMatchConstraintsWidget = firstMatchConstraintsWidget2;
            }
            if (isWrapContent) {
                if (widget.getVisibility() != 8 && widget.mListDimensionBehaviors[i2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    system.addGreaterThan(widget.mListAnchors[offset + 1].mSolverVariable, widget.mListAnchors[offset].mSolverVariable, 0, 5);
                }
                system.addGreaterThan(widget.mListAnchors[offset].mSolverVariable, container.mListAnchors[offset].mSolverVariable, 0, 8);
            }
            ConstraintAnchor nextAnchor = widget.mListAnchors[offset + 1].mTarget;
            if (nextAnchor != null) {
                ConstraintWidget next6 = nextAnchor.mOwner;
                if (next6.mListAnchors[offset].mTarget == null || next6.mListAnchors[offset].mTarget.mOwner != widget) {
                    next5 = null;
                } else {
                    next5 = next6;
                }
            } else {
                next5 = null;
            }
            if (next5 != null) {
                widget = next5;
            } else {
                done = true;
            }
            totalWeights2 = totalWeights2;
            previousMatchConstraintsWidget2 = previousMatchConstraintsWidget;
            firstMatchConstraintsWidget2 = firstMatchConstraintsWidget;
        }
        if (!(lastVisibleWidget2 == null || last.mListAnchors[offset + 1].mTarget == null)) {
            ConstraintAnchor end4 = lastVisibleWidget2.mListAnchors[offset + 1];
            if ((lastVisibleWidget2.mListDimensionBehaviors[i2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && lastVisibleWidget2.mResolvedMatchConstraintDefault[i2] == 0) && !isChainPacked && end4.mTarget.mOwner == container) {
                system.addEquality(end4.mSolverVariable, end4.mTarget.mSolverVariable, -end4.getMargin(), 5);
            } else if (isChainPacked && end4.mTarget.mOwner == container) {
                system.addEquality(end4.mSolverVariable, end4.mTarget.mSolverVariable, -end4.getMargin(), 4);
            }
            system.addLowerThan(end4.mSolverVariable, last.mListAnchors[offset + 1].mTarget.mSolverVariable, -end4.getMargin(), 6);
        }
        if (isWrapContent) {
            system.addGreaterThan(container.mListAnchors[offset + 1].mSolverVariable, last.mListAnchors[offset + 1].mSolverVariable, last.mListAnchors[offset + 1].getMargin(), 8);
        }
        ArrayList<ConstraintWidget> listMatchConstraints3 = chainHead2.mWeightedMatchConstraintsWidgets;
        if (listMatchConstraints3 != null) {
            int count = listMatchConstraints3.size();
            if (count > 1) {
                ConstraintWidget lastMatch = null;
                float lastWeight = 0.0f;
                if (!chainHead2.mHasUndefinedWeights || chainHead2.mHasComplexMatchWeights) {
                    totalWeights = totalWeights2;
                } else {
                    totalWeights = (float) chainHead2.mWidgetsMatchCount;
                }
                int i3 = 0;
                while (i3 < count) {
                    ConstraintWidget match = listMatchConstraints3.get(i3);
                    float currentWeight = match.mWeight[i2];
                    if (currentWeight >= 0.0f) {
                        widget5 = widget;
                        listMatchConstraints2 = listMatchConstraints3;
                    } else if (chainHead2.mHasComplexMatchWeights) {
                        widget5 = widget;
                        listMatchConstraints2 = listMatchConstraints3;
                        system.addEquality(match.mListAnchors[offset + 1].mSolverVariable, match.mListAnchors[offset].mSolverVariable, 0, 4);
                        head3 = head4;
                        i3++;
                        chainHead2 = chainHead;
                        head4 = head3;
                        count = count;
                        widget = widget5;
                        listMatchConstraints3 = listMatchConstraints2;
                    } else {
                        widget5 = widget;
                        listMatchConstraints2 = listMatchConstraints3;
                        currentWeight = 1.0f;
                    }
                    if (currentWeight == 0.0f) {
                        head3 = head4;
                        system.addEquality(match.mListAnchors[offset + 1].mSolverVariable, match.mListAnchors[offset].mSolverVariable, 0, 8);
                    } else {
                        head3 = head4;
                        if (lastMatch != null) {
                            SolverVariable begin3 = lastMatch.mListAnchors[offset].mSolverVariable;
                            SolverVariable end5 = lastMatch.mListAnchors[offset + 1].mSolverVariable;
                            SolverVariable nextBegin = match.mListAnchors[offset].mSolverVariable;
                            SolverVariable nextEnd = match.mListAnchors[offset + 1].mSolverVariable;
                            ArrayRow row = system.createRow();
                            row.createRowEqualMatchDimensions(lastWeight, totalWeights, currentWeight, begin3, end5, nextBegin, nextEnd);
                            system.addConstraint(row);
                        }
                        lastMatch = match;
                        lastWeight = currentWeight;
                    }
                    i3++;
                    chainHead2 = chainHead;
                    head4 = head3;
                    count = count;
                    widget = widget5;
                    listMatchConstraints3 = listMatchConstraints2;
                }
                widget2 = widget;
                listMatchConstraints = listMatchConstraints3;
                head = head4;
            } else {
                widget2 = widget;
                listMatchConstraints = listMatchConstraints3;
                head = head4;
            }
        } else {
            widget2 = widget;
            listMatchConstraints = listMatchConstraints3;
            head = head4;
        }
        if (firstVisibleWidget == null) {
            lastVisibleWidget = lastVisibleWidget2;
        } else if (firstVisibleWidget == lastVisibleWidget2 || isChainPacked) {
            ConstraintAnchor begin4 = first.mListAnchors[offset];
            ConstraintAnchor end6 = last.mListAnchors[offset + 1];
            SolverVariable beginTarget3 = begin4.mTarget != null ? begin4.mTarget.mSolverVariable : null;
            SolverVariable endTarget3 = end6.mTarget != null ? end6.mTarget.mSolverVariable : null;
            ConstraintAnchor begin5 = firstVisibleWidget.mListAnchors[offset];
            if (lastVisibleWidget2 != null) {
                end3 = lastVisibleWidget2.mListAnchors[offset + 1];
            } else {
                end3 = end6;
            }
            if (beginTarget3 == null || endTarget3 == null) {
                lastVisibleWidget = lastVisibleWidget2;
            } else {
                if (i2 == 0) {
                    head2 = head;
                    bias = head2.mHorizontalBiasPercent;
                } else {
                    head2 = head;
                    bias = head2.mVerticalBiasPercent;
                }
                lastVisibleWidget = lastVisibleWidget2;
                system.addCentering(begin5.mSolverVariable, beginTarget3, begin5.getMargin(), bias, endTarget3, end3.mSolverVariable, end3.getMargin(), 7);
            }
            if (!((!isChainSpread && !isChainSpreadInside) || firstVisibleWidget == null || firstVisibleWidget == lastVisibleWidget)) {
                ConstraintAnchor begin6 = firstVisibleWidget.mListAnchors[offset];
                if (lastVisibleWidget == null) {
                    lastVisibleWidget = firstVisibleWidget;
                }
                ConstraintAnchor end7 = lastVisibleWidget.mListAnchors[offset + 1];
                beginTarget = begin6.mTarget != null ? begin6.mTarget.mSolverVariable : null;
                SolverVariable endTarget4 = end7.mTarget != null ? end7.mTarget.mSolverVariable : null;
                if (last != lastVisibleWidget) {
                    ConstraintAnchor realEnd = last.mListAnchors[offset + 1];
                    endTarget = realEnd.mTarget != null ? realEnd.mTarget.mSolverVariable : null;
                } else {
                    endTarget = endTarget4;
                }
                if (firstVisibleWidget == lastVisibleWidget) {
                    begin = firstVisibleWidget.mListAnchors[offset];
                    end = firstVisibleWidget.mListAnchors[offset + 1];
                } else {
                    begin = begin6;
                    end = end7;
                }
                if (beginTarget == null && endTarget != null) {
                    system.addCentering(begin.mSolverVariable, beginTarget, begin.getMargin(), 0.5f, endTarget, end.mSolverVariable, lastVisibleWidget.mListAnchors[offset + 1].getMargin(), 5);
                }
            }
        } else {
            lastVisibleWidget = lastVisibleWidget2;
        }
        if (!isChainSpread || firstVisibleWidget == null) {
            int i4 = 8;
            if (isChainSpreadInside && firstVisibleWidget != null) {
                boolean applyFixedEquality = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
                ConstraintWidget widget6 = firstVisibleWidget;
                ConstraintWidget previousVisibleWidget4 = firstVisibleWidget;
                while (widget6 != null) {
                    ConstraintWidget next7 = widget6.mNextChainWidget[i2];
                    while (next7 != null && next7.getVisibility() == i4) {
                        next7 = next7.mNextChainWidget[i2];
                    }
                    if (widget6 == firstVisibleWidget || widget6 == lastVisibleWidget || next7 == null) {
                        widget3 = widget6;
                        previousVisibleWidget = previousVisibleWidget4;
                        i = 8;
                        next = next7;
                    } else {
                        if (next7 == lastVisibleWidget) {
                            next2 = null;
                        } else {
                            next2 = next7;
                        }
                        ConstraintAnchor beginAnchor = widget6.mListAnchors[offset];
                        SolverVariable begin7 = beginAnchor.mSolverVariable;
                        if (beginAnchor.mTarget != null) {
                            SolverVariable solverVariable = beginAnchor.mTarget.mSolverVariable;
                        }
                        SolverVariable beginTarget4 = previousVisibleWidget4.mListAnchors[offset + 1].mSolverVariable;
                        SolverVariable beginNext3 = null;
                        int beginMargin = beginAnchor.getMargin();
                        int nextMargin3 = widget6.mListAnchors[offset + 1].getMargin();
                        if (next2 != null) {
                            ConstraintAnchor beginNextAnchor3 = next2.mListAnchors[offset];
                            SolverVariable beginNext4 = beginNextAnchor3.mSolverVariable;
                            beginNextTarget = beginNextAnchor3.mTarget != null ? beginNextAnchor3.mTarget.mSolverVariable : null;
                            beginNext = beginNext4;
                            beginNextAnchor = beginNextAnchor3;
                        } else {
                            ConstraintAnchor beginNextAnchor4 = lastVisibleWidget.mListAnchors[offset];
                            if (beginNextAnchor4 != null) {
                                beginNext3 = beginNextAnchor4.mSolverVariable;
                            }
                            beginNextAnchor = beginNextAnchor4;
                            beginNextTarget = widget6.mListAnchors[offset + 1].mSolverVariable;
                            beginNext = beginNext3;
                        }
                        if (beginNextAnchor != null) {
                            nextMargin = nextMargin3 + beginNextAnchor.getMargin();
                        } else {
                            nextMargin = nextMargin3;
                        }
                        int beginMargin2 = beginMargin + previousVisibleWidget4.mListAnchors[offset + 1].getMargin();
                        if (applyFixedEquality) {
                            strength = 8;
                        } else {
                            strength = 4;
                        }
                        if (begin7 == null || beginTarget4 == null || beginNext == null || beginNextTarget == null) {
                            next3 = next2;
                            widget3 = widget6;
                            previousVisibleWidget = previousVisibleWidget4;
                            i = 8;
                        } else {
                            next3 = next2;
                            widget3 = widget6;
                            previousVisibleWidget = previousVisibleWidget4;
                            i = 8;
                            system.addCentering(begin7, beginTarget4, beginMargin2, 0.5f, beginNext, beginNextTarget, nextMargin, strength);
                        }
                        next = next3;
                    }
                    if (widget3.getVisibility() != i) {
                        previousVisibleWidget4 = widget3;
                    } else {
                        previousVisibleWidget4 = previousVisibleWidget;
                    }
                    widget6 = next;
                    i2 = orientation;
                    i4 = 8;
                }
                ConstraintAnchor begin8 = firstVisibleWidget.mListAnchors[offset];
                ConstraintAnchor beginTarget5 = first.mListAnchors[offset].mTarget;
                ConstraintAnchor end8 = lastVisibleWidget.mListAnchors[offset + 1];
                ConstraintAnchor endTarget5 = last.mListAnchors[offset + 1].mTarget;
                if (beginTarget5 == null) {
                    endPointsStrength = 5;
                    endTarget2 = endTarget5;
                    end2 = end8;
                } else if (firstVisibleWidget != lastVisibleWidget) {
                    system.addEquality(begin8.mSolverVariable, beginTarget5.mSolverVariable, begin8.getMargin(), 5);
                    endPointsStrength = 5;
                    endTarget2 = endTarget5;
                    end2 = end8;
                } else if (endTarget5 != null) {
                    endPointsStrength = 5;
                    endTarget2 = endTarget5;
                    end2 = end8;
                    system.addCentering(begin8.mSolverVariable, beginTarget5.mSolverVariable, begin8.getMargin(), 0.5f, end8.mSolverVariable, endTarget5.mSolverVariable, end8.getMargin(), 5);
                } else {
                    endPointsStrength = 5;
                    endTarget2 = endTarget5;
                    end2 = end8;
                }
                if (endTarget2 != null && firstVisibleWidget != lastVisibleWidget) {
                    system.addEquality(end2.mSolverVariable, endTarget2.mSolverVariable, -end2.getMargin(), endPointsStrength);
                }
            }
            ConstraintAnchor begin62 = firstVisibleWidget.mListAnchors[offset];
            if (lastVisibleWidget == null) {
            }
            ConstraintAnchor end72 = lastVisibleWidget.mListAnchors[offset + 1];
            if (begin62.mTarget != null) {
            }
            if (end72.mTarget != null) {
            }
            if (last != lastVisibleWidget) {
            }
            if (firstVisibleWidget == lastVisibleWidget) {
            }
            if (beginTarget == null) {
            }
        }
        boolean applyFixedEquality2 = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
        ConstraintWidget widget7 = firstVisibleWidget;
        ConstraintWidget previousVisibleWidget5 = firstVisibleWidget;
        while (widget7 != null) {
            ConstraintWidget next8 = widget7.mNextChainWidget[i2];
            while (true) {
                if (next8 != null) {
                    if (next8.getVisibility() != 8) {
                        break;
                    }
                    next8 = next8.mNextChainWidget[i2];
                } else {
                    break;
                }
            }
            if (next8 != null || widget7 == lastVisibleWidget) {
                ConstraintAnchor beginAnchor2 = widget7.mListAnchors[offset];
                SolverVariable begin9 = beginAnchor2.mSolverVariable;
                SolverVariable beginTarget6 = beginAnchor2.mTarget != null ? beginAnchor2.mTarget.mSolverVariable : null;
                if (previousVisibleWidget5 != widget7) {
                    beginTarget2 = previousVisibleWidget5.mListAnchors[offset + 1].mSolverVariable;
                } else if (widget7 == firstVisibleWidget) {
                    beginTarget2 = first.mListAnchors[offset].mTarget != null ? first.mListAnchors[offset].mTarget.mSolverVariable : null;
                } else {
                    beginTarget2 = beginTarget6;
                }
                int beginMargin3 = beginAnchor2.getMargin();
                int nextMargin4 = widget7.mListAnchors[offset + 1].getMargin();
                if (next8 != null) {
                    ConstraintAnchor beginNextAnchor5 = next8.mListAnchors[offset];
                    beginNextAnchor2 = beginNextAnchor5;
                    beginNext2 = beginNextAnchor5.mSolverVariable;
                } else {
                    ConstraintAnchor beginNextAnchor6 = last.mListAnchors[offset + 1].mTarget;
                    if (beginNextAnchor6 != null) {
                        beginNextAnchor2 = beginNextAnchor6;
                        beginNext2 = beginNextAnchor6.mSolverVariable;
                    } else {
                        beginNextAnchor2 = beginNextAnchor6;
                        beginNext2 = null;
                    }
                }
                SolverVariable beginNextTarget2 = widget7.mListAnchors[offset + 1].mSolverVariable;
                if (beginNextAnchor2 != null) {
                    nextMargin2 = nextMargin4 + beginNextAnchor2.getMargin();
                } else {
                    nextMargin2 = nextMargin4;
                }
                int beginMargin4 = beginMargin3 + previousVisibleWidget5.mListAnchors[offset + 1].getMargin();
                if (begin9 == null || beginTarget2 == null || beginNext2 == null || beginNextTarget2 == null) {
                    next4 = next8;
                    widget4 = widget7;
                    previousVisibleWidget2 = previousVisibleWidget5;
                } else {
                    if (widget7 == firstVisibleWidget) {
                        margin1 = firstVisibleWidget.mListAnchors[offset].getMargin();
                    } else {
                        margin1 = beginMargin4;
                    }
                    if (widget7 == lastVisibleWidget) {
                        margin2 = lastVisibleWidget.mListAnchors[offset + 1].getMargin();
                    } else {
                        margin2 = nextMargin2;
                    }
                    if (applyFixedEquality2) {
                        strength2 = 8;
                    } else {
                        strength2 = 5;
                    }
                    next4 = next8;
                    widget4 = widget7;
                    previousVisibleWidget2 = previousVisibleWidget5;
                    system.addCentering(begin9, beginTarget2, margin1, 0.5f, beginNext2, beginNextTarget2, margin2, strength2);
                }
            } else {
                next4 = next8;
                widget4 = widget7;
                previousVisibleWidget2 = previousVisibleWidget5;
            }
            if (widget4.getVisibility() != 8) {
                previousVisibleWidget3 = widget4;
            } else {
                previousVisibleWidget3 = previousVisibleWidget2;
            }
            widget7 = next4;
            previousVisibleWidget5 = previousVisibleWidget3;
        }
        ConstraintAnchor begin622 = firstVisibleWidget.mListAnchors[offset];
        if (lastVisibleWidget == null) {
        }
        ConstraintAnchor end722 = lastVisibleWidget.mListAnchors[offset + 1];
        if (begin622.mTarget != null) {
        }
        if (end722.mTarget != null) {
        }
        if (last != lastVisibleWidget) {
        }
        if (firstVisibleWidget == lastVisibleWidget) {
        }
        if (beginTarget == null) {
        }
    }
}
