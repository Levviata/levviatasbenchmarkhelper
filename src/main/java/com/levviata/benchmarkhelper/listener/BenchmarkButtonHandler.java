package com.levviata.benchmarkhelper.listener;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiWorldSelection;

public class BenchmarkButtonHandler {
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
    public void onGuiDraw(GuiScreenEvent.DrawScreenEvent.Post event) {
        // Check if the current GUI is the world selection screen
        if (event.getGui() instanceof GuiWorldSelection) {

        }
    }

    @SubscribeEvent
    public void onButtonClick(ActionPerformedEvent.Pre event) {
        // Check if the clicked button is your custom button
        if (event.getButton().id == 4) {
        }
    }
}