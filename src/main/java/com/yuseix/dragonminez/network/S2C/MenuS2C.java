package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MenuS2C {

	private final boolean openCharacterMenu;
	private boolean openCompactMenu;
	private String tipo;

	public MenuS2C(String tipo, boolean isConfirmCharacter, boolean isCompactMenu) {
		this.tipo = tipo;
		this.openCharacterMenu = isConfirmCharacter;
		this.openCompactMenu = isCompactMenu;
	}

	public MenuS2C(FriendlyByteBuf buf) {
		this.tipo = buf.readUtf();
		this.openCharacterMenu = buf.readBoolean();
		this.openCompactMenu = buf.readBoolean();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeUtf(tipo);
		buf.writeBoolean(openCharacterMenu);
		buf.writeBoolean(openCompactMenu);
	}

	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(
					Dist.CLIENT, () -> () -> ClientPacketHandler.handleMenuPacket(tipo, openCharacterMenu, openCompactMenu, ctxSupplier)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}
