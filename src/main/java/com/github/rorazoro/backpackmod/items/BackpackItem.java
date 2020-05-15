package com.github.rorazoro.backpackmod.items;

import com.github.rorazoro.backpackmod.containers.BackpackContainerProvider;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BackpackItem extends BlockItem {

    public BackpackItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemHeld = playerIn.getHeldItem(handIn);

        if (!worldIn.isRemote) {
            NetworkHooks.openGui((ServerPlayerEntity) playerIn, new BackpackContainerProvider(itemHeld));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}