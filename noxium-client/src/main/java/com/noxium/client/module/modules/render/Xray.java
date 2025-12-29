package com.noxium.client.module.modules.render;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;

import org.lwjgl.glfw.GLFW;

public class Xray extends Module {
    public Xray() {
        super("Xray", "See ores", ModuleCategory.RENDER);
        setKey(GLFW.GLFW_KEY_X);
    }
    @Override
    public void onEnable() {
        if (mc.worldRenderer != null) mc.worldRenderer.reload();
    }
    @Override
    public void onDisable() {
        if (mc.worldRenderer != null) mc.worldRenderer.reload();
    }
}