package com.github.rorazoro.backpackmod.eventSubscribers;

import com.github.rorazoro.backpackmod.client.gui.BackpackScreen;
import com.github.rorazoro.backpackmod.init.ContainerInit;
import com.github.rorazoro.backpackmod.util.ModConstants;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ModConstants.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ContainerInit.BACKPACK.get(), BackpackScreen::new);
    }
}