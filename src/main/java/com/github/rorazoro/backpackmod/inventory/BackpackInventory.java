package com.github.rorazoro.backpackmod.inventory;

import com.github.rorazoro.backpackmod.init.ItemInit;
import com.github.rorazoro.backpackmod.util.helpers.InventoryHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;

public class BackpackInventory extends Inventory {

    public BackpackInventory(int rows) {
        super(9 * rows);
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void openInventory(PlayerEntity player) {
        this.clear();
        ItemStack backpack = new ItemStack(ItemInit.BACKPACK.get(), 1);
        if (!backpack.isEmpty()) {
            CompoundNBT compound = backpack.getTag();
            if (compound != null) {
                if (compound.contains("Items", Constants.NBT.TAG_LIST)) {
                    InventoryHelper.loadAllItems(compound.getList("Items", Constants.NBT.TAG_COMPOUND), this);
                }
            }
        }
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        ItemStack backpack = player.inventory.getCurrentItem();
        if (backpack.getItem() == ItemInit.BACKPACK.get()) {
            if (!backpack.isEmpty()) {
                CompoundNBT compound = backpack.getTag();
                if (compound == null) {
                    compound = new CompoundNBT();
                }
                ListNBT list = new ListNBT();
                InventoryHelper.saveAllItems(list, this);
                compound.put("Items", list);
                backpack.setTag(compound);
            }
        }
    }

}