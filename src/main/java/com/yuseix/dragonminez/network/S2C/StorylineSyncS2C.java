package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import com.yuseix.dragonminez.storyline.player.PlayerStorylineProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StorylineSyncS2C {

	private CompoundTag nbt;
	private int playerId;

	public StorylineSyncS2C(Player player) {
		player.getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(cap -> nbt = cap.saveNBTData());
		playerId = player.getId();
	}

	public StorylineSyncS2C(FriendlyByteBuf buf) {

		nbt = buf.readNbt();
		playerId = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {

		buf.writeNbt(nbt);
		buf.writeInt(playerId);
	}

	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(
				Dist.CLIENT, () -> () -> ClientPacketHandler.handleStatsSyncPacket(playerId, nbt, ctxSupplier)
		));
		ctxSupplier.get().setPacketHandled(true);
	}

}
