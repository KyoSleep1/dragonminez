package com.yuseix.dragonminez.common.network.S2C;

import com.yuseix.dragonminez.common.network.ClientPacketHandler;
import com.yuseix.dragonminez.common.stats.forms.FormsData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DMZFormsS2C {
	private final Map<String, FormsData> forms;
	private final int playerId;

	public DMZFormsS2C(Player player, Map<String, FormsData> formas) {
		this.playerId = player.getId();
		this.forms = new HashMap<>(formas); // Copia los datos
	}

	public DMZFormsS2C(FriendlyByteBuf buf) {
		this.playerId = buf.readInt();
		int size = buf.readInt();
		this.forms = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String key = buf.readUtf();
			FormsData value = new FormsData(buf);
			forms.put(key, value);
		}
	}

	// Escribe los datos en el buffer
	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(playerId);
		buf.writeInt(forms.size());
		for (Map.Entry<String, FormsData> entry : forms.entrySet()) {
			buf.writeUtf(entry.getKey());
			entry.getValue().toBytes(buf);
		}
	}

	// Manejo del paquete
	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
					ClientPacketHandler.handleFormsPacket(playerId, forms, ctxSupplier)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}