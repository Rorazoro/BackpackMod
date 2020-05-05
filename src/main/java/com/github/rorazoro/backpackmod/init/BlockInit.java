package com.github.rorazoro.backpackmod.init;

import com.github.rorazoro.backpackmod.blocks.BackpackBlock;
import com.github.rorazoro.backpackmod.util.ModConstants;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
            ModConstants.MODID);

    public static final RegistryObject<BackpackBlock> BACKPACK_BLOCK = BLOCKS.register("backpack_block",
            () -> new BackpackBlock(Block.Properties.create(Material.ROCK)));
}