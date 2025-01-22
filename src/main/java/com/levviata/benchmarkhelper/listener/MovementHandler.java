package com.levviata.benchmarkhelper.listener;

import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.levviata.benchmarkhelper.gui.BenchHelperScreen.shouldWork;

public class MovementHandler {
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player.world.isRemote) {
            EntityPlayer player = event.player;

            if (shouldWork) {
                player.motionX = 1;
            }

        }
    }
}
