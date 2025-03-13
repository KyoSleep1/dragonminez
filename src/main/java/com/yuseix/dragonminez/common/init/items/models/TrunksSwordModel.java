package com.yuseix.dragonminez.common.init.items.models;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.items.custom.TrunksSword;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TrunksSwordModel extends GeoModel<TrunksSword> {
	@Override
	public ResourceLocation getModelResource(TrunksSword trunksSword) {
		return new ResourceLocation(Reference.MOD_ID, "geo/trunks_sword.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TrunksSword trunksSword) {
		return new ResourceLocation(Reference.MOD_ID, "textures/weapons/trunks_sword.png");
	}

	@Override
	public ResourceLocation getAnimationResource(TrunksSword trunksSword) {
		return null;
	}
}
