package com.noxium.client.module.modules.movement;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class Jesus extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private BooleanSetting onLiquid;
    private NumberSetting speed;

    public Jesus() {
        super("Jesus", "Walk on water", ModuleCategory.MOVEMENT);
        initSettings();
    }

    private void initSettings() {
        this.onLiquid = new BooleanSetting("On Liquid", true);
        this.speed = new NumberSetting("Speed", 1.0f, 0.1f, 3.0f, 0.1f);
        addSetting(onLiquid);
        addSetting(speed);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}