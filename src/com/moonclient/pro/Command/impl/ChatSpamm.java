package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import com.moonclient.pro.Exploit.Plugin.SignedVelocity;
import java.io.IOException;
import java.util.Random;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

@Environment(value=EnvType.CLIENT)
public class ChatSpamm
implements Command {
    @Override
    public void execute(String[] args) throws IOException {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§a[LodiasClient] §rFrom now on every player typing in the chat will send random messages."));
        Random random = new Random();
        String[] messages = new String[]{"§6§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa§r Buy Lodias §6§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa ", "§a§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa§r Fucked by LodiasClient §a§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa ", "§4§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa§r Exploited by LodiasClient §4§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa ", "§6§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa§r Buy LodiasClient §6§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa ", "§a§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa§r Buy LodiasClient §a§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa ", "§4§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa§r Fucked by LodiasClient §4§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa ", "§6§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa§r Chat Nuked by LodiasClient §6§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa ", "§a§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa§r Buy LodiasClient §a§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa ", "§4§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa§r Buy LodiasClient §4§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa ", "§7§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa§r Buy LodiasClient §7§l§kaaaaaaaaaaaaaaaaaaaaaaaaaaaa "};
        for (int i = 0; i < 500; ++i) {
            String randomMsg = messages[random.nextInt(messages.length)];
            SignedVelocity.sendMessageToAll(randomMsg);
        }
    }

    @Override
    public String getUsage() {
        return null;
    }
}
 