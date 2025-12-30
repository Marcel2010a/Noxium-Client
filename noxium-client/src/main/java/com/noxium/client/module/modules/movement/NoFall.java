package com.noxium.client.module.modules.movement;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class NoFall extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private BooleanSetting packetMode;

    public NoFall() {
        super("NoFall", "No fall damage", ModuleCategory.MOVEMENT);
        initSettings();
    }

    private void initSettings() {
        this.packetMode = new BooleanSetting("Packet Mode", true);
        addSetting(packetMode);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }

    @Override
    public void onTick() {
        if (mc.player != null) {
            mc.player.setOnGround(true);
        }
    }
}