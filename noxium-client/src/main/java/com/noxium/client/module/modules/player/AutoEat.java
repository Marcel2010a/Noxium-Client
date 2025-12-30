package com.noxium.client.module.modules.player;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.Setting;
import net.minecraft.item.ItemStack;
import net.minecraft.component.DataComponentTypes;
import java.util.ArrayList;
import java.util.List;

public class AutoEat extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting foodThreshold;
    private BooleanSetting pauseAttack;

    public AutoEat() {
        super("AutoEat", "Auto eats food", ModuleCategory.PLAYER);
        initSettings();
    }

    private void initSettings() {
        this.foodThreshold = new NumberSetting("Threshold", 16.0f, 1.0f, 20.0f, 1.0f);
        this.pauseAttack = new BooleanSetting("Pause Attack", true);
        addSetting(foodThreshold);
        addSetting(pauseAttack);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.getHungerManager().getFoodLevel() <= foodThreshold.getValue()) {
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