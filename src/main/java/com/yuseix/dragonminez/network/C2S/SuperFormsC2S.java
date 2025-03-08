package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.Locale;
import java.util.function.Supplier;


public class SuperFormsC2S {
	private String skill;
	private int value;

	public SuperFormsC2S(String skill, int value) {
		this.skill = skill;
		this.value = value;
	}

	public SuperFormsC2S(FriendlyByteBuf buf) {
		skill = buf.readUtf();
		value = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeUtf(skill);
		buf.writeInt(value);
	}

	public static void handle(SuperFormsC2S packet, Supplier<NetworkEvent.Context> crx) {
		NetworkEvent.Context context = crx.get();
		context.enqueueWork(() -> {
			ServerPlayer player = crx.get().getSender();

			if (player != null) {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					switch (packet.skill.toLowerCase(Locale.ROOT)) {
						case "super_form":
							cap.setFormSkillLvl("super_form", packet.value);
							break;
						// Más superforms luego.
					}
				});
			}

		});
	}
}
