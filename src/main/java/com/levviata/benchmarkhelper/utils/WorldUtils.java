package com.levviata.benchmarkhelper.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;

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
