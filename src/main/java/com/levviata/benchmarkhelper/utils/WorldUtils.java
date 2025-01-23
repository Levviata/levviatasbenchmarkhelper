package com.levviata.benchmarkhelper.utils;

import com.levviata.benchmarkhelper.world.BenchmarkWorldType;
import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;

import java.io.File;

import static com.levviata.benchmarkhelper.world.BenchmarkWorldType.BENCHMARK_WORLD_TYPE_NAME;

public class WorldUtils {
    public void createAndLoadWorld(String worldName) {
        Minecraft mc = Minecraft.getMinecraft();
        deleteWorld(worldName); // remove old world if present
        // as we wont be able to create a new one if it exists already

        WorldType benchmarkWorldType = WorldType.byName(BENCHMARK_WORLD_TYPE_NAME);
        if (benchmarkWorldType != null) {
            // Initialize the world settings
            WorldSettings settings = new WorldSettings(
                    0, // Use a random seed
                    WorldSettings.getGameTypeById(1), // Set the game mode
                    true, // Enable map features (villages, etc.)
                    false, // Disable hardcore mode
                    benchmarkWorldType // Default world type
            );
            settings.enableCommands();
            // Launch the integrated server to create and load the world
            mc.launchIntegratedServer(worldName, worldName, settings);
        } else {
            System.out.println("WorldType not found!");
        }
    }

    public void deleteWorld(String worldName) {
        File savesDir = new File(Minecraft.getMinecraft().gameDir, "saves");
        File worldDir = new File(savesDir, worldName);

        // Check if the world exists
        if (worldDir.exists() && worldDir.isDirectory()) {
            deleteDirectory(worldDir); // Recursive delete method
            System.out.println("World " + worldName + " has been deleted.");
        } else {
            System.out.println("World " + worldName + " does not exist.");
        }
    }

    // Recursive method to delete a directory and its contents
    private void deleteDirectory(File file) {
        if (file.isDirectory()) {
            // List all files in the directory
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteDirectory(f); // Recursively delete each file/folder
                }
            }
        }
        file.delete(); // Delete the file or folder
    }
}
