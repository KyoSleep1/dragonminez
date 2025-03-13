package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.network.C2S.MenuC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {

	@SubscribeEvent
	public static void onKeyInput(InputEvent.Key event) {
		if (Keys.STATS_MENU.consumeClick()) {
			ModMessages.sendToServer(new MenuC2S("stats"));
		}
		if (Keys.UTILITY_PANEL.consumeClick()) {
			ModMessages.sendToServer(new MenuC2S("utility"));
		}
	}

	// Solo cancela el render Vanilla si el jugador creó su personaje
	@SubscribeEvent
	public static void RenderHealthBar(RenderGuiOverlayEvent.Pre event) {
		if (Minecraft.getInstance().player != null) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {
				boolean isDmzUser = playerstats.getBoolean("dmzuser");
				if (isDmzUser) {
					if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay()) {
						event.setCanceled(true);
					}
				}
			});
		}
	}
}
