package com.github.rorazoro.backpackmod.init;

import com.github.rorazoro.backpackmod.containers.BackpackContainer;
import com.github.rorazoro.backpackmod.util.ModConstants;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerInit {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(
            ForgeRegistries.CONTAINERS, ModConstants.MODID);

    public static final RegistryObject<ContainerType<BackpackContainer>> BACKPACK = CONTAINER_TYPES.register("backpack",
            () -> IForgeContainerType.create(BackpackContainer::new));
}