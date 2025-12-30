package com.noxium.client.module.modules.movement;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class Sprint extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private BooleanSetting requireForward;

    public Sprint() {
        super("Sprint", "Auto sprint", ModuleCategory.MOVEMENT);
        initSettings();
    }

    private void initSettings() {
        this.requireForward = new BooleanSetting("Require Forward", true);
        addSetting(requireForward);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }

    @Override
    public void onTick() {
        if (mc.player != null && mc.player.forwardSpeed > 0) {
            mc.player.setSprinting(true);
        }
    }
}