package com.tcn.superultraepicmod.util;

import com.tcn.superultraepicmod.access.EntityAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;

public class FrostWalker
{
    public static void onEntityMovedNonLiving(Entity entity, Level world, BlockPos blockPos, int level) {
        if (entity.isOnGround()) {
            BlockState blockState = Blocks.FROSTED_ICE.defaultBlockState();
            float f = (float) Math.min(16, 2 + level);
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

            for (BlockPos blockPos2 : BlockPos.betweenClosed(blockPos.offset(-f, -1.0, -f), blockPos.offset(f, -1.0, f))) {
                if (blockPos2.closerToCenterThan(entity.position(), f)) {
                    mutableBlockPos.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
                    BlockState blockState2 = world.getBlockState(mutableBlockPos);
                    if (blockState2.isAir()) {
                        BlockState blockState3 = world.getBlockState(blockPos2);
                        if (blockState3.getMaterial() == Material.WATER
                              && blockState3.getValue(LiquidBlock.LEVEL) == 0
                              && blockState.canSurvive(world, blockPos2)
                              && world.isUnobstructed(blockState, blockPos2, CollisionContext.empty())) {
                            world.setBlockAndUpdate(blockPos2, blockState);
                            world.scheduleTick(blockPos2, Blocks.FROSTED_ICE, Mth.nextInt(((EntityAccess) entity).getRandomSource(), 60, 120));
                        }
                    }
                }
            }
        }
    }
}
