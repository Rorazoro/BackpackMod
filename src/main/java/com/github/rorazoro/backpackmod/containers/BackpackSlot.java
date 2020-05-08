package com.github.rorazoro.backpackmod.containers;

import com.github.rorazoro.backpackmod.init.ItemInit;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class BackpackSlot extends Slot {

    public BackpackSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() != ItemInit.BACKPACK.get();
    }
}