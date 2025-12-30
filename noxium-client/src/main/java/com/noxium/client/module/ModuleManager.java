package com.noxium.client.module;

import com.noxium.client.module.modules.combat.*;
import com.noxium.client.module.modules.movement.*;
import com.noxium.client.module.modules.player.*;
import com.noxium.client.module.modules.render.*;
import com.noxium.client.module.modules.world.*;
import com.noxium.client.module.modules.misc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addModule(new KillAura());
        addModule(new Criticals());
        addModule(new Velocity());
        addModule(new AutoTotem());
        addModule(new AutoArmor());
        addModule(new AutoCrystal());
        addModule(new Flight());
        addModule(new Speed());
        addModule(new Sprint());
        addModule(new NoFall());
        addModule(new Step());
        addModule(new Jesus());
        addModule(new Fullbright());
        addModule(new ESP());
        addModule(new Tracers());
        addModule(new Xray());
        addModule(new Nametags());
        addModule(new Freecam());
        addModule(new FastUse());
        addModule(new AntiHunger());
        addModule(new AutoEat());
        addModule(new SpeedMine());
        addModule(new Scaffold());
        addModule(new Nuker());
        addModule(new Timer());
        addModule(new AutoFarm());
        addModule(new AutoReconnect());
        addModule(new AutoRespawn());
        addModule(new DiscordRPC());
        addModule(new MiddleClick());
    }

    private void addModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getModulesByCategory(ModuleCategory category) {
        return modules.stream()
            .filter(module -> module.getCategory() == category)
            .collect(Collectors.toList());
    }

    public Module getModuleByName(String name) {
        return modules.stream()
            .filter(module -> module.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    public List<Module> getEnabledModules() {
        return modules.stream()
            .filter(Module::isEnabled)
            .collect(Collectors.toList());
    }

    public void onTick() {
        modules.stream()
            .filter(Module::isEnabled)
            .forEach(Module::onTick);
    }

    public void onRender() {
        modules.stream()
            .filter(Module::isEnabled)
            .forEach(Module::onRender);
    }

    public void onKeyPress(int key) {
        modules.stream()
            .filter(module -> module.getKey() == key)
            .forEach(Module::toggle);
    }
}