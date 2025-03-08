package com.yuseix.dragonminez.init;

import com.mojang.serialization.Codec;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.particles.particleoptions.KiLargeParticleOptions;
import com.yuseix.dragonminez.init.particles.particleoptions.KiSmallParticleOptions;
import com.yuseix.dragonminez.init.particles.particleoptions.KiStarParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MainParticles {

	public static final DeferredRegister<ParticleType<?>> PARTICLES_REGISTER =
			DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, DragonMineZ.MOD_ID);

	public static final RegistryObject<SimpleParticleType> AJISSA_LEAVES_PARTICLE = PARTICLES_REGISTER.register("ajissa_leaves_particle",
			() -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> SACRED_LEAVES_PARTICLE = PARTICLES_REGISTER.register("sacred_leaves_particle",
			() -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> HIT_ATTACK_PARTICLE = PARTICLES_REGISTER.register("hit_attack_particle",
			() -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> NIMBUS_TRACE_PARTICLE = PARTICLES_REGISTER.register("nimbus_trace_particle",
			() -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> BLACKNIMBUS_TRACE_PARTICLE = PARTICLES_REGISTER.register("blacknimbus_trace_particle",
			() -> new SimpleParticleType(true));
	public static final RegistryObject<ParticleType<KiSmallParticleOptions>> KI_SMALL_PARTICLE = PARTICLES_REGISTER.register("ki_small_particle",
			() -> new ParticleType<>(false, KiSmallParticleOptions.DESERIALIZER) {
				@Override
				public Codec<KiSmallParticleOptions> codec() {
					return KiSmallParticleOptions.CODEC;
				}
			});
	public static final RegistryObject<ParticleType<KiLargeParticleOptions>> KI_LARGE_PARTICLE = PARTICLES_REGISTER.register("ki_large_particle",
			() -> new ParticleType<>(false, KiLargeParticleOptions.DESERIALIZER) {
				@Override
				public Codec<KiLargeParticleOptions> codec() {
					return KiLargeParticleOptions.CODEC;
				}
			});
	public static final RegistryObject<ParticleType<KiStarParticleOptions>> KI_STAR_PARTICLE = PARTICLES_REGISTER.register("ki_star_particle",
			() -> new ParticleType<>(false, KiStarParticleOptions.DESERIALIZER) {
				@Override
				public Codec<KiStarParticleOptions> codec() {
					return KiStarParticleOptions.CODEC;
				}
			});
	public static void register(IEventBus busEvent) {
		PARTICLES_REGISTER.register(busEvent);
	}
}
