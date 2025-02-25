package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;

public class DescendFormC2S {

    public DescendFormC2S() {}

    public DescendFormC2S(FriendlyByteBuf buf) {}

    public void toBytes(FriendlyByteBuf buf) {}

    public static void handle(DescendFormC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {

                    int race = playerstats.getRace();
                    String currentForm = playerstats.getDmzForm();

                    switch (race) {
                        case 1: // Saiyans
                            switch (currentForm) {
                                case "goldenoozaru" -> playerstats.setDmzForm("oozaru");
                                case "oozaru" -> playerstats.setDmzForm("base");
                                case "ssgrade3" -> playerstats.setDmzForm("ssgrade2");
                                case "ssgrade2" -> playerstats.setDmzForm("ssj1");
                                case "ssj1" -> playerstats.setDmzForm("base");
                                case "ssjfp" -> playerstats.setDmzForm("base");
                                case "ssj3" -> playerstats.setDmzForm("ssj2");
                                case "ssj2" -> playerstats.setDmzForm("ssjfp");
                            }
                            break;
                        case 2: // Namek
                            switch (currentForm) {
                                case "giant" -> playerstats.setDmzForm("base");
                                case "full_power" -> playerstats.setDmzForm("giant");
                                case "super_namek" -> playerstats.setDmzForm("full_power");
                                case "potential_unleashed" -> playerstats.setDmzForm("base");
                                case "orange" -> playerstats.setDmzForm("potential_unleashed");
                                case "orange_giant" -> playerstats.setDmzForm("orange");
                            }
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            switch (currentForm) {
                                case "evil" -> playerstats.setDmzForm("base");
                                case "kid" -> playerstats.setDmzForm("evil");
                                case "super" -> playerstats.setDmzForm("kid");
                                case "ultra" -> playerstats.setDmzForm("super");
                            }
                            break;
                        default:
                            switch (currentForm) {
                                case "buffed" -> playerstats.setDmzForm("base");
                                case "full_power" -> playerstats.setDmzForm("buffed");
                                case "potential_unleashed" -> playerstats.setDmzForm("full_power");
                            }
                            break;
                    }
                });
            }
        });
        context.setPacketHandled(true);
    }
}