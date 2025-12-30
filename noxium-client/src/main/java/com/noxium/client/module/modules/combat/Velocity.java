package com.noxium.client.module.modules.combat;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;

import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class Velocity extends Module {
    
    private List<Setting<?>> settings = new ArrayList<>();
    
    // Settings
    private NumberSetting horizontalVelocity;
    private NumberSetting verticalVelocity;
    private BooleanSetting strictMode;

    public Velocity() {
        super("Velocity", "Modifies knockback you take", ModuleCategory.COMBAT);
        setKey(GLFW.GLFW_KEY_V);
        initSettings();
    }

    private void initSettings() {
        this.horizontalVelocity = new NumberSetting("Horizontal", 0.5f, 0.0f, 1.0f, 0.05f);
        this.verticalVelocity = new NumberSetting("Vertical", 0.5f, 0.0f, 1.0f, 0.05f);
        this.strictMode = new BooleanSetting("Strict Mode", false);

        addSetting(horizontalVelocity);
        addSetting(verticalVelocity);
        addSetting(strictMode);
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

        // Hier kommt deine Velocity Logik
        // Nutze die Settings:
        float horz = horizontalVelocity.getValue();
        float vert = verticalVelocity.getValue();
        boolean strict = strictMode.isEnabled();

        // Beispiel: Knockback reduzieren basierend auf Settings
        if (mc.player.getVelocity().lengthSquared() > 0) {
            mc.player.getVelocity().multiply(horz, vert, horz);
        }
    }

    public float getHorizontalVelocity() {
        return horizontalVelocity.getValue();
    }

    public float getVerticalVelocity() {
        return verticalVelocity.getValue();
    }

    public boolean isStrictMode() {
        return strictMode.isEnabled();
    }
}