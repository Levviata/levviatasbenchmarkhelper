package com.levviata.benchmarkhelper.listener;

import com.levviata.benchmarkhelper.utils.WorldUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BenchmarkButtonHandler {
    private final WorldUtils worldHandler = new WorldUtils();
    public static final int BENCHMARK_BUTTON_ID = 990;

    @SubscribeEvent
    public void onGuiInit(InitGuiEvent.Post event) {
        // Check if the current GUI is the world selection screen
        if (event.getGui() instanceof GuiWorldSelection) {
            String benchmarkButtonText = "Start Benchmarking";
            int benchmarkButtonWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(benchmarkButtonText) + 20;
            // Add a custom button
            event.getButtonList().add(new GuiButton(
                    BENCHMARK_BUTTON_ID, // Button ID
                    event.getGui().width / 2 - (benchmarkButtonWidth / 2),
                    10,
                    benchmarkButtonWidth,
                    20,
                    benchmarkButtonText
            ));
        }
    }

    @SubscribeEvent
    public void onActionPerformed(GuiScreenEvent.ActionPerformedEvent.Post event) {
        // Check if the current GUI is the world selection screen
        if (event.getGui() instanceof GuiWorldSelection) {
            if (event.getButton().id == BENCHMARK_BUTTON_ID) {
                String worldName = "BenchmarkingWorld";
                // Start benchmarking world
                worldHandler.createAndLoadWorld(worldName);
            }
        }
    }
}