package com.yuseix.dragonminez.common.init.entity.client.model.saiyansaga;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.custom.saiyansaga.OzaruVegetaEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
public class OzaruVegetaModel extends GeoModel<OzaruVegetaEntity> {
	@Override
	public ResourceLocation getModelResource(OzaruVegetaEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/ozaruvegeta.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(OzaruVegetaEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/sagas/saiyan/vegeta_ozaru.png");
	}

	@Override
	public ResourceLocation getAnimationResource(OzaruVegetaEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/ozaruvegeta.animation.json");
	}

	@Override
	public void setCustomAnimations(OzaruVegetaEntity animatable, long instanceId, AnimationState<OzaruVegetaEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("Head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}

}
