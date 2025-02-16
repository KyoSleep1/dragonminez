package com.yuseix.dragonminez.worldgen.biome;

import com.yuseix.dragonminez.init.MainBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModSurfaceRules {
	private static final SurfaceRules.RuleSource ROCKY_STONE = makeStateRule(MainBlocks.ROCKY_STONE.get());
	private static final SurfaceRules.RuleSource ROCKY_DIRT = makeStateRule(MainBlocks.ROCKY_DIRT.get());
	private static final SurfaceRules.RuleSource ROCKY_COBBLE = makeStateRule(MainBlocks.ROCKY_COBBLESTONE.get());

	public static SurfaceRules.RuleSource makeRules() {
		SurfaceRules.RuleSource rockySurfaceRule = SurfaceRules.sequence(
				SurfaceRules.ifTrue(
						SurfaceRules.isBiome(ModBiomes.ROCKY),
						SurfaceRules.sequence(
								SurfaceRules.ifTrue(
										SurfaceRules.abovePreliminarySurface(),
										SurfaceRules.ifTrue(
												SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
												SurfaceRules.sequence(
														SurfaceRules.ifTrue(
																SurfaceRules.waterBlockCheck(0, 0),
																ROCKY_DIRT
														),
														ROCKY_DIRT
												)
										)
								),
								SurfaceRules.ifTrue(
										SurfaceRules.yStartCheck(VerticalAnchor.absolute(50), 6),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.stoneDepthCheck(0, true, 3, CaveSurface.FLOOR),
														ROCKY_STONE
												)
										)
								),
								SurfaceRules.ifTrue(
										SurfaceRules.yStartCheck(VerticalAnchor.absolute(50), 16),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.stoneDepthCheck(0, true, 8, CaveSurface.FLOOR),
														ROCKY_COBBLE
												)
										)
								)
						)
				)
		);

		return SurfaceRules.sequence(
			rockySurfaceRule
		);
	}

	private static SurfaceRules.RuleSource makeStateRule(Block block) {
		return SurfaceRules.state(block.defaultBlockState());
	}
}
