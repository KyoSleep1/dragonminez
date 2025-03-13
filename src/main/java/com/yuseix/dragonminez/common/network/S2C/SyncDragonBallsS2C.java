package com.yuseix.dragonminez.common.network.S2C;

import com.yuseix.dragonminez.common.network.ClientPacketHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class SyncDragonBallsS2C {
	private final List<BlockPos> earthDragonBalls;
	private final List<BlockPos> namekDragonBalls;

	public SyncDragonBallsS2C(List<BlockPos> earthDragonBalls, List<BlockPos> namekDragonBalls) {
		this.earthDragonBalls = earthDragonBalls;
		this.namekDragonBalls = namekDragonBalls;
	}

	public static void encode(SyncDragonBallsS2C msg, FriendlyByteBuf buf) {
		buf.writeCollection(msg.earthDragonBalls, FriendlyByteBuf::writeBlockPos);
		buf.writeCollection(msg.namekDragonBalls, FriendlyByteBuf::writeBlockPos);
	}

	public static SyncDragonBallsS2C decode(FriendlyByteBuf buf) {
		List<BlockPos> earthDragonBalls = buf.readList(FriendlyByteBuf::readBlockPos);
		List<BlockPos> namekDragonBalls = buf.readList(FriendlyByteBuf::readBlockPos);
		return new SyncDragonBallsS2C(earthDragonBalls, namekDragonBalls);
	}

	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			ClientPacketHandler.handleSyncDragonBallsPacket(earthDragonBalls, namekDragonBalls, ctxSupplier);
		});
		ctxSupplier.get().setPacketHandled(true);
	}

}

