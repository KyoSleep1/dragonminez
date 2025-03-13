package com.yuseix.dragonminez.common.init.particles.particleoptions;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.yuseix.dragonminez.common.init.MainParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

public class KiStarParticleOptions implements ParticleOptions {
    private final int color;

    public KiStarParticleOptions(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    @Override
    public ParticleType<?> getType() {
        return MainParticles.KI_STAR_PARTICLE.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buf) {
        buf.writeInt(this.color);
    }

    @Override
    public String writeToString() {
        return Integer.toString(this.color);
    }

    public static final Codec<KiStarParticleOptions> CODEC = Codec.INT.xmap(
            KiStarParticleOptions::new, // Convierte un entero en KiTrailParticleOptions
            KiStarParticleOptions::getColor // Convierte KiTrailParticleOptions en un entero
    );

    public static final Deserializer<KiStarParticleOptions> DESERIALIZER = new Deserializer<>() {
        @Override
        public KiStarParticleOptions fromCommand(ParticleType<KiStarParticleOptions> type, StringReader reader) throws CommandSyntaxException {
            int color = reader.readInt();
            return new KiStarParticleOptions(color);
        }

        @Override
        public KiStarParticleOptions fromNetwork(ParticleType<KiStarParticleOptions> type, FriendlyByteBuf buf) {
            int color = buf.readInt();
            return new KiStarParticleOptions(color);
        }
    };
}