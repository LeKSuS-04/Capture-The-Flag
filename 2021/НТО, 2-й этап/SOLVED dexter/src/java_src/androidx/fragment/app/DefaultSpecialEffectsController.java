package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.p003os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class DefaultSpecialEffectsController extends SpecialEffectsController {
    DefaultSpecialEffectsController(ViewGroup container) {
        super(container);
    }

    /* access modifiers changed from: package-private */
    @Override // androidx.fragment.app.SpecialEffectsController
    public void executeOperations(List<SpecialEffectsController.Operation> operations, boolean isPop) {
        SpecialEffectsController.Operation firstOut = null;
        SpecialEffectsController.Operation lastIn = null;
        for (SpecialEffectsController.Operation operation : operations) {
            SpecialEffectsController.Operation.State currentState = SpecialEffectsController.Operation.State.from(operation.getFragment().mView);
            switch (C029410.f74xe493b431[operation.getFinalState().ordinal()]) {
                case 1:
                case 2:
                case 3:
                    if (currentState == SpecialEffectsController.Operation.State.VISIBLE && firstOut == null) {
                        firstOut = operation;
                        break;
                    }
                case 4:
                    if (currentState != SpecialEffectsController.Operation.State.VISIBLE) {
                        lastIn = operation;
                        break;
                    } else {
                        break;
                    }
            }
        }
        List<AnimationInfo> animations = new ArrayList<>();
        List<TransitionInfo> transitions = new ArrayList<>();
        final List<SpecialEffectsController.Operation> awaitingContainerChanges = new ArrayList<>(operations);
        Iterator<SpecialEffectsController.Operation> it = operations.iterator();
        while (true) {
            boolean z = true;
            if (it.hasNext()) {
                final SpecialEffectsController.Operation operation2 = it.next();
                CancellationSignal animCancellationSignal = new CancellationSignal();
                operation2.markStartedSpecialEffect(animCancellationSignal);
                animations.add(new AnimationInfo(operation2, animCancellationSignal, isPop));
                CancellationSignal transitionCancellationSignal = new CancellationSignal();
                operation2.markStartedSpecialEffect(transitionCancellationSignal);
                if (isPop) {
                    if (operation2 == firstOut) {
                        transitions.add(new TransitionInfo(operation2, transitionCancellationSignal, isPop, z));
                        operation2.addCompletionListener(new Runnable() {
                            /* class androidx.fragment.app.DefaultSpecialEffectsController.RunnableC02931 */

                            public void run() {
                                if (awaitingContainerChanges.contains(operation2)) {
                                    awaitingContainerChanges.remove(operation2);
                                    DefaultSpecialEffectsController.this.applyContainerChanges(operation2);
                                }
                            }
                        });
                    }
                } else if (operation2 == lastIn) {
                    transitions.add(new TransitionInfo(operation2, transitionCancellationSignal, isPop, z));
                    operation2.addCompletionListener(new Runnable() {
                        /* class androidx.fragment.app.DefaultSpecialEffectsController.RunnableC02931 */

                        public void run() {
                            if (awaitingContainerChanges.contains(operation2)) {
                                awaitingContainerChanges.remove(operation2);
                                DefaultSpecialEffectsController.this.applyContainerChanges(operation2);
                            }
                        }
                    });
                }
                z = false;
                transitions.add(new TransitionInfo(operation2, transitionCancellationSignal, isPop, z));
                operation2.addCompletionListener(new Runnable() {
                    /* class androidx.fragment.app.DefaultSpecialEffectsController.RunnableC02931 */

                    public void run() {
                        if (awaitingContainerChanges.contains(operation2)) {
                            awaitingContainerChanges.remove(operation2);
                            DefaultSpecialEffectsController.this.applyContainerChanges(operation2);
                        }
                    }
                });
            } else {
                Map<SpecialEffectsController.Operation, Boolean> startedTransitions = startTransitions(transitions, awaitingContainerChanges, isPop, firstOut, lastIn);
                startAnimations(animations, awaitingContainerChanges, startedTransitions.containsValue(true), startedTransitions);
                for (SpecialEffectsController.Operation operation3 : awaitingContainerChanges) {
                    applyContainerChanges(operation3);
                }
                awaitingContainerChanges.clear();
                return;
            }
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10 */
    static /* synthetic */ class C029410 {

        /* renamed from: $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State */
        static final /* synthetic */ int[] f74xe493b431;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            f74xe493b431 = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f74xe493b431[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f74xe493b431[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f74xe493b431[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private void startAnimations(List<AnimationInfo> animationInfos, List<SpecialEffectsController.Operation> awaitingContainerChanges, boolean startedAnyTransition, Map<SpecialEffectsController.Operation, Boolean> startedTransitions) {
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        ArrayList<AnimationInfo> animationsToRun = new ArrayList<>();
        View viewToAnimate = null;
        Iterator<AnimationInfo> it = animationInfos.iterator();
        while (it.hasNext()) {
            final AnimationInfo animationInfo = it.next();
            if (animationInfo.isVisibilityUnchanged()) {
                animationInfo.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator anim = animationInfo.getAnimation(context);
                if (anim == null) {
                    animationInfo.completeSpecialEffect();
                } else {
                    final Animator animator = anim.animator;
                    if (animator == null) {
                        animationsToRun.add(animationInfo);
                    } else {
                        final SpecialEffectsController.Operation operation = animationInfo.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Boolean.TRUE.equals(startedTransitions.get(operation))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.completeSpecialEffect();
                        } else {
                            final boolean isHideOperation = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (isHideOperation) {
                                awaitingContainerChanges.remove(operation);
                            }
                            final View viewToAnimate2 = fragment.mView;
                            container.startViewTransition(viewToAnimate2);
                            animator.addListener(new AnimatorListenerAdapter() {
                                /* class androidx.fragment.app.DefaultSpecialEffectsController.C02952 */

                                public void onAnimationEnd(Animator anim) {
                                    container.endViewTransition(viewToAnimate2);
                                    if (isHideOperation) {
                                        operation.getFinalState().applyState(viewToAnimate2);
                                    }
                                    animationInfo.completeSpecialEffect();
                                }
                            });
                            animator.setTarget(viewToAnimate2);
                            animator.start();
                            animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() {
                                /* class androidx.fragment.app.DefaultSpecialEffectsController.C02963 */

                                @Override // androidx.core.p003os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    animator.end();
                                }
                            });
                            viewToAnimate = 1;
                            it = it;
                        }
                    }
                }
            }
        }
        Iterator<AnimationInfo> it2 = animationsToRun.iterator();
        while (it2.hasNext()) {
            final AnimationInfo animationInfo2 = it2.next();
            SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (startedAnyTransition) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (viewToAnimate != null) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                final View viewToAnimate3 = fragment2.mView;
                Animation anim2 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(animationInfo2.getAnimation(context))).animation);
                if (operation2.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    viewToAnimate3.startAnimation(anim2);
                    animationInfo2.completeSpecialEffect();
                } else {
                    container.startViewTransition(viewToAnimate3);
                    Animation animation = new FragmentAnim.EndViewTransitionAnimation(anim2, container, viewToAnimate3);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        /* class androidx.fragment.app.DefaultSpecialEffectsController.animationAnimation$AnimationListenerC02974 */

                        public void onAnimationStart(Animation animation) {
                        }

                        public void onAnimationEnd(Animation animation) {
                            container.post(new Runnable() {
                                /* class androidx.fragment.app.DefaultSpecialEffectsController.animationAnimation$AnimationListenerC02974.RunnableC02981 */

                                public void run() {
                                    container.endViewTransition(viewToAnimate3);
                                    animationInfo2.completeSpecialEffect();
                                }
                            });
                        }

                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    viewToAnimate3.startAnimation(animation);
                }
                animationInfo2.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() {
                    /* class androidx.fragment.app.DefaultSpecialEffectsController.C02995 */

                    @Override // androidx.core.p003os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        viewToAnimate3.clearAnimation();
                        container.endViewTransition(viewToAnimate3);
                        animationInfo2.completeSpecialEffect();
                    }
                });
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:141:0x050f, code lost:
        if (r11 == r43) goto L_0x0516;
     */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0531  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0564  */
    private Map<SpecialEffectsController.Operation, Boolean> startTransitions(List<TransitionInfo> transitionInfos, List<SpecialEffectsController.Operation> awaitingContainerChanges, final boolean isPop, final SpecialEffectsController.Operation firstOut, final SpecialEffectsController.Operation lastIn) {
        Object mergedNonOverlappingTransition;
        boolean involvedInSharedElementTransition;
        Iterator<TransitionInfo> it;
        ArrayList<View> sharedElementFirstOutViews;
        View nonExistentView;
        Iterator<TransitionInfo> it2;
        FragmentTransitionImpl transitionImpl;
        Rect lastInEpicenterRect;
        View firstOutEpicenterView;
        SpecialEffectsController.Operation operation;
        ArrayList<View> sharedElementLastInViews;
        Map<SpecialEffectsController.Operation, Boolean> startedTransitions;
        FragmentTransitionImpl transitionImpl2;
        Rect lastInEpicenterRect2;
        ArrayMap<String, String> sharedElementNameMapping;
        ArrayList<View> sharedElementLastInViews2;
        SpecialEffectsController.Operation operation2;
        Map<SpecialEffectsController.Operation, Boolean> startedTransitions2;
        SpecialEffectsController.Operation operation3;
        ArrayList<View> sharedElementFirstOutViews2;
        View nonExistentView2;
        SharedElementCallback exitingCallback;
        SharedElementCallback exitingCallback2;
        SharedElementCallback exitingCallback3;
        ArrayList<String> exitingNames;
        SharedElementCallback enteringCallback;
        SharedElementCallback enteringCallback2;
        SharedElementCallback exitingCallback4;
        ArrayList<String> exitingNames2;
        boolean z = isPop;
        SpecialEffectsController.Operation operation4 = firstOut;
        SpecialEffectsController.Operation operation5 = lastIn;
        Map<SpecialEffectsController.Operation, Boolean> startedTransitions3 = new HashMap<>();
        final FragmentTransitionImpl transitionImpl3 = null;
        for (TransitionInfo transitionInfo : transitionInfos) {
            if (!transitionInfo.isVisibilityUnchanged()) {
                FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
                if (transitionImpl3 == null) {
                    transitionImpl3 = handlingImpl;
                } else if (!(handlingImpl == null || transitionImpl3 == handlingImpl)) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (transitionImpl3 == null) {
            for (TransitionInfo transitionInfo2 : transitionInfos) {
                startedTransitions3.put(transitionInfo2.getOperation(), false);
                transitionInfo2.completeSpecialEffect();
            }
            return startedTransitions3;
        }
        View nonExistentView3 = new View(getContainer().getContext());
        Object sharedElementTransition = null;
        final Rect lastInEpicenterRect3 = new Rect();
        ArrayList<View> sharedElementFirstOutViews3 = new ArrayList<>();
        ArrayList<View> sharedElementLastInViews3 = new ArrayList<>();
        ArrayMap<String, String> sharedElementNameMapping2 = new ArrayMap<>();
        View firstOutEpicenterView2 = null;
        boolean hasLastInEpicenter = false;
        for (TransitionInfo transitionInfo3 : transitionInfos) {
            if (!transitionInfo3.hasSharedElementTransition() || operation4 == null || operation5 == null) {
                sharedElementNameMapping = sharedElementNameMapping2;
                sharedElementLastInViews2 = sharedElementLastInViews3;
                startedTransitions2 = startedTransitions3;
                sharedElementFirstOutViews2 = sharedElementFirstOutViews3;
                lastInEpicenterRect2 = lastInEpicenterRect3;
                nonExistentView2 = nonExistentView3;
                transitionImpl2 = transitionImpl3;
                operation2 = operation4;
                operation3 = operation5;
                firstOutEpicenterView2 = firstOutEpicenterView2;
            } else {
                Object sharedElementTransition2 = transitionImpl3.wrapTransitionInSet(transitionImpl3.cloneTransition(transitionInfo3.getSharedElementTransition()));
                ArrayList<String> exitingNames3 = lastIn.getFragment().getSharedElementSourceNames();
                ArrayList<String> firstOutSourceNames = firstOut.getFragment().getSharedElementSourceNames();
                int index = 0;
                for (ArrayList<String> firstOutTargetNames = firstOut.getFragment().getSharedElementTargetNames(); index < firstOutTargetNames.size(); firstOutTargetNames = firstOutTargetNames) {
                    int nameIndex = exitingNames3.indexOf(firstOutTargetNames.get(index));
                    if (nameIndex != -1) {
                        exitingNames3.set(nameIndex, firstOutSourceNames.get(index));
                    }
                    index++;
                    firstOutEpicenterView2 = firstOutEpicenterView2;
                }
                ArrayList<String> enteringNames = lastIn.getFragment().getSharedElementTargetNames();
                if (!z) {
                    exitingCallback = firstOut.getFragment().getExitTransitionCallback();
                    exitingCallback2 = lastIn.getFragment().getEnterTransitionCallback();
                } else {
                    exitingCallback = firstOut.getFragment().getEnterTransitionCallback();
                    exitingCallback2 = lastIn.getFragment().getExitTransitionCallback();
                }
                int i = 0;
                for (int numSharedElements = exitingNames3.size(); i < numSharedElements; numSharedElements = numSharedElements) {
                    sharedElementNameMapping2.put(exitingNames3.get(i), enteringNames.get(i));
                    i++;
                }
                ArrayMap<String, View> firstOutViews = new ArrayMap<>();
                findNamedViews(firstOutViews, firstOut.getFragment().mView);
                firstOutViews.retainAll(exitingNames3);
                if (exitingCallback != null) {
                    exitingCallback.onMapSharedElements(exitingNames3, firstOutViews);
                    int i2 = exitingNames3.size() - 1;
                    while (i2 >= 0) {
                        String name = exitingNames3.get(i2);
                        View view = firstOutViews.get(name);
                        if (view == null) {
                            sharedElementNameMapping2.remove(name);
                            exitingNames2 = exitingNames3;
                            exitingCallback4 = exitingCallback;
                        } else {
                            exitingNames2 = exitingNames3;
                            if (!name.equals(ViewCompat.getTransitionName(view))) {
                                exitingCallback4 = exitingCallback;
                                sharedElementNameMapping2.put(ViewCompat.getTransitionName(view), sharedElementNameMapping2.remove(name));
                            } else {
                                exitingCallback4 = exitingCallback;
                            }
                        }
                        i2--;
                        exitingNames3 = exitingNames2;
                        exitingCallback = exitingCallback4;
                    }
                    exitingNames = exitingNames3;
                    exitingCallback3 = exitingCallback;
                } else {
                    exitingNames = exitingNames3;
                    exitingCallback3 = exitingCallback;
                    sharedElementNameMapping2.retainAll(firstOutViews.keySet());
                }
                final ArrayMap<String, View> lastInViews = new ArrayMap<>();
                findNamedViews(lastInViews, lastIn.getFragment().mView);
                lastInViews.retainAll(enteringNames);
                lastInViews.retainAll(sharedElementNameMapping2.values());
                if (exitingCallback2 != null) {
                    exitingCallback2.onMapSharedElements(enteringNames, lastInViews);
                    int i3 = enteringNames.size() - 1;
                    while (i3 >= 0) {
                        String name2 = enteringNames.get(i3);
                        View view2 = lastInViews.get(name2);
                        if (view2 == null) {
                            enteringCallback2 = exitingCallback2;
                            String key = FragmentTransition.findKeyForValue(sharedElementNameMapping2, name2);
                            if (key != null) {
                                sharedElementNameMapping2.remove(key);
                            }
                        } else {
                            enteringCallback2 = exitingCallback2;
                            if (!name2.equals(ViewCompat.getTransitionName(view2))) {
                                String key2 = FragmentTransition.findKeyForValue(sharedElementNameMapping2, name2);
                                if (key2 != null) {
                                    sharedElementNameMapping2.put(key2, ViewCompat.getTransitionName(view2));
                                }
                            }
                        }
                        i3--;
                        exitingCallback2 = enteringCallback2;
                    }
                    enteringCallback = exitingCallback2;
                } else {
                    enteringCallback = exitingCallback2;
                    FragmentTransition.retainValues(sharedElementNameMapping2, lastInViews);
                }
                retainMatchingViews(firstOutViews, sharedElementNameMapping2.keySet());
                retainMatchingViews(lastInViews, sharedElementNameMapping2.values());
                if (sharedElementNameMapping2.isEmpty()) {
                    sharedElementTransition = null;
                    sharedElementFirstOutViews3.clear();
                    sharedElementLastInViews3.clear();
                    sharedElementNameMapping = sharedElementNameMapping2;
                    sharedElementLastInViews2 = sharedElementLastInViews3;
                    startedTransitions2 = startedTransitions3;
                    sharedElementFirstOutViews2 = sharedElementFirstOutViews3;
                    lastInEpicenterRect2 = lastInEpicenterRect3;
                    nonExistentView2 = nonExistentView3;
                    transitionImpl2 = transitionImpl3;
                    firstOutEpicenterView2 = firstOutEpicenterView2;
                    operation2 = firstOut;
                    operation3 = lastIn;
                } else {
                    FragmentTransition.callSharedElementStartEnd(lastIn.getFragment(), firstOut.getFragment(), z, firstOutViews, true);
                    sharedElementNameMapping = sharedElementNameMapping2;
                    OneShotPreDrawListener.add(getContainer(), new Runnable() {
                        /* class androidx.fragment.app.DefaultSpecialEffectsController.RunnableC03006 */

                        public void run() {
                            FragmentTransition.callSharedElementStartEnd(lastIn.getFragment(), firstOut.getFragment(), isPop, lastInViews, false);
                        }
                    });
                    sharedElementFirstOutViews3.addAll(firstOutViews.values());
                    if (!exitingNames.isEmpty()) {
                        firstOutEpicenterView2 = firstOutViews.get(exitingNames.get(0));
                        transitionImpl3.setEpicenter(sharedElementTransition2, firstOutEpicenterView2);
                    } else {
                        firstOutEpicenterView2 = firstOutEpicenterView2;
                    }
                    sharedElementLastInViews3.addAll(lastInViews.values());
                    if (!enteringNames.isEmpty()) {
                        final View lastInEpicenterView = lastInViews.get(enteringNames.get(0));
                        if (lastInEpicenterView != null) {
                            hasLastInEpicenter = true;
                            OneShotPreDrawListener.add(getContainer(), new Runnable() {
                                /* class androidx.fragment.app.DefaultSpecialEffectsController.RunnableC03017 */

                                public void run() {
                                    transitionImpl3.getBoundsOnScreen(lastInEpicenterView, lastInEpicenterRect3);
                                }
                            });
                        }
                    }
                    nonExistentView2 = nonExistentView3;
                    transitionImpl3.setSharedElementTargets(sharedElementTransition2, nonExistentView2, sharedElementFirstOutViews3);
                    sharedElementFirstOutViews2 = sharedElementFirstOutViews3;
                    lastInEpicenterRect2 = lastInEpicenterRect3;
                    sharedElementLastInViews2 = sharedElementLastInViews3;
                    transitionImpl2 = transitionImpl3;
                    transitionImpl3.scheduleRemoveTargets(sharedElementTransition2, null, null, null, null, sharedElementTransition2, sharedElementLastInViews2);
                    operation2 = firstOut;
                    startedTransitions2 = startedTransitions3;
                    startedTransitions2.put(operation2, true);
                    operation3 = lastIn;
                    startedTransitions2.put(operation3, true);
                    sharedElementTransition = sharedElementTransition2;
                }
            }
            z = isPop;
            sharedElementFirstOutViews3 = sharedElementFirstOutViews2;
            operation5 = operation3;
            startedTransitions3 = startedTransitions2;
            operation4 = operation2;
            sharedElementLastInViews3 = sharedElementLastInViews2;
            sharedElementNameMapping2 = sharedElementNameMapping;
            lastInEpicenterRect3 = lastInEpicenterRect2;
            transitionImpl3 = transitionImpl2;
            nonExistentView3 = nonExistentView2;
        }
        View firstOutEpicenterView3 = firstOutEpicenterView2;
        ArrayList<View> sharedElementLastInViews4 = sharedElementLastInViews3;
        Map<SpecialEffectsController.Operation, Boolean> startedTransitions4 = startedTransitions3;
        ArrayList<View> sharedElementFirstOutViews4 = sharedElementFirstOutViews3;
        Rect lastInEpicenterRect4 = lastInEpicenterRect3;
        View nonExistentView4 = nonExistentView3;
        FragmentTransitionImpl transitionImpl4 = transitionImpl3;
        boolean z2 = false;
        SpecialEffectsController.Operation operation6 = operation4;
        SpecialEffectsController.Operation operation7 = operation5;
        ArrayList<View> enteringViews = new ArrayList<>();
        Object mergedTransition = null;
        Object mergedNonOverlappingTransition2 = null;
        Iterator<TransitionInfo> it3 = transitionInfos.iterator();
        while (it3.hasNext()) {
            TransitionInfo transitionInfo4 = it3.next();
            if (transitionInfo4.isVisibilityUnchanged()) {
                startedTransitions4.put(transitionInfo4.getOperation(), Boolean.valueOf(z2));
                transitionInfo4.completeSpecialEffect();
            } else {
                Object transition = transitionImpl4.cloneTransition(transitionInfo4.getTransition());
                SpecialEffectsController.Operation operation8 = transitionInfo4.getOperation();
                boolean involvedInSharedElementTransition2 = sharedElementTransition != null && (operation8 == operation6 || operation8 == operation7);
                if (transition == null) {
                    if (!involvedInSharedElementTransition2) {
                        it2 = it3;
                        startedTransitions4.put(operation8, Boolean.valueOf(z2));
                        transitionInfo4.completeSpecialEffect();
                    } else {
                        it2 = it3;
                    }
                    nonExistentView = nonExistentView4;
                    sharedElementFirstOutViews = sharedElementFirstOutViews4;
                    startedTransitions = startedTransitions4;
                    operation = operation6;
                    sharedElementLastInViews = sharedElementLastInViews4;
                    lastInEpicenterRect = lastInEpicenterRect4;
                    transitionImpl = transitionImpl4;
                    firstOutEpicenterView = firstOutEpicenterView3;
                } else {
                    it2 = it3;
                    final ArrayList<View> transitioningViews = new ArrayList<>();
                    captureTransitioningViews(transitioningViews, operation8.getFragment().mView);
                    if (!involvedInSharedElementTransition2) {
                        sharedElementLastInViews = sharedElementLastInViews4;
                    } else if (operation8 == operation6) {
                        transitioningViews.removeAll(sharedElementFirstOutViews4);
                        sharedElementLastInViews = sharedElementLastInViews4;
                    } else {
                        sharedElementLastInViews = sharedElementLastInViews4;
                        transitioningViews.removeAll(sharedElementLastInViews);
                    }
                    if (transitioningViews.isEmpty()) {
                        transitionImpl4.addTarget(transition, nonExistentView4);
                        nonExistentView = nonExistentView4;
                        sharedElementFirstOutViews = sharedElementFirstOutViews4;
                        startedTransitions = startedTransitions4;
                        operation = operation6;
                        transitionImpl = transitionImpl4;
                    } else {
                        transitionImpl4.addTargets(transition, transitioningViews);
                        nonExistentView = nonExistentView4;
                        startedTransitions = startedTransitions4;
                        sharedElementFirstOutViews = sharedElementFirstOutViews4;
                        operation = operation6;
                        transitionImpl4.scheduleRemoveTargets(transition, transition, transitioningViews, null, null, null, null);
                        if (operation8.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation8 = operation8;
                            awaitingContainerChanges.remove(operation8);
                            ArrayList<View> transitioningViewsToHide = new ArrayList<>(transitioningViews);
                            transitioningViewsToHide.remove(operation8.getFragment().mView);
                            transitionImpl = transitionImpl4;
                            transitionImpl.scheduleHideFragmentView(transition, operation8.getFragment().mView, transitioningViewsToHide);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() {
                                /* class androidx.fragment.app.DefaultSpecialEffectsController.RunnableC03028 */

                                public void run() {
                                    FragmentTransition.setViewVisibility(transitioningViews, 4);
                                }
                            });
                        } else {
                            operation8 = operation8;
                            transitionImpl = transitionImpl4;
                        }
                    }
                    if (operation8.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        enteringViews.addAll(transitioningViews);
                        if (hasLastInEpicenter) {
                            lastInEpicenterRect = lastInEpicenterRect4;
                            transitionImpl.setEpicenter(transition, lastInEpicenterRect);
                            firstOutEpicenterView = firstOutEpicenterView3;
                        } else {
                            lastInEpicenterRect = lastInEpicenterRect4;
                            firstOutEpicenterView = firstOutEpicenterView3;
                        }
                    } else {
                        lastInEpicenterRect = lastInEpicenterRect4;
                        firstOutEpicenterView = firstOutEpicenterView3;
                        transitionImpl.setEpicenter(transition, firstOutEpicenterView);
                    }
                    startedTransitions.put(operation8, true);
                    if (transitionInfo4.isOverlapAllowed()) {
                        mergedTransition = transitionImpl.mergeTransitionsTogether(mergedTransition, transition, null);
                    } else {
                        mergedNonOverlappingTransition2 = transitionImpl.mergeTransitionsTogether(mergedNonOverlappingTransition2, transition, null);
                    }
                }
                sharedElementLastInViews4 = sharedElementLastInViews;
                operation6 = operation;
                firstOutEpicenterView3 = firstOutEpicenterView;
                lastInEpicenterRect4 = lastInEpicenterRect;
                transitionImpl4 = transitionImpl;
                it3 = it2;
                sharedElementFirstOutViews4 = sharedElementFirstOutViews;
                z2 = false;
                operation7 = lastIn;
                startedTransitions4 = startedTransitions;
                nonExistentView4 = nonExistentView;
            }
        }
        Object mergedTransition2 = transitionImpl4.mergeTransitionsInSequence(mergedTransition, mergedNonOverlappingTransition2, sharedElementTransition);
        Iterator<TransitionInfo> it4 = transitionInfos.iterator();
        while (it4.hasNext()) {
            final TransitionInfo transitionInfo5 = it4.next();
            if (!transitionInfo5.isVisibilityUnchanged()) {
                Object transition2 = transitionInfo5.getTransition();
                SpecialEffectsController.Operation operation9 = transitionInfo5.getOperation();
                if (sharedElementTransition != null) {
                    if (operation9 != operation6) {
                        mergedNonOverlappingTransition = mergedNonOverlappingTransition2;
                    } else {
                        mergedNonOverlappingTransition = mergedNonOverlappingTransition2;
                    }
                    involvedInSharedElementTransition = true;
                    if (transition2 != null && !involvedInSharedElementTransition) {
                        it = it4;
                    } else if (ViewCompat.isLaidOut(getContainer())) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            StringBuilder sb = new StringBuilder();
                            it = it4;
                            sb.append("SpecialEffectsController: Container ");
                            sb.append(getContainer());
                            sb.append(" has not been laid out. Completing operation ");
                            sb.append(operation9);
                            Log.v("FragmentManager", sb.toString());
                        } else {
                            it = it4;
                        }
                        transitionInfo5.completeSpecialEffect();
                    } else {
                        it = it4;
                        transitionImpl4.setListenerForTransitionEnd(transitionInfo5.getOperation().getFragment(), mergedTransition2, transitionInfo5.getSignal(), new Runnable() {
                            /* class androidx.fragment.app.DefaultSpecialEffectsController.RunnableC03039 */

                            public void run() {
                                transitionInfo5.completeSpecialEffect();
                            }
                        });
                    }
                    mergedNonOverlappingTransition2 = mergedNonOverlappingTransition;
                    it4 = it;
                } else {
                    mergedNonOverlappingTransition = mergedNonOverlappingTransition2;
                }
                involvedInSharedElementTransition = false;
                if (transition2 != null) {
                }
                if (ViewCompat.isLaidOut(getContainer())) {
                }
                mergedNonOverlappingTransition2 = mergedNonOverlappingTransition;
                it4 = it;
            }
        }
        if (!ViewCompat.isLaidOut(getContainer())) {
            return startedTransitions4;
        }
        FragmentTransition.setViewVisibility(enteringViews, 4);
        ArrayList<String> inNames = transitionImpl4.prepareSetNameOverridesReordered(sharedElementLastInViews4);
        transitionImpl4.beginDelayedTransition(getContainer(), mergedTransition2);
        transitionImpl4.setNameOverridesReordered(getContainer(), sharedElementFirstOutViews4, sharedElementLastInViews4, inNames, sharedElementNameMapping2);
        FragmentTransition.setViewVisibility(enteringViews, 0);
        transitionImpl4.swapSharedElementTargets(sharedElementTransition, sharedElementFirstOutViews4, sharedElementLastInViews4);
        return startedTransitions4;
    }

    /* access modifiers changed from: package-private */
    public void retainMatchingViews(ArrayMap<String, View> sharedElementViews, Collection<String> transitionNames) {
        Iterator<Map.Entry<String, View>> iterator = sharedElementViews.entrySet().iterator();
        while (iterator.hasNext()) {
            if (!transitionNames.contains(ViewCompat.getTransitionName(iterator.next().getValue()))) {
                iterator.remove();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void captureTransitioningViews(ArrayList<View> transitioningViews, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!ViewGroupCompat.isTransitionGroup(viewGroup)) {
                int count = viewGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    View child = viewGroup.getChildAt(i);
                    if (child.getVisibility() == 0) {
                        captureTransitioningViews(transitioningViews, child);
                    }
                }
            } else if (!transitioningViews.contains(view)) {
                transitioningViews.add(viewGroup);
            }
        } else if (!transitioningViews.contains(view)) {
            transitioningViews.add(view);
        }
    }

    /* access modifiers changed from: package-private */
    public void findNamedViews(Map<String, View> namedViews, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            namedViews.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = viewGroup.getChildAt(i);
                if (child.getVisibility() == 0) {
                    findNamedViews(namedViews, child);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void applyContainerChanges(SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal signal) {
            this.mOperation = operation;
            this.mSignal = signal;
        }

        /* access modifiers changed from: package-private */
        public SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        /* access modifiers changed from: package-private */
        public CancellationSignal getSignal() {
            return this.mSignal;
        }

        /* access modifiers changed from: package-private */
        public boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State currentState = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return currentState == finalState || !(currentState == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        /* access modifiers changed from: package-private */
        public void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }
    }

    /* access modifiers changed from: private */
    public static class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim = false;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean isPop) {
            super(operation, signal);
            this.mIsPop = isPop;
        }

        /* access modifiers changed from: package-private */
        public FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = loadAnimation;
            this.mLoadedAnim = true;
            return loadAnimation;
        }
    }

    /* access modifiers changed from: private */
    public static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean isPop, boolean providesSharedElementTransition) {
            super(operation, signal);
            Object obj;
            Object obj2;
            boolean z;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (isPop) {
                    obj2 = operation.getFragment().getReenterTransition();
                } else {
                    obj2 = operation.getFragment().getEnterTransition();
                }
                this.mTransition = obj2;
                if (isPop) {
                    z = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    z = operation.getFragment().getAllowEnterTransitionOverlap();
                }
                this.mOverlapAllowed = z;
            } else {
                if (isPop) {
                    obj = operation.getFragment().getReturnTransition();
                } else {
                    obj = operation.getFragment().getExitTransition();
                }
                this.mTransition = obj;
                this.mOverlapAllowed = true;
            }
            if (!providesSharedElementTransition) {
                this.mSharedElementTransition = null;
            } else if (isPop) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        /* access modifiers changed from: package-private */
        public Object getTransition() {
            return this.mTransition;
        }

        /* access modifiers changed from: package-private */
        public boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        /* access modifiers changed from: package-private */
        public FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl transitionImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl sharedElementTransitionImpl = getHandlingImpl(this.mSharedElementTransition);
            if (transitionImpl == null || sharedElementTransitionImpl == null || transitionImpl == sharedElementTransitionImpl) {
                return transitionImpl != null ? transitionImpl : sharedElementTransitionImpl;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object transition) {
            if (transition == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(transition)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(transition)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + transition + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
