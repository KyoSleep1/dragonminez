package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.NaveSaiyanEntity;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.rmi.registry.Registry;
import java.util.function.Supplier;

public class SpacePodC2S {
    private final String targetDimension;

    public SpacePodC2S(String targetDimension) {
        this.targetDimension = targetDimension;
    }

    public static void encode(SpacePodC2S msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.targetDimension);
    }

    public static SpacePodC2S decode(FriendlyByteBuf buf) {
        return new SpacePodC2S(buf.readUtf(32767));
    }

    public static void handle(SpacePodC2S msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get().getSender() != null) {
                var player = ctx.get().getSender();
                ServerLevel currentWorld = player.server.getLevel(player.getCommandSenderWorld().dimension());
                ServerLevel targetWorld = null;
                switch (msg.targetDimension) {
                    case "overworld" -> targetWorld = player.getServer().getLevel(Level.OVERWORLD);
                    case "namek" -> targetWorld = player.getServer().getLevel(ModDimensions.NAMEK_DIM_LEVEL_KEY);
                    case "otherworld" -> targetWorld = player.getServer().getLevel(ModDimensions.OTHERWORLD_DIM_LEVEL_KEY);
                }
                if (targetWorld != null && player.level() != targetWorld) {
                    var entities = currentWorld.getEntitiesOfClass(NaveSaiyanEntity.class, player.getBoundingBox().inflate(50.0D));

                    for (NaveSaiyanEntity nave : entities) {
                        if (nave.getControllingPassenger() == player) {
                            nave.discard();
                            break;
                        }
                    }

                    if (targetWorld.dimension().location().toString().equals("dragonminez:namek") ||
                            targetWorld.dimension().location().toString().equals("minecraft:overworld"))  {
                        if (player.getY() < 0 ) {
                            player.teleportTo(targetWorld, player.getX(), player.getY()+180, player.getZ(), player.getYRot(), player.getXRot());
                        } else if (player.getY() > 0 && player.getY() < 60) {
                            player.teleportTo(targetWorld, player.getX(), player.getY()+90, player.getZ(), player.getYRot(), player.getXRot());
                        } else {
                            player.teleportTo(targetWorld, player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
                        }
                        player.onUpdateAbilities();
                        player.hasChangedDimension();
                    } else if (targetWorld.dimension().location().toString().equals("dragonminez:otherworld")) {
                        player.teleportTo(targetWorld, 121, 50, 102, player.getYRot(), player.getXRot());
                        player.onUpdateAbilities();
                        player.hasChangedDimension();
                    }


                    NaveSaiyanEntity naveEntity = new NaveSaiyanEntity(MainEntity.NAVE_SAIYAN.get(), targetWorld);
                    naveEntity.setPos(player.getX(), player.getY(), player.getZ());
                    targetWorld.addFreshEntity(naveEntity);
                    player.startRiding(naveEntity);
            }
        }});
        ctx.get().setPacketHandled(true);
    }
}
