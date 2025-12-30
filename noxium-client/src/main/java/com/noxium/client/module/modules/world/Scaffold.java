package com.noxium.client.module.modules.world;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class Scaffold extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting range;
    private BooleanSetting autoRotate;
    private BooleanSetting movementMode;

    public Scaffold() {
        super("Scaffold", "Auto place blocks", ModuleCategory.WORLD);
        initSettings();
    }

    private void initSettings() {
        this.range = new NumberSetting("Range", 5.0f, 1.0f, 8.0f, 0.5f);
        this.autoRotate = new BooleanSetting("Auto Rotate", true);
        this.movementMode = new BooleanSetting("Walk Mode", true);
        addSetting(range);
        addSetting(autoRotate);
        addSetting(movementMode);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}