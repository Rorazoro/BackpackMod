package com.github.rorazoro.backpackmod.init;

import com.github.rorazoro.backpackmod.tileentities.BackpackTileEntity;
import com.github.rorazoro.backpackmod.util.ModConstants;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
            ForgeRegistries.TILE_ENTITIES, ModConstants.MODID);

    public static final RegistryObject<TileEntityType<BackpackTileEntity>> BACKPACK = TILE_ENTITY_TYPES.register(
            "backpack_tile_entity",
            () -> TileEntityType.Builder.create(BackpackTileEntity::new, BlockInit.BACKPACK.get()).build(null));
}