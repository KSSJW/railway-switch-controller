package com.kssjw.railwayswitchcontroller.client;

import com.kssjw.railwayswitchcontroller.client.key.Keyboard;
import com.kssjw.railwayswitchcontroller.manager.NetworkManager.SwitchRailPayload;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class RailwaySwitchControllerClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {

        Keyboard.init();

        // 每 tick 检查按键
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (Keyboard.switchKey.wasPressed()) {
                ClientPlayNetworking.send(new SwitchRailPayload());
            }
        });
    }
}