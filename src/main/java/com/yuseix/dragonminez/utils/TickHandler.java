package com.yuseix.dragonminez.utils;

import com.yuseix.dragonminez.config.races.DMZHumanConfig;
import com.yuseix.dragonminez.config.races.DMZMajinConfig;
import com.yuseix.dragonminez.config.races.DMZNamekConfig;
import com.yuseix.dragonminez.config.races.DMZSaiyanConfig;
import com.yuseix.dragonminez.events.characters.StatsEvents;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
import net.minecraft.server.level.ServerPlayer;

public class TickHandler {
	private int energyRegenCounter = 0;
	private int staminaRegenCounter = 0;
	private int energyConsumeCounter = 0;
	private int chargeTimer = 0; // Aca calculamos el tiempo de espera
	private int dmzformTimer = 0; // Aca calculamos el tiempo de espera
	private int flyTimer = 0;
	private int passiveMajinCounter = 0, passiveSaiyanCounter = 0;

	public void tickRegenConsume(DMZStatsAttributes playerStats, DMZDatos dmzDatos, ServerPlayer player) {
		DMZSkill meditation = playerStats.getDMZSkills().get("meditation");
		DMZSkill flySkill = playerStats.getDMZSkills().get("fly");
		var raza = playerStats.getRace();

		// Regeneración de stamina cada 1 segundo
		staminaRegenCounter++;
		if (staminaRegenCounter >= 20) {
			if (!player.isCreative() && !player.isSpectator()) {
				int maxStamina = dmzDatos.calcularSTM(playerStats);
				int regenStamina = Math.max((int) Math.round(maxStamina / 12.0), 1);
				if (playerStats.getCurStam() < maxStamina) {
					if (meditation != null) {
						// Si tiene meditación, aumenta o reduce según el nivel de meditación (+10% por nivel)
						int medLevel = meditation.getLevel();
						regenStamina += (int) Math.ceil(regenStamina * 0.1 * medLevel);
					}
					playerStats.addCurStam(regenStamina);
					staminaRegenCounter = 0;
				}
			}
		}

		// Regeneración de energía cada 1 segundo (con turbo activo o no)
		energyRegenCounter++;
		if (energyRegenCounter >= 20) {
			if (!player.isCreative() && !player.isSpectator()) {

				int maxEnergy = dmzDatos.calcularENE(playerStats);

				if (playerStats.isTurboOn()) {
					// Si el turbo está activo, consumo de energía
					int consumeEnergy = dmzDatos.calcularKiRegen(playerStats) * 2;
					if (consumeEnergy < 2) consumeEnergy = 2;
					if (meditation != null) {
						// Reduce 5% del consumo por nivel de meditación
						int medLevel = meditation.getLevel();
						consumeEnergy -= (int) Math.ceil(consumeEnergy * 0.05 * medLevel);
					}
					playerStats.removeCurEnergy(consumeEnergy);
				} else if (!playerStats.isTurboOn() && playerStats.getCurrentEnergy() < maxEnergy) {
					if (flySkill != null && flySkill.isActive() && flySkill.getLevel() <= 7) {
						// No hacer nada
					} else {
						// Si el turbo no está activo, regeneración de energía
						int regenEnergy = dmzDatos.calcularKiRegen(playerStats) / 2;
						if (regenEnergy < 1) regenEnergy = 1;

						if (meditation != null) {
							// Aumenta 10% de la regeneración por nivel de meditación
							int medLevel = meditation.getLevel();
							regenEnergy += (int) Math.ceil(regenEnergy * 0.1 * medLevel);
						}
						if (raza == 2) {
							float passiveNamek = (float) DMZNamekConfig.PASSIVE_REGEN.get() / 100;
							// Aumenta 40% (default) de la regeneración por ser Namek
							regenEnergy += (int) Math.ceil(regenEnergy * passiveNamek);
						}

						playerStats.addCurEnergy(regenEnergy);
					}
				}
				energyRegenCounter = 0;
			}
		}

		// Consumo de energía cada 1 segundo
		energyConsumeCounter++;
		if (energyConsumeCounter >= 20) {
			if (!player.isCreative() && !player.isSpectator()) {
				int consumeEnergy = dmzDatos.calcularKiConsume(playerStats);
				if (playerStats.getCurrentEnergy() < consumeEnergy) {
					System.out.println("Consumes " + consumeEnergy + " energy");
					System.out.println("No tienes suficiente energía para mantener la forma.");
					playerStats.setDmzForm("base");
					energyConsumeCounter = 0;
				} else {
					playerStats.removeCurEnergy(consumeEnergy);
					energyConsumeCounter = 0;
				}
			}
		}
	}

	public void manejarPasivaMajin(DMZStatsAttributes playerstats, ServerPlayer player) {
		// Pasiva del Majin: Regen de HP
		passiveMajinCounter++;
		if (passiveMajinCounter >= 20) {
			if (playerstats.getRace() == 5 && player.getHealth() < playerstats.getMaxHealth()) {
				double passiveMajin = DMZMajinConfig.PASSIVE_HEALTH_REGEN.get() / 100;
				int regenHP = (int) Math.ceil(playerstats.getMaxHealth() * passiveMajin);
				if (regenHP < 1) regenHP = 1;
				player.heal(regenHP);
			}
			passiveMajinCounter = 0;
		}
	}

	public void manejarPasivaSaiyan(DMZStatsAttributes playerstats, ServerPlayer player) {
		// Pasiva del Saiyan: Zenkai
		/* Si el jugador permanece debajo del 10% de vida, durante 6 segundos, recupera 25% (config) de vida
		 * y obtiene un aumento de stats del 10% (config) actuales. Esto tiene un cooldown de 45 minutos (config).
		 */
		float zenkaiHealth = (float) DMZSaiyanConfig.ZENKAI_HEALTH_REGEN.get() / 100;
		float zenkaiStatBoost = (float) DMZSaiyanConfig.ZENKAI_STAT_BOOST.get() / 100;
		int zenkaiCant = DMZSaiyanConfig.ZENKAI_CANT.get();
		int zenkaiCooldown = DMZSaiyanConfig.ZENKAI_COOLDOWN.get();

		if (player.getHealth() < (playerstats.getMaxHealth() * 0.10)) {
			passiveSaiyanCounter++;
			if (passiveSaiyanCounter >= 20 * 6 && playerstats.getZenkaiCount() < zenkaiCant) {
				player.heal((int) Math.ceil(playerstats.getMaxHealth() * zenkaiHealth));
				playerstats.setStrength((int) (playerstats.getStrength() + (playerstats.getStrength() * zenkaiStatBoost)));
				playerstats.setDefense((int) (playerstats.getDefense() + (playerstats.getDefense() * zenkaiStatBoost)));
				playerstats.setConstitution((int) (playerstats.getConstitution() + (playerstats.getConstitution() * zenkaiStatBoost)));
				playerstats.setKiPower((int) (playerstats.getKiPower() + (playerstats.getKiPower() * zenkaiStatBoost)));
				playerstats.setEnergy((int) (playerstats.getEnergy() + (playerstats.getEnergy() * zenkaiStatBoost)));
				int cantZenkais = playerstats.getZenkaiCount() + 1;

				playerstats.setZenkaiCount(cantZenkais);
				playerstats.setSaiyanZenkaiTimer((zenkaiCooldown * 20 * 60));
				passiveSaiyanCounter = 0;
			}
		} else {
			passiveSaiyanCounter = 0;
		}
	}

	public void manejarCargaDeAura(DMZStatsAttributes playerstats, int maxenergia) {
		// Incrementa el temporizador en cada tick
		chargeTimer++;

		DMZDatos dmzdatos = new DMZDatos();
		DMZSkill meditation = playerstats.getDMZSkills().get("meditation");
		int meditationLevel = meditation != null ? meditation.getLevel() : 0;
		DMZSkill potUnlock = playerstats.getDMZSkills().get("potential_unlock");
		DMZSkill flySkill = playerstats.getDMZSkills().get("fly");
		int potUnlockLevel = potUnlock != null ? potUnlock.getLevel() : 0;
		int maxRelease = 50 + (potUnlockLevel * 5);
		var raza = playerstats.getRace();

		if (chargeTimer >= 20) {
			if (playerstats.isAuraOn() && playerstats.isDescendKeyOn()) {
				if (playerstats.getDmzRelease() > 0) {
					playerstats.setDmzRelease(playerstats.getDmzRelease() - 1);
					if (playerstats.getDmzRelease() < 0) {
						playerstats.setDmzRelease(0);
					}
				}
				chargeTimer = 16;
			} else if (playerstats.isAuraOn()) {
				if (playerstats.getDmzRelease() < maxRelease) {
					playerstats.setDmzRelease(playerstats.getDmzRelease() + 5);
					if (playerstats.getDmzRelease() > maxRelease) {
						playerstats.setDmzRelease(maxRelease);
					}
				}
				if (!playerstats.isTurboOn()) {
					if (playerstats.getCurrentEnergy() < maxenergia) {
						if ((flySkill != null && flySkill.isActive() && flySkill.getLevel() <= 7) || playerstats.isTransforming()) {
							// No hacer nada
						} else {
							int kiRegen = dmzdatos.calcularCargaKi(playerstats);
							if (meditation != null) {
								kiRegen += (int) Math.ceil(kiRegen * 0.10 * meditationLevel);
							}
							if (raza == 0) {
								float passiveHuman = (float) DMZHumanConfig.KICHARGE_REGEN_BOOST.get() / 100;
								// Aumenta 25% (default) de la regeneración por ser humano
								kiRegen += (int) Math.ceil(kiRegen * passiveHuman);
							}
							playerstats.addCurEnergy(kiRegen);
						}
					}
				}
				if (playerstats.isTurboOn() && playerstats.getCurrentEnergy() <= 1) {
					playerstats.setDmzRelease(0);
				}
				chargeTimer = 0;
			}
		}
	}

	public void manejarCargaForma(DMZStatsAttributes playerstats, ServerPlayer player) {
		if (!playerstats.hasFormSkill("super_form")) return;

		int formLevel = playerstats.getFormSkillLevel("super_form");


		if (playerstats.isTransforming() && playerstats.getFormRelease() >= 100) {
			manejarForms(playerstats);
			playerstats.setFormRelease(0);
		}

		if (playerstats.isTransforming() && playerstats.getFormRelease() >= 0) {
			if (StatsEvents.getNextForm(playerstats) == null) return;
			dmzformTimer++;
			if (dmzformTimer >= 20) {
				if (StatsEvents.getNextForm(playerstats).equals("oozaru")) {
					// Si el jugador está mirando hacia arriba y (es de noche + luna llena), aumenta la carga
					if (player.getXRot() <= -45.0F && player.level().getMoonPhase() == 0 && player.level().getDayTime() % 24000 >= 13000) {
						playerstats.setFormRelease(playerstats.getFormRelease() + 10);
					}
				} else playerstats.setFormRelease(playerstats.getFormRelease() + 5 + (5 * formLevel));
				dmzformTimer = 0;
			}
		} else if (!playerstats.isTransforming() && playerstats.getFormRelease() > 0) {
			playerstats.setFormRelease(playerstats.getFormRelease() - 1);
			if (dmzformTimer != 0) dmzformTimer = 0;
		}
	}

	public void manejarForms(DMZStatsAttributes playerstats) {
		if (playerstats.getFormRelease() < 100 || !playerstats.hasFormSkill("super_form")) return;

		// Obtener la siguiente transformación posible
		String nextForm = StatsEvents.getNextForm(playerstats);

		if (nextForm != null) {
			playerstats.setDmzForm(nextForm); // Transformar al jugador
		}
	}


	public void manejarFlyConsume(DMZStatsAttributes playerStats, int maxEnergy, ServerPlayer player) {
		DMZSkill flySkill = playerStats.getDMZSkills().get("fly");
		int flyLevel = flySkill != null ? flySkill.getLevel() : 0;
		if (player.isCreative() || player.isSpectator()) return;

		// Solo consume Ki si la habilidad está a nivel 7 o menos. A partir de nivel 8, no consume Ki.
		if (flyLevel > 0 && flyLevel <= 7 && flySkill.isActive()) {
			flyTimer++;

			if (flyTimer >= 20) {
				int consumeEnergy = 0;
				if (flyLevel < 4) {
					consumeEnergy = (int) Math.ceil(maxEnergy * 0.04);
				} else {
					consumeEnergy = (int) Math.ceil(maxEnergy * 0.02);
				}

				if (playerStats.getCurrentEnergy() < consumeEnergy) {
					if (!player.isCreative() && !player.isSpectator()) player.getAbilities().mayfly = false;

					player.getAbilities().flying = false;
					player.fallDistance = 0;
					player.onUpdateAbilities();
					flySkill.setActive(false);
				} else {
					playerStats.removeCurEnergy(consumeEnergy);
					flyTimer = 0;
				}
			}
		}
	}
}
