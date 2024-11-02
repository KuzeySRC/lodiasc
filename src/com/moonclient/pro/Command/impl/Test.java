package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import com.moonclient.pro.Screens.Altmanager;
import java.io.IOException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

@Environment(value=EnvType.CLIENT)
public class Test
implements Command {
    @Override
    public void execute(String[] args) throws IOException {
        Minecraft.getInstance().setScreen(new Altmanager(Component.literal("a")));
    }

    @Override
    public String getUsage() {
        return null;
    }
}
 