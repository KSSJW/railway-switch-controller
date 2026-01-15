package com.kssjw.railwayswitchcontroller.util;

import net.minecraft.block.enums.RailShape;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ScanUtil {

    private ScanUtil() {}

    private static final String N = "N";
    private static final String NE = "NE";
    private static final String E = "E";
    private static final String SE = "SE";
    private static final String S = "S";
    private static final String SW = "SW";
    private static final String W = "W";
    private static final String NW = "NW";

    public static BlockPos scan(AbstractMinecartEntity minecart) {
        World world = minecart.getEntityWorld();
        BlockPos pos = minecart.getBlockPos();
        String dir = DirectionUtil.getDetailedDirection(minecart);

        // TODO 激活铁轨待测试 轨道形状补全（上下坡）
        for (int i = 0; i < 50; i++) {
            if (JudgeUtil.isSwitch(world, pos)) return pos;

            if (!JudgeUtil.isAbstractRail(world, pos)) return null;

            if (JudgeUtil.getShape(world, pos) == RailShape.NORTH_SOUTH
                || JudgeUtil.getShape(world, pos) == RailShape.ASCENDING_NORTH
                || JudgeUtil.getShape(world, pos) == RailShape.ASCENDING_SOUTH
            ) {
                switch (dir) {
                    case N:
                        pos = pos.north();
                        continue;

                    case S:
                        pos = pos.south();
                        continue;
                
                    default:
                        break;
                }
            }

            if (JudgeUtil.getShape(world, pos) == RailShape.EAST_WEST
                || JudgeUtil.getShape(world, pos) == RailShape.ASCENDING_EAST
                || JudgeUtil.getShape(world, pos) == RailShape.ASCENDING_WEST
            ) {
                switch (dir) {
                    case E:
                        pos = pos.east();
                        continue;

                    case W:
                        pos = pos.west();
                        continue;
                
                    default:
                        break;
                }
            }

            if (JudgeUtil.getShape(world, pos) == RailShape.NORTH_EAST) {
                switch (dir) {
                    case N:
                    case NW:
                    case W:
                        pos = pos.north();
                        continue;

                    case E:
                    case SE:
                    case S:
                        pos = pos.east();
                        continue;
                
                    default:
                        break;
                }
            }

            if (JudgeUtil.getShape(world, pos) == RailShape.SOUTH_EAST) {
                switch (dir) {
                    case E:
                    case NE:
                    case N:
                        pos = pos.east();
                        continue;

                    case S:
                    case SW:
                    case W:
                        pos = pos.south();
                        continue;
                
                    default:
                        break;
                }
            }

            if (JudgeUtil.getShape(world, pos) == RailShape.SOUTH_WEST) {
                switch (dir) {
                    case S:
                    case SE:
                    case E:
                        pos = pos.south();
                        continue;
                
                    case W:
                    case NW:
                    case N:
                        pos = pos.west();
                        continue;

                    default:
                        break;
                }
            }

            if (JudgeUtil.getShape(world, pos) == RailShape.NORTH_WEST) {
                switch (dir) {
                    case W:
                    case SW:
                    case S:
                        pos = pos.west();
                        continue;

                    case N:
                    case NE:
                    case E:
                        pos = pos.north();
                        continue;
                
                    default:
                        break;
                }
            }
        }
        
        return null;    // Fallback
    }
}