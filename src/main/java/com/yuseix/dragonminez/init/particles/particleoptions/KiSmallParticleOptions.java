package com.yuseix.dragonminez.init.particles.particleoptions;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.yuseix.dragonminez.init.MainParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

public class KiSmallParticleOptions implements ParticleOptions {
    private final int color;

    public KiSmallParticleOptions(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    @Override
    public ParticleType<?> getType() {
        return MainParticles.KI_SMALL_PARTICLE.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buf) {
        buf.writeInt(this.color);
    }

    @Override
    public String writeToString() {
        return Integer.toString(this.color);
    }

    public static final Codec<KiSmallParticleOptions> CODEC = Codec.INT.xmap(
            KiSmallParticleOptions::new, // Convierte un entero en KiTrailParticleOptions
            KiSmallParticleOptions::getColor // Convierte KiTrailParticleOptions en un entero
    );

    public static final ParticleOptions.Deserializer<KiSmallParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        @Override
        public KiSmallParticleOptions fromCommand(ParticleType<KiSmallParticleOptions> type, StringReader reader) throws CommandSyntaxException {
            int color = reader.readInt();
            return new KiSmallParticleOptions(color);
        }

        @Override
        public KiSmallParticleOptions fromNetwork(ParticleType<KiSmallParticleOptions> type, FriendlyByteBuf buf) {
            int color = buf.readInt();
            return new KiSmallParticleOptions(color);
        }
    };
}