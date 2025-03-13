package com.yuseix.dragonminez.common.init.particles.particleoptions;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.yuseix.dragonminez.common.init.MainParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

public class KiLargeParticleOptions implements ParticleOptions {
    private final int color;

    public KiLargeParticleOptions(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    @Override
    public ParticleType<?> getType() {
        return MainParticles.KI_LARGE_PARTICLE.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buf) {
        buf.writeInt(this.color);
    }

    @Override
    public String writeToString() {
        return Integer.toString(this.color);
    }

    public static final Codec<KiLargeParticleOptions> CODEC = Codec.INT.xmap(
            KiLargeParticleOptions::new, // Convierte un entero en KiTrailParticleOptions
            KiLargeParticleOptions::getColor // Convierte KiTrailParticleOptions en un entero
    );

    public static final Deserializer<KiLargeParticleOptions> DESERIALIZER = new Deserializer<>() {
        @Override
        public KiLargeParticleOptions fromCommand(ParticleType<KiLargeParticleOptions> type, StringReader reader) throws CommandSyntaxException {
            int color = reader.readInt();
            return new KiLargeParticleOptions(color);
        }

        @Override
        public KiLargeParticleOptions fromNetwork(ParticleType<KiLargeParticleOptions> type, FriendlyByteBuf buf) {
            int color = buf.readInt();
            return new KiLargeParticleOptions(color);
        }
    };
}