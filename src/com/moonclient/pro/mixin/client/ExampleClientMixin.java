package com.moonclient.pro.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_310.class})
public class ExampleClientMixin {
    @Inject(at={@At(value="HEAD")}, method={"run"})
    private void init(CallbackInfo info) {
    }
}
 