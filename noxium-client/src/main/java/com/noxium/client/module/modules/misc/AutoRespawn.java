package com.noxium.client.module.modules.misc;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", "Auto respawn", ModuleCategory.MISC);
    }
    @Override
    public void onTick() {
        if (mc.player != null && mc.player.isDead()) {
            mc.player.requestRespawn();
        }
    }
}