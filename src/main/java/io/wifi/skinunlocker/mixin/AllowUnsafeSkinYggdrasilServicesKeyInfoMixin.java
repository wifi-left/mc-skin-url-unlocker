package io.wifi.skinunlocker.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.authlib.properties.Property;
import com.mojang.authlib.yggdrasil.YggdrasilServicesKeyInfo;

// ---------- 2. 修复新版验证入口 YggdrasilServicesKeyInfo ----------
@Mixin(value = YggdrasilServicesKeyInfo.class, remap = false)
public class AllowUnsafeSkinYggdrasilServicesKeyInfoMixin {

    /**
     * 拦截 validateProperty 方法，对 textures 属性直接返回 true
     */
    @Inject(method = "validateProperty", at = @At("HEAD"), cancellable = true)
    private void skipTextureValidation(Property property, CallbackInfoReturnable<Boolean> cir) {
        if ("textures".equals(property.name())) {
            cir.setReturnValue(true);
        }
    }
}