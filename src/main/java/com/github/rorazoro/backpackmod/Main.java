package com.github.rorazoro.backpackmod;

import com.github.rorazoro.backpackmod.init.BlockInit;
import com.github.rorazoro.backpackmod.util.ModConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModConstants.MODID)
public class Main {
    public static final Logger LOGGER = LogManager.getLogger(ModConstants.MODID);

    public Main() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockInit.BLOCKS.register(modEventBus);
        //ItemInit.ITEMS.register(modEventBus);
    }
}