package com.yuseix.dragonminez.init.entity.client.model.masters;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.custom.masters.KarinEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class KarinModel extends GeoModel<KarinEntity> {
	@Override
	public ResourceLocation getModelResource(KarinEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/masters/karin.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(KarinEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/karin_master.png");
	}

	@Override
	public ResourceLocation getAnimationResource(KarinEntity karinEntity) {
		return null;
	}

	@Override
	public void setCustomAnimations(KarinEntity animatable, long instanceId, AnimationState<KarinEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}

}
