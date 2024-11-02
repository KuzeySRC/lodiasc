package com.moonclient.pro.mixin.client;

import com.moonclient.pro.Command.CommandManager;
import java.io.IOException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_634;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_634.class})
public class ClientPlayNetworkHandlerMixin {
    @Inject(method={"sendChatMessage"}, at={@At(value="HEAD")}, cancellable=true)
    private void handleMessage(String content, CallbackInfo ci) {
        if (content.startsWith("#") || content.startsWith("-") || content.startsWith(".") || content.startsWith("+") || content.startsWith("!")) {
            try {
                CommandManager.execute(content);
            }
            catch (IOException var4) {
                throw new RuntimeException(var4);
            }
            ci.cancel();
        }
    }
}
 