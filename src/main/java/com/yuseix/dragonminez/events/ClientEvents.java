package com.yuseix.dragonminez.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.character.models.AuraModel;
import com.yuseix.dragonminez.client.character.renders.DmzRenderer;
import com.yuseix.dragonminez.init.MainParticles;
import com.yuseix.dragonminez.network.C2S.FlyToggleC2S;
import com.yuseix.dragonminez.network.C2S.PermaEffC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
import com.yuseix.dragonminez.utils.Keys;
import com.yuseix.dragonminez.utils.TextureManager;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.thread.EffectiveSide;
import com.yuseix.dragonminez.client.character.models.AuraModel;

import java.util.Random;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
	private static final String MOD_VERSION = System.getProperty("mod_version", "unknown");

	private static final Random RANDOM = new Random();
	private static final String title = "DragonMine Z - Release v" + "1.2 || Storyline and Skills!";
	private static boolean isDescending = false;

	private static final AuraModel AURA_MODEL = new AuraModel(AuraModel.createBodyLayer().bakeRoot());


	@SubscribeEvent
	public static void onRenderTick(TickEvent.RenderTickEvent event) {
		if (event.phase == TickEvent.RenderTickEvent.Phase.END) {
			Minecraft minecraft = Minecraft.getInstance();
			if (minecraft.getWindow() != null) {
				// Cambia el título de la ventana
				minecraft.getWindow().setTitle(title);
			}
		}
	}

	@SubscribeEvent
	public static void onRenderLevelLast(RenderLevelStageEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		if (!event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) return;

		for (Player player : minecraft.level.players()) {
			if (player != null) {
				Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();

				// Obtener posición de la cámara
				double camX = camera.getPosition().x;
				double camY = camera.getPosition().y;
				double camZ = camera.getPosition().z;

				double interpX = Mth.lerp(event.getPartialTick(), player.xOld, player.getX());
				double interpY = Mth.lerp(event.getPartialTick(), player.yOld, player.getY());
				double interpZ = Mth.lerp(event.getPartialTick(), player.zOld, player.getZ());

				var poseStack = event.getPoseStack();

				boolean isLocalPlayer = player == minecraft.player;

				var renderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(player);
				if (renderer instanceof DmzRenderer dmzRenderer) {
					poseStack.pushPose();
					poseStack.translate(interpX - camX, interpY - camY, interpZ - camZ);
					dmzRenderer.renderOnWorld((AbstractClientPlayer) player, 0, event.getPartialTick(), poseStack, minecraft.renderBuffers().bufferSource(), 15728880); // packedLight no deberia ser un valor estático, no aplica iluminación 'dinámica'
					poseStack.popPose();
				}


				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

					if (cap.isAuraOn() || cap.isTurbonOn()) {
						event.getPoseStack().pushPose();
						float transparency = isLocalPlayer && minecraft.options.getCameraType().isFirstPerson() ? 0.075f : 0.325f;

						RenderSystem.disableDepthTest();
						renderAuraBase(
								(AbstractClientPlayer) player,
								event.getPoseStack(),
								minecraft.renderBuffers().bufferSource(),
								15728880,
								event.getPartialTick(),
								transparency,
								cap.getAuraColor()
						);
						event.getPoseStack().popPose();
						RenderSystem.enableDepthTest();
					}
					});
			}
		}
	}

	private static void renderAuraBase(AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource buffer, int packedLight, float partialTicks, float transparencia, int colorAura) {
		Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();

		// Obtener posición de la cámara
		double camX = camera.getPosition().x;
		double camY = camera.getPosition().y;
		double camZ = camera.getPosition().z;

		// Descomponer el color en sus componentes RGBA
		float red = (colorAura >> 16 & 255) / 255.0f;
		float green = (colorAura >> 8 & 255) / 255.0f;
		float blue = (colorAura & 255) / 255.0f;

		// Obtener posición interpolada del jugador
		double interpX = Mth.lerp(partialTicks, player.xOld, player.getX());
		double interpY = Mth.lerp(partialTicks, player.yOld, player.getY());
		double interpZ = Mth.lerp(partialTicks, player.zOld, player.getZ());

		//ACA YA FUNCIONA
		poseStack.pushPose();

		//Ajustar posición del aura en el jugador
		poseStack.translate(interpX - camX, interpY - camY + player.getEyeHeight(), interpZ - camZ);

		poseStack.mulPose(Axis.XP.rotationDegrees(180f));

		float rotationAngle = 0.0F;
		rotationAngle = (player.tickCount + partialTicks) * 5.0F; // Ajusta la velocidad aquí

		float rotationAngle2 = 0.0F;
		rotationAngle2 = (player.tickCount + partialTicks) * -7.0F; // Ajusta la velocidad aquí

		VertexConsumer vertexConsumer = buffer.getBuffer(CustomRenderTypes.energy(TextureManager.AURA_BASE));


		// PARTE BAJA 1
		for (int i = 0; i < 8; i++) {  // Ajusta el número de planos
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.7F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(40));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.0D, -0.7D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}

		//PARTE BAJA 2
		for (int i = 0; i < 8; i++) {
			poseStack.pushPose();
			poseStack.scale(1.4F, 1.9F, 1.4F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(40));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.0D, -0.5D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//PARTE MEDIO 1 interior
		for (int i = 0; i < 10; i++) {
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.7F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(0));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -0.6D, -0.2D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//parte medio 2 exterior
		for (int i = 0; i < 10; i++) {
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.7F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(15f));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.0D, -0.4D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//parte medio 3 exterior
		for (int i = 0; i < 10; i++) {
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.9F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(15f));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.0D, -0.6D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//PARTE ARRIBA 1 interior
		for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.6F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(-35F));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.1D, -0.38D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//Parte 2 arriba exterior
		for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.6F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(25F));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -0.8D, -0.4D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//Parte 3 arriba exterior
		for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.6F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(-15F));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.2D, -0.4D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//PARTE ARRIBA 4 interior
		for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.6F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(5F));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.5D, -0.38D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}

		poseStack.popPose();
	}

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		Minecraft mc = Minecraft.getInstance();
		Level level = mc.level;

		if (level == null || mc.player == null || mc.isPaused()) {
			return;
		}

		// Frecuencia de generación (una vez cada 10 ticks)
		if (level.getGameTime() % 10 != 0) {
			return;
		}

		// Obtener posición y bioma
		BlockPos playerPos = mc.player.blockPosition();
		ResourceKey<Biome> currentBiomeKey = level.registryAccess()
				.registryOrThrow(Registries.BIOME)
				.getResourceKey(level.getBiome(playerPos).value())
				.orElse(null);

		if (currentBiomeKey == null) return;

		if (playerPos.getY() > 140 || playerPos.getY() < 62) return;

		// Genera partículas dependiendo del bioma
		if (currentBiomeKey.equals(ModBiomes.AJISSA_PLAINS)) {
			spawnParticles(level, MainParticles.AJISSA_LEAVES_PARTICLE.get(), playerPos);
		} else if (currentBiomeKey.equals(ModBiomes.SACRED_LAND)) {
			spawnParticles(level, MainParticles.SACRED_LEAVES_PARTICLE.get(), playerPos);
		}
	}

	@SubscribeEvent
	public static void onKeyPress(InputEvent.Key event) {
		if (Keys.FLY_KEY.consumeClick()) {
			LocalPlayer player = Minecraft.getInstance().player;

			if (player != null) {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					DMZSkill jumpSkill = cap.getDMZSkills().get("jump");
					DMZSkill flySkill = cap.getDMZSkills().get("fly");
					if (flySkill == null) return;

					int flyLevel = flySkill.getLevel();
					int consumeEnergy = 0;
					if (flyLevel < 4) {
						consumeEnergy = (int) Math.ceil(cap.getMaxEnergy() * 0.04);
					} else {
						consumeEnergy = (int) Math.ceil(cap.getMaxEnergy() * 0.02);
					}

					if (flySkill.getLevel() > 0 && cap.getCurrentEnergy() > consumeEnergy) {

						if (player.onGround()) {
							// Aplicar el salto inicial
							player.jumpFromGround();

							// Si el jugador tiene habilidad de salto, potenciamos el salto
							if (jumpSkill != null && jumpSkill.isActive()) {
								int jumpLevel = jumpSkill.getLevel();
								if (jumpLevel > 0) {
									float jumpBoost = 0.1f * jumpLevel;
									player.setDeltaMovement(player.getDeltaMovement().add(0, jumpBoost, 0));
								}
							}
							// Si no tiene habilidad de salto, salta normalmente
							else {
								player.setDeltaMovement(player.getDeltaMovement().x, 0.42D, player.getDeltaMovement().z);
							}
							isDescending = true;
						} else {
							ModMessages.sendToServer(new FlyToggleC2S());
						}
					}

					if(flySkill.isActive()){
						ModMessages.sendToServer(new PermaEffC2S("remove", "fly", 1));
					} else {
						ModMessages.sendToServer(new PermaEffC2S("add", "fly", 1));

					}


				});
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.START) return;
		if (!(event.player instanceof LocalPlayer player)) return;

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
			if (isDescending && player.getDeltaMovement().y < 0) { // Si está cayendo después del salto
				isDescending = false;
				ModMessages.sendToServer(new FlyToggleC2S());
			}

			DMZSkill flySkill = cap.getDMZSkills().get("fly");

			if (flySkill != null && flySkill.isActive()) {
				int flyLevel = flySkill.getLevel();

				// La vel de vuelo aumenta un 20% por nivel
				float baseSpeed = 0.05F;
				float flySpeed = baseSpeed * (1.0F + (0.20F * flyLevel));
				player.getAbilities().setFlyingSpeed(flySpeed);

				Vec3 motion = player.getDeltaMovement();
				double yVelocity = motion.y;

				// Si mantiene espacio, ascender
				if (player.input.jumping) {
					yVelocity = 0.1;
				}
				// Si mantiene shift, descender
				else if (player.input.shiftKeyDown) {
					yVelocity = -0.1;
				}
				// Si no presiona nada, descenso lento
				else {
					if (yVelocity != -0.02) {
						yVelocity = -0.02;
					}
				}

				player.setDeltaMovement(motion.x, yVelocity, motion.z);
				player.onUpdateAbilities();
			}
		});
	}

	private static void spawnParticles(Level level, SimpleParticleType particleType, BlockPos playerPos) {
		// Generar partículas alrededor del jugador
		int cantParticulas = 6;
		for (int i = 0; i < cantParticulas; i++) { // Cantidad
			double x = playerPos.getX() + RANDOM.nextDouble() * 16 - 8; // Rango: -8 a +8 bloques
			double y = playerPos.getY() + RANDOM.nextDouble() * 6;      // Rango: hasta 6 bloques arriba
			double z = playerPos.getZ() + RANDOM.nextDouble() * 16 - 8; // Rango: -8 a +8 bloques

			double xSpeed = (RANDOM.nextDouble() - 0.5) * 0.02; // Velocidad lateral mínima
			double ySpeed = RANDOM.nextDouble() * 0.01;         // Velocidad vertical lenta
			double zSpeed = (RANDOM.nextDouble() - 0.5) * 0.02; // Velocidad lateral mínima

			level.addParticle(particleType, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}
}
