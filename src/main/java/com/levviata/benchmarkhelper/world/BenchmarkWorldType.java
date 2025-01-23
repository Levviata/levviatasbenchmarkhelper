package com.levviata.benchmarkhelper.world;

import net.minecraft.world.WorldType;
import net.minecraft.world.World;

public class BenchmarkWorldType extends WorldType {
    public static final String BENCHMARK_WORLD_TYPE_NAME = "benchmark";

    /**
     * Creates a new world type, the ID is hidden and should not be referenced by modders.
     * It will automatically expand the underlying workdType array if there are no IDs left.
     *
     * @param name
     */
    public BenchmarkWorldType(String name) {
        super(name);
    }

    @Override
    public void onGUICreateWorldPress() {
        // Optional: Add custom behavior when creating a world with this type.
    }

    @Override
    public boolean canBeCreated() {
        // Return true to allow this world type to be selectable.
        return true;
    }

    @Override
    public net.minecraft.world.gen.IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        // Return your custom ChunkGenerator here.
        return new BenchmarkWorldGenerator(world, world.getSeed(), true, generatorOptions);
    }
}

