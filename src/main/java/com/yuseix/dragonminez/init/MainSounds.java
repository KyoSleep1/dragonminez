package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MainSounds {

	public static final DeferredRegister<SoundEvent> SOUND_EVENTS_REGISTER =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DragonMineZ.MOD_ID);

	public static final RegistryObject<SoundEvent> GOLPE1 = registerSoundEvent("punch1");
	public static final RegistryObject<SoundEvent> GOLPE2 = registerSoundEvent("punch2");
	public static final RegistryObject<SoundEvent> GOLPE3 = registerSoundEvent("punch3");
	public static final RegistryObject<SoundEvent> GOLPE4 = registerSoundEvent("punch4");
	public static final RegistryObject<SoundEvent> GOLPE5 = registerSoundEvent("punch5");
	public static final RegistryObject<SoundEvent> GOLPE6 = registerSoundEvent("punch6");
	public static final RegistryObject<SoundEvent> CRITICO1 = registerSoundEvent("critic_punch1");
	public static final RegistryObject<SoundEvent> CRITICO2 = registerSoundEvent("critic_punch2");

	public static final RegistryObject<SoundEvent> DRAGONRADAR = registerSoundEvent("dragonradar");
	public static final RegistryObject<SoundEvent> NUBE = registerSoundEvent("nube");
	public static final RegistryObject<SoundEvent> SENZU_BEAN = registerSoundEvent("senzu");
	public static final RegistryObject<SoundEvent> DRAGONBALLS = registerSoundEvent("dragonballssound");
	public static final RegistryObject<SoundEvent> SHENRON = registerSoundEvent("shenron");

	public static final RegistryObject<SoundEvent> NAVE_OPEN = registerSoundEvent("ship_open");
	public static final RegistryObject<SoundEvent> NAVE_LANDING_OPEN = registerSoundEvent("ship_landing_open");

	public static final RegistryObject<SoundEvent> FROG1 = registerSoundEvent("frogsound1");
	public static final RegistryObject<SoundEvent> FROG2 = registerSoundEvent("frogsound2");
	public static final RegistryObject<SoundEvent> FROG3 = registerSoundEvent("frogsound3");
	public static final RegistryObject<SoundEvent> FROG_LAUGH = registerSoundEvent("froglaugh");

	public static final RegistryObject<SoundEvent> MENU_MUSIC = registerSoundEvent("menu_music");

	public static final RegistryObject<SoundEvent> AURA_START = registerSoundEvent("aura_start");
	public static final RegistryObject<SoundEvent> KI_CHARGE_LOOP = registerSoundEvent("ki_charge_loop");
	public static final RegistryObject<SoundEvent> TURBO_LOOP = registerSoundEvent("turbo_loop");

	public static final RegistryObject<SoundEvent> UI_MENU_SWITCH = registerSoundEvent("ui_menu_switch");
	public static final RegistryObject<SoundEvent> UI_NAVE_COOLDOWN = registerSoundEvent("ui_nave_cooldown");
	public static final RegistryObject<SoundEvent> UI_NAVE_TAKEOFF = registerSoundEvent("ui_nave_takeoff");

	public static final RegistryObject<SoundEvent> FRIEZA_SOLDIER_AMBIENT = registerSoundEvent("entity.frieza.s.ambient");
	public static final RegistryObject<SoundEvent> FRIEZA_SOLDIER_HURT = registerSoundEvent("entity.frieza.s.hurt");
	public static final RegistryObject<SoundEvent> FRIEZA_SOLDIER_DEATH = registerSoundEvent("entity.friezas.s.death");
	public static final RegistryObject<SoundEvent> FRIEZA_SOLDIER_ATTACK = registerSoundEvent("entity.friezas.s.attack");

	public static final RegistryObject<SoundEvent> NAMEKIAN_VILLAGER_AMBIENT = registerSoundEvent("entity.namekian.vill.ambient");
	public static final RegistryObject<SoundEvent> NAMEKIAN_VILLAGER_HURT = registerSoundEvent("entity.namekian.vill.hurt");
	public static final RegistryObject<SoundEvent> NAMEKIAN_VILLAGER_DEATH = registerSoundEvent("entity.namekian.vill.death");

	public static final RegistryObject<SoundEvent> KIBLAST_ATTACK = registerSoundEvent("kiblast_shoot");

	private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
		ResourceLocation id = new ResourceLocation(DragonMineZ.MOD_ID, name);

		return SOUND_EVENTS_REGISTER.register(name, () -> SoundEvent.createVariableRangeEvent(id));
	}

	public static void register(IEventBus busEvent) {
		SOUND_EVENTS_REGISTER.register(busEvent);
	}
}
