package com.yuseix.dragonminez.common.init.items.models;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.items.custom.ZSword;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ZSwordModel extends GeoModel<ZSword> {
	@Override
	public ResourceLocation getModelResource(ZSword zSword) {
		return new ResourceLocation(Reference.MOD_ID, "geo/z_sword.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ZSword zSword) {
		return new ResourceLocation(Reference.MOD_ID, "textures/weapons/z_sword.png");
	}

	@Override
	public ResourceLocation getAnimationResource(ZSword zSword) {
		return null;
	}
}
