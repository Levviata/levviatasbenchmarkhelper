package com.levviata.benchmarkhelper.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;

import java.io.File;

import static com.levviata.benchmarkhelper.BenchmarkHelper.BENCHMARK_WORLD_SEED;

public class WorldUtils
{

    public static final String WORLD_NAME = "BenchmarkingWorld";

    public void createAndLoadWorld()
    {
        Minecraft mc = Minecraft.getMinecraft();

        deleteWorld(WORLD_NAME);
        // Initialize the world settings
        WorldSettings settings = new WorldSettings(
                BENCHMARK_WORLD_SEED,
                WorldSettings.getGameTypeById(3),
                true, // Enable map features (villages, etc.)
                false, // Disable hardcore mode
                WorldType.DEFAULT // Default world type
        );
        settings.enableCommands();
        // Launch the integrated server to create and load the world
        mc.launchIntegratedServer(WORLD_NAME, WORLD_NAME, settings);
    }

    public void deleteWorld(String worldName)
    {
        File savesDir = new File(Minecraft.getMinecraft().gameDir, "saves");
        File worldDir = new File(savesDir, worldName);

        // Check if the world exists
        if (worldDir.exists() && worldDir.isDirectory())
        {
            deleteDirectory(worldDir); // Recursive delete method
            System.out.println("World " + worldName + " has been deleted.");
        } else
        {
            System.out.println("World " + worldName + " does not exist.");
        }
    }

    // Recursive method to delete a directory and its contents
    private void deleteDirectory(File file)
    {
        if (file.isDirectory())
        {
            // List all files in the directory
            File[] files = file.listFiles();
            if (files != null)
            {
                for (File f : files)
                {
                    deleteDirectory(f); // Recursively delete each file/folder
                }
            }
        }
        file.delete(); // Delete the file or folder
    }
}
