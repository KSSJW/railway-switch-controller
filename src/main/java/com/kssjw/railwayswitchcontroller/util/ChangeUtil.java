package com.kssjw.railwayswitchcontroller.util;

import net.minecraft.block.RailBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChangeUtil {

    private ChangeUtil() {}

    private static void toNorthEast(World world, BlockPos pos) {
        world.setBlockState(pos, world.getBlockState(pos).with(RailBlock.SHAPE, RailShape.NORTH_EAST), 3);
    }

    private static void toNorthWest(World world, BlockPos pos) {
        world.setBlockState(pos, world.getBlockState(pos).with(RailBlock.SHAPE, RailShape.NORTH_WEST), 3);
    }

    private static void toSouthEast(World world, BlockPos pos) {
        world.setBlockState(pos, world.getBlockState(pos).with(RailBlock.SHAPE, RailShape.SOUTH_EAST), 3);
    }

    private static void toSouthWest(World world, BlockPos pos) {
        world.setBlockState(pos, world.getBlockState(pos).with(RailBlock.SHAPE, RailShape.SOUTH_WEST), 3);
    }
    
    public static void changeShape(World world, BlockPos pos) {

        if (pos == null) return;
        if ((world.getBlockState(pos).getBlock() instanceof RailBlock) == false) return;

        RailShape shape = world.getBlockState(pos).get(RailBlock.SHAPE);
        switch (shape) {
            case NORTH_EAST:
                if (JudgeUtil.canToS(world, pos) && JudgeUtil.canToE(world, pos)) toSouthEast(world, pos);
                if (JudgeUtil.canToS(world, pos) && JudgeUtil.canToW(world, pos)) toSouthWest(world, pos);
                if (JudgeUtil.canToN(world, pos) && JudgeUtil.canToW(world, pos)) toNorthWest(world, pos);
                break;
                
            case SOUTH_EAST:
                if (JudgeUtil.canToS(world, pos) && JudgeUtil.canToW(world, pos)) toSouthWest(world, pos);
                if (JudgeUtil.canToN(world, pos) && JudgeUtil.canToW(world, pos)) toNorthWest(world, pos);
                if (JudgeUtil.canToN(world, pos) && JudgeUtil.canToE(world, pos)) toNorthEast(world, pos);
                break;

            case SOUTH_WEST:
                if (JudgeUtil.canToN(world, pos) && JudgeUtil.canToW(world, pos)) toNorthWest(world, pos);
                if (JudgeUtil.canToN(world, pos) && JudgeUtil.canToE(world, pos)) toNorthEast(world, pos);
                if (JudgeUtil.canToS(world, pos) && JudgeUtil.canToE(world, pos)) toSouthEast(world, pos);
                break;

            case NORTH_WEST:
                if (JudgeUtil.canToN(world, pos) && JudgeUtil.canToE(world, pos)) toNorthEast(world, pos);
                if (JudgeUtil.canToS(world, pos) && JudgeUtil.canToE(world, pos)) toSouthEast(world, pos);
                if (JudgeUtil.canToS(world, pos) && JudgeUtil.canToW(world, pos)) toSouthWest(world, pos);
                break;

            default:
                break;
        }
    }
}