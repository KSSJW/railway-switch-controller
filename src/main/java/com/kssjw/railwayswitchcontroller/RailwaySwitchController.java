package com.kssjw.railwayswitchcontroller;

import com.kssjw.railwayswitchcontroller.manager.SwitchManager;
import com.kssjw.railwayswitchcontroller.manager.NetworkManager.SwitchRailPayload;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;

public class RailwaySwitchController implements ModInitializer {

    @Override
    public void onInitialize() {

        PayloadTypeRegistry.playC2S().register(SwitchRailPayload.ID, SwitchRailPayload.CODEC);  // 注册网络 Payload

        // 注册接收器
        ServerPlayNetworking.registerGlobalReceiver(
            SwitchRailPayload.ID,
            (payload, context) -> {
                PlayerEntity player = context.player();
                SwitchManager.change(player);
            }
        );
    }
}