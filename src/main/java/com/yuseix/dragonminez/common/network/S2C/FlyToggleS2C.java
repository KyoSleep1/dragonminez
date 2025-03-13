package com.yuseix.dragonminez.common.network.S2C;

import com.yuseix.dragonminez.common.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FlyToggleS2C {
	private final boolean isFlying;

	public FlyToggleS2C(boolean isFlying) {
		this.isFlying = isFlying;
	}

	public static void encode(FlyToggleS2C packet, FriendlyByteBuf buffer) {
		buffer.writeBoolean(packet.isFlying);
	}

	public static FlyToggleS2C decode(FriendlyByteBuf buffer) {
		return new FlyToggleS2C(buffer.readBoolean());
	}

	public static void handle(FlyToggleS2C packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ClientPacketHandler.handleFlyToggle(packet.isFlying, ctx);
		});
		ctx.get().setPacketHandled(true);
	}
}


