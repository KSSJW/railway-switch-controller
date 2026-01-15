package com.kssjw.railwayswitchcontroller.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.railwayswitchcontroller.manager.SwitchManager;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        AbstractMinecartEntity minecart = (AbstractMinecartEntity)(Object)this;
        SwitchManager.scan(minecart);
    }
}