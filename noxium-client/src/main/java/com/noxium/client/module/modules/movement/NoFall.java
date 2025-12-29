package com.noxium.client.module.modules.movement;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;

public class NoFall extends Module {
    public NoFall() {
        super("NoFall", "No fall damage", ModuleCategory.MOVEMENT);
    }
    @Override
    public void onTick() {
        if (mc.player != null) {
            mc.player.setOnGround(true);
        }
    }
}