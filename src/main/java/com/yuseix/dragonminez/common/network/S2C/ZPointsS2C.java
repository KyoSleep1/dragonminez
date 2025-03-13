package com.yuseix.dragonminez.common.network.S2C;

import com.yuseix.dragonminez.common.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ZPointsS2C {

	private int zPoints;

	public ZPointsS2C(int zPoints) {
		this.zPoints = zPoints;

	}

	public ZPointsS2C(FriendlyByteBuf buf) {
		zPoints = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(zPoints);
	}

	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(
					Dist.CLIENT, () -> () -> ClientPacketHandler.handleZPointsPacket(zPoints, ctxSupplier)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}
