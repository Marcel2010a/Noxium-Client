package com.noxium.client.module.setting;

public class StringSetting extends Setting<String> {

    private int maxLength;

    public StringSetting(String name, String defaultValue) {
        this(name, defaultValue, 50);
    }

    public StringSetting(String name, String defaultValue, int maxLength) {
        super(name, defaultValue);
        this.maxLength = maxLength;
    }

    @Override
    public void setValue(String value) {
        if (value.length() > maxLength) {
            super.setValue(value.substring(0, maxLength));
        } else {
            super.setValue(value);
        }
    }

    public int getMaxLength() {
        return maxLength;
    }
}