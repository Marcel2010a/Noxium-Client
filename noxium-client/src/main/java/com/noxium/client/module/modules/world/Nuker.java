package com.noxium.client.module.modules.world;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class Nuker extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting range;
    private BooleanSetting dropItems;

    public Nuker() {
        super("Nuker", "Auto mine blocks", ModuleCategory.WORLD);
        initSettings();
    }

    private void initSettings() {
        this.range = new NumberSetting("Range", 4.0f, 1.0f, 8.0f, 0.5f);
        this.dropItems = new BooleanSetting("Drop Items", false);
        addSetting(range);
        addSetting(dropItems);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}