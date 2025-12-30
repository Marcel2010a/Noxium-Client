package com.noxium.client.module.modules.combat;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class Criticals extends Module {

    private List<Setting<?>> settings = new ArrayList<>();

    private BooleanSetting onGround;
    private BooleanSetting jumpMode;
    private NumberSetting critChance;

    public Criticals() {
        super("Criticals", "Modifies your hits to always be critical", ModuleCategory.COMBAT);
        initSettings();
    }

    private void initSettings() {
        this.onGround = new BooleanSetting("On Ground", false);
        this.jumpMode = new BooleanSetting("Jump Mode", true);
        this.critChance = new NumberSetting("Crit Chance", 8.0f, 1.0f, 10.0f, 0.5f);

        addSetting(onGround);
        addSetting(jumpMode);
        addSetting(critChance);
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

        Vec3d velocity = mc.player.getVelocity();

        // Jump Mode für Critical Hits
        if (jumpMode.isEnabled() && !mc.player.isOnGround()) {
            mc.player.setVelocity(new Vec3d(velocity.x, velocity.y + 0.1, velocity.z));
        }

        // On Ground Mode
        if (onGround.isEnabled() && mc.player.isOnGround()) {
            mc.player.setVelocity(new Vec3d(velocity.x, 0.1, velocity.z));
        }
    }

    public boolean isJumpMode() {
        return jumpMode.isEnabled();
    }

    public boolean isOnGroundMode() {
        return onGround.isEnabled();
    }

    public float getCritChance() {
        return critChance.getValue();
    }
}
