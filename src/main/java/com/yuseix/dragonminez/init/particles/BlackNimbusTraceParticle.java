package com.yuseix.dragonminez.init.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

public class BlackNimbusTraceParticle extends TextureSheetParticle {
	protected BlackNimbusTraceParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.setSpriteFromAge(sprites);
		this.friction = 0.9F;
		this.quadSize = 0.4F;
		this.lifetime = 20; // Lasts 20 ticks (1 second)
	}

	@Override
	public @NotNull ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
		// Fade out over time
		this.alpha = 1.0F - (float) this.age / (float) this.lifetime;
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet spriteSet) {
			this.sprites = spriteSet;
		}

		@Override
		public Particle createParticle(@NotNull SimpleParticleType type, @NotNull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new NimbusTraceParticle(level, x, y, z, this.sprites, xSpeed, ySpeed, zSpeed);
		}
	}
}
