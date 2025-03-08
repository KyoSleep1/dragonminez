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

                    int race = playerstats.getIntValue("race");
                    String currentForm = playerstats.getStringValue("form");

                    switch (race) {
                        case 1: // Saiyans
                            switch (currentForm) {
                                case "goldenoozaru" -> playerstats.setStringValue("form", "oozaru");
                                case "oozaru" -> playerstats.setStringValue("form", "base");
                                case "ssgrade3" -> playerstats.setStringValue("form", "ssgrade2");
                                case "ssgrade2" -> playerstats.setStringValue("form", "ssj1");
                                case "ssj1", "ssjfp" -> playerstats.setStringValue("form", "base");
                                case "ssj3" -> playerstats.setStringValue("form", "ssj2");
                                case "ssj2" -> playerstats.setStringValue("form", "ssjfp");
                            }
                            break;
                        case 2: // Namek
                            switch (currentForm) {
                                case "giant", "potential_unleashed" -> playerstats.setStringValue("form", "base");
                                case "full_power" -> playerstats.setStringValue("form", "giant");
                                case "super_namek" -> playerstats.setStringValue("form", "full_power");
                                case "orange" -> playerstats.setStringValue("form", "potential_unleashed");
                                case "orange_giant" -> playerstats.setStringValue("form", "orange");
                            }
                            break;
                        case 3:
                            break;
                        case 4:
                            switch (currentForm) {
                                case "full_power" -> playerstats.setStringValue("form", "base");
                            }
                            break;
                        case 5:
                            switch (currentForm) {
                                case "evil" -> playerstats.setStringValue("form", "base");
                                case "kid" -> playerstats.setStringValue("form", "evil");
                                case "super" -> playerstats.setStringValue("form", "kid");
                                case "ultra" -> playerstats.setStringValue("form", "super");
                            }
                            break;
                        default:
                            switch (currentForm) {
                                case "buffed" -> playerstats.setStringValue("form", "base");
                                case "full_power" -> playerstats.setStringValue("form", "buffed");
                                case "potential_unleashed" -> playerstats.setStringValue("form", "full_power");
                            }
                            break;
                    }
                });
            }
        });
        context.setPacketHandled(true);
    }
}