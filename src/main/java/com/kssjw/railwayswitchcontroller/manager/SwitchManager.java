package com.kssjw.railwayswitchcontroller.manager;

import com.kssjw.railwayswitchcontroller.util.CartListUtil;
import com.kssjw.railwayswitchcontroller.util.ChangeUtil;
import com.kssjw.railwayswitchcontroller.util.MessageUtil;
import com.kssjw.railwayswitchcontroller.util.ScanUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SwitchManager {

    private static BlockPos cachePos;
    public static BlockPos targetPos;

    public static void scan(AbstractMinecartEntity minecart) {
        if (minecart.getEntityWorld().isClient()) return;
        if (!minecart.getPassengerList().stream().anyMatch(p -> p instanceof PlayerEntity)) return;
        // if (targetPos != null && targetPos == cachePos && CartListUtil.containsMinecart(minecart)) return;

        targetPos = ScanUtil.scan(minecart);
        cachePos = targetPos;

        if (targetPos != null) {
            CartListUtil.addMinecart(minecart);
        } else {
            CartListUtil.removeMinecart(minecart);
        }

        for (Entity entity : minecart.getPassengerList()) {
            if (entity instanceof PlayerEntity player) MessageUtil.sendDistance(player);
        }
    }

    public static void change(PlayerEntity player) {
        World world = player.getEntityWorld();
        if (world.isClient()) return;

        if (targetPos == null) return;
        if (!(player.getVehicle() instanceof AbstractMinecartEntity minecart)) return;
        if (!CartListUtil.containsMinecart(minecart)) return;

        double x = targetPos.getX();
        double y = targetPos.getY();
        double z = targetPos.getZ();
        if (minecart.squaredDistanceTo(x, y, z) >= Math.pow(50.0, 2.0)) return;   // 平方距离

        ChangeUtil.changeShape(world, targetPos);
        targetPos = null;
    }

    public static double getDistance(PlayerEntity player) {
        if (targetPos == null) return -1.0;
        if (player.getEntityWorld().isClient()) return -1.0;
        if (!(player.getVehicle() instanceof AbstractMinecartEntity minecart)) return -1.0;
        if (!CartListUtil.containsMinecart(minecart)) return -1.0;

        double x = targetPos.getX();
        double y = targetPos.getY();
        double z = targetPos.getZ();
        return Math.sqrt(minecart.squaredDistanceTo(x, y, z));
    }

    public static void removeCart(Entity entity) {
        if (entity instanceof AbstractMinecartEntity minecart && !minecart.getEntityWorld().isClient()) {
            CartListUtil.removeMinecart(minecart);
        }
    }
}