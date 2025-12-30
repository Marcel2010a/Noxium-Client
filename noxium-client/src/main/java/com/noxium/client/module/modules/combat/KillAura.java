package com.noxium.client.module.modules.combat;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;

import net.minecraft.entity.*;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class KillAura extends Module {
    
    private List<Setting<?>> settings = new ArrayList<>();
    
    // Settings
    private NumberSetting range;
    private NumberSetting attackSpeed;
    private BooleanSetting targetPlayers;
    private BooleanSetting targetMobs;
    private BooleanSetting targetAnimals;

    public KillAura() {
        super("KillAura", "Automatically attacks entities around you", ModuleCategory.COMBAT);
        setKey(GLFW.GLFW_KEY_R);
        initSettings();
    }

    private void initSettings() {
        this.range = new NumberSetting("Range", 6.0f, 1.0f, 10.0f, 0.5f);
        this.attackSpeed = new NumberSetting("Speed", 5.0f, 0.1f, 10.0f, 0.1f);
        this.targetPlayers = new BooleanSetting("Target Players", true);
        this.targetMobs = new BooleanSetting("Target Mobs", true);
        this.targetAnimals = new BooleanSetting("Target Animals", false);

        addSetting(range);
        addSetting(attackSpeed);
        addSetting(targetPlayers);
        addSetting(targetMobs);
        addSetting(targetAnimals);
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
        
        Entity target = null;
        double closestDistance = range.getValue();
        
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof LivingEntity && entity != mc.player) {
                double distance = mc.player.distanceTo(entity);
                if (distance < closestDistance) {
                    if (shouldTarget(entity)) {
                        closestDistance = distance;
                        target = entity;
                    }
                }
            }
        }
        
        if (target != null && mc.interactionManager != null) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }

    private boolean shouldTarget(Entity entity) {
        if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
            return targetPlayers.isEnabled();
        } else if (entity instanceof net.minecraft.entity.mob.MobEntity) {
            if (entity instanceof net.minecraft.entity.passive.AnimalEntity) {
                return targetAnimals.isEnabled();
            }
            return targetMobs.isEnabled();
        }
        return false;
    }

    public float getRange() {
        return range.getValue();
    }

    public float getAttackSpeed() {
        return attackSpeed.getValue();
    }
}