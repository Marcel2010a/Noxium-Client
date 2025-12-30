package com.noxium.client.module.modules.misc;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.Setting;
import java.util.ArrayList;
import java.util.List;

public class MiddleClick extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    private BooleanSetting friendFriends;
    private BooleanSetting toggleModule;

    public MiddleClick() {
        super("MiddleClick", "Middle click actions", ModuleCategory.MISC);
        initSettings();
    }

    private void initSettings() {
        this.friendFriends = new BooleanSetting("Friend Players", true);
        this.toggleModule = new BooleanSetting("Toggle Module", false);
        addSetting(friendFriends);
        addSetting(toggleModule);
    }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    @Override
    public List<Setting<?>> getSettings() { return settings; }
}