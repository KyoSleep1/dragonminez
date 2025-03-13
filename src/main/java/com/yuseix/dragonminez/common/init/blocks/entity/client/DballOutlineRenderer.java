package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.common.init.MainBlockEntities;
import com.yuseix.dragonminez.common.init.blocks.entity.Dball4BlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.yuseix.dragonminez.common.init.MainItems.*;

public class DballOutlineRenderer {

    public static final Set<Item> ALL_DBALL_BLOCK_ITEMS = new HashSet<>(Arrays.asList(
            DBALL1_BLOCK_ITEM.get(),
            DBALL2_BLOCK_ITEM.get(),
            DBALL3_BLOCK_ITEM.get(),
            DBALL4_BLOCK_ITEM.get(),
            DBALL5_BLOCK_ITEM.get(),
            DBALL6_BLOCK_ITEM.get(),
            DBALL7_BLOCK_ITEM.get()
    ));

    private static final int[][] PATTERN = {
            {0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0}
    };

    public static void renderOutlineDball(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_BLOCK_ENTITIES) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;

            if (mc.player == null || mc.level == null)
                return;

            ItemStack heldItem = player.getMainHandItem();

            if (ALL_DBALL_BLOCK_ITEMS.contains(heldItem.getItem()))
                locateDragonBallEntities(player, event.getPoseStack());
        }
    }

    private static void line(VertexConsumer builder, Matrix4f matrix, BlockPos pos, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2, float r, float g, float b) {
        builder.vertex(matrix, pos.getX() + dx1, pos.getY() + dy1, pos.getZ() + dz1)
                .color(r, g, b, 1.0f)
                .endVertex();
        builder.vertex(matrix, pos.getX() + dx2, pos.getY() + dy2, pos.getZ() + dz2)
                .color(r, g, b, 1.0f)
                .endVertex();
    }

    private static void locateDragonBallEntities(Player player, PoseStack matrixStack) {
        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer builder = buffer.getBuffer(DballOutlineRenderType.OVERLAY_LINES);

        BlockPos playerPos = player.blockPosition();
        int px = playerPos.getX();
        int py = playerPos.getY();
        int pz = playerPos.getZ();
        Level world = player.getCommandSenderWorld();

        matrixStack.pushPose();

        Vec3 projectedView = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        matrixStack.translate(-projectedView.x, -projectedView.y, -projectedView.z);

        Matrix4f matrix = matrixStack.last().pose();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        boolean found = false;

        int startX = (player.blockPosition().getX() < 0) ? 10 : -10;
        int endX = -startX;
        int stepX = (player.blockPosition().getX() < 0) ? -1 : 1;

        for (int dx = startX; dx != endX + stepX && !found; dx += stepX) {
            for (int dy = -10; dy <= 10 && !found; dy++) {
                for (int dz = -10; dz <= 10 && !found; dz++) {
                    pos.set(px + dx, py + dy, pz + dz);
                    BlockEntity blockEntity = world.getBlockEntity(pos);
                    if (blockEntity instanceof Dball4BlockEntity) {
                        // Draw outline around the block entity
                        drawOutline(builder, matrix, pos, 0.0f, 0.0f, 1.0f); // Blue

                        // Check the blocks around the block entity
                        for (int i = 0; i < PATTERN.length; i++) {
                            for (int j = 0; j < PATTERN[i].length; j++) {
                                if (PATTERN[i][j] == 1) {
                                    // Adjust the offsets to center the Dball4BlockEntity
                                    BlockPos pos2 = pos.offset(i - PATTERN.length / 2, 0, j - PATTERN[i].length / 2);
                                    BlockEntity blockEntity2 = world.getBlockEntity(pos2);
                                    if (blockEntity2 == null) {
                                        // Draw outline around the empty block
                                        drawOutline(builder, matrix, pos2, 0.0f, 0.0f, 1.0f); // Blue
                                    } else if (MainBlockEntities.ALL_DBALL_ENTITY_CLASSES.contains(blockEntity2.getClass())) {
                                        // Draw outline around the dragon ball block
                                        drawOutline(builder, matrix, pos2, 0.0f, 1.0f, 0.0f); // Green
                                    }
                                }
                            }
                        }

                        found = true;
                    }
                }
            }
        }

        matrixStack.popPose();

        RenderSystem.disableDepthTest();
        buffer.endBatch(DballOutlineRenderType.OVERLAY_LINES);
    }

    //Ignorar warning del red. Capaz usamos más líneas rojas.
    private static void drawOutline(VertexConsumer builder, Matrix4f matrix, BlockPos pos, float r, float g, float b) {
        line(builder, matrix, pos, 0, 0, 0, 1, 0, 0, r, g, b);
        line(builder, matrix, pos, 0, 1, 0, 1, 1, 0, r, g, b);
        line(builder, matrix, pos, 0, 0, 1, 1, 0, 1, r, g, b);
        line(builder, matrix, pos, 0, 1, 1, 1, 1, 1, r, g, b);

        line(builder, matrix, pos, 0, 0, 0, 0, 0, 1, r, g, b);
        line(builder, matrix, pos, 1, 0, 0, 1, 0, 1, r, g, b);
        line(builder, matrix, pos, 0, 1, 0, 0, 1, 1, r, g, b);
        line(builder, matrix, pos, 1, 1, 0, 1, 1, 1, r, g, b);

        line(builder, matrix, pos, 0, 0, 0, 0, 1, 0, r, g, b);
        line(builder, matrix, pos, 1, 0, 0, 1, 1, 0, r, g, b);
        line(builder, matrix, pos, 0, 0, 1, 0, 1, 1, r, g, b);
        line(builder, matrix, pos, 1, 0, 1, 1, 1, 1, r, g, b);
    }
}
