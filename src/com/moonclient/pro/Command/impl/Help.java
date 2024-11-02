package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import java.io.IOException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

@Environment(value=EnvType.CLIENT)
public class Help
implements Command {
    @Override
    public void execute(String[] args) throws IOException {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§6§l--------- Help Menu ---------"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§c#viaspamm §7<packets> <delay> - Spam packets"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§e#vialag §7<packets> <delay> - Lag server with packets"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§a#lpx §7<packets> <delay> - LPX exploit"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§b#lpx2 §7<packets> <delay> - Second LPX exploit"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§9#netty §7<packets> <delay> - Netty crash exploit"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§5#efcrash §7<packets> - ExploitFixer crash"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§d#sendmsgall §7<message> - Send message to all players"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§6#gm §7- Switch to creative mode"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§8#lockchat §7- Locks the chat"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§3#clickslot §7- Click slot in inventory"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§2#chatspamm §7- Spam chat messages"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§4#test §7- Run a test command"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§4#fly1 §7 Positon Crasher Or Smht :D"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§4#Made by 15w30x"));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§6§l----------------------------"));
    }

    @Override
    public String getUsage() {
        return "#help - Displays this help menu";
    }
}
 