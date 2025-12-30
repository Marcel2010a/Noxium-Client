package com.noxium.client.module.modules.combat;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class AutoCrystal extends Module {
    
    private List<Setting<?>> settings = new ArrayList<>();
    
    // Settings
    private NumberSetting range;
    private NumberSetting placeDelay;
    private BooleanSetting autoDestroy;
    private BooleanSetting targetSelf;
    private NumberSetting damageThreshold;

    public AutoCrystal() {
        super("AutoCrystal", "Auto crystal PvP", ModuleCategory.COMBAT);
        initSettings();
    }

    private void initSettings() {
        this.range = new NumberSetting("Range", 6.0f, 1.0f, 10.0f, 0.5f);
        this.placeDelay = new NumberSetting("Place Delay", 2.0f, 0.0f, 10.0f, 0.5f);
        this.autoDestroy = new BooleanSetting("Auto Destroy", true);
        this.targetSelf = new BooleanSetting("Target Self", false);
        this.damageThreshold = new NumberSetting("Min Damage", 3.0f, 0.5f, 10.0f, 0.5f);

        addSetting(range);
        addSetting(placeDelay);
        addSetting(autoDestroy);
        addSetting(targetSelf);
        addSetting(damageThreshold);
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
        if (mc.player == null || mc.world == null) return;

        // Suche Ziel-Spieler
        LivingEntity target = null;
        double closestDistance = range.getValue();
        
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof net.minecraft.entity.player.PlayerEntity && entity != mc.player) {
                double distance = mc.player.distanceTo(entity);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    target = (LivingEntity) entity;
                }
            }
        }

        // Suche und zerstöre Crystals
        if (autoDestroy.isEnabled()) {
            for (Entity entity : mc.world.getEntities()) {
                if (entity instanceof EndCrystalEntity) {
                    double distance = mc.player.distanceTo(entity);
                    if (distance < range.getValue()) {
                        if (mc.interactionManager != null) {
                            mc.interactionManager.attackEntity(mc.player, entity);
                            mc.player.swingHand(Hand.MAIN_HAND);
                        }
                    }
                }
            }
        }

        // Platziere Crystals neben Ziel
        if (target != null) {
            // Hier könnte die Logik zum Platzieren implementiert werden
        }
    }

    public float getRange() {
        return range.getValue();
    }

    public float getPlaceDelay() {
        return placeDelay.getValue();
    }

    public boolean shouldAutoDestroy() {
        return autoDestroy.isEnabled();
    }

    public float getDamageThreshold() {
        return damageThreshold.getValue();
    }
}