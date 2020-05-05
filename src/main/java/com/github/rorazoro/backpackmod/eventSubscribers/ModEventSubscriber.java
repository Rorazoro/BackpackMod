package com.github.rorazoro.backpackmod.eventSubscribers;

import java.util.Arrays;

import com.github.rorazoro.backpackmod.init.BlockInit;
import com.github.rorazoro.backpackmod.items.ItemGroups;
import com.github.rorazoro.backpackmod.util.ModConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = ModConstants.MODID, bus = Bus.MOD)
public class ModEventSubscriber {
    private static final Logger LOGGER = LogManager.getLogger(ModConstants.MODID + " Mod Event Subscriber");
    private static final String[] BLOCKITEMFILTER = {"backpack_block"};

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream()
                .filter(x -> checkBlockItemFilter(x.get().getRegistryName().getPath())).map(RegistryObject::get)
                .forEach(block -> {
                    final Item.Properties properties = new Item.Properties().group(ItemGroups.MOD_ITEM_GROUP);
                    final BlockItem blockItem = new BlockItem(block, properties);
                    blockItem.setRegistryName(block.getRegistryName());
                    registry.register(blockItem);
                });

        LOGGER.debug("Registered BlockItems!");
    }
    
    private static boolean checkBlockItemFilter(String name) {
        return Arrays.stream(BLOCKITEMFILTER).anyMatch(name::equals);
    }
}