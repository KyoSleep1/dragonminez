package com.yuseix.dragonminez.common.init.particles.particleoptions;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.yuseix.dragonminez.common.init.MainParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

public class SacredLeavesParticleOptions implements ParticleOptions {
	private final int color;

	public SacredLeavesParticleOptions(int color) {
		this.color = color;
	}

	public int getColor() {
		return this.color;
	}

	@Override
	public ParticleType<?> getType() {
		return MainParticles.SACRED_LEAVES_PARTICLE.get();
	}

	@Override
	public void writeToNetwork(FriendlyByteBuf buf) {
		buf.writeInt(this.color);
	}

	@Override
	public String writeToString() {
		return Integer.toString(this.color);
	}

	public static final Codec<SacredLeavesParticleOptions> CODEC = Codec.INT.xmap(
			SacredLeavesParticleOptions::new,
			SacredLeavesParticleOptions::getColor
	);

	public static final Deserializer<SacredLeavesParticleOptions> DESERIALIZER = new Deserializer<>() {
		@Override
		public SacredLeavesParticleOptions fromCommand(ParticleType<SacredLeavesParticleOptions> type, StringReader reader) throws CommandSyntaxException {
			int color = reader.readInt();
			return new SacredLeavesParticleOptions(color);
		}

		@Override
		public SacredLeavesParticleOptions fromNetwork(ParticleType<SacredLeavesParticleOptions> type, FriendlyByteBuf buf) {
			int color = buf.readInt();
			return new SacredLeavesParticleOptions(color);
		}
	};
}
