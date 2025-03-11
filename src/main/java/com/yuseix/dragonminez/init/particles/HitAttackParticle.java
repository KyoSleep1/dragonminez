package com.yuseix.dragonminez.init.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

public class HitAttackParticle extends TextureSheetParticle {
	protected HitAttackParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.setSpriteFromAge(sprites);
		this.friction = 0.9F;
		this.quadSize = 0.95F;
		this.lifetime = 20 + this.random.nextInt(10);
		this.xd = 0;
		this.zd = 0;
		this.roll = (float) (Math.random() * Math.PI * 2);
	}

	@Override
	public @NotNull ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public void tick() {
		super.tick();
		// Fade out over time
		this.alpha = 1.0F - (float) this.age / (float) this.lifetime;
		this.roll -= 0.01F; // Ajusta este valor para que rote más lento
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet spriteSet) {
			this.sprites = spriteSet;
		}

		@Override
		public Particle createParticle(@NotNull SimpleParticleType type, @NotNull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new HitAttackParticle(level, x, y, z, this.sprites, xSpeed, ySpeed, zSpeed);
		}
	}
}

