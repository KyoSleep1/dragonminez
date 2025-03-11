package com.yuseix.dragonminez.init.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class DustParticle extends TextureSheetParticle {
	private final SpriteSet sprites;

	protected DustParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
		super(level, x, y, z, xSpeed, ySpeed, zSpeed);
		this.sprites = sprites;
		this.lifetime = 40 + this.random.nextInt(20);
		this.gravity = 0.0F;
		this.friction = 0.98F;
		this.quadSize = 0.2F;
		this.setSpriteFromAge(sprites);

		this.yd = 0;
	}

	@Override
	public void tick() {
		super.tick();
		this.setSpriteFromAge(sprites);
		this.alpha = 1.0F - (float) this.age / (float) this.lifetime;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet spriteSet) {
			this.sprites = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new DustParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
		}
	}
}
