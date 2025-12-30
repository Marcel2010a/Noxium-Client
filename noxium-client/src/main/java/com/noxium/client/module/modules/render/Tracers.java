package com.noxium.client.module.modules.render;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class Tracers extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private BooleanSetting drawTeam;
    private NumberSetting lineWidth;

    public Tracers() {
        super("Tracers", "Lines to entities", ModuleCategory.RENDER);
        initSettings();
    }

    private void initSettings() {
        this.drawTeam = new BooleanSetting("Team Color", true);
        this.lineWidth = new NumberSetting("Width", 1.0f, 0.5f, 3.0f, 0.5f);
        addSetting(drawTeam);
        addSetting(lineWidth);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}