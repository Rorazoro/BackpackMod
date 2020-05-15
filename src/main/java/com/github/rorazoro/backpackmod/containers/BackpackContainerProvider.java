package com.github.rorazoro.backpackmod.containers;

import com.github.rorazoro.backpackmod.inventory.BackpackInventory;
import com.github.rorazoro.backpackmod.util.ModConstants;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class BackpackContainerProvider implements INamedContainerProvider {

    //This is the ItemStack of the one backpack that we are holding
    private final ItemStack BACKPACK_ITEMSTACK;

    public BackpackContainerProvider(ItemStack stack) {
        BACKPACK_ITEMSTACK = stack;
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BackpackContainer(windowId, playerInventory, new BackpackInventory(BACKPACK_ITEMSTACK, 9 * ModConstants.ROWS));
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.backpack");
    }
    
}