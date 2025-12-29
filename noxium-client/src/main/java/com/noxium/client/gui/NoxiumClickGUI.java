package com.noxium.client.gui;

import com.noxium.client.NoxiumClient;
import com.noxium.client.module.Module;
import com.noxium.client.module.ModuleCategory;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.util.List;

public class NoxiumClickGUI extends Screen {
    private ModuleCategory selectedCategory = ModuleCategory.COMBAT;
    private float scrollOffset = 0;
    private float targetScrollOffset = 0;
    private int mouseX = 0;
    private int mouseY = 0;

    public NoxiumClickGUI() {
        super(Text.literal("Noxium"));
    }

    @Override
    public boolean shouldPause() {
        return true;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        smoothScroll();
        
        // Dark overlay
        context.fill(0, 0, this.width, this.height, 0xAA000000);
        
        // Simple two-column layout
        int leftX = 20;
        int rightX = 250;
        int y = 20;
        
        // Title
        context.drawText(this.textRenderer, "NOXIUM", leftX, y, 0xFF00D9FF, false);
        y += 25;
        
        // Categories
        for (ModuleCategory cat : ModuleCategory.values()) {
            boolean selected = cat == selectedCategory;
            String text = (selected ? "▶ " : "  ") + cat.name();
            int color = selected ? 0xFF00D9FF : 0xFFFFFFFF;
            
            if (isHovered(leftX, y, 150, 16, mouseX, mouseY)) {
                color = 0xFF00FFA3;
            }
            
            context.drawText(this.textRenderer, text, leftX, y, color, false);
            y += 18;
        }
        
        // Right side - modules
        y = 20;
        context.drawText(this.textRenderer, selectedCategory.name(), rightX, y, 0xFF00D9FF, false);
        y += 25;
        
        List<Module> modules = NoxiumClient.getInstance().getModuleManager().getModulesByCategory(selectedCategory);
        
        for (Module mod : modules) {
            int moduleY = y + (int)scrollOffset;
            
            if (moduleY > 40 && moduleY < this.height - 20) {
                String name = mod.getName();
                String status = mod.isEnabled() ? "[ON]" : "[OFF]";
                int statusColor = mod.isEnabled() ? 0xFF00FFA3 : 0xFFFF0000;
                
                context.drawText(this.textRenderer, name, rightX, moduleY, 0xFFFFFFFF, false);
                context.drawText(this.textRenderer, status, this.width - 80, moduleY, statusColor, false);
            }
            
            y += 18;
        }
        
        super.render(context, mouseX, mouseY, delta);
    }

    private void smoothScroll() {
        float diff = targetScrollOffset - scrollOffset;
        scrollOffset += diff * 0.3f;
    }

    private boolean isHovered(int x, int y, int w, int h, int mX, int mY) {
        return mX > x && mX < x + w && mY > y && mY < y + h;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int y = 45;
        int leftX = 20;
        
        for (ModuleCategory cat : ModuleCategory.values()) {
            if (isHovered(leftX, y, 150, 16, (int)mouseX, (int)mouseY)) {
                selectedCategory = cat;
                scrollOffset = 0;
                targetScrollOffset = 0;
                return true;
            }
            y += 18;
        }
        
        int moduleY = 45;
        int rightX = 250;
        List<Module> modules = NoxiumClient.getInstance().getModuleManager().getModulesByCategory(selectedCategory);
        
        for (Module mod : modules) {
            int actualY = moduleY + (int)scrollOffset;
            if (isHovered(rightX, actualY, this.width - rightX - 20, 16, (int)mouseX, (int)mouseY)) {
                mod.toggle();
                return true;
            }
            moduleY += 18;
        }
        
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        targetScrollOffset -= (float)verticalAmount * 15;
        List<Module> modules = NoxiumClient.getInstance().getModuleManager().getModulesByCategory(selectedCategory);
        int maxScroll = Math.max(0, modules.size() * 18 - 400);
        if (targetScrollOffset > 0) targetScrollOffset = 0;
        if (targetScrollOffset < -maxScroll) targetScrollOffset = -maxScroll;
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.client.setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}