package com.yuseix.dragonminez.init.entity.client.model.masters;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.masters.KaiosamaEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.RoshiEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class KaiosamaModel extends GeoModel<KaiosamaEntity> {
	@Override
	public ResourceLocation getModelResource(KaiosamaEntity karinEntity) {
		return new ResourceLocation(DragonMineZ.MOD_ID, "geo/masters/kaiosama.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(KaiosamaEntity karinEntity) {
		return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/masters/kaiosama_master.png");
	}

	@Override
	public ResourceLocation getAnimationResource(KaiosamaEntity wa) {
		return new ResourceLocation(DragonMineZ.MOD_ID, "animations/kaiosama.animation.json");
	}

	@Override
	public void setCustomAnimations(KaiosamaEntity animatable, long instanceId, AnimationState<KaiosamaEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}

}
