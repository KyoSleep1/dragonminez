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
                        case 0: // Humanos
                            break;
                        case 1: // Saiyans
                            switch (currentForm) {
                                case "goldenoozaru" -> playerstats.setDmzForm("oozaru");
                                case "oozaru" -> playerstats.setDmzForm("base");
                                case "ssgrade3" -> playerstats.setDmzForm("ssgrade2");
                                case "ssgrade2" -> playerstats.setDmzForm("ssgrade1");
                                case "ssgrade1" -> playerstats.setDmzForm("base");
                                case "ssj3" -> playerstats.setDmzForm("ssj2");
                                case "ssj2" -> playerstats.setDmzForm("ssj1");
                                case "ssj1" -> playerstats.setDmzForm("base");
                            }
                            break;
                        case 2: // Namek
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                    }
                });
            }
        });
        context.setPacketHandled(true);
    }
}