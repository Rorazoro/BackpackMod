package com.github.rorazoro.backpackmod.items;

import java.util.function.Supplier;

import com.github.rorazoro.backpackmod.util.ModConstants;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ItemGroups {
    public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(
        ModConstants.MODID, 
        () -> new ItemStack(Items.CHEST)
    );

    public static class ModItemGroup extends ItemGroup {

        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon() {
            return iconSupplier.get();
        }
    }
}