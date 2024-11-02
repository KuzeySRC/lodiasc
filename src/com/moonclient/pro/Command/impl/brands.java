package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import com.moonclient.pro.Exploit.Plugin.SignedVelocity;
import java.io.IOException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

@Environment(value=EnvType.CLIENT)
public class brands
implements Command {
    @Override
    public void execute(String[] args) throws IOException {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal(SignedVelocity.getBrands()));
    }

    @Override
    public String getUsage() {
        return null;
    }
}
 