package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CharacterC2S {

    private String tipo;
    private int cantidad;


    public CharacterC2S(String tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public CharacterC2S(FriendlyByteBuf buf) {
        tipo = buf.readUtf();
        cantidad = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(tipo);
        buf.writeInt(cantidad);

    }

    public static void handle(CharacterC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {

                    switch (packet.tipo) {
                        case "Gender":
                            if (packet.cantidad == 0) {
                                playerstats.setGender("Male");
                            } else {
                                playerstats.setGender("Female");
                            }
                            break;
                        case "dmzClass":
                            if (packet.cantidad == 0) {
                                playerstats.setDmzClass("Warrior");
                            } else {
                                playerstats.setDmzClass("Spiritualist");
                            }
                            break;
                        case "dmzskiweapon":
                            if (packet.cantidad == 0) {
                                playerstats.setKiWeapon("scythe");
                            } else if(packet.cantidad == 1){
                                playerstats.setKiWeapon("trident");
                            } else {
                                playerstats.setKiWeapon("sword");
                            }
                            break;
                        case "dmzAlignment":
                            playerstats.setDmzAlignment(packet.cantidad);
                            break;
                        case "BodyType":
                            playerstats.setBodytype(packet.cantidad);
                            break;
                        case "EyeType":
                            playerstats.setEyesType(packet.cantidad);
                            break;
                        case "setRace":
                            playerstats.setRace(packet.cantidad);
                            playerstats.setBodytype(0);
                            playerstats.setEyesType(0);
                            break;
                        case "BodyColor1":
                            playerstats.setBodyColor(packet.cantidad);
                            break;
                        case "BodyColor2":
                            playerstats.setBodyColor2(packet.cantidad);
                            break;
                        case "BodyColor3":
                            playerstats.setBodyColor3(packet.cantidad);
                            break;
                        case "eye1Color":
                            playerstats.setEye1Color(packet.cantidad);
                            break;
                        case "eye2Color":
                            playerstats.setEye2Color(packet.cantidad);
                            break;
                        case "hairColor":
                            playerstats.setHairColor(packet.cantidad);
                            break;
                        case "auraColor":
                            playerstats.setAuraColor(packet.cantidad);
                            break;
                        case "hairID":
                            playerstats.setHairID(packet.cantidad);
                            break;
                        case "isConfirm":
                            if (packet.cantidad == 0) {
                                playerstats.setAcceptCharacter(false);
                            } else {
                                playerstats.setAcceptCharacter(true);
                            }
                            break;
                        case "isAuraOn":
                            if (packet.cantidad == 0) {
                                playerstats.setAuraOn(false);
                            } else {
                                playerstats.setAuraOn(true);
                            }
                            break;
                        case "isTurboOn":
                            if (packet.cantidad == 0) {
                                playerstats.setTurboOn(false);
                            } else {
                                playerstats.setTurboOn(true);
                            }
                            break;
                        case "isTransform":
                            if (packet.cantidad == 0) {
                                playerstats.setTransforming(false);
                            } else {
                                playerstats.setTransforming(true);
                            }
                            break;
                        case "isDescendOn":
                            if (packet.cantidad == 0) {
                                playerstats.setDescendKey(false);
                            } else {
                                playerstats.setDescendKey(true);
                            }
                            break;
                        case "zenkaiCount":
                            playerstats.setZenkaiCount(packet.cantidad);
                            break;
                        case "zenkaiCooldown":
                            playerstats.setSaiyanZenkaiTimer(packet.cantidad * 20 * 60); // Ticks * Segundos * Minutos
                            break;
                        case "isCompactMenu":
                            if (packet.cantidad == 1) {
                                playerstats.setCompactMenu(true);
                            } else {
                                playerstats.setCompactMenu(false);
                            }
                            break;
                        case "setPorungaRevive":
                            if (packet.cantidad == 1) {
                                playerstats.setPorungaRevive(true);
                            } else {
                                playerstats.setPorungaRevive(false);
                            }
                            break;
                        case "setShenronRevive":
                            if (packet.cantidad == 1) {
                                playerstats.setShenronRevive(true);
                            } else {
                                playerstats.setShenronRevive(false);
                            }
                            break;
                        case "str":
                            playerstats.setStrength(packet.cantidad);
                            break;
                        case "def":
                            playerstats.setDefense(packet.cantidad);
                            break;
                        case "con":
                            playerstats.setConstitution(packet.cantidad);
                            break;
                        case "pwr":
                            playerstats.setKiPower(packet.cantidad);
                            break;
                        case "ene":
                            playerstats.setEnergy(packet.cantidad);
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
