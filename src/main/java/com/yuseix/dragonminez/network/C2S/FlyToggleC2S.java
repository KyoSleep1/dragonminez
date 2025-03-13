package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.FlyToggleS2C;
import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import com.yuseix.dragonminez.common.stats.skills.DMZSkill;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FlyToggleC2S {

	public static void encode(FlyToggleC2S msg, FriendlyByteBuf buf) {
	}

	public static FlyToggleC2S decode(FriendlyByteBuf buf) {
		return new FlyToggleC2S();
	}

	public static void handle(FlyToggleC2S msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ServerPlayer player = ctx.get().getSender();
			if (player != null) {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					DMZSkill flySkill = cap.getDMZSkills().get("fly");

					if (flySkill != null) {
						int flyLevel = flySkill.getLevel();
						if (flyLevel > 0) {
							if (!flySkill.isActive()) {
								if (!player.isCreative() && !player.isSpectator()) player.getAbilities().mayfly = true;
								player.getAbilities().flying = true;
								flySkill.setActive(true);
							} else {
								if (!player.isCreative() && !player.isSpectator()) player.getAbilities().mayfly = false;
								player.getAbilities().flying = false;
								player.fallDistance = 0;
								flySkill.setActive(false);
							}

							player.onUpdateAbilities();
							ModMessages.sendToPlayer(new FlyToggleS2C(flySkill.isActive()), player);
						}
					}
				});
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
