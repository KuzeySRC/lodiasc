package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import com.moonclient.pro.Module.Module;
import com.moonclient.pro.Module.ModuleManager;
import java.io.IOException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

@Environment(value=EnvType.CLIENT)
public class Toggle
implements Command {
    private final ModuleManager moduleManager;

    public Toggle(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: .toggle <module_name>");
            return;
        }
        String moduleName = args[0];
        Module module = this.moduleManager.getModuleByName(moduleName);
        if (module == null) {
            System.out.println("Module not found: " + moduleName);
            return;
        }
        module.toggle();
        String status = module.isEnabled() ? "enabled" : "disabled";
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Module " + moduleName + " has been " + status + "."));
    }

    @Override
    public String getUsage() {
        return ".toggle <module_name> - Toggles the specified module on or off.";
    }
}
 