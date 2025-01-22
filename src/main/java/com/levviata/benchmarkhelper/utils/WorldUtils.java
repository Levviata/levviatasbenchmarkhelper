package com.levviata.benchmarkhelper.utils;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.SaveHandlerMP;
import net.minecraft.world.storage.WorldInfo;

import java.util.Arrays;

public class WorldUtils {
    public void createAndLoadWorld(MinecraftServer server, String worldName) {
        // Initialize the world settings
        WorldSettings settings = new WorldSettings(
                server.getWorld(0).getSeed(), // Seed
                WorldSettings.getGameTypeById(0), // Game mode
                true, // Map features enabled
                false, // Hardcore mode disabled
                WorldType.DEFAULT // Default world type
        );

        // Create a save handler
        SaveHandlerMP saveHandler = new SaveHandlerMP();

        // Create world info
        WorldInfo worldInfo = new WorldInfo(settings, worldName);

        // Create the new world
        WorldServer newWorld = new WorldServer(
                server,
                saveHandler,
                worldInfo,
                server.worlds.length, // Assign a new dimension ID
                server.profiler
        );
        newWorld.init();

        // Register the world in the server's world list
        server.worlds = Arrays.copyOf(server.worlds, server.worlds.length + 1);
        server.worlds[server.worlds.length - 1] = newWorld;
    }
}
