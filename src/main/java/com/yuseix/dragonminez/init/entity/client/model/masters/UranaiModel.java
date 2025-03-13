package com.yuseix.dragonminez.init.entity.client.model.masters;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.custom.masters.EnmaEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.RoshiEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.UranaiEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class UranaiModel extends GeoModel<UranaiEntity> {
	@Override
	public ResourceLocation getModelResource(UranaiEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/masters/uranai.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(UranaiEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/uranai.png");
	}

	@Override
	public ResourceLocation getAnimationResource(UranaiEntity wa) {
		return new ResourceLocation(Reference.MOD_ID, "animations/uranai.animation.json");
	}

	@Override
	public void setCustomAnimations(UranaiEntity animatable, long instanceId, AnimationState<UranaiEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}


}
