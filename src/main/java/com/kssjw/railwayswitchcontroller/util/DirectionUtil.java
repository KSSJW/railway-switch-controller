package com.kssjw.railwayswitchcontroller.util;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.Vec3d;

public class DirectionUtil {

    private static final String N = "N";
    private static final String NE = "NE";
    private static final String E = "E";
    private static final String SE = "SE";
    private static final String S = "S";
    private static final String SW = "SW";
    private static final String W = "W";
    private static final String NW = "NW";

    public static String getDetailedDirection(AbstractMinecartEntity minecart) {
        Vec3d velocity = minecart.getVelocity();
        double angle = Math.atan2(velocity.z, velocity.x);
        double degrees = Math.toDegrees(angle);
        if (degrees < 0) degrees += 360;

        // 四个直角方向：东(0)、北(90)、西(180)、南(270)
        int rounded = (int) Math.round(degrees);

        if (rounded == 0 || rounded == 360) return E;
        if (rounded == 90) return S;
        if (rounded == 180) return W;
        if (rounded == 270) return N;

        // 斜角方向
        if (degrees > 0 && degrees < 90) return SE;
        if (degrees > 90 && degrees < 180) return SW;
        if (degrees > 180 && degrees < 270) return NW;
        if (degrees > 270 && degrees < 360) return NE;

        return null;    // Fallback
    }
}