package com.noxium.client.module.modules.misc;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.BooleanSetting;
import com.noxium.client.module.setting.StringSetting;
import com.noxium.client.module.setting.Setting;
import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.List;

public class DiscordRPC extends Module {
    private List<Setting<?>> settings = new ArrayList<>();
    
    private BooleanSetting showServer;
    private StringSetting customStatus;
    private BooleanSetting showPlayers;
    
    private static final String APPLICATION_ID = "1234567890123456789"; // Deine Discord App ID hier!
    private DiscordRPCHandler rpcHandler;

    public DiscordRPC() {
        super("DiscordRPC", "Discord presence", ModuleCategory.MISC);
        initSettings();
    }

    private void initSettings() {
        this.showServer = new BooleanSetting("Show Server", true);
        this.customStatus = new StringSetting("Status", "Playing Noxium");
        this.showPlayers = new BooleanSetting("Show Players", false);
        
        addSetting(showServer);
        addSetting(customStatus);
        addSetting(showPlayers);
    }

    public void addSetting(Setting<?> setting) {
        settings.add(setting);
    }

    @Override
    public List<Setting<?>> getSettings() {
        return settings;
    }

    @Override
    public void onEnable() {
        try {
            rpcHandler = new DiscordRPCHandler(APPLICATION_ID);
            rpcHandler.start();
        } catch (Exception e) {
            System.err.println("Failed to start Discord RPC: " + e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        if (rpcHandler != null) {
            rpcHandler.stop();
            rpcHandler = null;
        }
    }

    @Override
    public void onTick() {
        if (rpcHandler != null) {
            MinecraftClient mc = MinecraftClient.getInstance();
            
            String details = customStatus.getValue();
            String state = "In Game";
            String largeImageKey = "noxium_logo"; // Assets ID in Discord
            String largeImageText = "Noxium Client";
            String smallImageKey = null;
            String smallImageText = null;
            
            if (showServer.isEnabled() && mc.getCurrentServerEntry() != null) {
                state = "Playing on " + mc.getCurrentServerEntry().name;
            }
            
            if (showPlayers.isEnabled() && mc.player != null) {
                state += " - " + mc.player.getName().getString();
            }
            
            rpcHandler.updatePresence(details, state, largeImageKey, largeImageText, smallImageKey, smallImageText);
        }
    }

    // Inner Klasse für Discord RPC Handling
    public static class DiscordRPCHandler {
        private String applicationId;
        private long lastUpdate = 0;
        private final long UPDATE_INTERVAL = 15000; // 15 Sekunden

        public DiscordRPCHandler(String applicationId) {
            this.applicationId = applicationId;
        }

        public void start() {
            System.out.println("[Noxium] Discord RPC started with App ID: " + applicationId);
        }

        public void stop() {
            System.out.println("[Noxium] Discord RPC stopped");
        }

        public void updatePresence(String details, String state, String largeImageKey, String largeImageText, String smallImageKey, String smallImageText) {
            long currentTime = System.currentTimeMillis();
            
            // Update nur alle 15 Sekunden um API Rate Limit zu vermeiden
            if (currentTime - lastUpdate < UPDATE_INTERVAL) {
                return;
            }
            
            lastUpdate = currentTime;
            
            try {
                // Hier würde die Discord IPC Integration erfolgen
                // Für Fabric müssen externe Libraries wie discord-ipc verwendet werden
                // Das ist ein Placeholder für die Struktur
                
                String json = buildJSON(details, state, largeImageKey, largeImageText, smallImageKey, smallImageText);
                // Sende JSON an Discord IPC Socket
                
                System.out.println("[Noxium RPC] Updated: " + details);
            } catch (Exception e) {
                System.err.println("[Noxium RPC Error] " + e.getMessage());
            }
        }

        private String buildJSON(String details, String state, String largeImageKey, String largeImageText, String smallImageKey, String smallImageText) {
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"details\":\"").append(escapeJson(details)).append("\",");
            json.append("\"state\":\"").append(escapeJson(state)).append("\",");
            json.append("\"largeImageKey\":\"").append(largeImageKey).append("\",");
            json.append("\"largeImageText\":\"").append(escapeJson(largeImageText)).append("\",");
            json.append("\"timestamps\":{\"start\":").append(System.currentTimeMillis()).append("}");
            
            if (smallImageKey != null) {
                json.append(",\"smallImageKey\":\"").append(smallImageKey).append("\",");
                json.append("\"smallImageText\":\"").append(escapeJson(smallImageText)).append("\"");
            }
            
            json.append("}");
            return json.toString();
        }

        private String escapeJson(String text) {
            return text.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
        }
    }
}