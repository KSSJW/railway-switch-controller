package com.kssjw.railwayswitchcontroller.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.railwayswitchcontroller.manager.SwitchManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Entity.RemovalReason;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "remove", at = @At("TAIL"))
    private void onRemove(RemovalReason reason, CallbackInfo ci) {
        Entity self = (Entity)(Object)this;
        SwitchManager.removeCart(self);
    }
}