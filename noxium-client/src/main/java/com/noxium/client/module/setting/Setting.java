package com.noxium.client.module.setting;

import java.util.function.Consumer;

public abstract class Setting<T> {
    
    private String name;
    protected T value;
    protected T defaultValue;
    private Consumer<T> callback;

    public Setting(String name, T defaultValue) {
        this.name = name;
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        if (callback != null) {
            callback.accept(value);
        }
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setCallback(Consumer<T> callback) {
        this.callback = callback;
    }

    public void reset() {
        setValue(defaultValue);
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}