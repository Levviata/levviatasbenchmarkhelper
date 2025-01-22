package com.levviata.benchmarkhelper.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class BenchHelperScreen extends net.minecraft.client.gui.GuiScreen {
    public static boolean shouldWork = false;

    @Override
    public void initGui() {
        // Add buttons
        buttonList.add(new GuiButton(0, this.width / 2 - 50, this.height / 2 - 10, 100, 20, "Done"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        // Handle button clicks
        if (button.id == 0) {
            shouldWork = true;
            // Close the GUI
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);

        initGui();

        // Draw our elements
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true; // Return true if you want the game to pause while the GUI is open
    }
}

