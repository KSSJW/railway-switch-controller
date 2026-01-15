package com.kssjw.railwayswitchcontroller.util;

import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.DetectorRailBlock;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.block.RailBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JudgeUtil {

    private JudgeUtil() {}

    public static boolean isAbstractRail(World world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() instanceof AbstractRailBlock) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isRail(World world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() instanceof RailBlock) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCurved(World world, BlockPos pos) {
        if (isRail(world, pos)
            && (getShape(world, pos) == RailShape.NORTH_EAST
            || getShape(world, pos) == RailShape.NORTH_WEST
            || getShape(world, pos) == RailShape.SOUTH_EAST
            || getShape(world, pos) == RailShape.SOUTH_WEST
            )                   
        ) {
            return true;
        } else {
            return false;
        }
    }

    public static RailShape getShape(World world, BlockPos pos) {
        if (!isAbstractRail(world, pos)) return null;

        if (world.getBlockState(pos).getBlock() instanceof RailBlock) return world.getBlockState(pos).get(Properties.RAIL_SHAPE);
        if (world.getBlockState(pos).getBlock() instanceof PoweredRailBlock) return world.getBlockState(pos).get(Properties.STRAIGHT_RAIL_SHAPE);
        if (world.getBlockState(pos).getBlock() instanceof DetectorRailBlock) return world.getBlockState(pos).get(Properties.STRAIGHT_RAIL_SHAPE);

        return null;    // Fallback
    }

    public static boolean isSwitch(World world, BlockPos pos) {
        if (isRail(world, pos) && isCurved(world, pos)) {
            int neighbor = 0;

            if (isAbstractRail(world, pos.north())
                && (
                    getShape(world, pos.north()) == RailShape.NORTH_SOUTH
                    || getShape(world, pos.north()) == RailShape.SOUTH_EAST
                    || getShape(world, pos.north()) == RailShape.SOUTH_WEST
                )
            ) {
                neighbor++;
            }

            if (isAbstractRail(world, pos.east())
                && (
                    getShape(world, pos.east()) == RailShape.EAST_WEST
                    || getShape(world, pos.east()) == RailShape.NORTH_WEST
                    || getShape(world, pos.east()) == RailShape.SOUTH_WEST
                )
            ) {
                neighbor++;
            }

            if (isAbstractRail(world, pos.south())
                && (
                    getShape(world, pos.south()) == RailShape.NORTH_SOUTH
                    || getShape(world, pos.south()) == RailShape.NORTH_EAST
                    || getShape(world, pos.south()) == RailShape.NORTH_WEST
                )
            ) {
                neighbor++;
            }

            if (isAbstractRail(world, pos.west())
                && (
                    getShape(world, pos.west()) == RailShape.EAST_WEST
                    || getShape(world, pos.west()) == RailShape.NORTH_EAST
                    || getShape(world, pos.west()) == RailShape.SOUTH_EAST
                )
            ) {
                neighbor++;
            }

            if (neighbor <= 2) return false;

            return true;
        } else {
            return false;
        }
    }

    public static boolean canToN(World world, BlockPos pos) {
        if (isAbstractRail(world, pos.north())
            && (
                getShape(world, pos.north()) == RailShape.NORTH_SOUTH
                || getShape(world, pos.north()) == RailShape.SOUTH_EAST
                || getShape(world, pos.north()) == RailShape.SOUTH_WEST
                || getShape(world, pos.north()) == RailShape.ASCENDING_NORTH
                || getShape(world, pos.north()) == RailShape.ASCENDING_SOUTH
            )
        ) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean canToE(World world, BlockPos pos) {
        if (isAbstractRail(world, pos.east())
            && (
                getShape(world, pos.east()) == RailShape.EAST_WEST
                || getShape(world, pos.east()) == RailShape.NORTH_WEST
                || getShape(world, pos.east()) == RailShape.SOUTH_WEST
                || getShape(world, pos.east()) == RailShape.ASCENDING_EAST
                || getShape(world, pos.east()) == RailShape.ASCENDING_WEST
            )
        ) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean canToS(World world, BlockPos pos) {
        if (isAbstractRail(world, pos.south())
            && (
                getShape(world, pos.south()) == RailShape.NORTH_SOUTH
                || getShape(world, pos.south()) == RailShape.NORTH_EAST
                || getShape(world, pos.south()) == RailShape.NORTH_WEST
                || getShape(world, pos.north()) == RailShape.ASCENDING_NORTH
                || getShape(world, pos.north()) == RailShape.ASCENDING_SOUTH
            )
        ) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean canToW(World world, BlockPos pos) {
        if (isAbstractRail(world, pos.west())
            && (
                getShape(world, pos.west()) == RailShape.EAST_WEST
                || getShape(world, pos.west()) == RailShape.NORTH_EAST
                || getShape(world, pos.west()) == RailShape.SOUTH_EAST
                || getShape(world, pos.east()) == RailShape.ASCENDING_EAST
                || getShape(world, pos.east()) == RailShape.ASCENDING_WEST
            )
        ) {
            return true;
        } else {
            return false;
        }
    }
}