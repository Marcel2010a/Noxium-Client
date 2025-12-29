package com.noxium.client.module.modules.movement;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Auto sprint", ModuleCategory.MOVEMENT);
    }
    @Override
    public void onTick() {
        if (mc.player != null && mc.player.forwardSpeed > 0) {
            mc.player.setSprinting(true);
        }
    }
}