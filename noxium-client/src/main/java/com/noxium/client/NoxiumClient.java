package com.noxium.client;

import com.noxium.client.gui.NoxiumClickGUI;
import com.noxium.client.module.ModuleManager;
import com.noxium.client.utils.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoxiumClient implements ClientModInitializer {
    public static final String MOD_ID = "noxium";
    public static final String MOD_NAME = "Noxium";
    public static final String VERSION = "1.0.0";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    private static NoxiumClient INSTANCE;
    private ModuleManager moduleManager;
    private NoxiumClickGUI clickGUI;
    private ConfigManager configManager;
    
    private KeyBinding guiKeybind;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        LOGGER.info("Initializing {} v{}", MOD_NAME, VERSION);

        this.moduleManager = new ModuleManager();
        this.configManager = new ConfigManager();
        this.configManager.loadConfig();
        
        this.guiKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.noxium.gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.noxium"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (this.guiKeybind.wasPressed()) {
                if (client.player != null) {
                    if (this.clickGUI == null) {
                        this.clickGUI = new NoxiumClickGUI();
                    }
                    client.setScreen(this.clickGUI);
                }
            }
            
            if (client.player != null) {
                this.moduleManager.onTick();
            }
        });

        LOGGER.info("{} initialized successfully! Press RIGHT_SHIFT to open GUI", MOD_NAME);
    }

    public static NoxiumClient getInstance() {
        return INSTANCE;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}