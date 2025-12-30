package com.noxium.client.module.setting;

public class NumberSetting extends Setting<Float> {

    private float min;
    private float max;
    private float increment;

    public NumberSetting(String name, float defaultValue, float min, float max) {
        this(name, defaultValue, min, max, 0.1f);
    }

    public NumberSetting(String name, float defaultValue, float min, float max, float increment) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    @Override
    public void setValue(Float value) {
        // Clamp value zwischen min und max
        float clamped = Math.max(min, Math.min(max, value));
        super.setValue(clamped);
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getIncrement() {
        return increment;
    }

    public float getProgress() {
        return (value - min) / (max - min);
    }

    public void setProgress(float progress) {
        float clamped = Math.max(0, Math.min(1, progress));
        setValue(min + (max - min) * clamped);
    }

    @Override
    public String toString() {
        return getName() + ": " + String.format("%.2f", value);
    }
}