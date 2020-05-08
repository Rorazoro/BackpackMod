package com.github.rorazoro.backpackmod.inventory;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ExtendedPlayerInventory extends PlayerInventory {

    public final NonNullList<ItemStack> backpackInventory = NonNullList.withSize(1, ItemStack.EMPTY);
    private final List<NonNullList<ItemStack>> allInventories = ImmutableList.of(this.mainInventory,
            this.armorInventory, this.offHandInventory, this.backpackInventory);

    public ExtendedPlayerInventory(PlayerEntity playerIn) {
        super(playerIn);
    }

}