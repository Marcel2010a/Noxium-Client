package com.noxium.client.module.modules.movement;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;

public class Speed extends Module {
    public Speed() {
        super("Speed", "Move faster", ModuleCategory.MOVEMENT);
    }
    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.isOnGround() && mc.player.forwardSpeed > 0) {
            mc.player.setVelocity(
                mc.player.getVelocity().x * 1.5,
                mc.player.getVelocity().y,
                mc.player.getVelocity().z * 1.5
            );
        }
    }
}