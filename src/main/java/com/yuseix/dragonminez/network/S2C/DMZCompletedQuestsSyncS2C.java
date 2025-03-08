package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class DMZCompletedQuestsSyncS2C {
    private final Set<String> completedQuests;
    private final int playerId;

    // Constructor para enviar datos
    public DMZCompletedQuestsSyncS2C(Player player, Set<String> completedQuests) {
        this.playerId = player.getId();
        this.completedQuests = new HashSet<>(completedQuests); // Copia los datos
    }

    // Constructor para recibir datos
    public DMZCompletedQuestsSyncS2C(FriendlyByteBuf buf) {
        this.playerId = buf.readInt();
        int size = buf.readInt();
        this.completedQuests = new HashSet<>();
        for (int i = 0; i < size; i++) {
            completedQuests.add(buf.readUtf()); // Lee cada misión completada
        }
    }

    // Escribe los datos en el buffer
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(playerId);
        buf.writeInt(completedQuests.size());
        for (String questId : completedQuests) {
            buf.writeUtf(questId);
        }
    }

    // Manejo del paquete
    public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
        ctxSupplier.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                    ClientPacketHandler.handleCompletedQuestsPacket(playerId, completedQuests)
            );
        });
        ctxSupplier.get().setPacketHandled(true);
    }
}
