package com.noxium.client.module.modules.combat;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;

import org.lwjgl.glfw.GLFW;

public class Velocity extends Module {
    public Velocity() {
        super("Velocity", "Modifies knockback you take", ModuleCategory.COMBAT);
        setKey(GLFW.GLFW_KEY_V);
    }
}