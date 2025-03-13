package com.yuseix.dragonminez.client.util.shader;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DMZShaders {

    private static ShaderInstance AURA_SHADER;

    @SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(Reference.MOD_ID, "aura"), DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP),
                shader -> AURA_SHADER = shader);
    }

    public static ShaderInstance getAuraShader() {
        return AURA_SHADER;
    }

}
