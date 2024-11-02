package com.moonclient.pro.Module;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value=EnvType.CLIENT)
public abstract class Module {
    private String name;
    private boolean enabled;

    public Module(String name) {
        this.name = name;
        this.enabled = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void toggle() {
        if (this.enabled) {
            this.onDisable();
        } else {
            this.onEnable();
        }
        this.enabled = !this.enabled;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void onUpdate();
}
 