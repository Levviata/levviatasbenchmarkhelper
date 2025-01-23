package com.levviata.benchmarkhelper.world;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorOverworld;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class BenchmarkWorldGenerator implements IChunkGenerator {
    private final World world;
    private final long seed;
    private final boolean mapFeaturesEnabled;
    private final String generatorOptions;
    ChunkGeneratorOverworld overworldGenerator;


    public BenchmarkWorldGenerator(World world, long seed, boolean mapFeaturesEnabled, String generatorOptions) {
        this.world = world;
        this.seed = seed;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.generatorOptions = generatorOptions;
        this.overworldGenerator = new ChunkGeneratorOverworld(world, seed, mapFeaturesEnabled, generatorOptions);
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        // Step 1: Generate the chunk using the overworld generator.
        Chunk chunk = overworldGenerator.generateChunk(x, z);

        // Step 2: Modify the chunk: Only affect chunks along the X axis at z = 0, starting from (0, 63, 0)
        int startY = 63;
        int endY = 255;  // The max Y value in Minecraft world

        if (x >= 0 && z == 0) {
            for (int bx = 0; bx < 16; bx++) {
                for (int by = startY; by <= endY; by++) {
                    chunk.setBlockState(new BlockPos(bx, by, -1), Blocks.AIR.getDefaultState());
                    chunk.setBlockState(new BlockPos(bx, by, 0), Blocks.AIR.getDefaultState());
                    chunk.setBlockState(new BlockPos(bx, by, 1), Blocks.AIR.getDefaultState());
                }
            }
        }

        // Step 3: Return the chunk
        return chunk;
    }

    @Override
    public void populate(int x, int z) {
        overworldGenerator.populate(x, z);
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return true;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return overworldGenerator.getPossibleCreatures(creatureType, pos);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return overworldGenerator.getNearestStructurePos(worldIn, structureName, position, findUnexplored);
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
        overworldGenerator.recreateStructures(chunkIn, x, z);
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return overworldGenerator.isInsideStructure(worldIn, structureName, pos);
    }
}
