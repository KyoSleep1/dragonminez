package com.yuseix.dragonminez.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.items.custom.DragonBallRadarItem;
import com.yuseix.dragonminez.init.items.custom.NamekDragonBallRadarItem;
import com.yuseix.dragonminez.network.C2S.DragonRadarC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.server.worldgen.dimension.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RadarEvents {
	private static final ResourceLocation fondo = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/radar.png");

	private static List<BlockPos> closestDballPositions = new ArrayList<>();
	private static List<BlockPos> closestNDballPositions = new ArrayList<>();
	private static long lastUpdateTime = 0;
	private static final int UPDATE_INTERVAL_TICKS = 20 * 5; // (20 Ticks * Cant Segundos) = Segundos en Minecraft, default 5.

	@SubscribeEvent
	public static void onRenderGameOverlay(RenderGuiOverlayEvent.Pre event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.isPaused()) return;

		Player player = mc.player;
		if (player == null) return;

		GuiGraphics gui = event.getGuiGraphics();
		int radarSize = 140; // Radar tamañito (121x146 px)

		// Determine the radar type and dimension
		boolean isOverworld = player.level().dimension().equals(Level.OVERWORLD);
		boolean isNamek = player.level().dimension().equals(ModDimensions.NAMEK_DIM_LEVEL_KEY);

		if (isOverworld || isNamek) {
			// Check if the player holds the correct radar
			ItemStack radarItem = player.getMainHandItem();
			ItemStack offhandRadarItem = player.getOffhandItem();
			if ((isOverworld && radarItem.getItem() instanceof DragonBallRadarItem) ||
					(isNamek && radarItem.getItem() instanceof NamekDragonBallRadarItem)) {

				// Get radar range from NBT or use default
				int radarRange = radarItem.getOrCreateTag().getInt(DragonBallRadarItem.NBT_RANGE);
				if (radarRange == 0) radarRange = 75; // Default range

				// Draw radar background
				int centerX = mc.getWindow().getGuiScaledWidth() - radarSize - 10;
				int centerY = mc.getWindow().getGuiScaledHeight() - radarSize - 10;

				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
				RenderSystem.setShaderTexture(0, fondo);
				gui.blit(fondo, centerX, centerY, 0, 0, 121, 146);

				// Update dragon ball positions every 2 seconds (40 ticks)
				long currentTime = player.level().getGameTime();

				String dimension = isOverworld ? "overworld" : "namek";

				if (currentTime - lastUpdateTime > UPDATE_INTERVAL_TICKS) {
					ModMessages.sendToServer(new DragonRadarC2S(dimension));
					lastUpdateTime = currentTime;
				}

				// Renderizar posiciones si ya están actualizadas
				if (isOverworld) renderDballRadar(gui, player, radarRange, closestDballPositions, centerX, centerY);
				else renderDballRadar(gui, player, radarRange, closestNDballPositions, centerX, centerY);
			} else if ((isOverworld && offhandRadarItem.getItem() instanceof DragonBallRadarItem) ||
					(isNamek && offhandRadarItem.getItem() instanceof NamekDragonBallRadarItem)) {
				// Get radar range from NBT or use default
				int radarRange = radarItem.getOrCreateTag().getInt(DragonBallRadarItem.NBT_RANGE);
				if (radarRange == 0) radarRange = 75; // Default range

				// Draw radar background
				int centerX = 0;
				if (mc.getWindow().getWidth() < 1080) {
					centerX = (mc.getWindow().getGuiScaledWidth() / 2) - radarSize - 60;
				} else {
					centerX = (mc.getWindow().getGuiScaledWidth() / 2) - radarSize - 310;
				}
				int centerY = mc.getWindow().getGuiScaledHeight() - radarSize - 10;

				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
				RenderSystem.setShaderTexture(0, fondo);
				gui.blit(fondo, centerX, centerY, 0, 0, 121, 146);

				// Update dragon ball positions every 2 seconds (40 ticks)
				long currentTime = player.level().getGameTime();

				String dimension = isOverworld ? "overworld" : "namek";

				if (currentTime - lastUpdateTime > UPDATE_INTERVAL_TICKS) {
					ModMessages.sendToServer(new DragonRadarC2S(dimension));
					lastUpdateTime = currentTime;
				}

				// Renderizar posiciones si ya están actualizadas
				if (isOverworld) renderDballRadar(gui, player, radarRange, closestDballPositions, centerX, centerY);
				else renderDballRadar(gui, player, radarRange, closestNDballPositions, centerX, centerY);
			}
		}
	}

	// Radar rendering logic
	private static void renderDballRadar(GuiGraphics gui, Player player, int radarRange, List<BlockPos> dballPositions, int centerX, int centerY) {
		for (BlockPos pos : dballPositions) {
			// Calculate 2D distance (ignoring Y)
			double dx = pos.getX() - player.getX();
			double dz = pos.getZ() - player.getZ();
			double distance = Math.sqrt(dx * dx + dz * dz);

			// Calculate angle to block
			double angleToBlock = Math.atan2(dz, dx);
			double playerYaw = Math.toRadians(player.getYRot());
			double adjustedAngle = angleToBlock - playerYaw;

			if (distance <= radarRange) {
				// Map distance to radar radius (0-50 px)
				double radarRadius = Math.min(distance / radarRange * 50, 50);

				// Calculate radar dot position
				int radarX = (int) (centerX + 61 - radarRadius * Math.cos(adjustedAngle));
				int radarY = (int) (centerY + 87 - radarRadius * Math.sin(adjustedAngle));

				// Draw yellow dot
				gui.blit(fondo, radarX - 2, radarY - 2, 130, 0, 6, 6);
			} else {

				// Draw yellow arrow
				int arrowX = (int) (centerX + 61 - 50 * Math.cos(adjustedAngle));
				int arrowY = (int) (centerY + 87 - 50 * Math.sin(adjustedAngle));

				// Compute vector from radar center to arrow position
				int radarCenterX = centerX + 61;
				int radarCenterY = centerY + 87;
				double screenDx = arrowX - radarCenterX;
				float arrowRotation = getArrowRotation(arrowY, radarCenterY, screenDx);

				// Now proceed to render with arrowRotation
				RenderSystem.setShaderTexture(0, fondo);
				gui.pose().pushPose();
				gui.pose().translate(arrowX, arrowY, 0);
				gui.pose().mulPose(new Quaternionf().rotationZ(arrowRotation));
				gui.pose().translate(-3.5f, -3.0f, 0);
				gui.blit(fondo, 0, +2, 130, 8, 7, 6);
				gui.pose().popPose();

			}
		}
	}

	private static float getArrowRotation(int arrowY, int radarCenterY, double screenDx) {
		double screenDy = arrowY - radarCenterY;

		// Determine the angle of that vector (atan2 gives angle relative to positive X)
		double screenAngle = Math.atan2(screenDy, screenDx);

		return (float) (screenAngle + Math.PI / 2);
	}

	public static void updateDragonBallsPositions(List<BlockPos> positionsDball) {
		closestDballPositions = positionsDball;
	}

	public static void updateNamekDragonBallsPositions(List<BlockPos> positionsNDball) {
		closestNDballPositions = positionsNDball;
	}
}