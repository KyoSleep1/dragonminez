package com.yuseix.dragonminez.common.network.S2C;

import com.yuseix.dragonminez.common.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DMZTempEffectsS2C {
	private final Map<String, Integer> tempEffects;
	private final int playerId;

	public DMZTempEffectsS2C(Player player, Map<String, Integer> tempEffects) {
		this.playerId = player.getId();
		this.tempEffects = new HashMap<>(tempEffects); // Copia los datos
	}

	// Constructor para recibir los datos del buffer
	public DMZTempEffectsS2C(FriendlyByteBuf buf) {
		this.playerId = buf.readInt();
		int size = buf.readInt();
		this.tempEffects = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String effect = buf.readUtf();
			int tiempo = buf.readInt();
			tempEffects.put(effect, tiempo);
		}
	}

	// Método para escribir los datos al buffer
	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(playerId);
		buf.writeInt(tempEffects.size());
		for (Map.Entry<String, Integer> entry : tempEffects.entrySet()) {
			buf.writeUtf(entry.getKey());
			buf.writeInt(entry.getValue());
		}
	}

	// Método para procesar el paquete en el lado del cliente
	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(
					Dist.CLIENT, () -> () -> ClientPacketHandler.handleTempEffectsPacket(playerId, tempEffects, ctxSupplier)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}
