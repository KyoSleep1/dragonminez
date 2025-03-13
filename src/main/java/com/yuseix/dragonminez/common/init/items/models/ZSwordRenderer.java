package com.yuseix.dragonminez.common.init.items.models;

import com.yuseix.dragonminez.common.init.items.custom.ZSword;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ZSwordRenderer extends GeoItemRenderer<ZSword> {
	public ZSwordRenderer() {
		super (new ZSwordModel());
	}
}
