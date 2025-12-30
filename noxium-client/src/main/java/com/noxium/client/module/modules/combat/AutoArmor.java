package com.noxium.client.module.modules.combat;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AutoArmor extends Module {
    
    private List<Setting<?>> settings = new ArrayList<>();
    
    // Settings
    private BooleanSetting preferBest;
    private NumberSetting checkInterval;
    private BooleanSetting swapWhileAttacking;

    public AutoArmor() {
        super("AutoArmor", "Automatically equips armor", ModuleCategory.COMBAT);
        initSettings();
    }

    private void initSettings() {
        this.preferBest = new BooleanSetting("Prefer Best", true);
        this.checkInterval = new NumberSetting("Check Speed", 1.0f, 0.1f, 5.0f, 0.1f);
        this.swapWhileAttacking = new BooleanSetting("While Attacking", true);

        addSetting(preferBest);
        addSetting(checkInterval);
        addSetting(swapWhileAttacking);
    }

    public void addSetting(Setting<?> setting) {
        settings.add(setting);
    }

    @Override
    public List<Setting<?>> getSettings() {
        return settings;
    }

    @Override
    public void onTick() {
        if (mc.player == null) return;

        // Prüfe Rüstung in Inventar und equip beste
        for (int i = 0; i < mc.player.getInventory().size(); i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            
            if (stack.getItem() instanceof ArmorItem) {
                ArmorItem armor = (ArmorItem) stack.getItem();
                
                // Prüfe ob besser als aktuelle Rüstung
                if (preferBest.isEnabled()) {
                    // Hier könnte die Logik zum Equip implementiert werden
                }
            }
        }
    }

    public boolean shouldPreferBest() {
        return preferBest.isEnabled();
    }

    public float getCheckInterval() {
        return checkInterval.getValue();
    }

    public boolean canSwapWhileAttacking() {
        return swapWhileAttacking.isEnabled();
    }
}