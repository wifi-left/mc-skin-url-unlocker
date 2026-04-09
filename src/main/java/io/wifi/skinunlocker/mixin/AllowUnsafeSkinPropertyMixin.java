package io.wifi.skinunlocker.mixin;

import com.mojang.authlib.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 客户端 Mixin：绕过外置登录皮肤签名验证
 * 仅用于 authlib 环境，强制接受所有 textures 属性的签名
 */
@Mixin(Property.class)
public class AllowUnsafeSkinPropertyMixin {

    @Inject(method = "isSignatureValid", at = @At("HEAD"), cancellable = true, remap = false)
    private void forceSignatureValidForTextures(CallbackInfoReturnable<Boolean> cir) {
        Property self = (Property) (Object) this;
        if ("textures".equals(self.name())) {
            cir.setReturnValue(true);
        }
    }

}