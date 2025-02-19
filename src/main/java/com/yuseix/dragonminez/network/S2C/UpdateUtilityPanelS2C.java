package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateUtilityPanelS2C {
	private final String tipo;
	private final String directionAction;

	public UpdateUtilityPanelS2C(String tipo, String directionAction) {
		this.tipo = tipo;
		this.directionAction = directionAction;
	}

	public static void encode(UpdateUtilityPanelS2C msg, FriendlyByteBuf buf) {
		buf.writeUtf(msg.tipo);
		buf.writeUtf(msg.directionAction);
	}

	public static UpdateUtilityPanelS2C decode(FriendlyByteBuf buf) {
		return new UpdateUtilityPanelS2C(buf.readUtf(), buf.readUtf());
	}

	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(
					Dist.CLIENT, () -> () -> ClientPacketHandler.handleUpdateUtilityPanelPacket(tipo, directionAction, ctxSupplier)

			);
		});
	}
}
