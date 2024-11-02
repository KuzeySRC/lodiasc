package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
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
public class ClickSlotCommand
implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§b[§9mlodias§b] §cUsage: " + this.getUsage()), false);
        } else {
            try {
                int packets = Integer.parseInt(args[1]);
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
                int syncId = handler.containerId;
                ItemStack cursorStack = handler.getCarried();
                ItemStack goldenCarrot = new ItemStack(Items.GOLDEN_CARROT, 1);
                Int2ObjectOpenHashMap slotMap = new Int2ObjectOpenHashMap();
                slotMap.put(0, goldenCarrot);
                System.out.println("Sending " + packets + " packets.");
                for (int p = 0; p < packets; ++p) {
                    int revision = handler.incrementStateId();
                    ServerboundContainerClickPacket packet = new ServerboundContainerClickPacket(syncId, revision, 36, -1, ClickType.SWAP, cursorStack, slotMap);
                    new ServerboundContainerClickPacket(syncId, revision, 18, 0, ClickType.SWAP, cursorStack, slotMap);
                    Minecraft.getInstance().getConnection().send(packet);
                    System.out.println("Sent packet " + (p + 1));
                }
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("§b[§9lodias§b] §7Sent " + packets + " packets."), false);
            }
            catch (NumberFormatException var13) {
                cMinecraft.getInstance().player.sendSystemMessage(Component.literal("§b[§9lodias§b] §7Invalid number format"), false);
                System.out.println("Invalid number format: " + args[1]);
            }
        }
    }

    @Override
    public String getUsage() {
        return "!clickslot <packets>";
    }
}
 