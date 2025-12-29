package com.noxium.client.module.modules.combat;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;

import net.minecraft.item.Items;

public class AutoTotem extends Module {
    public AutoTotem() {
        super("AutoTotem", "Auto replaces totems in offhand", ModuleCategory.COMBAT);
    }
    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING) {
            for (int i = 0; i < mc.player.getInventory().size(); i++) {
                if (mc.player.getInventory().getStack(i).getItem() == Items.TOTEM_OF_UNDYING) {
                    break;
                }
            }
        }
    }
}