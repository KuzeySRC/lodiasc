package com.moonclient.pro.Screens;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

@Environment(value=EnvType.CLIENT)
public class BlockedScreen
extends Screen {
    private final Component messageLine1;
    private final Component messageLine2;
    private final Component title = Component.nullToEmpty("LodiasClient");
    private final Component supportMessage;
    private final Component support2;

    public BlockedScreen() {
        super(Component.nullToEmpty("LodiasClient"));
        this.messageLine1 = Component.nullToEmpty("If you see this screen, you're either blocked");
        this.messageLine2 = Component.nullToEmpty("or haven't bought a license.");
        this.supportMessage = Component.nullToEmpty("If you believe this is an error");
        this.support2 = Component.nullToEmpty(" please contact us in the customer chat of the LodiasClient Discord. :D");
    }

    protected void init() {
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        context.drawCenteredString(this.field_22793, this.title, this.field_22789 / 2, 30, 0xFFCC00);
        context.drawCenteredString(this.field_22793, this.messageLine1, this.field_22789 / 2, this.field_22790 / 2 - 10, 0xFF0000);
        context.drawCenteredString(this.field_22793, this.messageLine2, this.field_22789 / 2, this.field_22790 / 2 + 10, 0xFF0000);
        context.drawCenteredString(this.field_22793, this.supportMessage, this.field_22789 / 2, this.field_22790 / 2 + 40, 0xFFFFFF);
        context.drawCenteredString(this.field_22793, this.support2, this.field_22789 / 2, this.field_22790 / 2 + 60, 0xFFFFFF);
    }

    protected void renderBackground(GuiGraphics context) {
        context.fillGradient(0, 0, this.field_22789, this.field_22790, -16777165, -16777216);
    }
}
 