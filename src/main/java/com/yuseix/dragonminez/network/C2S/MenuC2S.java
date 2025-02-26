package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.MenuS2C;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MenuC2S {
	private String tipo;

	public MenuC2S(String tipo) {
		this.tipo = tipo;
	}

	public MenuC2S(FriendlyByteBuf buf) {
		this.tipo = buf.readUtf();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeUtf(this.tipo);
	}

	public static void handle(MenuC2S packet, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		context.enqueueWork(() -> {

			ServerPlayer player = ctx.get().getSender();

			if (player != null) {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {

					boolean isDmzUser = playerstats.isAcceptCharacter();
					boolean compactMenu = playerstats.isCompactMenu();

					ModMessages.sendToPlayer(new MenuS2C(packet.tipo, isDmzUser, compactMenu), player);
				});
			}

		});
		context.setPacketHandled(true);
	}
}
