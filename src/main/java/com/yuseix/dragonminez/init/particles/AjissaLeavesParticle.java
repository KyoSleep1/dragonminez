package com.yuseix.dragonminez.init.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class AjissaLeavesParticle extends TextureSheetParticle {
	protected AjissaLeavesParticle(ClientLevel level, double x, double y, double z, SpriteSet texture, double xSpeed, double ySpeed, double zSpeed) {
		super(level, x, y, z, xSpeed, ySpeed, zSpeed);

		this.setSpriteFromAge(texture);

		this.gravity = 0.1F; // Caída
		this.friction = 0.98F; // Qué tan lejos va
		this.quadSize *= 0.8F; // Tamaño
		this.lifetime = 80 + this.random.nextInt(40); // Cuanto tarda en despawnear (ticks)

		this.rCol = 1f;
		this.gCol = 1f;
		this.bCol = 1f;
	}

	@Override
	public void tick() {
		super.tick();
		fadeOut();
	}

	private void fadeOut() {
		this.alpha = (-(1 / (float) lifetime) * age + 1);
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
			return new AjissaLeavesParticle(level, x, y, z, this.sprites, xSpeed, ySpeed, zSpeed);
		}
	}
}
