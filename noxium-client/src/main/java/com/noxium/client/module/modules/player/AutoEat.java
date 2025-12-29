package com.noxium.client.module.modules.player;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;

import net.minecraft.item.ItemStack;
import net.minecraft.component.DataComponentTypes;

public class AutoEat extends Module {
    public AutoEat() {
        super("AutoEat", "Auto eats food", ModuleCategory.PLAYER);
    }
    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.getHungerManager().getFoodLevel() <= 16) {
            for (int i = 0; i < 9; i++) {
                ItemStack stack = mc.player.getInventory().getStack(i);
                if (stack.contains(DataComponentTypes.FOOD)) {
                    mc.player.getInventory().selectedSlot = i;
                    mc.options.useKey.setPressed(true);
                    break;
                }
            }
        } else {
            mc.options.useKey.setPressed(false);
        }
    }
}