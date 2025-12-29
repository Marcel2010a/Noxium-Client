package com.noxium.client.module;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public abstract class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    
    private final String name;
    private final String description;
    private final ModuleCategory category;
    private boolean enabled;
    private int key;

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

    public String getName() { 
        return name; 
    }
    
    public String getDescription() { 
        return description; 
    }
    
    public ModuleCategory getCategory() { 
        return category; 
    }
    
    public boolean isEnabled() { 
        return enabled; 
    }
    
    public int getKey() { 
        return key; 
    }
    
    public void setKey(int key) { 
        this.key = key; 
    }
}