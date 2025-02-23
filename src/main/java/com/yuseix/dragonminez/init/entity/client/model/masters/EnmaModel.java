package com.yuseix.dragonminez.init.entity.client.model.masters;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.masters.EnmaEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.RoshiEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class EnmaModel extends GeoModel<EnmaEntity> {
	@Override
	public ResourceLocation getModelResource(EnmaEntity enmaEntity) {
		return new ResourceLocation(DragonMineZ.MOD_ID, "geo/masters/enma.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EnmaEntity enmaEntity) {
		return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/masters/enma.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EnmaEntity wa) {
		return new ResourceLocation(DragonMineZ.MOD_ID, "animations/enma.animation.json");
	}


}
