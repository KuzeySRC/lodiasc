package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import java.io.IOException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.GameType;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

@Environment(value=EnvType.CLIENT)
public class GmcCommand
implements Command {
    @Override
    public void execute(String[] args) throws IOException {
        if (Minecraft.getInstance().gameMode.getPlayerMode() == GameType.CREATIVE) {
            Minecraft.getInstance().gameMode.setLocalMode(GameType.SURVIVAL);
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§9[LodiasClient+]§b Set Gamemode to Survival"));
        } else if (Minecraft.getInstance().gameMode.getPlayerMode() == GameType.ADVENTURE || Minecraft.getInstance().gameMode.getPlayerMode() == GameType.SPECTATOR) {
            Minecraft.getInstance().gameMode.setLocalMode(GameType.SURVIVAL);
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§9[LodiasClient+]§b Set Gamemode to Survival"));
        } else {
            Minecraft.getInstance().gameMode.setLocalMode(GameType.CREATIVE);
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§9[LodiasClient+]§b Set Gamemode to Creative"));
        }
    }

    @Override
    public String getUsage() {
        return ".gm";
    }
}
 