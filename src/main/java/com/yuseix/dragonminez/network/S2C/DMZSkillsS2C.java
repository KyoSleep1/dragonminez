package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import com.yuseix.dragonminez.common.stats.skills.DMZSkill;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DMZSkillsS2C {
	private final Map<String, DMZSkill> skills;
	private final int playerId;

	public DMZSkillsS2C(Player player, Map<String, DMZSkill> skills) {
		this.playerId = player.getId();
		this.skills = new HashMap<>(skills); // Copia los datos
	}

	public DMZSkillsS2C(FriendlyByteBuf buf) {
		this.playerId = buf.readInt();
		int size = buf.readInt();
		this.skills = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String key = buf.readUtf(); // Lee el nombre del efecto
			DMZSkill value = new DMZSkill(buf); // Lee el estado (true/false)
			skills.put(key, value);
		}
	}

	// Escribe los datos en el buffer
	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(playerId);
		buf.writeInt(skills.size());
		for (Map.Entry<String, DMZSkill> entry : skills.entrySet()) {
			buf.writeUtf(entry.getKey());
			entry.getValue().toBytes(buf);
		}
	}

	// Manejo del paquete
	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
					ClientPacketHandler.handleSkillsPacket(playerId, skills, ctxSupplier)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}