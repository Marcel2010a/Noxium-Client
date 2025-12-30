package com.noxium.client.module.modules.player;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class FastUse extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting speed;

    public FastUse() {
        super("FastUse", "Use items faster", ModuleCategory.PLAYER);
        initSettings();
    }

    private void initSettings() {
        this.speed = new NumberSetting("Speed", 5.0f, 1.0f, 10.0f, 0.5f);
        addSetting(speed);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}