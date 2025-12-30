package com.noxium.client.module.modules.render;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;

public class Fullbright extends Module {
    private double previousGamma;
    private NumberSetting brightnessLevel;

    public Fullbright() {
        super("Fullbright", "See in the dark", ModuleCategory.RENDER);
        initSettings();
    }

    private void initSettings() {
        // Floats verwenden, nicht Doubles
        this.brightnessLevel = new NumberSetting("Brightness", 16.0f, 1.0f, 16.0f, 1.0f);
        addSetting(brightnessLevel);
    }

    @Override
    public void onEnable() {
        if (mc.options != null) {
            previousGamma = mc.options.getGamma().getValue();
            mc.options.getGamma().setValue((double) brightnessLevel.getValue());
        }
    }

    @Override
    public void onDisable() {
        if (mc.options != null) {
            mc.options.getGamma().setValue(previousGamma);
        }
    }
}
