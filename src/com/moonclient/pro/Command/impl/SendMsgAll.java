package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import com.moonclient.pro.Exploit.Plugin.SignedVelocity;
import java.io.IOException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

@Environment(value=EnvType.CLIENT)
public class SendMsgAll
implements Command {
    @Override
    public void execute(String[] args) throws IOException {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§a[LodiasClient] §rSending Msg Results"));
        if (args.length < 1) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§a[LodiasClient] §r#add [Messeage]"));
        }
        for (int i = 0; i < 500; ++i) {
            SignedVelocity.sendMessageToAll(args[1]);
        }
    }

    @Override
    public String getUsage() {
        return null;
    }
}
 