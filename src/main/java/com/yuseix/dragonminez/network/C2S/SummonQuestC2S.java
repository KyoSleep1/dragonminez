package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.NappaEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.RaditzEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.SaibamanEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.VegetaEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SummonQuestC2S {
	private final String questId;

	public SummonQuestC2S(String questId) {
		this.questId = questId;
	}

	public SummonQuestC2S(FriendlyByteBuf buf) {
		this.questId = buf.readUtf();
	}

	public void encode(FriendlyByteBuf buf) {
		buf.writeUtf(questId);
	}

	public static void handle(SummonQuestC2S msg, Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			ServerPlayer player = context.get().getSender();
			if (player != null) {
				ServerLevel world = player.serverLevel();
				String entityName = "";
				switch(msg.questId) {
					case "saiyQuest2" -> {
						BlockPos spawnPos = player.blockPosition().offset(4, 3,4);
						RaditzEntity raditz = new RaditzEntity(MainEntity.RADITZ_SAGA.get(), world);
						raditz.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						world.addFreshEntity(raditz);
						entityName = "Raditz";
					}
					case "saiyQuest3" -> {
						BlockPos spawnPos = player.blockPosition().offset(4, 3,4);
						SaibamanEntity saibaman = new SaibamanEntity(MainEntity.SAIBAMAN.get(), world);
						SaibamanEntity kaiwareman = new SaibamanEntity(MainEntity.KAIWAREMAN.get(), world);
						SaibamanEntity kyukonman = new SaibamanEntity(MainEntity.KYUKONMAN.get(), world);
						SaibamanEntity copyman = new SaibamanEntity(MainEntity.COPYMAN.get(), world);
						SaibamanEntity jinkouman = new SaibamanEntity(MainEntity.JINKOUMAN.get(), world);
						SaibamanEntity tennenman = new SaibamanEntity(MainEntity.TENNENMAN.get(), world);

						saibaman.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						kaiwareman.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						kyukonman.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						copyman.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						jinkouman.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						tennenman.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);

						world.addFreshEntity(saibaman);
						world.addFreshEntity(kaiwareman);
						world.addFreshEntity(kyukonman);
						world.addFreshEntity(copyman);
						world.addFreshEntity(jinkouman);
						world.addFreshEntity(tennenman);

						entityName = "Saibamen";
					}
					case "saiyQuest4" -> {
						BlockPos spawnPos = player.blockPosition().offset(4, 3,4);
						NappaEntity nappa = new NappaEntity(MainEntity.NAPPA_SAGA.get(), world);
						nappa.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						world.addFreshEntity(nappa);

						entityName = "Nappa";
					}
					case "saiyQuest5" -> {
						BlockPos spawnPos = player.blockPosition().offset(4, 3,4);
						NappaEntity nappa = new NappaEntity(MainEntity.NAPPA_SAGA.get(), world);
						nappa.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						world.addFreshEntity(nappa);

						entityName = "Nappa";
					}
					case "saiyQuest6" -> {
						BlockPos spawnPos = player.blockPosition().offset(4, 3,4);
						VegetaEntity vegeta = new VegetaEntity(MainEntity.VEGETA_SAIYAN.get(), world);
						vegeta.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						world.addFreshEntity(vegeta);

						entityName = "Vegeta";
					}
					case "saiyQuest7" -> {
						BlockPos spawnPos = player.blockPosition().offset(4, 3,4);
						VegetaEntity vegeta = new VegetaEntity(MainEntity.VEGETA_SAIYAN.get(), world);
						vegeta.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						world.addFreshEntity(vegeta);

						entityName = "Vegeta";
					}
					case "saiyQuest8" -> {
						BlockPos spawnPos = player.blockPosition().offset(4, 3,4);
						VegetaEntity vegeta = new VegetaEntity(MainEntity.VEGETA_SAIYAN.get(), world);
						vegeta.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
						world.addFreshEntity(vegeta);

						entityName = "Vegeta";
					}
				}
				player.sendSystemMessage(Component.translatable("dmz.storyline.summoned", msg.questId, entityName));
			}
		});
		context.get().setPacketHandled(true);
	}
}
