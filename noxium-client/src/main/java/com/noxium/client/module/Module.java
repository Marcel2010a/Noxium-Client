package com.noxium.client.module;

import com.noxium.client.module.setting.Setting;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();

    private final String name;
    private final String description;
    private final ModuleCategory category;
    private boolean enabled;
    private int key;

    // NEU: Settings-Liste in der Basis-Klasse
    private final List<Setting<?>> settings = new ArrayList<>();

    public Module(String name, String description, ModuleCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.key = GLFW.GLFW_KEY_UNKNOWN;
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public void onRender() {}

    public String getName() { return name; }
    public String getDescription() { return description; }
    public ModuleCategory getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }

    // SETTINGS MANAGEMENT
    public void addSetting(Setting<?> setting) {
        settings.add(setting);
    }

    // jetzt können alle Module @Override verwenden
    public List<Setting<?>> getSettings() {
        return settings;
    }
}