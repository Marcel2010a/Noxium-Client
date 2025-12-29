package com.noxium.client.utils;

import com.noxium.client.NoxiumClient;
import com.noxium.client.module.Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;

public class ConfigManager {
    private final File configDir;
    private final File configFile;
    private final Gson gson;

    public ConfigManager() {
        this.configDir = new File("noxium");
        this.configFile = new File(configDir, "config.json");
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
    }

    public void saveConfig() {
        try (FileWriter writer = new FileWriter(configFile)) {
            JsonObject config = new JsonObject();
            JsonObject modulesJson = new JsonObject();
            for (Module module : NoxiumClient.getInstance().getModuleManager().getModules()) {
                JsonObject moduleJson = new JsonObject();
                moduleJson.addProperty("enabled", module.isEnabled());
                moduleJson.addProperty("key", module.getKey());
                modulesJson.add(module.getName(), moduleJson);
            }
            config.add("modules", modulesJson);
            gson.toJson(config, writer);
            NoxiumClient.LOGGER.info("Config saved successfully!");
        } catch (IOException e) {
            NoxiumClient.LOGGER.error("Failed to save config", e);
        }
    }

    public void loadConfig() {
        if (!configFile.exists()) {
            NoxiumClient.LOGGER.info("No config file found, using defaults");
            return;
        }
        try (FileReader reader = new FileReader(configFile)) {
            JsonObject config = JsonParser.parseReader(reader).getAsJsonObject();
            JsonObject modulesJson = config.getAsJsonObject("modules");
            if (modulesJson != null) {
                for (Module module : NoxiumClient.getInstance().getModuleManager().getModules()) {
                    if (modulesJson.has(module.getName())) {
                        JsonObject moduleJson = modulesJson.getAsJsonObject(module.getName());
                        if (moduleJson.has("enabled")) {
                            module.setEnabled(moduleJson.get("enabled").getAsBoolean());
                        }
                        if (moduleJson.has("key")) {
                            module.setKey(moduleJson.get("key").getAsInt());
                        }
                    }
                }
            }
            NoxiumClient.LOGGER.info("Config loaded successfully!");
        } catch (Exception e) {
            NoxiumClient.LOGGER.error("Failed to load config", e);
        }
    }
}