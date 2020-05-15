package com.github.rorazoro.backpackmod.inventory;

import com.github.rorazoro.backpackmod.init.ItemInit;
import com.github.rorazoro.backpackmod.tileentities.BackpackTileEntity;
import com.github.rorazoro.backpackmod.util.ModConstants;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;

public class BackpackInventory extends Inventory {

    private NonNullList<ItemStack> backpackContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

    // This is the ItemStack of the one backpack that we are holding
    private final ItemStack BACKPACK_ITEMSTACK;

    public BackpackInventory() {
        this(new ItemStack(ItemInit.BACKPACK.get()), ModConstants.ROWS * 9);
    }

    public BackpackInventory(ItemStack stack) {
        this(stack, ModConstants.ROWS * 9);
    }

    public BackpackInventory(ItemStack stack, int size) {
        super(size);
        BACKPACK_ITEMSTACK = stack;
        readItemStack();
    }

    public ItemStack getStack() {
        return BACKPACK_ITEMSTACK;
    }

    public void readItemStack() {
        if (BACKPACK_ITEMSTACK.getTag() != null) {
            readNBT(BACKPACK_ITEMSTACK.getTag());
        }
    }

    public void writeItemStack() {
        if (isEmpty()) {
            BACKPACK_ITEMSTACK.removeChildTag("Items");
        } else {
            writeNBT(BACKPACK_ITEMSTACK.getOrCreateTag());
        }
    }

    private void readNBT(CompoundNBT compound) {
        this.backpackContents = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.backpackContents);
        for (int index = 0; index < backpackContents.size(); index++) {
            setInventorySlotContents(index, this.backpackContents.get(index));
        }
    }

    private void writeNBT(CompoundNBT compound) {
        for (int index = 0; index < this.backpackContents.size(); index++) {
            this.backpackContents.set(index, getStackInSlot(index));
        }
        ItemStackHelper.saveAllItems(compound, this.backpackContents, false);
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        super.closeInventory(player);
        this.writeItemStack();
    }

    public NonNullList<ItemStack> getItems() {
        return this.backpackContents;
    }

    public static void transferToTileEntity(BackpackInventory inv, BackpackTileEntity te) {
        te.setItems(inv.getItems());
    }
}