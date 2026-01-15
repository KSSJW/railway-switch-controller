package com.kssjw.railwayswitchcontroller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;

public class CartListUtil {

    // 用来存储矿车的 UUID
    private static List<UUID> minecartIds = new ArrayList<>();

    // 添加矿车
    public static void addMinecart(AbstractMinecartEntity minecart) {
        if (!containsMinecart(minecart)) minecartIds.add(minecart.getUuid());
    }

    // 移除矿车
    public static void removeMinecart(AbstractMinecartEntity minecart) {
        if (containsMinecart(minecart)) minecartIds.remove(minecart.getUuid());
    }

    // 判断是否记录过某个矿车
    public static boolean containsMinecart(AbstractMinecartEntity minecart) {
        return minecartIds.contains(minecart.getUuid());
    }

    // 获取所有矿车 ID
    public static List<UUID> getMinecartIds() {
        return minecartIds;
    }
}