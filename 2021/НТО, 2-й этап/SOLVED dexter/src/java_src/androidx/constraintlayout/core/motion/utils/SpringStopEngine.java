package androidx.constraintlayout.core.motion.utils;

import java.io.PrintStream;

public class SpringStopEngine implements StopEngine {
    private static final double UNSET = Double.MAX_VALUE;
    private int mBoundaryMode = 0;
    double mDamping = 0.5d;
    private boolean mInitialized = false;
    private float mLastTime;
    private double mLastVelocity;
    private float mMass;
    private float mPos;
    private double mStiffness;
    private float mStopThreshold;
    private double mTargetPos;

    /* renamed from: mV */
    private float f34mV;

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public String debug(String desc, float time) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public void log(String str) {
        StackTraceElement s = new Throwable().getStackTrace()[1];
        PrintStream printStream = System.out;
        printStream.println((".(" + s.getFileName() + ":" + s.getLineNumber() + ") " + s.getMethodName() + "() ") + str);
    }

    public void springConfig(float currentPos, float target, float currentVelocity, float mass, float stiffness, float damping, float stopThreshold, int boundaryMode) {
        this.mTargetPos = (double) target;
        this.mDamping = (double) damping;
        this.mInitialized = false;
        this.mPos = currentPos;
        this.mLastVelocity = (double) currentVelocity;
        this.mStiffness = (double) stiffness;
        this.mMass = mass;
        this.mStopThreshold = stopThreshold;
        this.mBoundaryMode = boundaryMode;
        this.mLastTime = 0.0f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity(float t) {
        return this.f34mV;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float time) {
        compute((double) (time - this.mLastTime));
        this.mLastTime = time;
        return this.mPos;
    }

    public float getAcceleration() {
        double k = this.mStiffness;
        double c = this.mDamping;
        return ((float) (((-k) * (((double) this.mPos) - this.mTargetPos)) - (((double) this.f34mV) * c))) / this.mMass;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity() {
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean isStopped() {
        double x = ((double) this.mPos) - this.mTargetPos;
        double k = this.mStiffness;
        double v = (double) this.f34mV;
        return Math.sqrt((((v * v) * ((double) this.mMass)) + ((k * x) * x)) / k) <= ((double) this.mStopThreshold);
    }

    private void compute(double dt) {
        double k = this.mStiffness;
        double c = this.mDamping;
        int overSample = (int) ((9.0d / ((Math.sqrt(this.mStiffness / ((double) this.mMass)) * dt) * 4.0d)) + 1.0d);
        double dt2 = dt / ((double) overSample);
        int i = 0;
        while (i < overSample) {
            float f = this.mPos;
            double d = this.mTargetPos;
            float f2 = this.f34mV;
            float f3 = this.mMass;
            double avgV = ((double) f2) + ((((((-k) * (((double) f) - d)) - (((double) f2) * c)) / ((double) f3)) * dt2) / 2.0d);
            double dv = ((((-((((double) f) + ((dt2 * avgV) / 2.0d)) - d)) * k) - (avgV * c)) / ((double) f3)) * dt2;
            float f4 = (float) (((double) f2) + dv);
            this.f34mV = f4;
            float f5 = (float) (((double) f) + ((((double) f2) + (dv / 2.0d)) * dt2));
            this.mPos = f5;
            int i2 = this.mBoundaryMode;
            if (i2 > 0) {
                if (f5 < 0.0f && (i2 & 1) == 1) {
                    this.mPos = -f5;
                    this.f34mV = -f4;
                }
                float f6 = this.mPos;
                if (f6 > 1.0f && (i2 & 2) == 2) {
                    this.mPos = 2.0f - f6;
                    this.f34mV = -this.f34mV;
                }
            }
            i++;
            overSample = overSample;
            c = c;
            k = k;
        }
    }
}
