package com.yuseix.dragonminez.common.network.C2S;

import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
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
							cap.setStringValue("groupform", packet.group);
						}
						case "teropc" -> {
							switch (cap.getIntValue("race")) {
								case 0 -> {}
								case 1 -> cap.setBoolean("tailmode", !cap.getBoolean("tailmode"));
								case 2 -> {}
								case 3 -> {
									switch (cap.getStringValue("form")) {
										case "semi_perfect" -> cap.setStringValue("form", "base");
										case "perfect" -> cap.setStringValue("form", "semi_perfect");
									}
								}
								case 4 -> {
									switch (cap.getStringValue("form")) {
										case "second_form" -> cap.setStringValue("form", "base");
										case "third_form" -> cap.setStringValue("form", "second_form");
										case "final_form" -> cap.setStringValue("form", "third_form");
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
