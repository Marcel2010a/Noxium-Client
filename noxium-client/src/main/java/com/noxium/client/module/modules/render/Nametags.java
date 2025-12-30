package com.noxium.client.module.modules.render;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class Nametags extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private BooleanSetting showHealth;
    private NumberSetting scale;

    public Nametags() {
        super("Nametags", "Better nametags", ModuleCategory.RENDER);
        initSettings();
    }

    private void initSettings() {
        this.showHealth = new BooleanSetting("Show Health", true);
        this.scale = new NumberSetting("Scale", 1.0f, 0.5f, 2.0f, 0.1f);
        addSetting(showHealth);
        addSetting(scale);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}