package com.moonclient.pro.Module.impl;

import com.moonclient.pro.Module.Module;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.world.item.CreativeModeTabs;

@Environment(value=EnvType.CLIENT)
public class InvWalk
extends Module {
    private final Minecraft mc = Minecraft.getInstance();
    private boolean allowOtherScreens = true;
    private boolean allowSneak = true;
    private boolean allowSprint = true;
    private boolean allowJump = true;

    public InvWalk(String name) {
        super("InvWalk");
    }

    @Override
    public void onEnable() {
        this.mc.player.sendSystemMessage(Component.literal("§a[Lodias] §rEnabled InvWalk"));
    }

    @Override
    public void onDisable() {
        this.mc.player.sendSystemMessage(Component.literal("§a[Lodias] §rDisabled InvWalk"));
    }

    @Override
    public void onUpdate() {
        Screen screen = this.mc.screen;
        if (screen == null || !this.isAllowedScreen(screen)) {
            return;
        }
        ArrayList<KeyMapping> keys = new ArrayList<KeyMapping>(Arrays.asList(this.mc.options.keyUp, this.mc.options.keyDown, this.mc.options.keyLeft, this.mc.options.keyRight));
        if (this.allowSneak) {
            keys.add(this.mc.options.keyShift);
        }
        if (this.allowSprint) {
            keys.add(this.mc.options.keySprint);
        }
        if (this.allowJump) {
            keys.add(this.mc.options.keyJump);
        }
        for (KeyMapping key : keys) {
            key.setDown(false);
        }
    }

    private boolean isAllowedScreen(Screen screen) {
        if (screen instanceof EffectRenderingInventoryScreen && !this.isCreativeSearchBarOpen(screen)) {
            return true;
        }
        return this.allowOtherScreens && screen instanceof AbstractContainerScreen && !this.hasTextBox(screen);
    }

    private boolean isCreativeSearchBarOpen(Screen screen) {
        if (!(screen instanceof CreativeModeInventoryScreen)) {
            return false;
        }
        try {
            Field selectedTabField = CreativeModeInventoryScreen.class.getDeclaredField("selectedTab");
            selectedTabField.setAccessible(true);
            Object selectedTab = selectedTabField.get(screen);
            return selectedTab.equals(CreativeModeTabs.searchTab());
        }
        catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean hasTextBox(Screen screen) {
        return screen.children().stream().anyMatch(child -> child instanceof EditBox);
    }
}
 