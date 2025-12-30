package com.noxium.client.gui;

import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import com.noxium.client.module.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.*;

public class NoxiumClickGUI extends Screen {

    private static final int WINDOW_WIDTH = 120;
    private static final int HEADER_HEIGHT = 18;
    private static final int ITEM_HEIGHT = 14;

    // Farben
    private static final int BG_WINDOW = 0x22000000; // komplett transparent
    private static final int BG_HEADER = 0x8822FF22; // hellgrün
    private static final int DROPDOWN_BG = 0x5522FF22; // hellgrün, transparent

    private static final Map<ModuleCategory, WindowPos> SAVED_POS = new HashMap<>();

    private WindowPos dragging = null;
    private int dragX, dragY;

    private Module openSettings = null;

    public NoxiumClickGUI() {
        super(Text.literal("Noxium ClickGUI"));
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        int screenW = width;
        int screenH = height;
        int startX = 10;

        for (ModuleCategory cat : ModuleCategory.values()) {
            WindowPos pos = SAVED_POS.get(cat);
            if (pos == null) {
                pos = new WindowPos(startX, 20);
                SAVED_POS.put(cat, pos);
                startX += WINDOW_WIDTH + 6;
            }

            clamp(pos, screenW, screenH);

            int x = pos.x;
            int y = pos.y;

            List<Module> modules = getModules(cat);

            // Fenster Hintergrund
            drawRect(ctx, x, y, x + WINDOW_WIDTH, y + HEADER_HEIGHT + modules.size() * ITEM_HEIGHT, BG_WINDOW);

            // Header
            drawRect(ctx, x, y, x + WINDOW_WIDTH, y + HEADER_HEIGHT, BG_HEADER);
            ctx.drawText(textRenderer, cat.name(), x + 6, y + 5, 0xFFFFFFFF, false); // weiß

            // Module Items
            int my = y + HEADER_HEIGHT;
            for (Module m : modules) {
                ctx.drawText(textRenderer, m.getName(), x + 6, my + 1, 0xFFFFFFFF, false);

                // Statuspunkt rechts
                int circleX = x + WINDOW_WIDTH - 10;
                int circleY = my + 4;
                int circleColor = m.isEnabled() ? 0xFF00FF00 : 0xFF555555; // grün wenn an, grau wenn aus
                drawCircle(ctx, circleX, circleY, 4, circleColor);

                // Dropdown
                if (openSettings == m && !m.getSettings().isEmpty()) {
                    drawDropdownPanel(ctx, m, x, my + ITEM_HEIGHT, mouseX, mouseY, m.getSettings());
                    my += m.getSettings().size() * 20;
                }

                my += ITEM_HEIGHT;
            }
        }
    }

    private void drawDropdownPanel(DrawContext ctx, Module module, int x, int y, int mouseX, int mouseY, List<Setting<?>> settings) {
        int dropdownWidth = WINDOW_WIDTH;
        int itemHeight = 20;
        int panelHeight = itemHeight * settings.size();

        drawRect(ctx, x, y, x + dropdownWidth, y + panelHeight, DROPDOWN_BG);

        int itemY = y;
        for (Setting<?> s : settings) {
            ctx.drawText(textRenderer, s.getName(), x + 6, itemY + 4, 0xFFFFFFFF, false);
            itemY += itemHeight;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (ModuleCategory cat : ModuleCategory.values()) {
            WindowPos pos = SAVED_POS.get(cat);
            if (pos == null) continue;

            int y = pos.y + HEADER_HEIGHT;
            for (Module m : getModules(cat)) {
                int itemHeight = ITEM_HEIGHT;
                if (openSettings == m && !m.getSettings().isEmpty()) {
                    itemHeight += m.getSettings().size() * 20;
                }

                if (inside(mouseX, mouseY, pos.x, y, WINDOW_WIDTH, ITEM_HEIGHT)) {
                    if (button == 0) {
                        m.toggle();
                    } else if (button == 1) {
                        openSettings = (openSettings == m) ? null : m;
                    }
                    return true;
                }

                y += itemHeight;
            }

            // Drag Header
            if (inside(mouseX, mouseY, pos.x, pos.y, WINDOW_WIDTH, HEADER_HEIGHT)) {
                dragging = pos;
                dragX = (int) mouseX - pos.x;
                dragY = (int) mouseY - pos.y;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        dragging = null;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dx, double dy) {
        if (dragging != null) {
            dragging.x = (int) mouseX - dragX;
            dragging.y = (int) mouseY - dragY;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dx, dy);
    }

    @Override
    public boolean shouldPause() { return false; }

    @Override
    public boolean shouldCloseOnEsc() { return true; }

    private static void drawRect(DrawContext ctx, int x1, int y1, int x2, int y2, int col) {
        ctx.fill(x1, y1, x2, y2, col);
    }

    private static void drawCircle(DrawContext ctx, int cx, int cy, int radius, int col) {
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                if (x*x + y*y <= radius*radius) {
                    ctx.fill(cx + x, cy + y, cx + x + 1, cy + y + 1, col);
                }
            }
        }
    }

    private static boolean inside(double mx, double my, int x, int y, int w, int h) {
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }

    private static void clamp(WindowPos pos, int w, int h) {
        pos.x = Math.max(2, Math.min(pos.x, w - WINDOW_WIDTH - 2));
        pos.y = Math.max(2, Math.min(pos.y, h - 40));
    }

    private static List<Module> getModules(ModuleCategory cat) {
        return com.noxium.client.NoxiumClient.getInstance().getModuleManager().getModulesByCategory(cat);
    }

    private static class WindowPos {
        int x, y;
        WindowPos(int x, int y) { this.x = x; this.y = y; }
    }
}
