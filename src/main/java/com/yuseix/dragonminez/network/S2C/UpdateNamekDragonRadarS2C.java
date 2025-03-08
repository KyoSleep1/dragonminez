package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class UpdateNamekDragonRadarS2C {
	private final List<BlockPos> positions;

	public UpdateNamekDragonRadarS2C(List<BlockPos> positions) {
		this.positions = positions;
	}

	public static void encode(UpdateNamekDragonRadarS2C msg, FriendlyByteBuf buf) {
		buf.writeVarInt(msg.positions.size());
		for (BlockPos pos : msg.positions) {
			buf.writeBlockPos(pos);
		}
	}

	public static UpdateNamekDragonRadarS2C decode(FriendlyByteBuf buf) {
		int size = buf.readVarInt();
		List<BlockPos> positions = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			positions.add(buf.readBlockPos());
		}
		return new UpdateNamekDragonRadarS2C(positions);
	}

	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleUpdateNamekDragonBallsPositionsPacket(positions, ctxSupplier));
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}
