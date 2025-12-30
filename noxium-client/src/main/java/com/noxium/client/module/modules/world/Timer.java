package com.noxium.client.module.modules.world;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class Timer extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting gameSpeed;

    public Timer() {
        super("Timer", "Change game speed", ModuleCategory.WORLD);
        initSettings();
    }

    private void initSettings() {
        this.gameSpeed = new NumberSetting("Speed", 1.0f, 0.1f, 5.0f, 0.1f);
        addSetting(gameSpeed);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}