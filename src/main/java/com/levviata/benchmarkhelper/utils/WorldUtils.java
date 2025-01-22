package com.levviata.benchmarkhelper.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.SaveHandlerMP;
import net.minecraft.world.storage.WorldInfo;

import java.io.File;
import java.util.Arrays;

public class WorldUtils {
    public void createAndLoadWorld(String worldName) {
        Minecraft mc = Minecraft.getMinecraft();

        // Initialize the world settings
        WorldSettings settings = new WorldSettings(
                0, // Use a random seed
                WorldSettings.getGameTypeById(0), // Set the game mode
                true, // Enable map features (villages, etc.)
                false, // Disable hardcore mode
                WorldType.DEFAULT // Default world type
        );

        // Launch the integrated server to create and load the world
        mc.launchIntegratedServer(worldName, worldName, settings);
    }

}
