package com.yuseix.dragonminez.server.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
	public ModOverworldRegion() {
		super(new ResourceLocation(Reference.MOD_ID, "rocky_region"), RegionType.OVERWORLD, 1);
	}

	@Override
	public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
		Climate.ParameterPoint rockyBiomePoint = Climate.parameters(
				Climate.Parameter.span(0.6F, 1.0F), // Temperatura T
				Climate.Parameter.span(-0.15F, 0.1F), // Humedad V
				Climate.Parameter.span(0.95F, 1.0F),  // Continentalidad (montañas) C
				Climate.Parameter.span(-1.0F, -0.95F),  // Erosión E
				Climate.Parameter.span(0.95F, 2.0F),  // Rareza W
				Climate.Parameter.span(0.0F, 0.0F),  // Profundidad D
				0.0F                                		     // Offset
		);

		mapper.accept(Pair.of(rockyBiomePoint, ModBiomes.ROCKY));
	}
}