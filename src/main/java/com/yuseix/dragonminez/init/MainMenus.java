package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.menus.menutypes.KarinEntityMenuType;
import com.yuseix.dragonminez.init.menus.menutypes.KikonoArmorStationMenuType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MainMenus {

	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, DragonMineZ.MOD_ID);

	//############################################################################################################
	//GENERADOR DE MENUS SIMPLES (SIN FriendlyByteBuf)
	//############################################################################################################
	public static final RegistryObject<MenuType<KarinEntityMenuType>> KARIN_ENTITY_MENU = MENUS.register("karin_entity_menu",
			() -> new MenuType<>(KarinEntityMenuType::new, FeatureFlags.DEFAULT_FLAGS));

	//############################################################################################################
	//GENERADOR DE MENUS CON MÁS INFORMACIÓN (ADICIONAL AL MENU SIMPLE, ESTE TIENE UN FriendlyByteBuf PARA ENVIAR INFORMACIÓN)
	//############################################################################################################
	public static final RegistryObject<MenuType<KikonoArmorStationMenuType>> KIKONO_ARMOR_STATION_MENU =
			registerMenuType("kikono_armor_station_menu", KikonoArmorStationMenuType::new);

	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
		return MENUS.register(name, () -> IForgeMenuType.create(factory));
	}

	public static void register(IEventBus modEventBus) {
		MENUS.register(modEventBus);
	}

}
