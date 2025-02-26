package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UtilityPanelC2S {
	private String tipo;
	private String group;

	public UtilityPanelC2S(String tipo, String group) {
		this.tipo = tipo;
		this.group = group;
	}

	public UtilityPanelC2S(FriendlyByteBuf buf) {
		tipo = buf.readUtf();
		group = buf.readUtf();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeUtf(tipo);
		buf.writeUtf(group);
	}

	public static void handle(UtilityPanelC2S packet, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		context.enqueueWork(() -> {
			ServerPlayer player = ctx.get().getSender();

			if (player != null) {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					switch (packet.tipo) {
						case "group" -> {
							cap.setDmzGroupForm(packet.group);
						}
						case "teropc" -> {
							switch (cap.getRace()) {
								case 0 -> {}
								case 1 -> cap.setTailMode(!cap.isTailMode());
								case 2 -> {}
								case 3 -> {
									switch (cap.getDmzForm()) {
										case "semi_perfect" -> cap.setDmzForm("base");
										case "perfect" -> cap.setDmzForm("semi_perfect");
									}
								}
								case 4 -> {
									switch (cap.getDmzForm()) {
										case "second_form" -> cap.setDmzForm("base");
										case "third_form" -> cap.setDmzForm("second_form");
										case "final_form" -> cap.setDmzForm("third_form");
									}
								}
								case 5 -> {}
							}
						}
					}
				});
			}
		});
	}
}
