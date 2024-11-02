package com.moonclient.pro.Screens;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

@Environment(value=EnvType.CLIENT)
public class Altmanager
extends Screen {
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 20;

    public Altmanager(Component title) {
        super(title);
    }

    protected void method_25426() {
        int centerX = this.field_22789 / 2 - 75;
        int centerY = this.field_22790 / 2 - 10;
        this.addRenderableWidget(Button.builder(Component.nullToEmpty("Offline Login"), button -> this.offlineLogin()).bounds(centerX, centerY - 25, 150, 20).build());
        this.addRenderableWidget(Button.builder(Component.nullToEmpty("Session ID Login"), button -> this.sessionIdLogin()).bounds(centerX, centerY + 25, 150, 20).build());
    }

    private void offlineLogin() {
        System.out.println("Offline Login Clicked");
    }

    private void sessionIdLogin() {
        System.out.println("Session ID Login Clicked");
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredString(this.field_22793, this.field_22785, this.field_22789 / 2, 20, 0xFFFFFF);
    }
}
 