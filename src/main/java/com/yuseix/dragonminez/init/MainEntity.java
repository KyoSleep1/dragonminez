package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.*;
import com.yuseix.dragonminez.init.entity.custom.fpcharacters.*;
import com.yuseix.dragonminez.init.entity.custom.masters.*;
import com.yuseix.dragonminez.init.entity.custom.namek.*;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiSmallBallProjectil;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiSmallWaveProjectil;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.NappaEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.RaditzEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.SaibamanEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.VegetaEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.entity.SpawnPlacements.Type.ON_GROUND;
import static net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING;
import static net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation.REPLACE;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class MainEntity {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES_REGISTER =
			DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DragonMineZ.MOD_ID);

	//MOBS / ENTITIES
	public static final RegistryObject<EntityType<DinoEntity>> DINO1 =
			ENTITY_TYPES_REGISTER.register("dino",
					() -> EntityType.Builder.of(DinoEntity::new, MobCategory.CREATURE)
							.sized(4.5f, 4.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "dino").toString())
			);
	public static final RegistryObject<EntityType<NamekFrogEntity>> NAMEK_FROG =
			ENTITY_TYPES_REGISTER.register("namek_frog",
					() -> EntityType.Builder.of(NamekFrogEntity::new, MobCategory.CREATURE)
							.sized(0.7f, 0.7f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "namek_frog").toString())
			);
	public static final RegistryObject<EntityType<PinkFrogEntity>> PINK_FROG =
			ENTITY_TYPES_REGISTER.register("pink_frog",
					() -> EntityType.Builder.of(PinkFrogEntity::new, MobCategory.CREATURE)
							.sized(0.7f, 0.7f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "pink_frog").toString())
			);
	public static final RegistryObject<EntityType<YellowFrogEntity>> YELLOW_FROG =
			ENTITY_TYPES_REGISTER.register("yellow_frog",
					() -> EntityType.Builder.of(YellowFrogEntity::new, MobCategory.CREATURE)
							.sized(0.7f, 0.7f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "yellow_frog").toString())
			);
	public static final RegistryObject<EntityType<GinyuFrogEntity>> GINYU_FROG =
			ENTITY_TYPES_REGISTER.register("ginyu_frog",
					() -> EntityType.Builder.of(GinyuFrogEntity::new, MobCategory.CREATURE)
							.sized(0.7f, 0.7f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "ginyu_frog").toString())
			);

	public static final RegistryObject<EntityType<NamekWarriorEntity>> NAMEKNPC_WARRIOR1 =
			ENTITY_TYPES_REGISTER.register("namek_warrior01",
					() -> EntityType.Builder.of(NamekWarriorEntity::new, MobCategory.CREATURE)
							.sized(0.8f, 2.1f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "namek_warrior01").toString())
			);
	public static final RegistryObject<EntityType<NamekWarrior02Entity>> NAMEKNPC_WARRIOR2 =
			ENTITY_TYPES_REGISTER.register("namek_warrior02",
					() -> EntityType.Builder.of(NamekWarrior02Entity::new, MobCategory.CREATURE)
							.sized(0.8f, 2.1f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "namek_warrior02").toString())
			);
	public static final RegistryObject<EntityType<NamekTraderEntity>> NAMEKNPC_TRADER1 =
			ENTITY_TYPES_REGISTER.register("namek_trader01",
					() -> EntityType.Builder.of(NamekTraderEntity::new, MobCategory.CREATURE)
							.sized(0.8f, 2.1f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "namek_trader01").toString())
			);
	public static final RegistryObject<EntityType<NamekTrader02Entity>> NAMEKNPC_TRADER2 =
			ENTITY_TYPES_REGISTER.register("namek_trader02",
					() -> EntityType.Builder.of(NamekTrader02Entity::new, MobCategory.CREATURE)
							.sized(0.8f, 2.1f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "namek_trader02").toString())
			);
	public static final RegistryObject<EntityType<NamekTrader03Entity>> NAMEKNPC_TRADER3 =
			ENTITY_TYPES_REGISTER.register("namek_trader03",
					() -> EntityType.Builder.of(NamekTrader03Entity::new, MobCategory.CREATURE)
							.sized(0.8f, 2.1f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "namek_trader03").toString())
			);
	public static final RegistryObject<EntityType<FriezaSoldierEntity>> FRIEZA_SOLDIER01 =
			ENTITY_TYPES_REGISTER.register("soldier01",
					() -> EntityType.Builder.of(FriezaSoldierEntity::new, MobCategory.CREATURE)
							.sized(0.8f, 2.1f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "soldier01").toString())
			);
	public static final RegistryObject<EntityType<FriezaSoldier02Entity>> FRIEZA_SOLDIER02 =
			ENTITY_TYPES_REGISTER.register("soldier02",
					() -> EntityType.Builder.of(FriezaSoldier02Entity::new, MobCategory.CREATURE)
							.sized(0.8f, 2.1f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "soldier02").toString())
			);
	public static final RegistryObject<EntityType<FriezaSoldier03Entity>> FRIEZA_SOLDIER03 =
			ENTITY_TYPES_REGISTER.register("soldier03",
					() -> EntityType.Builder.of(FriezaSoldier03Entity::new, MobCategory.CREATURE)
							.sized(0.8f, 2.1f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "soldier03").toString())
			);
	public static final RegistryObject<EntityType<MoroSoldierEntity>> MORO_SOLDIER =
			ENTITY_TYPES_REGISTER.register("moro_soldier",
					() -> EntityType.Builder.of(MoroSoldierEntity::new, MobCategory.CREATURE)
							.sized(0.8f, 2.1f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "moro_soldier").toString())
			);
	public static final RegistryObject<EntityType<NaveSaiyanEntity>> NAVE_SAIYAN =
			ENTITY_TYPES_REGISTER.register("nave_saiyan",
					() -> EntityType.Builder.of(NaveSaiyanEntity::new, MobCategory.CREATURE)
							.sized(1.3f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "nave_saiyan").toString())
			);
	//NPCS
	public static final RegistryObject<EntityType<KarinEntity>> MASTER_KARIN =
			ENTITY_TYPES_REGISTER.register("karin",
					() -> EntityType.Builder.of(KarinEntity::new, MobCategory.CREATURE)
							.sized(0.7f, 0.7f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "karin").toString())
			);
	public static final RegistryObject<EntityType<DendeEntity>> MASTER_DENDE =
			ENTITY_TYPES_REGISTER.register("dende",
					() -> EntityType.Builder.of(DendeEntity::new, MobCategory.CREATURE)
							.sized(0.6f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "dende").toString())
			);
	public static final RegistryObject<EntityType<RoshiEntity>> MASTER_ROSHI =
			ENTITY_TYPES_REGISTER.register("roshi",
					() -> EntityType.Builder.of(RoshiEntity::new, MobCategory.CREATURE)
							.sized(0.6f, 1.5f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "roshi").toString())
			);
	public static final RegistryObject<EntityType<GokuMasterEntity>> MASTER_GOKU =
			ENTITY_TYPES_REGISTER.register("master_goku",
					() -> EntityType.Builder.of(GokuMasterEntity::new, MobCategory.CREATURE)
							.sized(0.6f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "master_goku").toString())
			);
	public static final RegistryObject<EntityType<KaiosamaEntity>> MASTER_KAIOSAMA =
			ENTITY_TYPES_REGISTER.register("master_kaiosama",
					() -> EntityType.Builder.of(KaiosamaEntity::new, MobCategory.CREATURE)
							.sized(0.6f, 1.5f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "master_kaiosama").toString())
			);
	public static final RegistryObject<EntityType<EnmaEntity>> ENMA =
			ENTITY_TYPES_REGISTER.register("enma",
					() -> EntityType.Builder.of(EnmaEntity::new, MobCategory.CREATURE)
							.sized(1.0f, 2.5f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "enma").toString())
			);
	public static final RegistryObject<EntityType<UranaiEntity>> URANAI =
			ENTITY_TYPES_REGISTER.register("uranaibaba",
					() -> EntityType.Builder.of(UranaiEntity::new, MobCategory.CREATURE)
							.sized(0.6f, 1.5f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "uranaibaba").toString())
			);
	public static final RegistryObject<EntityType<GuruEntity>> GURU =
			ENTITY_TYPES_REGISTER.register("guru",
					() -> EntityType.Builder.of(GuruEntity::new, MobCategory.CREATURE)
							.sized(1.2f, 2.5f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "guru").toString())
			);
	public static final RegistryObject<EntityType<NubeEntity>> NUBE_VOLADORA =
			ENTITY_TYPES_REGISTER.register("nube",
					() -> EntityType.Builder.of(NubeEntity::new, MobCategory.AMBIENT)
							.sized(1.2f, 1.2f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "nube").toString())
			);
	public static final RegistryObject<EntityType<NubeNegraEntity>> NUBE_NEGRA =
			ENTITY_TYPES_REGISTER.register("nube_negra",
					() -> EntityType.Builder.of(NubeNegraEntity::new, MobCategory.AMBIENT)
							.sized(1.2f, 1.2f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "nube_negra").toString())
			);
	public static final RegistryObject<EntityType<ShenlongEntity>> SHENLONG =
			ENTITY_TYPES_REGISTER.register("shenlong",
					() -> EntityType.Builder.of(ShenlongEntity::new, MobCategory.AMBIENT)
							.sized(2.5f, 20f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "shenlong").toString())
			);
	public static final RegistryObject<EntityType<PorungaEntity>> PORUNGA =
			ENTITY_TYPES_REGISTER.register("porunga",
					() -> EntityType.Builder.of(PorungaEntity::new, MobCategory.AMBIENT)
							.sized(2.5f, 20f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "porunga").toString())
			);

	//SAGAS
	public static final RegistryObject<EntityType<RaditzEntity>> RADITZ_SAGA =
			ENTITY_TYPES_REGISTER.register("saga_raditz",
					() -> EntityType.Builder.of(RaditzEntity::new, MobCategory.CREATURE)
							.sized(0.6f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "saga_raditz").toString())
			);
	public static final RegistryObject<EntityType<NappaEntity>> NAPPA_SAGA =
			ENTITY_TYPES_REGISTER.register("saga_nappa",
					() -> EntityType.Builder.of(NappaEntity::new, MobCategory.CREATURE)
							.sized(0.8f, 2.2f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "saga_nappa").toString())
			);
	public static final RegistryObject<EntityType<SaibamanEntity>> SAIBAMAN =
			ENTITY_TYPES_REGISTER.register("saibaman",
					() -> EntityType.Builder.of(SaibamanEntity::new, MobCategory.CREATURE)
							.sized(0.5f, 1.6f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "saibaman").toString())
			);
	public static final RegistryObject<EntityType<SaibamanEntity>> KAIWAREMAN =
			ENTITY_TYPES_REGISTER.register("kaiwareman",
					() -> EntityType.Builder.of(SaibamanEntity::new, MobCategory.CREATURE)
							.sized(0.5f, 1.6f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "kaiwareman").toString())
			);
	public static final RegistryObject<EntityType<SaibamanEntity>> KYUKONMAN =
			ENTITY_TYPES_REGISTER.register("kyukonman",
					() -> EntityType.Builder.of(SaibamanEntity::new, MobCategory.CREATURE)
							.sized(0.5f, 1.6f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "kyukonman").toString())
			);
	public static final RegistryObject<EntityType<SaibamanEntity>> COPYMAN =
			ENTITY_TYPES_REGISTER.register("copyman",
					() -> EntityType.Builder.of(SaibamanEntity::new, MobCategory.CREATURE)
							.sized(0.5f, 1.6f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "copyman").toString())
			);
	public static final RegistryObject<EntityType<SaibamanEntity>> TENNENMAN =
			ENTITY_TYPES_REGISTER.register("tennenman",
					() -> EntityType.Builder.of(SaibamanEntity::new, MobCategory.CREATURE)
							.sized(0.5f, 1.6f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "tennenman").toString())
			);
	public static final RegistryObject<EntityType<SaibamanEntity>> JINKOUMAN =
			ENTITY_TYPES_REGISTER.register("jinkouman",
					() -> EntityType.Builder.of(SaibamanEntity::new, MobCategory.CREATURE)
							.sized(0.5f, 1.6f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "jinkouman").toString())
			);
	public static final RegistryObject<EntityType<VegetaEntity>> VEGETA_SAIYAN =
			ENTITY_TYPES_REGISTER.register("saga_vegetasaiyan",
					() -> EntityType.Builder.of(VegetaEntity::new, MobCategory.CREATURE)
							.sized(0.6f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "saga_vegetasaiyan").toString())
			);
	//FAKEPLAYERS
	public static final RegistryObject<EntityType<FPBioAndroidEntity>> FP_BIOANDROIDE =
			ENTITY_TYPES_REGISTER.register("fp_bioandroide",
					() -> EntityType.Builder.of(FPBioAndroidEntity::new, MobCategory.MISC)
							.sized(1.5f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "fp_bioandroide").toString())
			);
	public static final RegistryObject<EntityType<FPDemonColdEntity>> FP_DEMONCOLD =
			ENTITY_TYPES_REGISTER.register("fp_demoncold",
					() -> EntityType.Builder.of(FPDemonColdEntity::new, MobCategory.MISC)
							.sized(1.5f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "fp_demoncold").toString())
			);
	public static final RegistryObject<EntityType<FPHumanSaiyanEntity>> FP_HUMANSAIYAN =
			ENTITY_TYPES_REGISTER.register("fp_humansaiyan",
					() -> EntityType.Builder.of(FPHumanSaiyanEntity::new, MobCategory.MISC)
							.sized(1.5f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "fp_humansaiyan").toString())
			);
	public static final RegistryObject<EntityType<FPMajinGordEntity>> FP_MAJINGORDO =
			ENTITY_TYPES_REGISTER.register("fp_majingordo",
					() -> EntityType.Builder.of(FPMajinGordEntity::new, MobCategory.MISC)
							.sized(1.5f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "fp_majingordo").toString())
			);
	public static final RegistryObject<EntityType<FPNamekianEntity>> FP_NAMEK =
			ENTITY_TYPES_REGISTER.register("fp_namek",
					() -> EntityType.Builder.of(FPNamekianEntity::new, MobCategory.MISC)
							.sized(1.5f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "fp_namek").toString())
			);
	public static final RegistryObject<EntityType<FPSlimEntity>> FP_SLIMSAIYANHUM =
			ENTITY_TYPES_REGISTER.register("fp_slim",
					() -> EntityType.Builder.of(FPSlimEntity::new, MobCategory.MISC)
							.sized(1.5f, 1.8f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "fp_slim").toString())
			);
	//Ki
	public static final RegistryObject<EntityType<KiSmallBallProjectil>> KI_SMALL_BLAST =
			ENTITY_TYPES_REGISTER.register("ki_smallball",
					() -> EntityType.Builder.of(KiSmallBallProjectil::new, MobCategory.MISC)
							.sized(0.9f, 0.9f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "ki_smallball").toString())
			);
	public static final RegistryObject<EntityType<KiSmallWaveProjectil>> KI_SMALL_WAVE =
			ENTITY_TYPES_REGISTER.register("ki_smallwave",
					() -> EntityType.Builder.of(KiSmallWaveProjectil::new, MobCategory.MISC)
							.sized(0.9f, 0.9f)
							.build(new ResourceLocation(DragonMineZ.MOD_ID, "ki_smallwave").toString())
			);
	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent e) {
		e.register(MainEntity.DINO1.get(),
				ON_GROUND,
				MOTION_BLOCKING,
				DinoEntity::checkDinoSpawnRules,
				REPLACE);
		e.register(MainEntity.FRIEZA_SOLDIER01.get(),
				ON_GROUND,
				MOTION_BLOCKING,
				SoldierEntity::canSpawnHere,
				REPLACE);
		e.register(MainEntity.FRIEZA_SOLDIER02.get(),
				ON_GROUND,
				MOTION_BLOCKING,
				SoldierEntity::canSpawnHere,
				REPLACE);
		e.register(MainEntity.FRIEZA_SOLDIER03.get(),
				ON_GROUND,
				MOTION_BLOCKING,
				SoldierEntity::canSpawnHere,
				REPLACE);
		e.register(MainEntity.MORO_SOLDIER.get(),
				ON_GROUND,
				MOTION_BLOCKING,
				SoldierEntity::canSpawnHere,
				REPLACE);
	}

	public static void register(IEventBus bus) {
		ENTITY_TYPES_REGISTER.register(bus);
	}
}
