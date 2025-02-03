package com.yuseix.dragonminez.init.armor.client;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.armor.client.model.ArmorPiccoloModel;
import com.yuseix.dragonminez.init.armor.client.model.ArmorSaiyanModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SaiyanCapeArmorItem extends ArmorItem {

    private final String itemId;
    private final boolean isDamageOn;


    public SaiyanCapeArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, String itemId, boolean isDamageOn) {
        super(pMaterial, pType, pProperties);
        this.itemId = itemId; // ID del item
        this.isDamageOn = isDamageOn;

    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {

        String texturePath = DragonMineZ.MOD_ID + ":textures/armor/" + itemId;

        if(isDamageOn()){
            int maxDamage = stack.getMaxDamage();
            int currentDamage = stack.getDamageValue();

            // Determinamos si la armadura está dañada (menos de la mitad de durabilidad)
            boolean isDamaged = currentDamage > maxDamage / 2;

            // Retornamos las texturas dependiendo del daño
            switch (slot) {
                case HEAD:
                    return texturePath + (isDamaged ? "_damaged_layer1.png" : "_layer1.png");
                case LEGS:
                    return texturePath + (isDamaged ? "_damaged_layer2.png" : "_layer2.png");
                case FEET:
                    return texturePath + (isDamaged ? "_damaged_layer1.png" : "_layer1.png");
                default:
                    return texturePath + (isDamaged ? "_damaged_layer1.png" : "_layer1.png");
            }
        } else {
            switch (slot) {
                case HEAD:
                    return texturePath + "_layer1.png";
                case LEGS:
                    return texturePath + "_layer2.png";
                case FEET:
                    return texturePath + "_layer1.png";
                default:
                    return texturePath + "_layer1.png";
            }
        }

    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private ArmorPiccoloModel model;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                if(model == null){
                    model = new ArmorPiccoloModel(Minecraft.getInstance().getEntityModels().bakeLayer(ArmorPiccoloModel.LAYER_LOCATION));
                }
                return model;

            }
        });
    }

    public String getItemId() {
        return itemId;
    }

    public boolean isDamageOn() {
        return isDamageOn;
    }

}
