package com.yuseix.dragonminez.common.init.items.models;

import com.yuseix.dragonminez.common.init.items.custom.TrunksSword;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class TrunksSwordRenderer extends GeoItemRenderer<TrunksSword> {
	public TrunksSwordRenderer() {
		super (new TrunksSwordModel());
	}
}
