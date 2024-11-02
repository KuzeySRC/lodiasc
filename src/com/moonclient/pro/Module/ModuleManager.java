package com.moonclient.pro.Module;

import com.moonclient.pro.Module.Module;
import com.moonclient.pro.Module.impl.InvWalk;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value=EnvType.CLIENT)
public class ModuleManager {
    private List<Module> modules = new ArrayList<Module>();
    private boolean running = false;
    private long updateInterval;
    private Thread updateThread;

    public ModuleManager(long updateInterval) {
        this.updateInterval = updateInterval;
    }

    public void registerModule(Module module) {
        this.modules.add(module);
    }

    public void enableAll() {
        for (Module module : this.modules) {
            if (module.isEnabled()) continue;
            module.toggle();
        }
        this.startUpdating();
    }

    public void disableAll() {
        this.stopUpdating();
        for (Module module : this.modules) {
            if (!module.isEnabled()) continue;
            module.toggle();
        }
    }

    private void startUpdating() {
        if (this.running) {
            return;
        }
        this.running = true;
        this.updateThread = new Thread(() -> {
            while (this.running) {
                for (Module module : this.modules) {
                    if (!module.isEnabled()) continue;
                    module.onUpdate();
                }
                try {
                    Thread.sleep(this.updateInterval);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        this.updateThread.start();
    }

    private void stopUpdating() {
        this.running = false;
        if (this.updateThread != null) {
            this.updateThread.interrupt();
        }
    }

    private void updateModules() {
        for (Module module : this.modules) {
            if (!module.isEnabled()) continue;
            module.onUpdate();
        }
    }

    public void registerModules() {
        this.registerModule(new InvWalk("InvWalk"));
    }

    public Module getModuleByName(String name) {
        for (Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(name)) continue;
            return module;
        }
        return null;
    }
}
 