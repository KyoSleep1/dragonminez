package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
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


	public static void register(IEventBus busEvent) {
		PARTICLES_REGISTER.register(busEvent);
	}
}
