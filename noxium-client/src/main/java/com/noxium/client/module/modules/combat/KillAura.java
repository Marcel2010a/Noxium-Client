package com.noxium.client.module.modules.combat;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;

import net.minecraft.entity.*;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class KillAura extends Module {
    public KillAura() {
        super("KillAura", "Automatically attacks entities around you", ModuleCategory.COMBAT);
        setKey(GLFW.GLFW_KEY_R);
    }
    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;
        Entity target = null;
        double closestDistance = 6.0;
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof LivingEntity && entity != mc.player) {
                double distance = mc.player.distanceTo(entity);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    target = entity;
                }
            }
        }
        if (target != null && mc.interactionManager != null) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}