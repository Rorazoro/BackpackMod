package com.github.rorazoro.backpackmod.util.helpers;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class InventoryHelper
{
    public static ListNBT saveAllItems(ListNBT list, IInventory inventory)
    {
        for(int i = 0; i < inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = inventory.getStackInSlot(i);
            if(!itemstack.isEmpty())
            {
                CompoundNBT compound = new CompoundNBT();
                compound.putByte("Slot", (byte) i);
                itemstack.write(compound);
                list.add(compound);
            }
        }
        return list;
    }

    public static void loadAllItems(ListNBT list, IInventory inventory)
    {
        for(int i = 0; i < list.size(); i++)
        {
            CompoundNBT compound = list.getCompound(i);
            int slot = compound.getByte("Slot") & 255;
            if(slot < inventory.getSizeInventory())
            {
                inventory.setInventorySlotContents(slot, ItemStack.read(compound));
            }
        }
    }
}