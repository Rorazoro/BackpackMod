package com.github.rorazoro.backpackmod.containers;

import com.github.rorazoro.backpackmod.init.ContainerInit;
import com.github.rorazoro.backpackmod.inventory.BackpackInventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class BackpackContainer extends Container {

    private final IInventory backpackInventory;
    private final int rows;

    public BackpackContainer(int windowId, PlayerInventory playerInventory, int backpackRows) {
        this(windowId, playerInventory, new BackpackInventory(9 * backpackRows), backpackRows);
    }

    public BackpackContainer(int windowId, PlayerInventory playerInventory, IInventory backpackInventory,
            int backpackRows) {
        super(ContainerInit.BACKPACK.get(), windowId);
        assertInventorySize(backpackInventory, backpackRows * 9);
        this.backpackInventory = backpackInventory;
        this.rows = backpackRows;
        backpackInventory.openInventory(playerInventory.player);

        int offset = (this.rows - 4) * 18;

        // Backpack Inventory
        for(int j = 0; j < this.rows; j++)
        {
            for (int i = 0; i < 9; ++i) {
                this.addSlot(new BackpackSlot(backpackInventory, i + j * 9, 8 + i * 18, 18 + j * 18));
            }
        }

        //Main Player Inventory
        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 103 + i * 18 + offset));
            }
        }
        
        // Hotbar
        for(int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 161 + offset));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.backpackInventory.closeInventory(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.rows * 9) {
                if (!this.mergeItemStack(itemstack1, this.rows * 9, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.rows * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    public int getRows() {
        return rows;
    }
}