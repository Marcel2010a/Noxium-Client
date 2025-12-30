package com.noxium.client.module.modules.misc;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class AutoRespawn extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private BooleanSetting instantRespawn;

    public AutoRespawn() {
        super("AutoRespawn", "Auto respawn", ModuleCategory.MISC);
        initSettings();
    }

    private void initSettings() {
        this.instantRespawn = new BooleanSetting("Instant", true);
        addSetting(instantRespawn);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }

    @Override
    public void onTick() {
        if (mc.player != null && mc.player.isDead()) {
            mc.player.requestRespawn();
        }
    }
}