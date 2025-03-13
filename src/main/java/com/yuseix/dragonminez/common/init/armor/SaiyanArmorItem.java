package com.yuseix.dragonminez.common.init.armor;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.armor.client.model.ArmorSaiyanModel;
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

public class SaiyanArmorItem extends ArmorItem {

    private final String itemId;
    private final boolean isDamageOn;
    private final boolean hasCape;

    public SaiyanArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, String itemId, boolean isDamageOn, boolean hasCape) {
        super(pMaterial, pType, pProperties);
        this.itemId = itemId; // ID del item
        this.isDamageOn = isDamageOn;
        this.hasCape = hasCape;
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {

        String texturePath = Reference.MOD_ID + ":textures/armor/saiyans/" + itemId;

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

            private ArmorSaiyanModel model;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                if(model == null){
                    model = new ArmorSaiyanModel(Minecraft.getInstance().getEntityModels().bakeLayer(ArmorSaiyanModel.LAYER_LOCATION));
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

	public boolean hasCape() {
		return hasCape;
	}
}
