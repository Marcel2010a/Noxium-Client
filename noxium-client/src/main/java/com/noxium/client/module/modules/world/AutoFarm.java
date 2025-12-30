package com.noxium.client.module.modules.world;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class AutoFarm extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting range;
    private BooleanSetting autoPlant;

    public AutoFarm() {
        super("AutoFarm", "Auto farms crops", ModuleCategory.WORLD);
        initSettings();
    }

    private void initSettings() {
        this.range = new NumberSetting("Range", 5.0f, 1.0f, 10.0f, 0.5f);
        this.autoPlant = new BooleanSetting("Auto Plant", true);
        addSetting(range);
        addSetting(autoPlant);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}