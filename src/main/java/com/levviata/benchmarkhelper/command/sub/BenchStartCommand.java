package com.levviata.benchmarkhelper.command.sub;

import com.levviata.benchmarkhelper.gui.BenchHelperScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.MinecraftForge;

public class BenchStartCommand extends CommandBase {
    @Override
    public String getName() {
        return "start";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/benchmarkhelper start";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        Minecraft.getMinecraft().displayGuiScreen(new BenchHelperScreen());
        if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
            Minecraft.getMinecraft().getSoundHandler().playSound(new net.minecraft.client.audio.PositionedSoundRecord(
                    SoundEvents.ENTITY_ITEM_PICKUP.getSoundName(),
                    SoundCategory.MASTER,
                    0.3f,
                    1.0F, // Pitch
                    false, // Repeat
                    0, // Repeat delay
                    net.minecraft.client.audio.ISound.AttenuationType.NONE,
                    0, 0, 0 // Position irrelevant for UI sounds
            ));
        }


    }
}
