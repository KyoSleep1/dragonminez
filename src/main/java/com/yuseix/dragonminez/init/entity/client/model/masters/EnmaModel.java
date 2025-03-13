package com.yuseix.dragonminez.init.entity.client.model.masters;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.custom.masters.EnmaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class EnmaModel extends GeoModel<EnmaEntity> {
	@Override
	public ResourceLocation getModelResource(EnmaEntity enmaEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/masters/enma.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EnmaEntity enmaEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/enma.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EnmaEntity wa) {
		return new ResourceLocation(Reference.MOD_ID, "animations/enma.animation.json");
	}


}
