package com.yuseix.dragonminez.init.items.custom;

import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.NubeNegraEntity;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlackNimbusItem extends Item {
    public BlackNimbusItem( ) {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        // Obtener el jugador y el nivel (mundo) del contexto
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        Direction direction = pContext.getClickedFace();

        BlockPos spawnPos = pos.above(); // Posición encima del bloque clickeado

        // Verificar si el jugador y el mundo no son nulos
        if (player != null && level != null) {
            NubeNegraEntity nube = new NubeNegraEntity(MainEntity.NUBE_NEGRA.get(), level); // Usa el tipo de entidad de nube

            nube.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());

            // Agregar la nube al mundo
            level.addFreshEntity(nube);

            pContext.getItemInHand().shrink(1);

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.useOn(pContext);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.dragonminez.black_nimbus.tooltip").withStyle(ChatFormatting.GRAY));
    }
}
