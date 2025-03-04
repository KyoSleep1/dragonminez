package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.events.StorylineEvents;
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
		PlayerStorylineProvider.getCap(StorylineEvents.INSTANCE, player).ifPresent(cap -> {
			this.nbt = cap.saveNBTData();
			System.out.println("Received player: " + player);
			System.out.println("NBT: " + nbt);
		});
		this.playerId = player.getId();
		System.out.println("Player ID: " + playerId);
	}

	public StorylineSyncS2C(FriendlyByteBuf buf) {
		nbt = buf.readNbt();
		playerId = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeNbt(nbt);
		buf.writeInt(playerId);
	}

	public void handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(
				Dist.CLIENT, () -> () -> ClientPacketHandler.handleStorylineSyncPacket(playerId, nbt, ctx)
		));
		ctx.get().setPacketHandled(true);
	}
}
