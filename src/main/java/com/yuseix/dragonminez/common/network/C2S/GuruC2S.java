package com.yuseix.dragonminez.common.network.C2S;

import com.yuseix.dragonminez.common.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import com.yuseix.dragonminez.common.stats.skills.DMZSkill;
import com.yuseix.dragonminez.common.util.DMZDatos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GuruC2S {
	private int option;

	public GuruC2S(int option) {
		this.option = option;
	}
	public GuruC2S(FriendlyByteBuf buf) {
		option = buf.readInt();

	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(option);

	}
	public static void handle(GuruC2S packet, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		context.enqueueWork(() -> {

			ServerPlayer player = ctx.get().getSender();

			if (player != null) {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
					if(packet.option == 1){
						DMZSkill skill = new DMZSkill("dmz.skill.potential_unlock.name", "dmz.skill.potential_unlock.desc", 11, true);
						playerstats.addSkill("potential_unlock", skill);
					} else if(packet.option == 2) {
						healPlayer(player, playerstats);
					}
				});
			}
		});
		context.setPacketHandled(true);
	}

	private static void healPlayer(ServerPlayer player, DMZStatsAttributes playerstats) {
		DMZDatos dmzdatos = new DMZDatos();

		player.displayClientMessage(Component.translatable("lines.dende.heal.success"), true);

		double vidaTotal = dmzdatos.calcConstitution(playerstats);
		int energiaMax = dmzdatos.calcEnergy(playerstats);
		int staminaMax = dmzdatos.calcStamina(playerstats);

		player.heal((float) vidaTotal);
		playerstats.setIntValue("curstam", staminaMax);
		playerstats.setIntValue("curenergy", energiaMax);

		player.getFoodData().setFoodLevel(20);
		player.getFoodData().setSaturation(15.0F);
	}
}
