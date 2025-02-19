package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.UpdateUtilityPanelS2C;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UtilityPanelC2S {
	private final String tipo;
	private final String directionAction;

	public UtilityPanelC2S(String tipo, String directionAction) {
		this.tipo = tipo;
		this.directionAction = directionAction;
	}

	public static void encode(UtilityPanelC2S msg, FriendlyByteBuf buf) {
		buf.writeUtf(msg.tipo);
		buf.writeUtf(msg.directionAction);
	}

	public static UtilityPanelC2S decode(FriendlyByteBuf buf) {
		return new UtilityPanelC2S(buf.readUtf(), buf.readUtf());
	}

	public static void handle(UtilityPanelC2S msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			var player = ctx.get().getSender();
			if (player != null) {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					String groupForm = cap.getDmzGroupForm();
					int superFormLevel = cap.getFormSkillLevel("super_form");
					int race = cap.getRace();

					System.out.println("Packet received: " + msg.tipo + " " + msg.directionAction);
					
					switch (msg.tipo) {
						case "kaioken":
							if (msg.directionAction.equals("up")) {
								ModMessages.sendToPlayer(new UpdateUtilityPanelS2C(msg.tipo, msg.directionAction), player);
							} else if (msg.directionAction.equals("down")) {
								ModMessages.sendToPlayer(new UpdateUtilityPanelS2C(msg.tipo, msg.directionAction), player);
							} else {
								// Lógica para alternar el kaioken, igual q lo de la cola xd
							}
							break;
						case "groupforms":
							if (msg.directionAction.equals("up")) {
								ModMessages.sendToPlayer(new UpdateUtilityPanelS2C(msg.tipo, msg.directionAction), player);
							} else if (msg.directionAction.equals("down")) {
								ModMessages.sendToPlayer(new UpdateUtilityPanelS2C(msg.tipo, msg.directionAction), player);
							} else {
								switch (race) {
									case 0:
										switch (groupForm) {
											case "":
												if (msg.directionAction.equals("left")) {
													cap.setDmzGroupForm("");
												} else if (msg.directionAction.equals("right")) {
													cap.setDmzGroupForm("");
												}
												break;
										}
										break;
									case 1:
										switch (groupForm) {
											case "":
												if (msg.directionAction.equals("left")) {
													cap.setDmzGroupForm("ssj");
												} else if (msg.directionAction.equals("right")) {
													cap.setDmzGroupForm("ssgrades");
												}
												break;
											case "ssgrades":
												if (msg.directionAction.equals("left")) {
													cap.setDmzGroupForm("");
												} else if (msg.directionAction.equals("right")) {
													cap.setDmzGroupForm("ssj");
												}
												break;
											case "ssj":
												if (msg.directionAction.equals("left")) {
													cap.setDmzGroupForm("ssgrades");
												} else if (msg.directionAction.equals("right")) {
													cap.setDmzGroupForm("");
												}
												break;
										}
										break;
									case 2:
										switch (groupForm) {
											case "":
												if (msg.directionAction.equals("left")) {
													cap.setDmzGroupForm("");
												} else if (msg.directionAction.equals("right")) {
													cap.setDmzGroupForm("");
												}
												break;
										}
										break;
									case 3:
										switch (groupForm) {
											case "":
												if (msg.directionAction.equals("left")) {
													cap.setDmzGroupForm("");
												} else if (msg.directionAction.equals("right")) {
													cap.setDmzGroupForm("");
												}
												break;
										}
										break;
									case 4:
										switch (groupForm) {
											case "":
												if (msg.directionAction.equals("left")) {
													cap.setDmzGroupForm("definitive");
												} else if (msg.directionAction.equals("right")) {
													cap.setDmzGroupForm("definitive");
												}
												break;
											case "definitive":
												if (msg.directionAction.equals("left")) {
													cap.setDmzGroupForm("");
												} else if (msg.directionAction.equals("right")) {
													cap.setDmzGroupForm("");
												}
												break;
										}
										break;
									case 5:
										switch (groupForm) {
											case "":
												if (msg.directionAction.equals("left")) {
													cap.setDmzGroupForm("ssj");
												} else if (msg.directionAction.equals("right")) {
													cap.setDmzGroupForm("ssj");
												}
												break;
											case "ssj":
												if (msg.directionAction.equals("left")) {
													cap.setDmzGroupForm("");
												} else if (msg.directionAction.equals("right")) {
													cap.setDmzGroupForm("");
												}
												break;
										}
										break;
									default:
										cap.setDmzGroupForm("");
										break;
								}
								break;
							}
						case "cola":
							if (msg.directionAction.equals("up")) {
								ModMessages.sendToPlayer(new UpdateUtilityPanelS2C(msg.tipo, msg.directionAction), player);
							} else if (msg.directionAction.equals("down")) {
								ModMessages.sendToPlayer(new UpdateUtilityPanelS2C(msg.tipo, msg.directionAction), player);
							} else {
								cap.setTailMode(!cap.isTailMode()); // Alterna la cola
							}
							break;
					}
					
				});
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
