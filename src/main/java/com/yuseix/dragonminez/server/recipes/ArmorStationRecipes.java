package com.yuseix.dragonminez.server.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ArmorStationRecipes implements Recipe<SimpleContainer> {
   private final NonNullList<Ingredient> inputItems;
   private final ItemStack outputItem;
   private final ResourceLocation id;
   private final int craftingTime;

    public ArmorStationRecipes(NonNullList<Ingredient> inputItems, ItemStack outputItem, ResourceLocation id, int craftingTime) {
        this.inputItems = inputItems;
        this.outputItem = outputItem;
        this.id = id;
        this.craftingTime = craftingTime; // Añadir tiempo de crafteo en TICKS
    }


    public int getCraftingTime() {
        return craftingTime;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            if (!inputItems.get(i).test(pContainer.getItem(i))) {
                return false;  // Si algún item no coincide, no se cumple la receta
            }
        }
        // Slots separados pa ordenar nama :p
        if(!inputItems.get(9).test(pContainer.getItem(9))) {
            return false;  // PRESET_SLOT
        }
        if(!inputItems.get(10).test(pContainer.getItem(10))) {
            return false;  // ARMOR_SLOT
        }
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return outputItem.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return outputItem.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ArmorStationRecipes> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "armor_station";
    }

    public static class Serializer implements RecipeSerializer<ArmorStationRecipes> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Reference.MOD_ID, "armor_station");

        @Override
        public ArmorStationRecipes fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            JsonArray slot1 = GsonHelper.getAsJsonArray(pSerializedRecipe, "slot_1");
            JsonArray slot2 = GsonHelper.getAsJsonArray(pSerializedRecipe, "slot_2");
            JsonArray slot3 = GsonHelper.getAsJsonArray(pSerializedRecipe, "slot_3");
            JsonArray slot4 = GsonHelper.getAsJsonArray(pSerializedRecipe, "slot_4");
            JsonArray slot5 = GsonHelper.getAsJsonArray(pSerializedRecipe, "slot_5");
            JsonArray slot6 = GsonHelper.getAsJsonArray(pSerializedRecipe, "slot_6");
            JsonArray slot7 = GsonHelper.getAsJsonArray(pSerializedRecipe, "slot_7");
            JsonArray slot8 = GsonHelper.getAsJsonArray(pSerializedRecipe, "slot_8");
            JsonArray slot9 = GsonHelper.getAsJsonArray(pSerializedRecipe, "slot_9");
            JsonArray presetItem = GsonHelper.getAsJsonArray(pSerializedRecipe, "preset");
            JsonArray armorItem = GsonHelper.getAsJsonArray(pSerializedRecipe, "armor");
            ItemStack outputItem = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
            NonNullList<Ingredient> inputs = NonNullList.withSize(11, Ingredient.EMPTY);

            inputs.set(0, Ingredient.fromJson(slot1));
            inputs.set(1, Ingredient.fromJson(slot2));
            inputs.set(2, Ingredient.fromJson(slot3));
            inputs.set(3, Ingredient.fromJson(slot4));
            inputs.set(4, Ingredient.fromJson(slot5));
            inputs.set(5, Ingredient.fromJson(slot6));
            inputs.set(6, Ingredient.fromJson(slot7));
            inputs.set(7, Ingredient.fromJson(slot8));
            inputs.set(8, Ingredient.fromJson(slot9));
            inputs.set(9, Ingredient.fromJson(presetItem));
            inputs.set(10, Ingredient.fromJson(armorItem));

            int craftingTime = GsonHelper.getAsInt(pSerializedRecipe, "crafting_time", 200); // Default de 200 ticks/10 segundos

            return new ArmorStationRecipes(inputs, outputItem, pRecipeId, craftingTime);
        }

        @Override
        public @Nullable ArmorStationRecipes fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack outputItem = pBuffer.readItem();
            int craftingTime = pBuffer.readInt();

            return new ArmorStationRecipes(inputs, outputItem, pRecipeId, craftingTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, ArmorStationRecipes pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
            pBuffer.writeInt(pRecipe.getCraftingTime());
        }
    }

}
