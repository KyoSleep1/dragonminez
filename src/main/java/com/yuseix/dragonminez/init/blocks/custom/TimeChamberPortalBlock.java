package com.yuseix.dragonminez.init.blocks.custom;

import com.yuseix.dragonminez.world.StructuresProvider;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.ITeleporter;

import java.util.Collections;
import java.util.function.Function;

public class TimeChamberPortalBlock extends Block {
    public TimeChamberPortalBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).noParticlesOnBreak().strength(-1.0F, 3600000.0F).noLootTable());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockpos, Player pPlayer, InteractionHand hand, BlockHitResult hit) {
        if (pPlayer.canChangeDimensions()) {
            if (pPlayer.level() instanceof ServerLevel) {
                boolean onTC = pPlayer.level().dimension() == ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY;
                ServerLevel targetWorld = ((ServerLevel) pPlayer.level()).getServer().getLevel(onTC ? Level.OVERWORLD : ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY);

                if (targetWorld != null && !pPlayer.isPassenger()) {
                    // Cambiar de dimensión primero
                    pPlayer.changeDimension(targetWorld, new ITeleporter() {
                        @Override
                        public Entity placeEntity(Entity entity, ServerLevel current, ServerLevel destination, float yaw, Function<Boolean, Entity> repositionEntity) {
                            // Mantener el reposicionamiento estándar
                            return repositionEntity.apply(false);
                        }
                    });

                    // Asignar la nueva posición después de cambiar de dimensión
                    targetWorld.getCapability(StructuresProvider.CAPABILITY).ifPresent(structures -> {
                        int rotX = 0; int rotY = 0; ResourceKey<Level> dim; BlockPos pos;
                        if (onTC) { // Saliste
                            dim = Level.OVERWORLD;
                            pos = structures.getPortalHabTiempoPosition();
						} else { // Entraste
                            dim = ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY;
                            pos = structures.getHabTiempoPos().offset(70, 3, 7);
						} // Forzar el reposicionamiento después de que el jugador cambie de dimensión
						ServerLevel targetLevel = pPlayer.getServer().getLevel(dim);
                        pPlayer.teleportTo(targetLevel, pos.getX(), pos.getY(), pos.getZ(), Collections.emptySet(), rotX, rotY);
                    });
                }
            }

            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }
    }

    @Override
	public boolean addLandingEffects(BlockState state1, ServerLevel level, BlockPos pos, BlockState state2, LivingEntity entity, int numberOfParticles) {
        return true;
    }
    @Override
    public boolean addRunningEffects(BlockState state, Level level, BlockPos pos, Entity entity) {
        return true;
    }
}
