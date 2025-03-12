package com.yuseix.dragonminez.init.particles.particleoptions;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.yuseix.dragonminez.init.MainParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

public class AjissaLeavesParticleOptions implements ParticleOptions {
	private final int color;

	public AjissaLeavesParticleOptions(int color) {
		this.color = color;
	}

	public int getColor() {
		return this.color;
	}

	@Override
	public ParticleType<?> getType() {
		return MainParticles.AJISSA_LEAVES_PARTICLE.get();
	}

	@Override
	public void writeToNetwork(FriendlyByteBuf buf) {
		buf.writeInt(this.color);
	}

	@Override
	public String writeToString() {
		return Integer.toString(this.color);
	}

	public static final Codec<AjissaLeavesParticleOptions> CODEC = Codec.INT.xmap(
			AjissaLeavesParticleOptions::new,
			AjissaLeavesParticleOptions::getColor
	);

	public static final ParticleOptions.Deserializer<AjissaLeavesParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
		@Override
		public AjissaLeavesParticleOptions fromCommand(ParticleType<AjissaLeavesParticleOptions> type, StringReader reader) throws CommandSyntaxException {
			int color = reader.readInt();
			return new AjissaLeavesParticleOptions(color);
		}

		@Override
		public AjissaLeavesParticleOptions fromNetwork(ParticleType<AjissaLeavesParticleOptions> type, FriendlyByteBuf buf) {
			int color = buf.readInt();
			return new AjissaLeavesParticleOptions(color);
		}
	};
}
