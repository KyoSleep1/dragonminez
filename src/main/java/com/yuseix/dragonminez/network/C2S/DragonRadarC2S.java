package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.UpdateDragonRadarS2C;
import com.yuseix.dragonminez.network.S2C.UpdateNamekDragonRadarS2C;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import com.yuseix.dragonminez.world.NamekDragonBallGenProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class DragonRadarC2S {
	String dimension;

	public DragonRadarC2S(String dimension) {
		this.dimension = dimension;
	}

	public static void encode(DragonRadarC2S msg, FriendlyByteBuf buf) {
		buf.writeUtf(msg.dimension);
	}

	public static DragonRadarC2S decode(FriendlyByteBuf buf) {
		return new DragonRadarC2S(buf.readUtf());
	}

	public static void handle(DragonRadarC2S msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ServerPlayer player = ctx.get().getSender();
			if (player != null) {
				Level level = player.level();
				if (level.isClientSide()) return; // Solo manejar en el servidor

				List<BlockPos> positionsDBall = null;
				List<BlockPos> positionsNDball = null;

				if (level.getCapability(DragonBallGenProvider.CAPABILITY).isPresent()) {
					positionsDBall = level.getCapability(DragonBallGenProvider.CAPABILITY)
							.orElseThrow(() -> new IllegalStateException("DragonBallGenProvider not found"))
							.DragonBallPositions();
				}
				if (level.getCapability(NamekDragonBallGenProvider.CAPABILITY).isPresent()) {
					positionsNDball = level.getCapability(NamekDragonBallGenProvider.CAPABILITY)
							.orElseThrow(() -> new IllegalStateException("NamekDragonBallGenProvider not found"))
							.namekDragonBallPositions();
				}

				switch (msg.dimension) {
					case "overworld":
						ModMessages.sendToPlayer(new UpdateDragonRadarS2C(positionsDBall), player);
						break;
					case "namek":
						ModMessages.sendToPlayer(new UpdateNamekDragonRadarS2C(positionsNDball), player);
						break;
				}
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
