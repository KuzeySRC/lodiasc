package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

@Environment(value=EnvType.CLIENT)
public class LPX
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
                private long endTime = System.currentTimeMillis() + 10000L;
                private int packetsSent = 0;

                @Override
                public void run() {
                    if (System.currentTimeMillis() >= this.endTime || this.packetsSent >= totalPackets) {
                        scheduler.shutdown();
                        return;
                    }
                    LPX.this.sendPackets(handler);
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

    private void sendPackets(AbstractContainerMenu handler) {
        int syncId = handler.containerId;
        ItemStack cursorStack = handler.getCarried();
        ItemStack goldenCarrot = new ItemStack(Items.GOLDEN_CARROT, 1);
        Int2ObjectOpenHashMap slotMap = new Int2ObjectOpenHashMap();
        slotMap.put(0, goldenCarrot);
        System.out.println("Sending packet.");
        try {
            int revision = handler.incrementStateId();
            ServerboundContainerClickPacket packet1 = new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap);
            ServerboundContainerClickPacket packet2 = new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap);
            ServerboundContainerClickPacket packet3 = new ServerboundContainerClickPacket(syncId, revision, 22, 1, ClickType.SWAP, cursorStack, slotMap);
            ServerboundContainerClickPacket packet4 = new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.QUICK_CRAFT, cursorStack, slotMap);
            ServerboundContainerClickPacket packet5 = new ServerboundContainerClickPacket(syncId, revision, 22, 1, ClickType.PICKUP_ALL, cursorStack, slotMap);
            ServerboundContainerClickPacket packet6 = new ServerboundContainerClickPacket(syncId, revision, 18, 1, ClickType.QUICK_CRAFT, cursorStack, slotMap);
            ServerboundContainerClickPacket packet7 = new ServerboundContainerClickPacket(syncId, revision, 22, 0, ClickType.SWAP, cursorStack, slotMap);
            ServerboundContainerClickPacket packet8 = new ServerboundContainerClickPacket(syncId, revision, 22, -1, ClickType.SWAP, cursorStack, slotMap);
            ServerboundContainerClickPacket packet9 = new ServerboundContainerClickPacket(syncId, revision, 1023, 1023, ClickType.SWAP, cursorStack, slotMap);
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap));
            Minecraft.getInstance().getConnection().send(packet1);
            Minecraft.getInstance().getConnection().send(packet2);
            Minecraft.getInstance().getConnection().send(packet3);
            Minecraft.getInstance().getConnection().send(packet4);
            Minecraft.getInstance().getConnection().send(packet5);
            Minecraft.getInstance().getConnection().send(packet6);
            Minecraft.getInstance().getConnection().send(packet7);
            Minecraft.getInstance().getConnection().send(packet8);
            Minecraft.getInstance().getConnection().send(packet9);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUsage() {
        return "#lpx <packets> <delay>";
    }
}
 