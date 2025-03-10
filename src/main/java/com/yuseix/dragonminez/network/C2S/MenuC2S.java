package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.config.races.*;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.MenuS2C;
import com.yuseix.dragonminez.network.S2C.PacketSyncConfig;
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

					boolean isDmzUser = playerstats.getBoolean("dmzuser");
					boolean compactMenu = playerstats.getBoolean("compactmenu");

					ModMessages.sendToPlayer(new PacketSyncConfig("maxattributes", DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("zpoints_cost", DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("majin_multiplier", DMZGeneralConfig.MULTIPLIER_MAJIN.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("tree_might_multiplier", DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get()), player);
					//WARRIOR
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_human_warrior", DMZHumanConfig.INITIAL_STR_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_saiyan_warrior", DMZSaiyanConfig.INITIAL_STR_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_namek_warrior", DMZNamekConfig.INITIAL_STR_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_bio_warrior", DMZBioAndroidConfig.INITIAL_STR_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_cold_warrior", DMZColdDemonConfig.INITIAL_STR_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_majin_warrior", DMZMajinConfig.INITIAL_STR_WARRIOR.get()), player);

					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_human_warrior", DMZHumanConfig.INITIAL_DEF_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_saiyan_warrior", DMZSaiyanConfig.INITIAL_DEF_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_namek_warrior", DMZNamekConfig.INITIAL_DEF_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_bio_warrior", DMZBioAndroidConfig.INITIAL_DEF_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_cold_warrior", DMZColdDemonConfig.INITIAL_DEF_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_majin_warrior", DMZMajinConfig.INITIAL_DEF_WARRIOR.get()), player);

					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_human_warrior", DMZHumanConfig.INITIAL_CON_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_saiyan_warrior", DMZSaiyanConfig.INITIAL_CON_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_namek_warrior", DMZNamekConfig.INITIAL_CON_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_bio_warrior", DMZBioAndroidConfig.INITIAL_CON_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_cold_warrior", DMZColdDemonConfig.INITIAL_CON_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_majin_warrior", DMZMajinConfig.INITIAL_CON_WARRIOR.get()), player);

					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_human_warrior", DMZHumanConfig.INITIAL_KIPWR_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_saiyan_warrior", DMZSaiyanConfig.INITIAL_KIPWR_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_namek_warrior", DMZNamekConfig.INITIAL_KIPWR_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_bio_warrior", DMZBioAndroidConfig.INITIAL_KIPWR_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_cold_warrior", DMZColdDemonConfig.INITIAL_KIPWR_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_majin_warrior", DMZMajinConfig.INITIAL_KIPWR_WARRIOR.get()), player);

					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_human_warrior", DMZHumanConfig.INITIAL_ENE_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_saiyan_warrior", DMZSaiyanConfig.INITIAL_ENE_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_namek_warrior", DMZNamekConfig.INITIAL_ENE_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_bio_warrior", DMZBioAndroidConfig.INITIAL_ENE_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_cold_warrior", DMZColdDemonConfig.INITIAL_ENE_WARRIOR.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_majin_warrior", DMZMajinConfig.INITIAL_ENE_WARRIOR.get()), player);
					//SPIRITUALIST
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_human_spiritualist", DMZHumanConfig.INITIAL_STR_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_saiyan_spiritualist", DMZSaiyanConfig.INITIAL_STR_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_namek_spiritualist", DMZNamekConfig.INITIAL_STR_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_bio_spiritualist", DMZBioAndroidConfig.INITIAL_STR_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_cold_spiritualist", DMZColdDemonConfig.INITIAL_STR_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_str_majin_spiritualist", DMZMajinConfig.INITIAL_STR_SPIRITUALIST.get()), player);

					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_human_spiritualist", DMZHumanConfig.INITIAL_DEF_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_saiyan_spiritualist", DMZSaiyanConfig.INITIAL_DEF_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_namek_spiritualist", DMZNamekConfig.INITIAL_DEF_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_bio_spiritualist", DMZBioAndroidConfig.INITIAL_DEF_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_cold_spiritualist", DMZColdDemonConfig.INITIAL_DEF_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_def_majin_spiritualist", DMZMajinConfig.INITIAL_DEF_SPIRITUALIST.get()), player);

					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_human_spiritualist", DMZHumanConfig.INITIAL_CON_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_saiyan_spiritualist", DMZSaiyanConfig.INITIAL_CON_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_namek_spiritualist", DMZNamekConfig.INITIAL_CON_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_bio_spiritualist", DMZBioAndroidConfig.INITIAL_CON_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_cold_spiritualist", DMZColdDemonConfig.INITIAL_CON_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_con_majin_spiritualist", DMZMajinConfig.INITIAL_CON_SPIRITUALIST.get()), player);

					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_human_spiritualist", DMZHumanConfig.INITIAL_KIPWR_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_saiyan_spiritualist", DMZSaiyanConfig.INITIAL_KIPWR_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_namek_spiritualist", DMZNamekConfig.INITIAL_KIPWR_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_bio_spiritualist", DMZBioAndroidConfig.INITIAL_KIPWR_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_cold_spiritualist", DMZColdDemonConfig.INITIAL_KIPWR_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_pwr_majin_spiritualist", DMZMajinConfig.INITIAL_KIPWR_SPIRITUALIST.get()), player);

					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_human_spiritualist", DMZHumanConfig.INITIAL_ENE_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_saiyan_spiritualist", DMZSaiyanConfig.INITIAL_ENE_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_namek_spiritualist", DMZNamekConfig.INITIAL_ENE_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_bio_spiritualist", DMZBioAndroidConfig.INITIAL_ENE_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_cold_spiritualist", DMZColdDemonConfig.INITIAL_ENE_SPIRITUALIST.get()), player);
					ModMessages.sendToPlayer(new PacketSyncConfig("ini_ene_majin_spiritualist", DMZMajinConfig.INITIAL_ENE_SPIRITUALIST.get()), player);

					ModMessages.sendToPlayer(new MenuS2C(packet.tipo, isDmzUser, compactMenu), player);
				});
			}

		});
		context.setPacketHandled(true);
	}
}
