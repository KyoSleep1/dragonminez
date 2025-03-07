package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.*;
import com.yuseix.dragonminez.init.entity.custom.fpcharacters.*;
import com.yuseix.dragonminez.init.entity.custom.masters.*;
import com.yuseix.dragonminez.init.entity.custom.namek.*;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.NappaEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.RaditzEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.SaibamanEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.VegetaEntity;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import com.yuseix.dragonminez.world.NamekDragonBallGenProvider;
import com.yuseix.dragonminez.world.StructuresCapability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

	@SubscribeEvent
	public void entityAttributeEvent(EntityAttributeCreationEvent event) {
		event.put(MainEntity.DINO1.get(), DinoEntity.setAttributes());
		event.put(MainEntity.NUBE_VOLADORA.get(), NubeEntity.createAttributes());
		event.put(MainEntity.NUBE_NEGRA.get(), NubeNegraEntity.createAttributes());
		event.put(MainEntity.SHENLONG.get(), ShenlongEntity.setAttributes());
		event.put(MainEntity.PORUNGA.get(), PorungaEntity.setAttributes());
		//MAESTROS
		event.put(MainEntity.MASTER_DENDE.get(), DendeEntity.setAttributes());
		event.put(MainEntity.MASTER_KARIN.get(), KarinEntity.setAttributes());
		event.put(MainEntity.MASTER_ROSHI.get(), RoshiEntity.setAttributes());
		event.put(MainEntity.MASTER_GOKU.get(), GokuMasterEntity.setAttributes());
		event.put(MainEntity.MASTER_KAIOSAMA.get(), KaiosamaEntity.setAttributes());
		event.put(MainEntity.ENMA.get(), EnmaEntity.setAttributes());
		event.put(MainEntity.URANAI.get(), UranaiEntity.setAttributes());
		event.put(MainEntity.GURU.get(), GuruEntity.setAttributes());

		event.put(MainEntity.NAMEK_FROG.get(), NamekFrogEntity.setAttributes());
		event.put(MainEntity.PINK_FROG.get(), PinkFrogEntity.setAttributes());
		event.put(MainEntity.YELLOW_FROG.get(), YellowFrogEntity.setAttributes());
		event.put(MainEntity.GINYU_FROG.get(), GinyuFrogEntity.setAttributes());

		event.put(MainEntity.NAMEKNPC_WARRIOR1.get(), NamekWarriorEntity.setAttributes());
		event.put(MainEntity.NAMEKNPC_WARRIOR2.get(), NamekWarrior02Entity.setAttributes());
		event.put(MainEntity.NAMEKNPC_TRADER1.get(), NamekTraderEntity.setAttributes());
		event.put(MainEntity.NAMEKNPC_TRADER2.get(), NamekTrader02Entity.setAttributes());
		event.put(MainEntity.NAMEKNPC_TRADER3.get(), NamekTrader03Entity.setAttributes());

		event.put(MainEntity.FRIEZA_SOLDIER01.get(), FriezaSoldierEntity.setAttributes());
		event.put(MainEntity.FRIEZA_SOLDIER02.get(), FriezaSoldier02Entity.setAttributes());
		event.put(MainEntity.FRIEZA_SOLDIER03.get(), FriezaSoldier03Entity.setAttributes());
		event.put(MainEntity.REDRIBBON_SOLDIER.get(), RedRibbonSoldierEntity.setAttributes());
		event.put(MainEntity.MORO_SOLDIER.get(), MoroSoldierEntity.setAttributes());
		event.put(MainEntity.NAVE_SAIYAN.get(), NaveSaiyanEntity.setAttributes());

		//SAGAS
		event.put(MainEntity.RADITZ_SAGA.get(), RaditzEntity.setAttributes());
		event.put(MainEntity.NAPPA_SAGA.get(), NappaEntity.setAttributes());
		event.put(MainEntity.SAIBAMAN.get(), SaibamanEntity.setAttributes());
		event.put(MainEntity.KAIWAREMAN.get(), SaibamanEntity.setAttributes());
		event.put(MainEntity.KYUKONMAN.get(), SaibamanEntity.setAttributes());
		event.put(MainEntity.COPYMAN.get(), SaibamanEntity.setAttributes());
		event.put(MainEntity.TENNENMAN.get(), SaibamanEntity.setAttributes());
		event.put(MainEntity.JINKOUMAN.get(), SaibamanEntity.setAttributes());

		event.put(MainEntity.VEGETA_SAIYAN.get(), VegetaEntity.setAttributes());

		//FAKEPLAYERS
		event.put(MainEntity.FP_BIOANDROIDE.get(), FPBioAndroidEntity.setAttributes());
		event.put(MainEntity.FP_DEMONCOLD.get(), FPDemonColdEntity.setAttributes());
		event.put(MainEntity.FP_HUMANSAIYAN.get(), FPHumanSaiyanEntity.setAttributes());
		event.put(MainEntity.FP_SLIMSAIYANHUM.get(), FPSlimEntity.setAttributes());
		event.put(MainEntity.FP_NAMEK.get(), FPNamekianEntity.setAttributes());
		event.put(MainEntity.FP_MAJINGORDO.get(), FPMajinGordEntity.setAttributes());

	}

	@SubscribeEvent
	public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(DragonBallGenProvider.class);
		event.register(NamekDragonBallGenProvider.class);
		event.register(StructuresCapability.class);
	}

}
