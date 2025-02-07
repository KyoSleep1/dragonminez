package com.yuseix.dragonminez.world;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Random;

public class StructuresCapability {

    private boolean hasTorreKamisama = false;
    private boolean hasHabTiempo = false;
    private BlockPos torreKamisamaPosition;
    private BlockPos portalHabTiempoPosition;
    private BlockPos torreKarinPosition;
    private BlockPos habTiempoPos;

    public boolean hasTorreKamisama() {
        return hasTorreKamisama;
    }
    public boolean isHasHabTiempo() {
        return hasHabTiempo;
    }
    public void setHasTorreKamisama(boolean hasTorreKamisama) {
        this.hasTorreKamisama = hasTorreKamisama;
    }
    public void setHasHabTiempo(boolean hasHabTiempo) {
        this.hasHabTiempo = hasHabTiempo;
    }
    public BlockPos getHabTiempoPos() {
        return habTiempoPos;
    }
    public void setHabTiempoPos(BlockPos habTiempoPos) {
        this.habTiempoPos = habTiempoPos;
    }
    public void setTorreKamisamaPosition(BlockPos torreKamisamaPosition) {
        this.torreKamisamaPosition = torreKamisamaPosition;
    }
    private void setPortalHabTiempoPosition(BlockPos portalHabTiempoPosition) {
        this.portalHabTiempoPosition = portalHabTiempoPosition;
    }
    public void setTorreKarinPosition(BlockPos torreKarinPosition) {
        this.torreKarinPosition = torreKarinPosition;
    }
    public BlockPos getTorreKamisamaPosition() {
        return torreKamisamaPosition;
    }
    public BlockPos getPortalHabTiempoPosition() {
        return portalHabTiempoPosition;
    }
    public BlockPos getTorreKarinPosition() {
        return torreKarinPosition;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("hasTorreKamisama", hasTorreKamisama);
        nbt.putBoolean("hasHabTiempo", hasHabTiempo);

        if (torreKamisamaPosition != null || torreKarinPosition != null || portalHabTiempoPosition != null) {
            nbt.put("torreKamisamaPosition", NbtUtils.writeBlockPos(torreKamisamaPosition));
            nbt.put("torreKarinPosition", NbtUtils.writeBlockPos(torreKarinPosition));
            nbt.put("portalHabPosition", NbtUtils.writeBlockPos(portalHabTiempoPosition));
        }
        if (habTiempoPos != null) {
            nbt.put("habTiempoPosition", NbtUtils.writeBlockPos(habTiempoPos));
        }
    }

    public void loadNBTData(CompoundTag nbt) {
        hasTorreKamisama = nbt.getBoolean("hasTorreKamisama");
        hasHabTiempo = nbt.getBoolean("hasHabTiempo");

        if (nbt.contains("torreKamisamaPosition") || nbt.contains("torreKarinPosition") || nbt.contains("portalHabPosition")) {
            torreKamisamaPosition = NbtUtils.readBlockPos(nbt.getCompound("torreKamisamaPosition"));
            torreKarinPosition = NbtUtils.readBlockPos(nbt.getCompound("torreKarinPosition"));
            portalHabTiempoPosition = NbtUtils.readBlockPos(nbt.getCompound("portalHabPosition"));
        }
        if (nbt.contains("habTiempoPosition")) {
            habTiempoPos = NbtUtils.readBlockPos(nbt.getCompound("habTiempoPosition"));
        }
    }

    public void generateKamisamaStructure(ServerLevel level) {
        if (!hasTorreKamisama) {
            Random random = new Random();
            BlockPos spawnPos = level.getSharedSpawnPos();

            int intentos = 0; // Contador para testear xd
            BlockPos posicionValida = new BlockPos(0, 0, 0);

            while (posicionValida.equals(new BlockPos(0, 0, 0))) {
                // Generar posición aleatoria dentro de un rango de 1200 bloques a cada lado desde el spawn
                int x = spawnPos.getX() + random.nextInt(2400) - 1200;
                int z = spawnPos.getZ() + random.nextInt(2400) - 1200;

                level.getChunk(x >> 4, z >> 4); // Carga el chunk

                // Obtener altura del terreno en esa posición
                int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);

                // Validar que la altura sea menor a 90 para q no se corte la estructura
                if (y <= 90) {
                    BlockPos posiblePos = new BlockPos(x, y, z);
                    BlockState belowBlockState = level.getBlockState(posiblePos.below());
                    BlockState belowBelowBlockState = level.getBlockState(posiblePos.below().below());

                    // Validar que la posición sea sólida
                    if (!belowBlockState.isAir() && !(belowBlockState.getBlock() == Blocks.WATER)
                            && !belowBelowBlockState.isAir() && !(belowBelowBlockState.getBlock() == Blocks.WATER)) {
                        posicionValida = posiblePos;
                        //System.out.println("Posición válida encontrada: " + posicionValida);
                        //System.out.println("/teleport Dev " + x + " " + y + " " + z);
                    }
                } else {
                    //System.out.println("No se encontró posición válida, reintentando...");
                }

                intentos++;
            }

            if (!posicionValida.equals(new BlockPos(0, 0, 0))) {
                // Obtener bloques anteriores
                BlockState blockState = level.getBlockState(posicionValida.below()).getBlock().defaultBlockState();
                BlockState redstoneBlockState = level.getBlockState(posicionValida.below().offset(1, 0, 0)).getBlock().defaultBlockState();
                BlockState belowBlockState = level.getBlockState(posicionValida.below().below()).getBlock().defaultBlockState();
                BlockState belowRedstoneBlockState = level.getBlockState(posicionValida.below().below().offset(1, 0, 0)).getBlock().defaultBlockState();

                // Generar StructureBlocks
                BlockState structureBlock = Blocks.STRUCTURE_BLOCK.defaultBlockState();
                BlockState redstoneBlockAdyacente = Blocks.REDSTONE_BLOCK.defaultBlockState();

                // Colocar primer StructureBlock
                level.setBlock(posicionValida.below(), structureBlock, 3);
                // Obtener el BlockEntity
                BlockEntity blockEntity = level.getBlockEntity(posicionValida.below());
                if (blockEntity instanceof StructureBlockEntity) {
                    StructureBlockEntity structureBlockEntity = (StructureBlockEntity) blockEntity;

                    // Crear y colocar el NBT
                    CompoundTag nbtData = new CompoundTag();
                    nbtData.putString("mirror", "NONE");
                    nbtData.putString("rotation", "NORTH");
                    nbtData.putInt("posX", -1);
                    nbtData.putInt("posY", 1);
                    nbtData.putInt("posZ", -1);
                    nbtData.putString("mode", "LOAD");
                    nbtData.putString("name", "dragonminez:kamisama/kami_bottom");


                    structureBlockEntity.load(nbtData);
                    structureBlockEntity.setChanged();
                    //System.out.println("StructureBlock 1 cargado en " + posicionValida.below() + " con NBT: " + nbtData);
                    //System.out.println("Comando: /setblock " + posicionValida.below().getX() + " " + posicionValida.below().getY() + " " + posicionValida.below().getZ() + " minecraft:structure_block" + nbtData);

                    // Colocar la Redstone que active el StructureBlock
                    level.setBlock(posicionValida.below().offset(1, 0, 0), redstoneBlockAdyacente, 3);
                }

                BlockPos secPos = posicionValida.offset(-3, 0, -3);

                level.setBlock(secPos.below().below(), structureBlock, 3);
                BlockEntity blockEntityBelow = level.getBlockEntity(secPos.below().below());
                if (blockEntityBelow instanceof StructureBlockEntity) {
                    StructureBlockEntity structureBlockEntity = (StructureBlockEntity) blockEntityBelow;

                    CompoundTag nbtData = new CompoundTag();
                    nbtData.putString("mirror", "NONE");
                    nbtData.putString("rotation", "NORTH");
                    nbtData.putInt("posX", -51);
                    nbtData.putInt("posY", 76);
                    nbtData.putInt("posZ", -51);
                    nbtData.putString("mode", "LOAD");
                    nbtData.putString("name", "dragonminez:kamisama/kami_top");

                    structureBlockEntity.load(nbtData);
                    structureBlockEntity.setChanged();
                    //System.out.println("StructureBlock 2 cargado en " + secPos.below().below() + " con NBT: " + nbtData);
                    //System.out.println("Comando: /setblock " + secPos.below().below().getX() + " " + secPos.below().below().getY() + " " + secPos.below().below().getZ() + " minecraft:structure_block" + nbtData);

                    level.setBlock(secPos.below().below().offset(1, 0, 0), redstoneBlockAdyacente, 3);
                }

                level.setBlock(posicionValida.below(), blockState, 3);
                level.setBlock(posicionValida.below().offset(1, 0, 0), redstoneBlockState, 3);
                level.setBlock(secPos.below().below(), belowBlockState, 3);
                level.setBlock(secPos.below().below().offset(1, 0, 0), belowRedstoneBlockState, 3);
            }

            BlockPos torreKami = new BlockPos(posicionValida.getX(), posicionValida.getY() + 152, posicionValida.getZ() + 50);
            BlockPos portalHab = new BlockPos(posicionValida.getX(), posicionValida.getY() + 153, posicionValida.getZ() - 27);
            BlockPos torreKarin = new BlockPos(posicionValida.getX() - 6, posicionValida.getY() + 56, posicionValida.getZ() - 4);
            setHasTorreKamisama(true);
            setTorreKamisamaPosition(torreKami);
            setTorreKarinPosition(torreKarin);
            setPortalHabTiempoPosition(portalHab);
            //System.out.println("Torre Kami: " + getTorreKamisamaPosition()); // Debug para ver si funciona las coord pal comando xd
            //System.out.println("Portal HabTiempo: " + getPortalHabTiempoPosition());
        }
    }

    public void generateHabTiempoStructure(ServerLevel level) {
        if (!hasHabTiempo) {
            BlockPos position = new BlockPos(-9, 31, -70);
            spawnHabTiempo(level, position);

            // Marcar como generada y guardar la posición
            setHasHabTiempo(true);
            setHabTiempoPos(position);
            System.out.println("Habitación del Tiempo generada en " + position);
        }
    }

    private void spawnHabTiempo(ServerLevel level, BlockPos position) {
        StructureTemplate template = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "habtiempo"));

        if (template != null) {
            StructurePlaceSettings settings = new StructurePlaceSettings();
            template.placeInWorld(level, position, position, settings, level.getRandom(), 2);
        }
    }
}