package com.yuseix.dragonminez.events.characters;


import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.config.races.DMZBioAndroidConfig;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.C2S.DescendFormC2S;
import com.yuseix.dragonminez.network.C2S.PermaEffC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.forms.FormsData;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
import com.yuseix.dragonminez.utils.DMZDatos;
import com.yuseix.dragonminez.utils.Keys;
import com.yuseix.dragonminez.utils.TickHandler;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StatsEvents {

	private static final Map<UUID, TickHandler> playerTickHandlers = new HashMap<>();

	//Teclas
	private static boolean previousKeyDescendState = false;
	private static boolean previousKiChargeState = false;
	private static boolean turboOn = false;
	private static boolean transformOn = false;

	//Sonidos
	private static SimpleSoundInstance kiChargeLoop, turboLoop, oozaruLoop;


	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		// Verificar que estamos en el servidor y en la fase final
		if (event.phase == TickEvent.Phase.START) {
			return;
		}

		Player player = event.player;

		// Verificar que el jugador es un ServerPlayer
		if (!(player instanceof ServerPlayer serverPlayer)) {
			return;
		}

		DMZDatos dmzdatos = new DMZDatos();

		TickHandler tickHandler = playerTickHandlers.computeIfAbsent(player.getUUID(), uuid -> new TickHandler());

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, serverPlayer).ifPresent(playerstats -> {
			var raza = playerstats.getRace();
			boolean isDmzUser = playerstats.isAcceptCharacter();

			int maxenergia = dmzdatos.calcularENE(playerstats);

			// Verificar que haya creado su personaje antes de comenzar a hacer cosas referentes a las stats
			if (isDmzUser) {
				if (!playerstats.hasFormSkill("super_form")) {
					FormsData skill = new FormsData("dmz.dmzforms.super_form.name", 0);
					playerstats.addFormSkill("super_form", skill);
				}
				Objects.requireNonNull(serverPlayer.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(dmzdatos.calcularCON(playerstats));
				// Tickhandler
				tickHandler.tickRegenConsume(playerstats, dmzdatos, serverPlayer);

				if (raza == 5) {
					// Pasiva Majin
					tickHandler.manejarPasivaMajin(playerstats, serverPlayer);
				} else if (raza == 1) {
					playerstats.setSaiyanZenkaiTimer(zenkaiContador(playerstats.getSaiyanZenkaiTimer()));
					if (playerstats.getSaiyanZenkaiTimer() == 0 && player.getHealth() < (playerstats.getMaxHealth() * 0.10)) {
						// Pasiva Saiyan
						tickHandler.manejarPasivaSaiyan(playerstats, serverPlayer);
					}
				}
				serverPlayer.refreshDimensions();
				//Aca manejamos la carga de aura
				tickHandler.manejarCargaDeAura(playerstats, maxenergia);
				//Aca manejamos la carga de la transformacion
				tickHandler.manejarCargaForma(playerstats, serverPlayer);


				//Restar el tiempo que se pone en el comando dmztempeffect
				updateTemporaryEffects(serverPlayer);
				DMZSkill flySkill = playerstats.getDMZSkills().get("fly");

				if (flySkill != null) {
					if (flySkill.isActive()) {
						// Consumo de Ki del Fly
						tickHandler.manejarFlyConsume(playerstats, maxenergia, serverPlayer);

						if (player.onGround() || !player.getFeetBlockState().isAir()) { // Desactivar vuelo si toca el suelo
							flySkill.setActive(false);
							if (!player.isCreative() && !player.isSpectator()) player.getAbilities().mayfly = false;
							player.getAbilities().flying = false;
							player.fallDistance = 0;
							player.onUpdateAbilities(); // IMPORTANTE: Aplicar cambios de habilidades
							playerstats.removePermanentEffect("fly");

						}
					}
				}
				if (playerstats.getBabaCooldown() > 10) playerstats.setBabaCooldown(10);
				if (!playerstats.isDmzAlive() && playerstats.getBabaAliveTimer() <= 0) {
					if (serverPlayer.level().dimension() != ModDimensions.OTHERWORLD_DIM_LEVEL_KEY) {
						ServerLevel serverLevel = serverPlayer.getServer().getLevel(ModDimensions.OTHERWORLD_DIM_LEVEL_KEY);
						if (serverLevel != null) {
							serverPlayer.teleportTo(serverLevel, 121, 46, -17, serverPlayer.getYRot(), serverPlayer.getXRot());
							serverPlayer.displayClientMessage(Component.translatable("dmz.otherworld.cannot_leave"), true);
						}
					}
				}

				if (playerstats.getBabaCooldown() > 0) playerstats.setBabaCooldown(babaCooldown(playerstats.getBabaCooldown()));
				if (playerstats.getBabaAliveTimer() > 0) playerstats.setBabaAliveTimer(babaDuration(playerstats.getBabaAliveTimer()));

			} else {
				serverPlayer.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
			}

			//Tiempo para reclamar una senzu
			playerstats.setDmzSenzuDaily(senzuContador(playerstats.getDmzSenzuDaily()));

		});
	}

	@SubscribeEvent
	public static void onLivingUpdateEvent(LivingEvent.LivingTickEvent event) {
		if (!(event.getEntity() instanceof Player player)) return;

		if (turboOn) {
			// Obtener la velocidad actual
			Vec3 currentMovement = player.getDeltaMovement();

			if (player.onGround()) {
				// En tierra: Aplicar multiplicador al movimiento horizontal (X y Z)
				double turboSpeedX = currentMovement.x * 1.5;
				double turboSpeedZ = currentMovement.z * 1.5;

				// Configurar el nuevo movimiento con el multiplicador
				player.setDeltaMovement(turboSpeedX, currentMovement.y, turboSpeedZ);
			} else {
				// En el aire: Normalizar la velocidad horizontal para evitar acumulación infinita
				double horizontalSpeed = Math.sqrt(currentMovement.x * currentMovement.x + currentMovement.z * currentMovement.z);
				if (horizontalSpeed > 0.65) {
					// Limitar la velocidad horizontal al máximo permitido
					double scale = 0.65 / horizontalSpeed;
					double limitedX = currentMovement.x * scale;
					double limitedZ = currentMovement.z * scale;
					player.setDeltaMovement(limitedX, currentMovement.y, limitedZ);
				}
			}
		}
	}


	private static void updateTemporaryEffects(Player player) {
		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
			for (Map.Entry<String, Integer> entry : playerstats.getDMZTemporalEffects().entrySet()) {
				int timeLeft = entry.getValue() - 1;  // Reducir en 1 tick cada vez
				if (timeLeft <= 0) {
					playerstats.removeTemporalEffect(entry.getKey());  // Usalo para eliminar el efecto
				} else {
					entry.setValue(timeLeft);  // Actualiza el tiempo restante
				}
			}
		});
	}

	@SubscribeEvent
	public static void Recibirdano(LivingHurtEvent event) {

		DMZDatos dmzdatos = new DMZDatos();

		// Si el que hace el daño es un jugador
		if (event.getSource().getEntity() instanceof Player atacante) {
			// Verificar si el ataque es melee (no proyectil)
			if (event.getSource().getMsgId().equals("player")) {
				// Obtener las estadísticas del atacante
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, atacante).ifPresent(cap -> {
					int curStamina = cap.getCurStam();
					boolean isDmzUser = cap.isAcceptCharacter();

					float danoDefault = event.getAmount(); // Capturamos el daño original

					// Calcular el daño basado en la fuerza del atacante
					int maxDamage = dmzdatos.calcularSTR(cap);

					int staminacost = maxDamage / 12;
					int danoKiWeapon = dmzdatos.calcularKiPower(cap);
					var ki_control = cap.hasSkill("ki_control");
					var ki_manipulation = cap.hasSkill("ki_manipulation");
					var meditation = cap.hasSkill("meditation");
					var is_kimanipulation = cap.isActiveSkill("ki_manipulation");
					int maxKi = cap.getMaxEnergy();
					int currKi = cap.getCurrentEnergy();
					int staminaCost = maxDamage / 10;

					// Si el usuario creó su personaje, entonces aplica la lógica del Daño del Mod + Consumo de Stamina
					if (isDmzUser) {
						if (curStamina > 0) {
							int staminaToConsume = Math.min(curStamina, staminaCost);
							if (staminaCost <= 2) {
								staminaCost = 2;
								staminaToConsume = 2; // Mínimo 2 de Stamina
							}
							// Consumir Stamina proporcional al daño
							// Consume lo que se puede
							float damageMultiplier = (float) staminaToConsume / staminaCost; // Factor de daño basado en Stamina disponible

							if (curStamina >= staminacost) {
								// Aplicar daño ajustado si la Stamina no alcanza
								float adjustedDamage = maxDamage * damageMultiplier;
								if (cap.getRace() == 3) {
									if (atacante.getHealth() < (cap.getMaxHealth() * 0.25)) {
										float passiveBioAndroid = (float) DMZBioAndroidConfig.QUARTER_HEALTH_LIFESTEAL.get() / 100;
										float lifesteal = adjustedDamage * passiveBioAndroid;
										if (lifesteal < 1) lifesteal = 1;
										atacante.heal(lifesteal);
									} else if (atacante.getHealth() < (cap.getMaxHealth() * 0.50)) {
										float passiveBioAndroid = (float) DMZBioAndroidConfig.HALF_HEALTH_LIFESTEAL.get() / 100;
										float lifesteal = adjustedDamage * passiveBioAndroid;
										if (lifesteal < 1) lifesteal = 1;
										atacante.heal(lifesteal);
									}
								}
								// Verificar si el atacante tiene algún arma de Ki activa, si las tiene, revisa su cantidad de Ki para hacer daño extra.
								if (ki_control && ki_manipulation && meditation && is_kimanipulation) {
									if (cap.getKiWeaponId().equals("scythe")) {
										float danoFinal = adjustedDamage + ((float) danoKiWeapon / 4);
										int kiCost = (int) (maxKi * 0.10);
										if (currKi > kiCost) {
											event.setAmount(danoFinal);
											cap.removeCurEnergy(kiCost);
										} else {
											event.setAmount(adjustedDamage);
											sonidosGolpes(atacante);
										}
									} else if (cap.getKiWeaponId().equals("trident")) {
										float danoFinal = adjustedDamage + ((float) danoKiWeapon / 2);
										int kiCost = (int) (maxKi * 0.16);
										if (currKi > kiCost) {
											event.setAmount(danoFinal);
											cap.removeCurEnergy(kiCost);
										} else {
											event.setAmount(adjustedDamage);
											sonidosGolpes(atacante);
										}
									} else {
										float danoFinal = adjustedDamage + ((float) danoKiWeapon / 8);
										int kiCost = (int) (maxKi * 0.05);
										if (currKi > kiCost) {
											event.setAmount(danoFinal);
											cap.removeCurEnergy(kiCost);
										} else {
											event.setAmount(adjustedDamage);
											sonidosGolpes(atacante);
										}
									}
								} else {
									event.setAmount(adjustedDamage);
									sonidosGolpes(atacante);
								}
								// Descontar stamina del atacante
								if (!atacante.isCreative() || !atacante.isSpectator()) {
									cap.removeCurStam(staminaToConsume);
								}
							} else {
								// Daño por defecto si al atacante le falta stamina
								event.setAmount(danoDefault);
							}

							// Si la entidad que recibe el daño es un jugador
							if (event.getEntity() instanceof Player objetivo) {
								DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, objetivo).ifPresent(statsObjetivo -> {

									int defObjetivo = dmzdatos.calcularDEF(statsObjetivo, objetivo);
									// Restar la defensa del objetivo al daño
									float danoFinal = event.getAmount() - defObjetivo;
									event.setAmount(Math.max(danoFinal, 1)); // Asegurarse de que al menos se haga 1 de daño
								});
							} else {
								// Si golpeas a otra entidad (no jugador), aplica el daño máximo basado en la fuerza
								event.setAmount(event.getAmount()); // Aplica tu máximo daño
							}
						} else {
							// Aquí manejamos el caso donde el atacante no es un jugador
							if (event.getEntity() instanceof Player objetivo) {
								DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, objetivo).ifPresent(statsObjetivo -> {

									int defObjetivo = dmzdatos.calcularDEF(cap, objetivo);

									// Restar la defensa del objetivo al daño
									float danoFinal = event.getAmount() - defObjetivo;
									event.setAmount(Math.max(danoFinal, 1)); // Asegurarse de que al menos se haga 1 de daño
								});
							}
						}
					}
				});
			}
		}
	}


	@SubscribeEvent
	public static void livingFallEvent(LivingFallEvent event) {
		float fallDistance = event.getDistance();

		DMZDatos dmzdatos = new DMZDatos();

		if (event.getEntity() instanceof ServerPlayer player) {
			if (fallDistance > 3.0f) {

				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
					boolean isDmzUser = stats.isAcceptCharacter();

					DMZSkill jump = stats.getDMZSkills().get("jump");
					DMZSkill fly = stats.getDMZSkills().get("fly");

					if (jump != null && jump.isActive() || fly != null && fly.isActive()) {
						event.setCanceled(true);
					} else {
						int maxEnergy = dmzdatos.calcularENE(stats);

						// drenaje de config
						int baseEnergyDrain = (int) Math.ceil(maxEnergy * DMZGeneralConfig.MULTIPLIER_FALLDMG.get());

						// Incrementar el drenaje por altura
						int extraEnergyDrain = (int) ((fallDistance - 4.5f) * baseEnergyDrain / 4.5f);

						int totalEnergyDrain = baseEnergyDrain + extraEnergyDrain;

						// Solo drenar energía si el jugador tiene suficiente y cancelar el daño
						if (isDmzUser && stats.getCurrentEnergy() >= totalEnergyDrain) {
							stats.removeCurEnergy(totalEnergyDrain);
							event.setCanceled(true);
						}
					}
				});
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onKeyInputEvent(InputEvent.Key event) {
		boolean isKiChargeKeyPressed = Keys.KI_CHARGE.isDown();
		boolean isDescendKeyPressed = Keys.DESCEND_KEY.isDown();
		boolean isTurboKeypressed = Keys.TURBO_KEY.consumeClick();
		boolean isTransformKeyPressed = Keys.TRANSFORM.isDown();
		boolean isUtilityPanelKeyPressed = Keys.UTILITY_PANEL.isDown();

		LocalPlayer player = Minecraft.getInstance().player;

		if (player != null) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
				int curEne = stats.getCurrentEnergy();
				int maxEne = stats.getMaxEnergy();
				int porcentaje = (int) Math.ceil((double) (curEne * 100) / maxEne);

				if (stats.isAcceptCharacter()) {

					//Cargar Ki
					if (isKiChargeKeyPressed && !previousKiChargeState && !transformOn) {
						ModMessages.sendToServer(new CharacterC2S("isAuraOn", 1));
						previousKiChargeState = true; // Actualiza el estado previo
						playSoundOnce(MainSounds.AURA_START.get());
						startLoopSound(MainSounds.KI_CHARGE_LOOP.get(), "ki");
					} else if (!isKiChargeKeyPressed && previousKiChargeState) {
						ModMessages.sendToServer(new CharacterC2S("isAuraOn", 0));
						previousKiChargeState = false; // Actualiza el estado previo
						stopLoopSound("ki");
					}

					// Descender de ki
					if (isDescendKeyPressed && !previousKeyDescendState) {
						ModMessages.sendToServer(new CharacterC2S("isDescendOn", 1));
						previousKeyDescendState = true; // Actualiza el estado previo
					} else if (!isDescendKeyPressed && previousKeyDescendState) {
						ModMessages.sendToServer(new CharacterC2S("isDescendOn", 0));
						previousKeyDescendState = false; // Actualiza el estado previo
					}

					//Turbo
					if (isTurboKeypressed) {
						if (!turboOn && porcentaje > 10) {
							// Solo activar Turbo si tiene más del 10% de energía
							turboOn = true;
							ModMessages.sendToServer(new CharacterC2S("isTurboOn", 1));
							ModMessages.sendToServer(new PermaEffC2S("add", "turbo", 1));
							playSoundOnce(MainSounds.AURA_START.get());
							startLoopSound(MainSounds.TURBO_LOOP.get(), "turbo");
							setTurboSpeed(player, true);
						} else if (turboOn) {
							// Permitir desactivar Turbo incluso si el porcentaje es menor al 10%
							turboOn = false;
							ModMessages.sendToServer(new CharacterC2S("isTurboOn", 0));
							ModMessages.sendToServer(new PermaEffC2S("remove", "turbo", 1));
							stopLoopSound("turbo");
							setTurboSpeed(player, false);
						} else {
							player.displayClientMessage(Component.translatable("ui.dmz.turbo_fail"), true);
						}
					}

					// Desactivar Turbo automáticamente si la energía llega a 1
					if (turboOn && curEne <= 1) {
						turboOn = false;
						ModMessages.sendToServer(new CharacterC2S("isTurboOn", 0));
						ModMessages.sendToServer(new PermaEffC2S("remove", "turbo", 1));
						stopLoopSound("turbo");
						setTurboSpeed(player, false);
					}

					// Transformación
					if (isDescendKeyPressed && isTransformKeyPressed) {
						ModMessages.sendToServer(new CharacterC2S("isTransform", 0));
						transformOn = false;
						ModMessages.sendToServer(new CharacterC2S("isAuraOn", 0));
						stopLoopSound("ki");
						ModMessages.sendToServer(new DescendFormC2S());
					} else if (transformOn && !isTransformKeyPressed) { // Al soltar la tecla, desactiva transformación
						ModMessages.sendToServer(new CharacterC2S("isTransform", 0));
						ModMessages.sendToServer(new CharacterC2S("isAuraOn", 0));
						stopLoopSound("ki");
						stopLoopSound("oozaru");
						transformOn = false;
					} else if (getNextForm(stats) != null) {
						if (isTransformKeyPressed && !transformOn) { // Solo activa si no estaba transformado
							ModMessages.sendToServer(new CharacterC2S("isTransform", 1));
							transformOn = true;
							if (getNextForm(stats).equals("oozaru")) {
								if (player.getXRot() <= -45.0F && player.level().getMoonPhase() == 0 && player.level().getDayTime() % 24000 >= 13500) {
									startLoopSound(MainSounds.OOZARU_HEARTBEAT.get(), "oozaru");
								}
							} else {
								ModMessages.sendToServer(new CharacterC2S("isAuraOn", 1));
								playSoundOnce(MainSounds.AURA_START.get());
								startLoopSound(MainSounds.KI_CHARGE_LOOP.get(), "ki");
							}
						}

						if (transformOn && stats.getFormRelease() >= 100) {
							ModMessages.sendToServer(new CharacterC2S("isAuraOn", 0));
							stopLoopSound("ki");
							stopLoopSound("oozaru");
						}
					} else if (isTransformKeyPressed && stats.getFormRelease() >= 100) {
						ModMessages.sendToServer(new CharacterC2S("isAuraOn", 0));
						stopLoopSound("ki");
						stopLoopSound("oozaru");
					}

				}
			});
		}
	}

	@SubscribeEvent
	@SuppressWarnings("removal")
	public static void onPlayerSizeChange(EntityEvent.Size event) {
		if(event.getEntity() instanceof Player player){
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
				var raza = cap.getRace();
				var transf = cap.getDmzForm();

				switch (raza) {
					case 1: // Saiyan
						switch (transf) {
							case "oozaru", "goldenoozaru":
								// Ajustamos el ancho y la altura según el estado del jugador
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(2.3F, 2.4F, true));
									event.setNewEyeHeight(1.7F);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(2.3F, 6.2F, true));
									event.setNewEyeHeight(5.5F);
								} else if(player.isVisuallyCrawling()){
									event.setNewSize(new EntityDimensions(2.3F, 2.4F, true));
									event.setNewEyeHeight(1.7F);
								} else {
									event.setNewSize(new EntityDimensions(2.3F, 7.2F, true));
									event.setNewEyeHeight(6.2F);
								}
								break;
							case "ssj1":
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(0.6F, 0.6F, true));
									event.setNewEyeHeight(0.4f);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(0.5F, 1.6F, true));
									event.setNewEyeHeight(1.3f);
								} else if(player.isVisuallyCrawling()){
									event.setNewSize(new EntityDimensions(0.6F, 0.6F, true));
									event.setNewEyeHeight(0.4f);
								} else {
									event.setNewSize(new EntityDimensions(0.6F, 1.9F, true));
									event.setNewEyeHeight(1.65f);
								}

								break;
							case "ssgrade2":
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(0.7F, 0.6F, true));
									event.setNewEyeHeight(0.4F);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(0.7F, 1.5F, true));
									event.setNewEyeHeight(1.4F);
								} else if(player.isVisuallyCrawling()) {
									event.setNewSize(new EntityDimensions(0.7F, 0.6F, true));
									event.setNewEyeHeight(0.4F);
								}else {
									event.setNewSize(new EntityDimensions(0.7F, 2.0F, true));
									event.setNewEyeHeight(1.7F);
								}
								break;
							case "ssgrade3":
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(0.8F, 0.8F, true));
									event.setNewEyeHeight(0.5F);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(0.7F, 1.7F, true));
									event.setNewEyeHeight(1.6F);
								} else if(player.isVisuallyCrawling()) {
									event.setNewSize(new EntityDimensions(0.8F, 0.8F, true));
									event.setNewEyeHeight(0.5F);
								}else {
									event.setNewSize(new EntityDimensions(0.7F, 2.2F, true));
									event.setNewEyeHeight(1.9F);
								}
								break;
							default:
								break;
						}
						break;
					case 2: // Namek
						switch (transf) {
							case "orange_giant", "giant":
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(2.3F, 2.4F, true));
									event.setNewEyeHeight(1.7F);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(2.3F, 6.2F, true));
									event.setNewEyeHeight(5.5F);
								} else if(player.isVisuallyCrawling()){
									event.setNewSize(new EntityDimensions(2.3F, 2.4F, true));
									event.setNewEyeHeight(1.7F);
								} else {
									event.setNewSize(new EntityDimensions(2.3F, 7.2F, true));
									event.setNewEyeHeight(6.2F);
								}
								break;
							case "super_namek":
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(0.6F, 0.6F, true));
									event.setNewEyeHeight(0.4f);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(0.5F, 1.6F, true));
									event.setNewEyeHeight(1.3f);
								} else if(player.isVisuallyCrawling()){
									event.setNewSize(new EntityDimensions(0.6F, 0.6F, true));
									event.setNewEyeHeight(0.4f);
								} else {
									event.setNewSize(new EntityDimensions(0.6F, 1.9F, true));
									event.setNewEyeHeight(1.65f);
								}
								break;
							default:
								break;
						}
						break;
					case 3: // BioAndroide
						switch (transf) {
							case "semi_perfect":
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(0.7F, 0.6F, true));
									event.setNewEyeHeight(0.4F);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(0.7F, 1.5F, true));
									event.setNewEyeHeight(1.4F);
								} else if(player.isVisuallyCrawling()) {
									event.setNewSize(new EntityDimensions(0.7F, 0.6F, true));
									event.setNewEyeHeight(0.4F);
								}else {
									event.setNewSize(new EntityDimensions(0.7F, 2.0F, true));
									event.setNewEyeHeight(1.7F);
								}
								break;
							default:
								break;
						}

						break;
					case 4: // ColdDemon
						switch (transf) {
							case "second_form":
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(1.0F, 0.9F, true));
									event.setNewEyeHeight(0.5F);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(0.9F, 2.4F, true));
									event.setNewEyeHeight(2.0F);
								} else if(player.isVisuallyCrawling()) {
									event.setNewSize(new EntityDimensions(1.0F, 0.9F, true));
									event.setNewEyeHeight(0.5F);
								}else {
									event.setNewSize(new EntityDimensions(0.9F, 2.8F, true));
									event.setNewEyeHeight(2.4F);
								}
								break;
							case "third_form":
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(1.0F, 0.9F, true));
									event.setNewEyeHeight(0.5F);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(0.9F, 2.2F, true));
									event.setNewEyeHeight(1.8F);
								} else if(player.isVisuallyCrawling()) {
									event.setNewSize(new EntityDimensions(1.0F, 0.9F, true));
									event.setNewEyeHeight(0.5F);
								}else {
									event.setNewSize(new EntityDimensions(0.8F, 2.6F, true));
									event.setNewEyeHeight(2.1F);
								}
								break;
							default:
								break;
						}
						break;
					case 5: // Majin
						switch (transf){
							case "kid":
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(0.4F, 0.4F, true));
									event.setNewEyeHeight(0.3F);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(0.4F, 1.5F, true));
									event.setNewEyeHeight(1.2F);
								} else if(player.isVisuallyCrawling()) {
									event.setNewSize(new EntityDimensions(0.4F, 0.4F, true));
									event.setNewEyeHeight(0.3F);
								}else {
									event.setNewSize(new EntityDimensions(0.4F, 1.7F, true));
									event.setNewEyeHeight(1.4F);
								}
								break;
							default:
								break;
						}
						break;
					default: //Humano
						switch (transf) {
							case "buffed":
								if (player.isSwimming()) {
									event.setNewSize(new EntityDimensions(0.8F, 0.8F, true));
									event.setNewEyeHeight(0.5F);
								} else if (player.isCrouching()) {
									event.setNewSize(new EntityDimensions(0.7F, 1.7F, true));
									event.setNewEyeHeight(1.6F);
								} else if(player.isVisuallyCrawling()) {
									event.setNewSize(new EntityDimensions(0.8F, 0.8F, true));
									event.setNewEyeHeight(0.5F);
								}else {
									event.setNewSize(new EntityDimensions(0.7F, 2.2F, true));
									event.setNewEyeHeight(1.9F);
								}
								break;
						}
							break;
				}


			});
		}

	}

	@OnlyIn(Dist.CLIENT)
	private static void playSoundOnce(SoundEvent soundEvent) {
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			LocalPlayer player = Minecraft.getInstance().player;
			if (player != null) {
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(),
						soundEvent, SoundSource.PLAYERS, 1.0F, 1.0F, false);
			}
		});
	}

	@OnlyIn(Dist.CLIENT)
	private static void startLoopSound(SoundEvent soundEvent, String sonido) {
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			LocalPlayer player = Minecraft.getInstance().player;
			if (player == null) return;

			SimpleSoundInstance loopSound = new SimpleSoundInstance(
					soundEvent.getLocation(),
					SoundSource.PLAYERS,
					1.0F, 1.0F,
					player.level().random,
					true, 0,
					SimpleSoundInstance.Attenuation.LINEAR,
					player.getX(), player.getY(), player.getZ(),
					false
			);

			Minecraft.getInstance().getSoundManager().play(loopSound);
			switch (sonido) {
				case "ki":
					kiChargeLoop = loopSound;
					break;
				case "turbo":
					turboLoop = loopSound;
					break;
				case "oozaru":
					oozaruLoop = loopSound;
					break;
			}
		});
	}

	@OnlyIn(Dist.CLIENT)
	private static void stopLoopSound(String sonido) {
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			if (sonido.equals("ki") && kiChargeLoop != null) {
				Minecraft.getInstance().getSoundManager().stop(kiChargeLoop);
				kiChargeLoop = null;
			} else if (sonido.equals("turbo") && turboLoop != null) {
				Minecraft.getInstance().getSoundManager().stop(turboLoop);
				turboLoop = null;
			} else if (sonido.equals("oozaru") && oozaruLoop != null) {
				Minecraft.getInstance().getSoundManager().stop(oozaruLoop);
				oozaruLoop = null;
			}
		});
	}

	private static final double originalSpeed = 0.10000000149011612;

	private static void setTurboSpeed(Player player, boolean enable) {
		AttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
		if (speedAttribute == null) return;

		if (enable) {
			speedAttribute.setBaseValue(originalSpeed + 0.06);
		} else {
			speedAttribute.setBaseValue(originalSpeed);
		}
	}

	private static void sonidosGolpes(Player player) {

		SoundEvent[] golpeSounds = {
				MainSounds.GOLPE1.get(),
				MainSounds.GOLPE2.get(),
				MainSounds.GOLPE3.get(),
				MainSounds.GOLPE4.get(),
				MainSounds.GOLPE5.get(),
				MainSounds.GOLPE6.get()
		};

		Random rand = new Random();
		int randomIndex = rand.nextInt(golpeSounds.length);
		SoundEvent randomGolpeSound = golpeSounds[randomIndex];

		player.level().playSound(null, player.getOnPos(), randomGolpeSound, SoundSource.PLAYERS, 2.2F, 0.9F);

	}

	public static int senzuContador(int segundos) {
		if (segundos > 0) return segundos - 1;
		return 0;
	}

	public static int zenkaiContador(int segundos) {
		if (segundos > 0) return segundos - 1;
		return 0;
	}

	public static int babaCooldown(int segundos) {
		if (segundos > 0) return segundos - 1;
		return 0;
	}

	public static int babaDuration(int segundos) {
		if (segundos > 0) return segundos - 1;
		return 0;
	}

	public static String getNextForm(DMZStatsAttributes playerstats) {
		int race = playerstats.getRace();
		int superFormLvl = playerstats.getFormSkillLevel("super_form");
		String groupForm = playerstats.getDmzGroupForm();
		String dmzForm = playerstats.getDmzForm();

		// Definir las transformaciones por grupo
		Map<String, String[]> saiyanForms = Map.of(
				"", new String[]{"oozaru", "goldenoozaru"},
				"ssgrades", new String[]{"ssj1", "ssgrade2", "ssgrade3"},
				"ssj", new String[]{"ssjfp", "ssj2", "ssj3"}
		);
		// Lógica de transformación para Humanos
		if (race == 0 && groupForm.equals("")) {
			if (superFormLvl >= 1 && dmzForm.equals("base")) return "buffed";
			if (superFormLvl >= 2 && dmzForm.equals("buffed")) return "full_power";
			if (superFormLvl >= 3 && dmzForm.equals("full_power")) return "potential_unleashed";
		}
		// Lógica de transformación para Saiyans
		if (race == 1) {
			if (!saiyanForms.containsKey(groupForm)) return null;

			// Lógica de transformación para Saiyans
			if (superFormLvl >= 1 && groupForm.equals("") && dmzForm.equals("base")) {
				return "oozaru";
			}
			if (superFormLvl >= 8 && groupForm.equals("") && dmzForm.equals("oozaru")) {
				return "goldenoozaru";
			}
			if (superFormLvl >= 2 && groupForm.equals("ssgrades")) {
				if (superFormLvl >= 2 && dmzForm.equals("base")) return "ssj1";
				if (superFormLvl >= 3 && dmzForm.equals("ssj1")) return "ssgrade2";
				if (superFormLvl >= 4 && dmzForm.equals("ssgrade2")) return "ssgrade3";
			}
			if (superFormLvl >= 5 && groupForm.equals("ssj")) {
				if (superFormLvl >= 5 && dmzForm.equals("base")) return "ssjfp";
				if (superFormLvl >= 6 && dmzForm.equals("ssjfp")) return "ssj2";
				if (superFormLvl >= 7 && dmzForm.equals("ssj2")) return "ssj3";
			}
		}
		// Lógica de transformación para Namekianos
		if (race == 2 && groupForm.equals("")) {
			if (superFormLvl >= 1 && dmzForm.equals("base")) return "giant";
			if (superFormLvl >= 2 && dmzForm.equals("giant")) return "full_power";
			if (superFormLvl >= 4 && dmzForm.equals("full_power")) return "super_namek";
		}

		// Lógica de transformación para Bioandroides
		if (race == 3 && groupForm.equals("")) {
			if (superFormLvl >= 2 && dmzForm.equals("base")) {
				return "semi_perfect";
			}
			if (superFormLvl >= 4 && dmzForm.equals("semi_perfect")) {
				return "perfect";
			}
		}

		// Lógica de transformación para Cold Demons
		if (race == 4 && groupForm.equals("")) {
			if (superFormLvl >= 2 && dmzForm.equals("base")) {
				return "second_form";
			}
			if (superFormLvl >= 4 && dmzForm.equals("second_form")) {
				return "third_form";
			}
			if (superFormLvl >= 6 && dmzForm.equals("third_form")) {
				return "final_form";
			}
			if (superFormLvl >= 8 && dmzForm.equals("final_form")) {
				return "full_power";
			}
		}

		// Lógica de transformación para Majins
		if (race == 5 && groupForm.equals("")) {
			if (superFormLvl >= 2 && dmzForm.equals("base")) {
				return "evil";
			}
			if (superFormLvl >= 4 && dmzForm.equals("evil")) {
				return "kid";
			}
			if (superFormLvl >= 6 && dmzForm.equals("kid")) {
				return "super";
			}
			if (superFormLvl >= 8 && dmzForm.equals("super")) {
				return "ultra";
			}
		}

		return null; // No hay transformación disponible
	}

}



