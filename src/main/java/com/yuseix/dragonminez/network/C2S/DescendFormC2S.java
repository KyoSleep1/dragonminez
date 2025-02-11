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

                    if (race == 1) { // 🔹 Saiyan
                        if(currentForm.equals("goldenoozaru")){
                            playerstats.setDmzForm("oozaru");
                            System.out.println("📢 Ahora eres un mono normal");
                        } else if (currentForm.equals("oozaru")) {
                            playerstats.setDmzForm("base");
                            System.out.println("❤️Estas en estado base❤️");
                        }
                    }
                });
            }
        });
        context.setPacketHandled(true);
    }
}