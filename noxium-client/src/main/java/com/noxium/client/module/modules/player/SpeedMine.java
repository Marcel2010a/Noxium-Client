package com.noxium.client.module.modules.player;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class SpeedMine extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting mineSpeed;
    private BooleanSetting veinMiner;

    public SpeedMine() {
        super("SpeedMine", "Mine faster", ModuleCategory.PLAYER);
        initSettings();
    }

    private void initSettings() {
        this.mineSpeed = new NumberSetting("Speed", 1.5f, 1.0f, 3.0f, 0.1f);
        this.veinMiner = new BooleanSetting("Vein Miner", false);
        addSetting(mineSpeed);
        addSetting(veinMiner);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}