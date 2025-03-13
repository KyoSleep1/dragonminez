package com.yuseix.dragonminez.common.init.blocks.custom;

import com.yuseix.dragonminez.common.init.blocks.entity.GeteFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class GeteFurnaceBlock extends AbstractFurnaceBlock {
    public GeteFurnaceBlock(Properties pProperties) {
        super(pProperties);
    }

    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GeteFurnaceBlockEntity(blockPos, blockState);
    }

    @Override
    protected void openContainer(Level level, BlockPos blockPos, Player player) {
        //TODO EL CONTAINER (SCREEN)
    }

    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pLevel.isClientSide) {
            if (pPlayer.isCreative()) {
                pLevel.setBlock(pPos, pState.setValue(LIT, false), 3);
            } else {
                dropResources(pState, pLevel, pPos);
            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LIT)) {
            double lX = (double) pPos.getX() + 0.5D;
            double lY = pPos.getY();
            double lZ = (double) pPos.getZ() + 0.5D;
            if (pRandom.nextDouble() < 0.1D) {
                //TODO: SONIDO CUSTOM DEL HORNO
            }

            Direction $$7 = pState.getValue(FACING);
            Direction.Axis $$8 = $$7.getAxis();
            double $$10 = pRandom.nextDouble() * 0.6 - 0.3;
            double $$11 = $$8 == Direction.Axis.X ? (double) $$7.getStepX() * 0.52 : $$10;
            double $$12 = pRandom.nextDouble() * 6.0 / 16.0;
            double $$13 = $$8 == Direction.Axis.Z ? (double) $$7.getStepZ() * 0.52 : $$10;
            pLevel.addParticle(ParticleTypes.SMOKE, lX + $$11, lY + $$12, lZ + $$13, 0.0, 0.0, 0.0);
            pLevel.addParticle(ParticleTypes.FLAME, lX + $$11, lY + $$12, lZ + $$13, 0.0, 0.0, 0.0);
            pLevel.addParticle(DustParticleOptions.REDSTONE, lX, lY, lZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createFurnaceTicker(pLevel, pBlockEntityType, BlockEntityType.FURNACE);
    }

}