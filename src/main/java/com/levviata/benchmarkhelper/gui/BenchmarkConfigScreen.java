package com.levviata.benchmarkhelper.gui;

import com.levviata.benchmarkhelper.BenchmarkHelper;
import com.levviata.benchmarkhelper.util.WorldUtils;
import net.minecraft.client.gui.GuiButton;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkConfigScreen extends net.minecraft.client.gui.GuiScreen
{

    private final WorldUtils worldHandler = new WorldUtils();

    private List<Integer> benchmarkReservedButtonIds = new ArrayList<>();

    @Override
    public void initGui()
    {
        int idsToReserve = 16;
        // Reserving numbers from 0 to 16
        for (int i = 0; i <= idsToReserve; i++)
        {
            benchmarkReservedButtonIds.add(i);
        }

        if (benchmarkReservedButtonIds.toArray().length == idsToReserve)
        {
            BenchmarkHelper.LOGGER.debug("Reserved button ids are correct");
        }

        // Add buttons
        buttonList.add(
                new GuiButton(0, this.width / 2 - 50, this.height / 2 - 10, 100, 20, "Done"));
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        // Handle button clicks
        if (button.id == 0)
        {
            // Close the GUI
            this.mc.displayGuiScreen(null);

            worldHandler.createAndLoadWorld();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawBackground(0);

        // Draw our elements
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false; // Return true if you want the game to pause while the GUI is open
    }

    public void setReservedIds(List<Integer> listIn)
    {
        benchmarkReservedButtonIds = listIn;
    }

    public List<Integer> getReservedIds()
    {
        return benchmarkReservedButtonIds;
    }
}
