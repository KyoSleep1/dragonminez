package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.world.StructuresProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OtroMundoC2S {

	private String option;

	public OtroMundoC2S(String option) {
		this.option = option;
	}

	public OtroMundoC2S(FriendlyByteBuf buf) {
		option = buf.readUtf(32767);
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeUtf(option);
	}

	public static void handle(OtroMundoC2S packet, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		context.enqueueWork(() -> {
			ServerPlayer player = ctx.get().getSender();
			if (player != null) {
				switch (packet.option) {
					case "enma":
						ServerLevel level = player.server.overworld();
						level.getCapability(StructuresProvider.CAPABILITY).ifPresent(structures -> {
							BlockPos pos = structures.getTorreKamisamaPosition();
							player.teleportTo(level, pos.getX(), pos.getY(), pos.getZ(), player.getYRot(), player.getXRot());
						});
						break;
					case "baba":
						ServerLevel level2 = player.server.overworld();
						level2.getCapability(StructuresProvider.CAPABILITY).ifPresent(structures -> {
							BlockPos pos = structures.getTorreKamisamaPosition();
							player.teleportTo(level2, pos.getX(), pos.getY(), pos.getZ(), player.getYRot(), player.getXRot());
						});
						DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
							int babaCooldown = (DMZGeneralConfig.BABA_COOLDOWN.get() * 20 * 60);
							int babaDuration = (DMZGeneralConfig.BABA_DURATION.get() * 20 * 60);
							cap.setBabaCooldown(babaCooldown);
							cap.setBabaAliveTimer(babaDuration);
						});
						break;
				}
			}
		});
		context.setPacketHandled(true);
	}
}
