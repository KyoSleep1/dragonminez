package com.yuseix.dragonminez.init.items.models;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.items.custom.YajirobeKatana;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class YajirobeKatanaModel extends GeoModel<YajirobeKatana> {
	@Override
	public ResourceLocation getModelResource(YajirobeKatana yajirobeKatana) {
		return new ResourceLocation(Reference.MOD_ID, "geo/yajirobe_katana.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(YajirobeKatana yajirobeKatana) {
		return new ResourceLocation(Reference.MOD_ID, "textures/weapons/yajirobe_katana.png");
	}

	@Override
	public ResourceLocation getAnimationResource(YajirobeKatana yajirobeKatana) {
		return null;
	}
}
