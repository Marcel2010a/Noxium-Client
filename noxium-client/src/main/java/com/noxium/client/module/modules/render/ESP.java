package com.noxium.client.module.modules.render;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class ESP extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private BooleanSetting drawBox;
    private BooleanSetting drawTeam;
    private NumberSetting boxWidth;

    public ESP() {
        super("ESP", "Highlights entities", ModuleCategory.RENDER);
        initSettings();
    }

    private void initSettings() {
        this.drawBox = new BooleanSetting("Draw Box", true);
        this.drawTeam = new BooleanSetting("Team Color", true);
        this.boxWidth = new NumberSetting("Line Width", 1.0f, 0.5f, 3.0f, 0.5f);
        addSetting(drawBox);
        addSetting(drawTeam);
        addSetting(boxWidth);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}