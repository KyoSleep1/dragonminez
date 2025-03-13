package com.yuseix.dragonminez.compat;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.recipes.ArmorStationRecipes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ArmorStationCategory implements IRecipeCategory<ArmorStationRecipes> {
    public static final ResourceLocation UID = new ResourceLocation(Reference.MOD_ID, "armor_station");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/kikono_armor_station_gui.png");
    public static final RecipeType<ArmorStationRecipes> ARMOR_STATION_TYPE = new RecipeType<>(UID, ArmorStationRecipes.class);
    private final IDrawable background;
    private final IDrawable icon;

    public ArmorStationCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MainBlocks.KIKONO_ARMOR_STATION.get()));
    }

    @Override
    public RecipeType<ArmorStationRecipes> getRecipeType() {
        return ARMOR_STATION_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.dragonminez.kikono_armor_station");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ArmorStationRecipes recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 28, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 46, 17).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 64, 17).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 28, 35).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 46, 35).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.INPUT, 64, 35).addIngredients(recipe.getIngredients().get(5));
        builder.addSlot(RecipeIngredientRole.INPUT, 28, 53).addIngredients(recipe.getIngredients().get(6));
        builder.addSlot(RecipeIngredientRole.INPUT, 46, 53).addIngredients(recipe.getIngredients().get(7));
        builder.addSlot(RecipeIngredientRole.INPUT, 64, 53).addIngredients(recipe.getIngredients().get(8));
        builder.addSlot(RecipeIngredientRole.INPUT, 89, 17).addIngredients(recipe.getIngredients().get(9));
        builder.addSlot(RecipeIngredientRole.INPUT, 89, 53).addIngredients(recipe.getIngredients().get(10));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 141, 35).addItemStack(recipe.getResultItem(null));
    }
}
