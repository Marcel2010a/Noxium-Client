package com.noxium.client.module.modules.movement;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

public class Flight extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting speed;
    private BooleanSetting boostMode;

    public Flight() {
        super("Flight", "Allows you to fly", ModuleCategory.MOVEMENT);
        setKey(GLFW.GLFW_KEY_F);
        initSettings();
    }

    private void initSettings() {
        this.speed = new NumberSetting("Speed", 1.0f, 0.1f, 5.0f, 0.1f);
        this.boostMode = new BooleanSetting("Boost Mode", false);
        addSetting(speed);
        addSetting(boostMode);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }

    @Override
    public void onEnable() {
        if (mc.player != null) {
            mc.player.getAbilities().allowFlying = true;
            mc.player.getAbilities().flying = true;
        }
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.getAbilities().allowFlying = false;
            mc.player.getAbilities().flying = false;
        }
    }
}