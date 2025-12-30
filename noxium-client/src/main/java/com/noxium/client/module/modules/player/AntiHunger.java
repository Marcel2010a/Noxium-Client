package com.noxium.client.module.modules.player;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class AntiHunger extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting hungerReduction;

    public AntiHunger() {
        super("AntiHunger", "Reduces hunger", ModuleCategory.PLAYER);
        initSettings();
    }

    private void initSettings() {
        this.hungerReduction = new NumberSetting("Reduction", 0.5f, 0.1f, 1.0f, 0.1f);
        addSetting(hungerReduction);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}