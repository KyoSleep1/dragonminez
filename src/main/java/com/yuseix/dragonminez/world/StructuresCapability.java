package com.yuseix.dragonminez.world;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.List;
import java.util.Random;

public class StructuresCapability {
    private boolean hasTorreKamisama = false;
    private boolean hasHabTiempo = false;
    private boolean hasGokuHouse = false;
    private boolean hasRoshiHouse = false;
    private boolean hasElderGuru = false;
    private boolean hasEnmaPalace = false;
    private boolean hasKaioPlanet = false;

    private BlockPos torreKamisamaPosition;
    private BlockPos portalHabTiempoPosition;
    private BlockPos torreKarinPosition;
    private BlockPos gokuHousePosition;
    private BlockPos habTiempoPos;
    private BlockPos db4Position;
    private BlockPos roshiHousePosition;
    private BlockPos elderGuruPosition;
    private BlockPos namekDB4Position;
    private BlockPos enmaPalacePosition;
    private BlockPos kaioPlanetPosition;

    public void setHasTorreKamisama(boolean hasTorreKamisama) {
        this.hasTorreKamisama = hasTorreKamisama;
    }

    public void setHasHabTiempo(boolean hasHabTiempo) {
        this.hasHabTiempo = hasHabTiempo;
    }

    public void setHasGokuHouse(boolean hasGokuHouse) {
        this.hasGokuHouse = hasGokuHouse;
    }

    public void setHabTiempoPos(BlockPos habTiempoPos) {
        this.habTiempoPos = habTiempoPos;
    }

    public void setHasRoshiHouse(boolean hasRoshiHouse) {
        this.hasRoshiHouse = hasRoshiHouse;
    }

    public void setHasElderGuru(boolean hasElderGuru) {
        this.hasElderGuru = hasElderGuru;
    }

    public void setHasEnmaPalace(boolean hasEnmaPalace) {
        this.hasEnmaPalace = hasEnmaPalace;
    }

    public void setHasKaioPlanet(boolean hasKaioPlanet) {
        this.hasKaioPlanet = hasKaioPlanet;
    }

    public boolean getHasTorreKami() {
        return this.hasTorreKamisama;
    }

    public boolean getHasGokuHouse() {
        return this.hasGokuHouse;
    }

    public boolean getHasRoshiHouse() {
        return this.hasRoshiHouse;
    }

    public boolean getHasElderGuru() {
        return this.hasElderGuru;
    }

    public boolean getHasKaioPlanet() {
        return this.hasKaioPlanet;
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

    public void setGokuHousePosition(BlockPos gokuHousePosition) {
        this.gokuHousePosition = gokuHousePosition;
    }

    public void setDB4Position(BlockPos db4Position) {
        this.db4Position = db4Position;
    }

    public void setRoshiHousePosition(BlockPos roshiHousePosition) {
        this.roshiHousePosition = roshiHousePosition;
    }

    public void setElderGuruPosition(BlockPos elderGuruPosition) {
        this.elderGuruPosition = elderGuruPosition;
    }

    public void setNamekDB4Position(BlockPos namekDB4Position) {
        this.namekDB4Position = namekDB4Position;
    }

    public void setEnmaPalacePosition(BlockPos enmaPalacePosition) {
        this.enmaPalacePosition = enmaPalacePosition;
    }

    public void setKaioPlanetPosition(BlockPos kaioPlanetPosition) {
        this.kaioPlanetPosition = kaioPlanetPosition;
    }

    public BlockPos getHabTiempoPos() {
        return habTiempoPos;
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

    public BlockPos getGokuHousePosition() {
        return gokuHousePosition;
    }

    public BlockPos getDB4Position() {
        return db4Position;
    }

    public BlockPos getRoshiHousePosition() {
        return roshiHousePosition;
    }

    public BlockPos getElderGuruPosition() {
        return elderGuruPosition;
    }

    public BlockPos getNamekDB4Position() {
        return namekDB4Position;
    }

    public BlockPos getEnmaPalacePosition() {
        return enmaPalacePosition;
    }

    public BlockPos getKaioPlanetPosition() {
        return kaioPlanetPosition;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("hasTorreKamisama", hasTorreKamisama);
        nbt.putBoolean("hasHabTiempo", hasHabTiempo);
        nbt.putBoolean("hasGokuHouse", hasGokuHouse);
        nbt.putBoolean("hasRoshiHouse", hasRoshiHouse);
        nbt.putBoolean("hasElderGuru", hasElderGuru);
        nbt.putBoolean("hasEnmaPalace", hasEnmaPalace);
        nbt.putBoolean("hasKaioPlanet", hasKaioPlanet);

        if (torreKamisamaPosition != null || torreKarinPosition != null || portalHabTiempoPosition != null) {
            nbt.put("torreKamisamaPosition", NbtUtils.writeBlockPos(torreKamisamaPosition));
            nbt.put("torreKarinPosition", NbtUtils.writeBlockPos(torreKarinPosition));
            nbt.put("portalHabPosition", NbtUtils.writeBlockPos(portalHabTiempoPosition));
        }
        if (habTiempoPos != null) {
            nbt.put("habTiempoPosition", NbtUtils.writeBlockPos(habTiempoPos));
        }
        if (gokuHousePosition != null) {
            nbt.put("gokuHousePosition", NbtUtils.writeBlockPos(gokuHousePosition));
        }
        if (db4Position != null) {
            nbt.put("db4Position", NbtUtils.writeBlockPos(db4Position));
        }
        if (roshiHousePosition != null) {
            nbt.put("roshiHousePosition", NbtUtils.writeBlockPos(roshiHousePosition));
        }
        if (elderGuruPosition != null) {
            nbt.put("elderGuruPosition", NbtUtils.writeBlockPos(elderGuruPosition));
        }
        if (namekDB4Position != null) {
            nbt.put("namekDB4Position", NbtUtils.writeBlockPos(namekDB4Position));
        }
        if (enmaPalacePosition != null) {
            nbt.put("enmaPalacePosition", NbtUtils.writeBlockPos(enmaPalacePosition));
        }
        if (kaioPlanetPosition != null) {
            nbt.put("kaioPlanetPosition", NbtUtils.writeBlockPos(kaioPlanetPosition));
        }
    }

    public void loadNBTData(CompoundTag nbt) {
        hasTorreKamisama = nbt.getBoolean("hasTorreKamisama");
        hasHabTiempo = nbt.getBoolean("hasHabTiempo");
        hasGokuHouse = nbt.getBoolean("hasGokuHouse");
        hasRoshiHouse = nbt.getBoolean("hasRoshiHouse");
        hasElderGuru = nbt.getBoolean("hasElderGuru");
        hasEnmaPalace = nbt.getBoolean("hasEnmaPalace");
        hasKaioPlanet = nbt.getBoolean("hasKaioPlanet");

        if (nbt.contains("torreKamisamaPosition") || nbt.contains("torreKarinPosition") || nbt.contains("portalHabPosition")) {
            torreKamisamaPosition = NbtUtils.readBlockPos(nbt.getCompound("torreKamisamaPosition"));
            torreKarinPosition = NbtUtils.readBlockPos(nbt.getCompound("torreKarinPosition"));
            portalHabTiempoPosition = NbtUtils.readBlockPos(nbt.getCompound("portalHabPosition"));
        }
        if (nbt.contains("habTiempoPosition")) {
            habTiempoPos = NbtUtils.readBlockPos(nbt.getCompound("habTiempoPosition"));
        }
        if (nbt.contains("gokuHousePosition")) {
            gokuHousePosition = NbtUtils.readBlockPos(nbt.getCompound("gokuHousePosition"));
        }
        if (nbt.contains("db4Position")) {
            db4Position = NbtUtils.readBlockPos(nbt.getCompound("db4Position"));
        }
        if (nbt.contains("roshiHousePosition")) {
            roshiHousePosition = NbtUtils.readBlockPos(nbt.getCompound("roshiHousePosition"));
        }
        if (nbt.contains("elderGuruPosition")) {
            elderGuruPosition = NbtUtils.readBlockPos(nbt.getCompound("elderGuruPosition"));
        }
        if (nbt.contains("namekDB4Position")) {
            namekDB4Position = NbtUtils.readBlockPos(nbt.getCompound("namekDB4Position"));
        }
        if (nbt.contains("enmaPalacePosition")) {
            enmaPalacePosition = NbtUtils.readBlockPos(nbt.getCompound("enmaPalacePosition"));
        }
        if (nbt.contains("kaioPlanetPosition")) {
            kaioPlanetPosition = NbtUtils.readBlockPos(nbt.getCompound("kaioPlanetPosition"));
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
                    if (!belowBlockState.isAir() && !(belowBlockState.is(Blocks.WATER))
                            && !belowBelowBlockState.isAir() && !(belowBelowBlockState.is(Blocks.WATER))) {
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
            System.out.println("[DMZ-Generation] Korin Tower generated in " + torreKarin);
            System.out.println("[DMZ-Generation] Kami's Lookout generated in " + torreKami);
        }
    }

    public void generateGokuHouseStructure(ServerLevel level) {
        if (!hasGokuHouse) {
            Random random = new Random();
            BlockPos spawnPos = level.getSharedSpawnPos();

            BlockPos posicionValida = new BlockPos(0, 0, 0);

            List<ResourceKey<Biome>> gokuBiomes = List.of(
                    Biomes.PLAINS,
                    Biomes.FLOWER_FOREST,
                    Biomes.SUNFLOWER_PLAINS,
                    Biomes.FOREST,
                    Biomes.SAVANNA,
                    Biomes.SAVANNA_PLATEAU,
                    Biomes.WINDSWEPT_HILLS,
                    Biomes.WINDSWEPT_GRAVELLY_HILLS,
                    Biomes.WINDSWEPT_SAVANNA
            );

            while(posicionValida.equals(new BlockPos(0, 0, 0))) {
                int x = spawnPos.getX() + random.nextInt(12000) - 6000;
                int z = spawnPos.getZ() + random.nextInt(12000) - 6000;

                level.getChunk(x >> 4, z >> 4); // Carga el chunk

                int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
                BlockPos posiblePos = new BlockPos(x, y, z);
                Holder<Biome> biome = level.getBiome(posiblePos);

                if (y <= 200 && gokuBiomes.stream().anyMatch(biome::is)) {
                    BlockState belowBlockState = level.getBlockState(posiblePos.below());
                    BlockState belowBelowBlockState = level.getBlockState(posiblePos.below().below());

                    if (!belowBlockState.isAir() && !(belowBlockState.is(Blocks.WATER))
                            && !belowBelowBlockState.isAir() && !(belowBelowBlockState.is(Blocks.WATER))) {
                        posicionValida = posiblePos;
                    }
                }
            }

            if (!posicionValida.equals(new BlockPos(0, 0, 0))) {
                // Obtener bloques anteriores
                BlockState blockState = level.getBlockState(posicionValida).getBlock().defaultBlockState();
                BlockState redstoneBlockState = level.getBlockState(posicionValida.offset(1, 0, 0)).getBlock().defaultBlockState();
                BlockState pathBlockState = level.getBlockState(posicionValida.offset(24, 0, 0)).getBlock().defaultBlockState();
                BlockState pathRedstoneBlockState = level.getBlockState(posicionValida.offset(25, 0, 0)).getBlock().defaultBlockState();
                BlockState gohanBlockState = level.getBlockState(posicionValida.offset(0, 0, 52)).getBlock().defaultBlockState();
                BlockState gohanRedstoneBlockState = level.getBlockState(posicionValida.offset(1, 0, 52)).getBlock().defaultBlockState();

                BlockState structureBlock = Blocks.STRUCTURE_BLOCK.defaultBlockState(); BlockState redstoneBlock = Blocks.REDSTONE_BLOCK.defaultBlockState();

                level.setBlock(posicionValida, structureBlock, 3);
                BlockEntity blockEntity1 = level.getBlockEntity(posicionValida);
                if (blockEntity1 instanceof StructureBlockEntity) {
                    StructureBlockEntity structureBlockEntity = (StructureBlockEntity) blockEntity1;

                    // Crear y colocar el NBT - GokuHouse (1/3)
                    CompoundTag nbtData = new CompoundTag();
                    nbtData.putString("mirror", "NONE");
                    nbtData.putString("rotation", "NORTH");
                    nbtData.putInt("posX", -24);
                    nbtData.putInt("posY", -3);
                    nbtData.putInt("posZ", -17);
                    nbtData.putString("mode", "LOAD");
                    nbtData.putString("name", "dragonminez:goku_house/goku_house");

                    structureBlockEntity.load(nbtData);
                    structureBlockEntity.setChanged();
                    //System.out.println("Comando: /setblock " + posicionValida.below().below().below().getX() + " " + posicionValida.below().below().below().getY() + " " + posicionValida.below().below().below().getZ() + " minecraft:structure_block" + nbtData);

                    level.setBlock(posicionValida.offset(1, 0, 0), redstoneBlock, 3);
                    level.setBlock(posicionValida, Blocks.AIR.defaultBlockState(), 3);
                    level.setBlock(posicionValida.offset(0, 3, 0), Blocks.AIR.defaultBlockState(), 3);
                }

                BlockPos secPos = posicionValida.offset(24, 0, 0);

                level.setBlock(secPos, structureBlock, 3);
                BlockEntity blockEntity2 = level.getBlockEntity(secPos);
                if (blockEntity2 instanceof  StructureBlockEntity) {
                    StructureBlockEntity structureBlockEntity = (StructureBlockEntity) blockEntity2;

                    // Crear y colocar el NBT - GokuHousePath (2/3)
                    CompoundTag nbtData = new CompoundTag();
                    nbtData.putString("mirror", "NONE");
                    nbtData.putString("rotation", "NORTH");
                    nbtData.putInt("posX", 0);
                    nbtData.putInt("posY", -3);
                    nbtData.putInt("posZ", -11);
                    nbtData.putString("mode", "LOAD");
                    nbtData.putString("name", "dragonminez:goku_house/goku_path");

                    structureBlockEntity.load(nbtData);
                    structureBlockEntity.setChanged();
                    //System.out.println("Comando: /setblock " + secPos.below().below().below().getX() + " " + secPos.below().below().below().getY() + " " + secPos.below().below().below().getZ() + " minecraft:structure_block" + nbtData);

                    level.setBlock(secPos.offset(1, 0, 0), redstoneBlock, 3);
                    level.setBlock(secPos, Blocks.AIR.defaultBlockState(), 3);
                    level.setBlock(secPos.offset(0, 3, 0), Blocks.AIR.defaultBlockState(), 3);
                }

                BlockPos terPos = posicionValida.offset(1, 0, 52);

                level.setBlock(terPos, structureBlock, 3);
                BlockEntity blockEntity3 = level.getBlockEntity(terPos);
                if (blockEntity3 instanceof StructureBlockEntity) {
                    StructureBlockEntity structureBlockEntity = (StructureBlockEntity) blockEntity3;

                    // Crear y colocar el NBT - GokuHouseGohan (3/3)
                    CompoundTag nbtData = new CompoundTag();
                    nbtData.putString("mirror", "NONE");
                    nbtData.putString("rotation", "NORTH");
                    nbtData.putInt("posX", -23);
                    nbtData.putInt("posY", -3);
                    nbtData.putInt("posZ", -21);
                    nbtData.putString("mode", "LOAD");
                    nbtData.putString("name", "dragonminez:goku_house/goku_gohanhouse");

                    structureBlockEntity.load(nbtData);
                    structureBlockEntity.setChanged();
                    //System.out.println("Comando: /setblock " + terPos.below().below().below().getX() + " " + terPos.below().below().below().getY() + " " + terPos.below().below().below().getZ() + " minecraft:structure_block" + nbtData);

                    level.setBlock(terPos.offset(1, 0, 0), redstoneBlock, 3);
                    level.setBlock(terPos.offset(0, 3, 0), Blocks.AIR.defaultBlockState(), 3);
                }

                // Colocar bloques anteriores
                level.setBlock(posicionValida, blockState, 3);
                level.setBlock(posicionValida.offset(1, 0, 0), redstoneBlockState, 3);
                level.setBlock(secPos, pathBlockState, 3);
                level.setBlock(secPos.offset(1, 0, 0), pathRedstoneBlockState, 3);
                level.setBlock(terPos, gohanBlockState, 3);
                level.setBlock(terPos.offset(1, 0, 0), gohanRedstoneBlockState, 3);
            }

            BlockPos spawnPosition = new BlockPos(posicionValida.getX() + 30, posicionValida.getY() + 3, posicionValida.getZ() + 9);
            // Marcar como generada y guardar la posición
            setDB4Position(posicionValida.offset(-16, 2, 52));
            //System.out.println("DB4 generada en " + getDB4Position());
            setHasGokuHouse(true);
            setGokuHousePosition(spawnPosition);
            System.out.println("[DMZ-Generation] Goku House generated in " + spawnPosition);
            //System.out.println("Comando: /teleport Dev " + spawnPosition.getX() + " " + spawnPosition.getY() + " " + spawnPosition.getZ());
        }
    }

    public void generateRoshiHouseStructure(ServerLevel level) {
        if (!hasRoshiHouse) {
            Random random = new Random();
            BlockPos spawnPos = level.getSharedSpawnPos();

            BlockPos posicionValida = new BlockPos(0, 0, 0);
            List<ResourceKey<Biome>> oceanBiomes = List.of(
                    Biomes.OCEAN,
                    Biomes.DEEP_OCEAN,
                    Biomes.WARM_OCEAN,
                    Biomes.LUKEWARM_OCEAN
            );

            while (posicionValida.equals(new BlockPos(0, 0, 0))) {
                int x = spawnPos.getX() + random.nextInt(30000) - 15000;
                int z = spawnPos.getZ() + random.nextInt(30000) - 15000;

                level.getChunk(x >> 4, z >> 4);
                int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
                BlockPos posiblePos = new BlockPos(x, y, z);
                Holder<Biome> biome = level.getBiome(posiblePos);

                if (y >= 63 && y <= 67) {
                    if (oceanBiomes.stream().anyMatch(biome::is)) {
                        BlockState belowBlockState = level.getBlockState(posiblePos.below());
                        BlockState belowBelowBlockState = level.getBlockState(posiblePos.below().below());

                         if (!belowBlockState.isAir() && belowBlockState.is(Blocks.WATER)
                                && !belowBelowBlockState.isAir() && belowBelowBlockState.is(Blocks.WATER)) {
                                posicionValida = posiblePos;
                         }
                    }
                }
            }

            if (!posicionValida.equals(new BlockPos(0, 0, 0))) {
                BlockState blockState = level.getBlockState(posicionValida.offset(0, -5, 0)).getBlock().defaultBlockState();
                BlockState redstoneBlockState = level.getBlockState(posicionValida.offset(1, -5, 0)).getBlock().defaultBlockState();

                BlockState structureBlock = Blocks.STRUCTURE_BLOCK.defaultBlockState(); BlockState redstoneBlock = Blocks.REDSTONE_BLOCK.defaultBlockState();

                level.setBlock(posicionValida.offset(0, -5, 0), structureBlock, 3);
                BlockEntity blockEntity = level.getBlockEntity(posicionValida.offset(0, -5, 0));
                if (blockEntity instanceof StructureBlockEntity) {
                    StructureBlockEntity structureBlockEntity = (StructureBlockEntity) blockEntity;

                    CompoundTag nbtData = new CompoundTag();
                    nbtData.putString("mirror", "NONE");
                    nbtData.putString("rotation", "NORTH");
                    nbtData.putInt("posX", -33);
                    nbtData.putInt("posY", 1);
                    nbtData.putInt("posZ", -33);
                    nbtData.putString("mode", "LOAD");
                    nbtData.putString("name", "dragonminez:roshihouse");

                    structureBlockEntity.load(nbtData);
                    structureBlockEntity.setChanged();
                    //System.out.println("Comando: /setblock " + posicionValida.offset(0, -4, 0).getX() + " " + posicionValida.offset(0, -4, 0).getY() + " " + posicionValida.offset(0, -4, 0).getZ() + " minecraft:structure_block" + nbtData);

                    level.setBlock(posicionValida.offset(1, -5, 0), redstoneBlock, 3);
                }

                level.setBlock(posicionValida.offset(0, -5, 0), blockState, 3);
                level.setBlock(posicionValida.offset(1, -5, 0), redstoneBlockState, 3);

                BlockPos spawnPosition = new BlockPos(posicionValida.getX() + 19, posicionValida.getY() + 5, posicionValida.getZ());
                setHasRoshiHouse(true);
                setRoshiHousePosition(spawnPosition);
                System.out.println("[DMZ-Generation] Roshi House generated in " + spawnPosition);
            }
        }
    }

    public void generateElderGuru(ServerLevel level) {
        if (!hasElderGuru) {
            Random random = new Random();
            BlockPos spawnPos = level.getSharedSpawnPos();

            BlockPos posicionValida = new BlockPos(0, 0, 0);

            while (posicionValida.equals(new BlockPos(0, 0, 0))) {
                int x = spawnPos.getX() + random.nextInt(10000) - 5000;
                int z = spawnPos.getZ() + random.nextInt(10000) - 5000;

                level.getChunk(x >> 4, z >> 4);
                int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
                BlockPos posiblePos = new BlockPos(x, y, z);

                if (y > 60 && y <= 66) {
                    BlockState belowBlockState = level.getBlockState(posiblePos.below());
                    BlockState belowBelowBlockState = level.getBlockState(posiblePos.below().below());

                    if (!belowBlockState.isAir() && (belowBlockState.is(MainBlocks.NAMEK_WATER_LIQUID.get()))
                            && !belowBelowBlockState.isAir() && (belowBelowBlockState.is(MainBlocks.NAMEK_WATER_LIQUID.get()))) {
                        posicionValida = posiblePos;
                    }
                }
            }

            if (!posicionValida.equals(new BlockPos(0, 0, 0))) {
                BlockState blockState = level.getBlockState(posicionValida.below().below().below().below().below()).getBlock().defaultBlockState();
                BlockState redstoneBlockState = level.getBlockState(posicionValida.below().below().below().below().below().offset(1, 0, 0)).getBlock().defaultBlockState();

                BlockState structureBlock = Blocks.STRUCTURE_BLOCK.defaultBlockState(); BlockState redstoneBlock = Blocks.REDSTONE_BLOCK.defaultBlockState();

                level.setBlock(posicionValida.below().below().below().below().below(), structureBlock, 3);
                BlockEntity blockEntity = level.getBlockEntity(posicionValida.below().below().below().below().below());
                if (blockEntity instanceof StructureBlockEntity) {
                    StructureBlockEntity structureBlockEntity = (StructureBlockEntity) blockEntity;

                    CompoundTag nbtData = new CompoundTag();
                    nbtData.putString("mirror", "NONE");
                    nbtData.putString("rotation", "NORTH");
                    nbtData.putInt("posX", -29);
                    nbtData.putInt("posY", 2);
                    nbtData.putInt("posZ", -33);
                    nbtData.putString("mode", "LOAD");
                    nbtData.putString("name", "dragonminez:elder_guru");

                    structureBlockEntity.load(nbtData);
                    structureBlockEntity.setChanged();
                    //System.out.println("Comando: /setblock " + posicionValida.below().below().below().below().below().below().getX() + " " + posicionValida.below().below().below().below().below().below().getY() + " " + posicionValida.below().below().below().below().below().below().getZ() + " minecraft:structure_block" + nbtData);

                    level.setBlock(posicionValida.below().below().below().below().below().offset(1, 0, 0), redstoneBlock, 3);
                }

                level.setBlock(posicionValida.below().below().below().below().below(), blockState, 3);
                level.setBlock(posicionValida.below().below().below().below().below().offset(1, 0, 0), redstoneBlockState, 3);
                //System.out.println("Elder Guru's House generada en " + posicionValida);
                //System.out.println("Comando: /execute in dragonminez:namek run teleport Dev " + posicionValida.getX() + " " + posicionValida.getY() + " " + posicionValida.getZ());

                BlockPos spawnPosition = new BlockPos(posicionValida.getX() + 7, posicionValida.getY() + 35, posicionValida.getZ() + 24);
                BlockPos namekDB4 = new BlockPos(posicionValida.getX() + 12, posicionValida.getY() + 43, posicionValida.getZ() - 11);
                setHasElderGuru(true);
                setNamekDB4Position(namekDB4);
                setElderGuruPosition(spawnPosition);
                System.out.println("[DMZ-Generation] Elder Guru's House generated in " + spawnPosition);
            }
        }
    }

    public void generateHabTiempoStructure(ServerLevel level) {
        if (!hasHabTiempo) {
            BlockPos position = new BlockPos(-9, 31, -70);
            StructureTemplate template = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "habtiempo"));

            if (template != null) {
                StructurePlaceSettings settings = new StructurePlaceSettings();
                template.placeInWorld(level, position, position, settings, level.getRandom(), 2);
            }

            // Marcar como generada y guardar la posición
            setHasHabTiempo(true);
            setHabTiempoPos(position);
            System.out.println("[DMZ-Generation] Hyperbolic Time Chamber generated in " + position);
        }
    }

    public void generatePalacioEnma(ServerLevel level) {
        if (!hasEnmaPalace) {
            StructurePlaceSettings settings = new StructurePlaceSettings();
            BlockPos position = new BlockPos(0, 40, 0);

            StructureTemplate template = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "otherworld/enma/palacioenma1"));

            if (template != null) {
                template.placeInWorld(level, position, position, settings, level.getRandom(), 2);
            }

            BlockPos secPos = position.offset(-129, 0, 0);

            StructureTemplate secTemp = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "otherworld/enma/palacioenma2"));

            if (secTemp != null) {
                secTemp.placeInWorld(level, secPos, secPos, settings, level.getRandom(), 2);
            }

            BlockPos terPos = position.offset(-129, 0, -161);

            StructureTemplate terTemp = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "otherworld/enma/palacioenma3"));

            if (terTemp != null) {
                terTemp.placeInWorld(level, terPos, terPos, settings, level.getRandom(), 2);
            }

            BlockPos quarPos = position.offset(0, 0, -161);

            StructureTemplate quarTemp = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "otherworld/enma/palacioenma4"));

            if (quarTemp != null) {
                quarTemp.placeInWorld(level, quarPos, quarPos, settings, level.getRandom(), 2);
            }

            BlockPos spawnPosition = new BlockPos(0, 41, -101);

            // Marcar como generada y guardar la posición
            setHasEnmaPalace(true);
            setEnmaPalacePosition(spawnPosition);
            System.out.println("[DMZ-Generation] Enma's Palace generated in " + spawnPosition);

            BlockPos snakewayPos = new BlockPos(-67, 40, 146);

            StructureTemplate snakewayInit = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "otherworld/snakeway/snakeway_init"));

            if (snakewayInit != null) {
                snakewayInit.placeInWorld(level, snakewayPos, snakewayPos, settings, level.getRandom(), 2);
            }
            level.setBlock(new BlockPos(0, 42, 149), Blocks.AIR.defaultBlockState(), 3);

            Random random = new Random();
            int maxLong = random.nextInt(3) + 4;
            snakewayPos = snakewayPos.offset(-62, 4, 160);

            for (int i = 0; i < maxLong; i++) {
                StructureTemplate snakewayMid = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "otherworld/snakeway/snakeway_mid"));

                if (snakewayMid != null) {
                    snakewayMid.placeInWorld(level, snakewayPos, snakewayPos, settings, level.getRandom(), 2);
                }
                snakewayPos = snakewayPos.offset(-63, 1, 133);
            }

            BlockPos endSnakewayPos = snakewayPos.offset(49, -1, 4);

            StructureTemplate snakewayEnd = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "otherworld/snakeway/snakeway_end"));

            if (snakewayEnd != null) {
                snakewayEnd.placeInWorld(level, endSnakewayPos, endSnakewayPos, settings, level.getRandom(), 2);
            }

            BlockPos kaioStructure = snakewayPos.offset(-20, 10, 90);

            StructureTemplate kaioPlanet = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "otherworld/kaioplanet"));

            if (kaioPlanet != null) {
                kaioPlanet.placeInWorld(level, kaioStructure, kaioStructure, settings, level.getRandom(), 2);
            }

            BlockPos kaioSpawnPos = new BlockPos(kaioStructure.getX() + 74, kaioStructure.getY() + 127, kaioStructure.getZ() + 29);
            setHasKaioPlanet(true);
            setKaioPlanetPosition(kaioSpawnPos);
            System.out.println("[DMZ-Generation] Kaio's Planet generated in " + kaioSpawnPos);
        }
    }
}