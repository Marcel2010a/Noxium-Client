package com.noxium.client.module.modules.misc;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.NumberSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class AutoReconnect extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private NumberSetting reconnectDelay;
    private BooleanSetting autoJoin;

    public AutoReconnect() {
        super("AutoReconnect", "Auto reconnect", ModuleCategory.MISC);
        initSettings();
    }

    private void initSettings() {
        this.reconnectDelay = new NumberSetting("Delay", 5.0f, 1.0f, 30.0f, 0.5f);
        this.autoJoin = new BooleanSetting("Auto Join", true);
        addSetting(reconnectDelay);
        addSetting(autoJoin);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}