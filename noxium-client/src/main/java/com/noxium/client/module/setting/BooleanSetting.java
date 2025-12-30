package com.noxium.client.module.setting;

public class BooleanSetting extends Setting<Boolean> {

    public BooleanSetting(String name, boolean defaultValue) {
        super(name, defaultValue);
    }

    public boolean isEnabled() {
        return value;
    }

    public void toggle() {
        setValue(!value);
    }
}