package io.wifi.skinunlocker.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.authlib.yggdrasil.TextureUrlChecker;

@Mixin(value = TextureUrlChecker.class, remap = false)
public class AllowUrlMixin {
    @Inject(method = "isAllowedTextureDomain", at = @At("HEAD"), cancellable = true)
    public static void isAllowedTextureDomain(final String url,CallbackInfoReturnable<Boolean> cir) {
        cir.cancel();
        cir.setReturnValue(true);
    }
}
