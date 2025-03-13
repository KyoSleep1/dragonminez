package com.yuseix.dragonminez.common.network.C2S;

import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import com.yuseix.dragonminez.client.config.DMZClientConfig;
import com.yuseix.dragonminez.common.world.cap.provider.StructuresProvider;
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
						System.out.println("Enma");
						ServerLevel level = player.server.overworld();
						level.getCapability(StructuresProvider.CAPABILITY).ifPresent(structures -> {
							BlockPos pos = structures.getTorreKamisamaPosition();
							player.teleportTo(level, pos.getX(), pos.getY(), pos.getZ(), player.getYRot(), player.getXRot());
						});
						break;
					case "baba":
						DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
							int babaCooldown = (DMZClientConfig.getBabaCooldown() * 20 * 60);
							int babaDuration = (DMZClientConfig.getBabaDuration() * 20 * 60);
							cap.setIntValue("babacooldown", babaCooldown);
							cap.setIntValue("babaalivetimer", babaDuration);
						});
						break;
				}
			}
		});
		context.setPacketHandled(true);
	}
}
