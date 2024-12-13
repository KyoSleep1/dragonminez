package com.yuseix.dragonminez.network;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.network.C2S.*;
import com.yuseix.dragonminez.network.S2C.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

	public static SimpleChannel INSTANCE;
	private static int packetID = 0;

	private static int id() {
		return packetID++;
	}

	public static void register() {
		SimpleChannel net = NetworkRegistry.ChannelBuilder
				.named(new ResourceLocation(DragonMineZ.MOD_ID, "messagesold"))
				.networkProtocolVersion(() -> "1.0")
				.clientAcceptedVersions(s -> true)
				.serverAcceptedVersions(s -> true)
				.simpleChannel();

		INSTANCE = net;


		//ENVIAR DATOS AL SERVIDOR
		net.messageBuilder(StatsC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(StatsC2S::new)
				.encoder(StatsC2S::toBytes)
				.consumerMainThread(StatsC2S::handle)
				.add();
		net.messageBuilder(ZPointsC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(ZPointsC2S::new)
				.encoder(ZPointsC2S::toBytes)
				.consumerMainThread(ZPointsC2S::handle)
				.add();
		net.messageBuilder(CharacterC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(CharacterC2S::new)
				.encoder(CharacterC2S::toBytes)
				.consumerMainThread(CharacterC2S::handle)
				.add();
		net.messageBuilder(MenuC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(MenuC2S::new)
				.encoder(MenuC2S::toBytes)
				.consumerMainThread(MenuC2S::handle)
				.add();
		net.messageBuilder(KarinC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(KarinC2S::new)
				.encoder(KarinC2S::toBytes)
				.consumerMainThread(KarinC2S::handle)
				.add();
		net.messageBuilder(DendeC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(DendeC2S::new)
				.encoder(DendeC2S::toBytes)
				.consumerMainThread(DendeC2S::handle)
				.add();
		net.messageBuilder(ShenlongC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(ShenlongC2S::new)
				.encoder(ShenlongC2S::toBytes)
				.consumerMainThread(ShenlongC2S::handle)
				.add();
		net.messageBuilder(PorungaC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(PorungaC2S::new)
				.encoder(PorungaC2S::toBytes)
				.consumerMainThread(PorungaC2S::handle)
				.add();
		net.messageBuilder(SpacePodC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(SpacePodC2S::decode)
				.encoder(SpacePodC2S::encode)
				.consumerMainThread(SpacePodC2S::handle)
				.add();
		net.messageBuilder(PlanetSelectionC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(PlanetSelectionC2S::decode)
				.encoder(PlanetSelectionC2S::encode)
				.consumerMainThread(PlanetSelectionC2S::handle)
				.add();

		//ENVIAR DATOS AL CLIENTE
		net.messageBuilder(ZPointsS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.decoder(ZPointsS2C::new)
				.encoder(ZPointsS2C::toBytes)
				.consumerMainThread(ZPointsS2C::handle)
				.add();
		net.messageBuilder(StatsSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.decoder(StatsSyncS2C::new)
				.encoder(StatsSyncS2C::toBytes)
				.consumerMainThread(StatsSyncS2C::handle)
				.add();
		net.messageBuilder(MenuS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.decoder(MenuS2C::new)
				.encoder(MenuS2C::toBytes)
				.consumerMainThread(MenuS2C::handle)
				.add();
		net.messageBuilder(DMZTempEffectsS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.encoder(DMZTempEffectsS2C::toBytes)
				.decoder(DMZTempEffectsS2C::new)
				.consumerMainThread(DMZTempEffectsS2C::handle)
				.add();
		net.messageBuilder(DMZSkillsS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.encoder(DMZSkillsS2C::toBytes)
				.decoder(DMZSkillsS2C::new)
				.consumerMainThread(DMZSkillsS2C::handle)
				.add();
		net.messageBuilder(DMZPermanentEffectsSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.encoder(DMZPermanentEffectsSyncS2C::toBytes)
				.decoder(DMZPermanentEffectsSyncS2C::new)
				.consumerMainThread(DMZPermanentEffectsSyncS2C::handle)
				.add();
		net.messageBuilder(KaioPlanetUnlockS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.encoder(KaioPlanetUnlockS2C::encode)
				.decoder(KaioPlanetUnlockS2C::decode)
				.consumerMainThread(KaioPlanetUnlockS2C::handle)
				.add();
		net.messageBuilder(UpdatePlanetSelectionS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.encoder(UpdatePlanetSelectionS2C::encode)
				.decoder(UpdatePlanetSelectionS2C::decode)
				.consumerMainThread(UpdatePlanetSelectionS2C::handle)
				.add();
	}

	//Manda un paquete al Servidor (message no es un mensaje, es un paquete)
	public static <MSG> void sendToServer(MSG message) {
		INSTANCE.sendToServer(message);
	}

	//Manda un paquete a un jugador (message no es un mensaje, es un paquete)
	public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
		INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
	}

	public static <MSG> void sendToClients(MSG message) {
		INSTANCE.send(PacketDistributor.ALL.noArg(), message);
	}

	public static <MSG> void sendToAll(Player player, MSG message) {
		INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), message);
	}
}
