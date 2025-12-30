package com.noxium.client.module.modules.movement;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class Step extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting stepHeight;

    public Step() {
        super("Step", "Step up blocks", ModuleCategory.MOVEMENT);
        initSettings();
    }

    private void initSettings() {
        this.stepHeight = new NumberSetting("Height", 1.0f, 0.5f, 2.5f, 0.5f);
        addSetting(stepHeight);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}