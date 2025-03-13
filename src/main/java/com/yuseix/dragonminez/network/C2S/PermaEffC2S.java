package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PermaEffC2S {

    private String tipo;
    private String id;
    private int value;


    public PermaEffC2S(String tipo, String id, int value) {
        this.tipo = tipo;
        this.id = id;
        this.value = value;
    }

    public PermaEffC2S(FriendlyByteBuf buf) {
        tipo = buf.readUtf();
        id = buf.readUtf();
        value = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(tipo);
        buf.writeUtf(id);
        buf.writeInt(value);

    }

    public static void handle(PermaEffC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {

                    switch (packet.tipo){
                        case "remove":
                            playerstats.removePermanentEffect(packet.id);
                            break;
                        case "add":
                            playerstats.addDMZPermanentEffect(packet.id, true);
                        default:
                            break;
                    }

                });
            }

        });
        context.setPacketHandled(true);
    }
}
