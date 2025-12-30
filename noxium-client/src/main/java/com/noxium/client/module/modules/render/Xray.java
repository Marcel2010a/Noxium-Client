package com.noxium.client.module.modules.render;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.Setting;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

public class Xray extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private BooleanSetting showOres;
    private BooleanSetting showChests;

    public Xray() {
        super("Xray", "See ores", ModuleCategory.RENDER);
        setKey(GLFW.GLFW_KEY_X);
        initSettings();
    }

    private void initSettings() {
        this.showOres = new BooleanSetting("Show Ores", true);
        this.showChests = new BooleanSetting("Show Chests", true);
        addSetting(showOres);
        addSetting(showChests);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }

    @Override
    public void onEnable() {
        if (mc.worldRenderer != null) mc.worldRenderer.reload();
    }

    @Override
    public void onDisable() {
        if (mc.worldRenderer != null) mc.worldRenderer.reload();
    }
}