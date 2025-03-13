package com.yuseix.dragonminez.compat;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.menus.screens.KikonoArmorStationScreen;
import com.yuseix.dragonminez.recipes.ArmorStationRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIDragonMineZPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(Reference.MOD_ID, "jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new ArmorStationCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

		List<ArmorStationRecipes> armorStationRecipes = recipeManager.getAllRecipesFor(ArmorStationRecipes.Type.INSTANCE);
		registration.addRecipes(ArmorStationCategory.ARMOR_STATION_TYPE, armorStationRecipes);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(KikonoArmorStationScreen.class, 112, 35, 23, 20,
				ArmorStationCategory.ARMOR_STATION_TYPE);
	}
}
