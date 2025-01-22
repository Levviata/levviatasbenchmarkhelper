package com.levviata.benchmarkhelper.command;

import com.levviata.benchmarkhelper.command.sub.BenchStartCommand;
import net.minecraft.command.ICommandSender;

public class BenchHelperCommand extends net.minecraftforge.server.command.CommandTreeBase {
    public BenchHelperCommand() {
        addSubcommand(new BenchStartCommand());
    }

    @Override
    public String getName() {
        return "benchmarkhelper";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/benchmarkhelper [command]";
    }
}
