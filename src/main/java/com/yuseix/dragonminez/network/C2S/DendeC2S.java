package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DendeC2S {

    private int option;

    public DendeC2S(int option) {
        this.option = option;
    }
    public DendeC2S(FriendlyByteBuf buf) {
        option = buf.readInt();

    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(option);

    }
    public static void handle(DendeC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
                    if(packet.option == 1){
                        resetPlayerStats(player, playerstats);
                    } else if(packet.option == 2) {
                        healPlayer(player, playerstats);
                    }
                });
            }
        });
        context.setPacketHandled(true);
    }

    public static void resetPlayerStats(ServerPlayer player, DMZStatsAttributes playerstats) {
        player.displayClientMessage(Component.translatable("lines.dende.reset.success"), true);

        player.setHealth(20);
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
        player.setHealth(20);

        playerstats.setAcceptCharacter(false);
        //Luego cambiar cuando decidamos las stats
        playerstats.setStrength(5);
        playerstats.setDefense(5);
        playerstats.setConstitution(5);
        playerstats.setKiPower(5);
        playerstats.setEnergy(5);
        playerstats.setZpoints(0);
        playerstats.removeAllSkills();
        playerstats.setDmzForm("base");
        playerstats.setDmzGroupForm("");
        playerstats.setTurboOn(false);
        playerstats.setAuraOn(false);
        playerstats.setKaioPlanet(false);
        playerstats.setBabaAliveTimer(0);
        playerstats.setBabaCooldown(0);
        playerstats.setSaiyanZenkaiTimer(0);
        playerstats.setZenkaiCount(0);
        playerstats.setDmzForm("base");
        playerstats.setDmzRelease(0);
        playerstats.removeTemporalEffect("mightfruit");
        playerstats.removePermanentEffect("majin");
        playerstats.removeFormSkill("super_form");
        playerstats.setCurrentEnergy(0);

        // NOTA: Lo de la vida se hace dos veces, pq a veces se buguea la primera vez xd

        player.setHealth(20);
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
        player.setHealth(20);
    }

    private static void healPlayer(ServerPlayer player, DMZStatsAttributes playerstats) {
        DMZDatos dmzdatos = new DMZDatos();

        player.displayClientMessage(Component.translatable("lines.dende.heal.success"), true);

        double vidaTotal = dmzdatos.calcularCON(playerstats);
        int energiaMax = dmzdatos.calcularENE(playerstats);
        int staminaMax = dmzdatos.calcularSTM(playerstats);

        player.heal((float) vidaTotal);
        playerstats.setCurStam(staminaMax);
        playerstats.setCurrentEnergy(energiaMax);

        player.getFoodData().setFoodLevel(20);
        player.getFoodData().setSaturation(15.0F);
    }
}
