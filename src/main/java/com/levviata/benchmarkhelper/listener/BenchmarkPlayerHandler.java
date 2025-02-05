package com.levviata.benchmarkhelper.listener;

import com.levviata.benchmarkhelper.BenchmarkHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class BenchmarkPlayerHandler
{

    private float benchPreviousYaw = 0;
    private float benchPreviousPitch = 0;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if (event.player.world.isRemote)
        {
            EntityPlayer player = event.player;
            if (event.phase == TickEvent.Phase.START)
            {
                if (benchPreviousYaw != 0 && benchPreviousPitch != 0)
                {
                    player.rotationYaw = 0;
                    player.rotationPitch = 0;

                    // Apply to head rotation too
                    player.rotationYawHead = 0;
                    player.prevRotationYawHead = 0;
                } else
                {
                    BenchmarkHelper.LOGGER.debug("Skipped setting yaw and pitch!");
                }

                player.motionZ += 0.1;
            }
            if (event.phase == TickEvent.Phase.END)
            {
                benchPreviousYaw = player.rotationYaw;
                benchPreviousPitch = player.rotationPitch;
            }
        }
    }
}
