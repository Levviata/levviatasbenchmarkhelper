package com.levviata.benchmarkhelper;

import com.levviata.benchmarkhelper.listener.BenchmarkButtonHandler;
import com.levviata.benchmarkhelper.listener.BenchmarkPlayerHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class BenchmarkHelper
{

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    // No specific seed used here, only to keep consistency
    public static final long BENCHMARK_WORLD_SEED = -3259099078L;

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     * Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation
     * here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new BenchmarkButtonHandler());
        MinecraftForge.EVENT_BUS.register(new BenchmarkPlayerHandler());
    }
}
