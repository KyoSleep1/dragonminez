package com.yuseix.dragonminez.common.init.entity.client.model.saiyansaga;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.custom.saiyansaga.NappaEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NappaModel extends GeoModel<NappaEntity> {
	@Override
	public ResourceLocation getModelResource(NappaEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/nappa.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(NappaEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/sagas/saiyan/nappa_1.png");
	}

	@Override
	public ResourceLocation getAnimationResource(NappaEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/nappa.animation.json");
	}

	@Override
	public void setCustomAnimations(NappaEntity animatable, long instanceId, AnimationState<NappaEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}

}
