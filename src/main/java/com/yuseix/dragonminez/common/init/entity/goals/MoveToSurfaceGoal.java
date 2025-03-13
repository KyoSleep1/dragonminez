package com.yuseix.dragonminez.common.init.entity.goals;

import com.yuseix.dragonminez.common.init.MainFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.material.Fluids;

import java.util.EnumSet;

public class MoveToSurfaceGoal extends Goal {
	private final Mob mob;

	public MoveToSurfaceGoal(Mob mob) {
		this.mob = mob;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		// Verifica si la entidad está en el agua
		return this.mob.isInWater();
	}

	@Override
	public void start() {
		// Encuentra la superficie más cercana directamente sobre la entidad
		BlockPos pos = this.mob.blockPosition();
		while (this.mob.level().getFluidState(pos).is(Fluids.WATER) || this.mob.level().getFluidState(pos).is(MainFluids.SOURCE_NAMEK.get())) {
			pos = pos.above();
		}
		// Mueve a la entidad hacia la superficie
		this.mob.getNavigation().moveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 1.0D);
	}

	@Override
	public boolean canContinueToUse() {
		// Continúa si no está fuera del agua todavía
		return this.mob.isInWater();
	}
}

