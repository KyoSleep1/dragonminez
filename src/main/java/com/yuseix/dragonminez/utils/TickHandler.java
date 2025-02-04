package com.yuseix.dragonminez.utils;

import com.yuseix.dragonminez.network.C2S.FlyToggleC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.skills.DMZSkill;

public class TickHandler {
    private int energyRegenCounter = 0;
    private int staminaRegenCounter = 0;
    private int energyConsumeCounter = 0;
    private int chargeTimer = 0; // Aca calculamos el tiempo de espera
    private int flyTimer = 0;

    private final int CHARGE_INTERVAL = 1 * (20); // No borrar el 20, eso es el tiempo en ticks lo que si puedes configurar es lo que esta la lado

    public void tickRegenConsume(DMZStatsAttributes playerStats, DMZDatos dmzDatos) {
        DMZSkill meditation = playerStats.getDMZSkills().get("meditation");
        DMZSkill flySkill = playerStats.getDMZSkills().get("fly");

        // Regeneración de stamina cada 1 segundo
        staminaRegenCounter++;
        if (staminaRegenCounter >= 20) {
            int maxStamina = dmzDatos.calcularSTM(playerStats.getRace(), playerStats.getMaxHealth());
            System.out.println("Max Stamina CALC: " + Math.round(playerStats.getConstitution() * 0.5));
            System.out.println("Max Stamina: " + maxStamina);
            int regenStamina = (int) Math.round(maxStamina / 12.0);
            System.out.println("Regen Stamina: " + regenStamina);
            if (playerStats.getCurStam() < maxStamina) {
                if (meditation != null) {
                    // Si tiene meditación, aumenta o reduce según el nivel de meditación (+10% por nivel)
                    int medLevel = meditation.getLevel();
                    regenStamina += (int) Math.ceil(regenStamina * 0.1 * medLevel);
                }
                playerStats.addCurStam(regenStamina);
                staminaRegenCounter = 0;
                System.out.println("Stamina regen: " + regenStamina);
            } else {
                System.out.println("Current stamina: " + playerStats.getCurStam());
                staminaRegenCounter = 0;
            }
        }

        // Regeneración de energía cada 1 segundo (con turbo activo o no)
        energyRegenCounter++;
        if (energyRegenCounter >= 20) {
            int maxEnergy = dmzDatos.calcularENE(playerStats.getRace(), playerStats.getEnergy(), playerStats.getDmzClass());

            if (playerStats.isTurbonOn()) {
                // Si el turbo está activo, consumo de energía
                int consumeEnergy = dmzDatos.calcularKiRegen(playerStats.getRace(), maxEnergy, playerStats.getDmzClass()) * 2;
                if (consumeEnergy < 2) consumeEnergy = 2;
                if (meditation != null) {
                    // Reduce 5% del consumo por nivel de meditación
                    int medLevel = meditation.getLevel();
                    consumeEnergy -= (int) Math.ceil(consumeEnergy * 0.05 * medLevel);
                }
                playerStats.removeCurEnergy(consumeEnergy);
            } else if (!playerStats.isTurbonOn() && playerStats.getCurrentEnergy() < maxEnergy) {
                if (flySkill == null || !flySkill.isActive()) {
                    // Si el turbo no está activo, regeneración de energía
                    int regenEnergy = dmzDatos.calcularKiRegen(playerStats.getRace(), maxEnergy, playerStats.getDmzClass()) / 2;
                    if (regenEnergy < 1) regenEnergy = 1;

                    if (meditation != null) {
                        // Aumenta 10% de la regeneración por nivel de meditación
                        int medLevel = meditation.getLevel();
                        regenEnergy += (int) Math.ceil(regenEnergy * 0.1 * medLevel);
                    }
                    playerStats.addCurEnergy(regenEnergy);
                }
            }
            energyRegenCounter = 0;
        }

        // Consumo de energía cada 3 segundos
        energyConsumeCounter++;
        if (energyConsumeCounter >= 20 * 3) {
            int consumeEnergy = dmzDatos.calcularKiConsume(playerStats.getRace(), playerStats.getEnergy(), playerStats.getDmzState());
            playerStats.removeCurEnergy(consumeEnergy);
            energyConsumeCounter = 0;
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

        if (chargeTimer >= CHARGE_INTERVAL) {
            if (playerstats.isAuraOn() && playerstats.isDescendKeyOn()) {
                if (playerstats.getDmzRelease() > 0) {
                    playerstats.setDmzRelease(playerstats.getDmzRelease() - 5);
                    if (playerstats.getDmzRelease() < 0) {
                        playerstats.setDmzRelease(0);
                    }
                }
            } else if (playerstats.isAuraOn()) {
                if (playerstats.getDmzRelease() < maxRelease) {
                    playerstats.setDmzRelease(playerstats.getDmzRelease() + 5);
                    if (playerstats.getDmzRelease() > maxRelease) {
                        playerstats.setDmzRelease(maxRelease);
                    }
                }
                if (!playerstats.isTurbonOn() && flySkill != null && !flySkill.isActive()) {
                    if (playerstats.getCurrentEnergy() < maxenergia) {
                        int kiRegen  = dmzdatos.calcularCargaKi(maxenergia, playerstats.getDmzClass());
                        if (meditation != null) {
                            kiRegen += (int) Math.ceil(kiRegen * 0.10 * meditationLevel);
                        }
                        playerstats.addCurEnergy(kiRegen);
                    }
                }
            }

            if (playerstats.isTurbonOn() && playerstats.getCurrentEnergy() <= 1) {
                playerstats.setDmzRelease(0);
            }

            chargeTimer = 0;
        }
    }

    public void manejarFlyConsume(DMZStatsAttributes playerStats, int maxEnergy) {
        DMZSkill flySkill = playerStats.getDMZSkills().get("fly");
        int flyLevel = flySkill != null ? flySkill.getLevel() : 0;

        // Solo consume Ki si la habilidad está a nivel 7 o menos. A partir de nivel 8, no consume Ki.
        if (flySkill != null && flyLevel > 0 && flyLevel <= 7 && flySkill.isActive()) {
            flyTimer++;

            if (flyTimer >= 20) {
                int consumeEnergy = 0;
                if (flyLevel < 4) {
                    consumeEnergy = (int) Math.ceil(maxEnergy * 0.04);
                } else {
                    consumeEnergy = (int) Math.ceil(maxEnergy * 0.02);
                }

                if (playerStats.getCurrentEnergy() < consumeEnergy) ModMessages.sendToServer(new FlyToggleC2S());
                playerStats.removeCurEnergy(consumeEnergy);
                flyTimer = 0;
            }
        }
    }
}
