package com.github.rorazoro.backpackmod.init;

import com.github.rorazoro.backpackmod.enums.ModArmorMaterial;
import com.github.rorazoro.backpackmod.items.BackpackItem;
import com.github.rorazoro.backpackmod.items.ItemGroups;
import com.github.rorazoro.backpackmod.util.ModConstants;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
            ModConstants.MODID);

    public static final RegistryObject<BackpackItem> BACKPACK_BLOCKITEM = ITEMS.register("backpack_block",
            () -> new BackpackItem(ModArmorMaterial.BACKPACK, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ItemGroups.MOD_ITEM_GROUP)));
}