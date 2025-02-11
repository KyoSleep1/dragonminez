package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StatsC2S {

    private int id;
    private int cantidad;


    public StatsC2S(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public StatsC2S(FriendlyByteBuf buf) {
        id = buf.readInt();
        cantidad = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(cantidad);

    }

    public static void handle(StatsC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            DMZDatos dmzdatos = new DMZDatos();

            ServerPlayer player = ctx.get().getSender();

            if (player != null) {

                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {

                    var vidaMC = 20;
                    var raza = playerstats.getRace();
                    var con = playerstats.getConstitution();
                    int maxStats = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
                    int incrementoStats = packet.cantidad;

                    switch (packet.id) {
                        case 0:
                            incrementoStats = Math.min(packet.cantidad, maxStats - playerstats.getStrength());
                            playerstats.addStrength(incrementoStats);
                            break;
                        case 1:
                            incrementoStats = Math.min(packet.cantidad, maxStats - playerstats.getDefense());
                            playerstats.addDefense(incrementoStats);
                            break;
                        case 2:
                            incrementoStats = Math.min(packet.cantidad, maxStats - playerstats.getConstitution());
                            playerstats.addCon(incrementoStats);

                            var conMax = dmzdatos.calcularCON(playerstats);
                            playerstats.setCurStam(dmzdatos.calcularSTM(playerstats));
                            player.refreshDimensions();
                            break;
                        case 3:
                            incrementoStats = Math.min(packet.cantidad, maxStats - playerstats.getKiPower());
                            playerstats.addKipwr(incrementoStats);
                            break;
                        case 4:
                            incrementoStats = Math.min(packet.cantidad, maxStats - playerstats.getEnergy());
                            playerstats.addEnergy(incrementoStats);
                            break;
                        default:
                            //System.out.println("Algo salio mal !");
                            break;
                    }

                });
            }

        });
        context.setPacketHandled(true);
    }
}

