package com.yuseix.dragonminez.common.stats;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.network.ModMessages;
import com.yuseix.dragonminez.common.network.S2C.*;
import com.yuseix.dragonminez.common.util.DMZDatos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class DMZStatsCapabilities {

    private DMZDatos dmzdatos = new DMZDatos();

    public static final Capability<DMZStatsAttributes> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        syncStats(event.getEntity());
        syncPermanentEffects(event.getEntity());
        syncTempEffects(event.getEntity());
        syncSkills(event.getEntity());
        syncFormsSkills(event.getEntity());

        event.getEntity().refreshDimensions();

        DMZStatsProvider.getCap(INSTANCE, event.getEntity()).ifPresent(cap -> {
            event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(dmzdatos.calcConstitution(cap));
        });


    }

    @SubscribeEvent
    public void playerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        syncStats(event.getEntity());
        syncPermanentEffects(event.getEntity());
        syncTempEffects(event.getEntity());
        syncSkills(event.getEntity());
        syncFormsSkills(event.getEntity());

    }

    @SubscribeEvent
    public void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        syncStats(event.getEntity());
        syncPermanentEffects(event.getEntity());
        syncTempEffects(event.getEntity());
        syncSkills(event.getEntity());

        DMZStatsProvider.getCap(INSTANCE, event.getEntity()).ifPresent(cap -> {
            var maxVIDA = dmzdatos.calcConstitution(cap);
            event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxVIDA);
            event.getEntity().heal((float) maxVIDA);
            cap.setIntValue("curstam", dmzdatos.calcStamina(cap));
            cap.setIntValue("curenergy", dmzdatos.calcEnergy(cap));
        });

    }

    @SubscribeEvent
    public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(DMZStatsAttributes.class);
    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        var player = event.getEntity();
        var original = event.getOriginal();

        original.reviveCaps();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(
                cap -> DMZStatsProvider.getCap(INSTANCE, original).ifPresent(originalcap ->
                        cap.loadNBTData(originalcap.saveNBTData())));


        original.invalidateCaps();

    }

    @SubscribeEvent
    public static void onTrack(PlayerEvent.StartTracking event) {
        var trackingplayer = event.getEntity();
        if (!(trackingplayer instanceof ServerPlayer serverplayer)) return;

        var tracked = event.getTarget();
        if (tracked instanceof ServerPlayer trackedplayer) {
            DMZStatsProvider.getCap(INSTANCE, tracked).ifPresent(cap -> {

                ModMessages.sendToPlayer(new StatsSyncS2C(trackedplayer), serverplayer);

                ModMessages.sendToPlayer(
                        new DMZPermanentEffectsSyncS2C(trackedplayer, cap.getDMZPermanentEffects()),
                        serverplayer
                );

                ModMessages.sendToPlayer(
                        new DMZTempEffectsS2C(trackedplayer, cap.getDMZTemporalEffects()),
                        serverplayer
                );

                ModMessages.sendToPlayer(
                        new DMZSkillsS2C(trackedplayer, cap.getDMZSkills()),
                        serverplayer
                );
                ModMessages.sendToPlayer(
                        new DMZFormsS2C(trackedplayer, cap.getAllDMZForms()),
                        serverplayer
                );

            });

        }
    }

    public static void syncStats(Player player) {
        ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new StatsSyncS2C(player));

    }

    public static void syncPermanentEffects(Player player) {
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                    new DMZPermanentEffectsSyncS2C(player, cap.getDMZPermanentEffects()));
        });
    }
    public static void syncTempEffects(Player player) {
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                    new DMZTempEffectsS2C(player, cap.getDMZTemporalEffects()));
        });
    }
    public static void syncSkills(Player player) {
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                    new DMZSkillsS2C(player, cap.getDMZSkills()));
        });
    }
    public static void syncFormsSkills(Player player) {
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                    new DMZFormsS2C(player, cap.getAllDMZForms()));
        });
    }
}
