package com.yuseix.dragonminez.server.worldgen.dimension;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import org.joml.Matrix4f;

public class CustomSpecialEffects extends DimensionSpecialEffects {
	/* NOTA: No hace falta hacer un runData cada vez que se modifique algo, nomás cerrar y abrir runClient.
	 * Si agregamos nuevas dimensiones, es tan simple como copiar y pegar lo que hice con las ya existentes.
	 */
	public static final ResourceLocation NAMEK_EFFECTS = new ResourceLocation(Reference.MOD_ID, "namek_effects");
	public static final ResourceLocation OTHERWORLD_EFFECTS = new ResourceLocation(Reference.MOD_ID, "otherworld_effects");
	public static final ResourceLocation HTC_EFFECT = new ResourceLocation(Reference.MOD_ID, "htc_effects");

	public CustomSpecialEffects(float cloudLevel, boolean hasGround, SkyType skyType, boolean forceBrightLightMap, boolean constantAmbientLight) {
		super(cloudLevel, hasGround, skyType, forceBrightLightMap, constantAmbientLight);
	}

	@Override
	public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight) {
		return biomeFogColor.multiply((double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.91F + 0.09F));
	}

	@Override
	public boolean isFoggyAt(int x, int y) {
		return false;
	}

	@Override
	public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
		return false; // False = No se renderizan nubes | True = Se renderizan nubes
	}

	@Override
	public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
		return false; // False = No se renderiza el cielo | True = Se renderiza el cielo
	}

	@Override
	public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
		return false; // False = No se renderiza la lluvia | True = Se renderiza la lluvia
	}

	public static class NamekEffects extends CustomSpecialEffects {
		public NamekEffects() {
			super(192.0F, true, SkyType.NORMAL, false, false);
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight) {
			return biomeFogColor.multiply((double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.91F + 0.09F));
		}

		@Override
		public boolean isFoggyAt(int x, int y) {
			return false;
		}

		@Override
		public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
			return true;
		}

		@Override
		public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
			return true;
		}
	}

	public static class HTCEffects extends CustomSpecialEffects {
		public HTCEffects() {
			super(192.0F, true, SkyType.NORMAL, false, false);
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight) {
			return biomeFogColor.multiply((double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.91F + 0.09F));
		}

		@Override
		public boolean isFoggyAt(int x, int y) {
			return false;
		}

		@Override
		public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
			return true;
		}

		@Override
		public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
			return true;
		}

		@Override
		public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
			return true;
		}
	}

	public static class OtherWorldEffects extends CustomSpecialEffects {
		public OtherWorldEffects() {
			super(192.0F, false, SkyType.NORMAL, false, false);
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight) {
			return biomeFogColor.multiply((double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.91F + 0.09F));
		}

		@Override
		public boolean isFoggyAt(int x, int y) {
			return false;
		}

		@Override
		public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
			return true;
		}

		@Override
		public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
			return true;
		}

		@Override
		public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
			return true;
		}
	}

	public static void registerSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
		event.register(NAMEK_EFFECTS, new NamekEffects());
		event.register(OTHERWORLD_EFFECTS, new OtherWorldEffects());
		event.register(HTC_EFFECT, new HTCEffects());
	}

}
