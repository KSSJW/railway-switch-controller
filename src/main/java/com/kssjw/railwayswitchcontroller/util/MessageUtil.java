package com.kssjw.railwayswitchcontroller.util;

import com.kssjw.railwayswitchcontroller.manager.SwitchManager;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class MessageUtil {
    
    private MessageUtil() {}

    public static void sendDistance(PlayerEntity player) {
        double distance = SwitchManager.getDistance(player);
        if (distance == -1) return;

        player.sendMessage(Text.translatable("message.railway-switch-controller.distance")
            .append(String.format("%.2f", distance))
            .formatted(Formatting.YELLOW), true);
    }
}