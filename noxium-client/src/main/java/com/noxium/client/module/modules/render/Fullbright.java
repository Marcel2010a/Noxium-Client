package com.noxium.client.module.modules.render;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;

public class Fullbright extends Module {
    private double previousGamma;
    public Fullbright() {
        super("Fullbright", "See in the dark", ModuleCategory.RENDER);
    }
    @Override
    public void onEnable() {
        if (mc.options != null) {
            previousGamma = mc.options.getGamma().getValue();
            mc.options.getGamma().setValue(16.0);
        }
    }
    @Override
    public void onDisable() {
        if (mc.options != null) {
            mc.options.getGamma().setValue(previousGamma);
        }
    }
}