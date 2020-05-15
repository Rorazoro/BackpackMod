package com.github.rorazoro.backpackmod.blocks;

import java.util.stream.Stream;

import com.github.rorazoro.backpackmod.init.TileEntityTypeInit;
import com.github.rorazoro.backpackmod.inventory.BackpackInventory;
import com.github.rorazoro.backpackmod.tileentities.BackpackTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BackpackBlock extends Block {

    public static final VoxelShape SHAPE_N = Stream.of(
            // VoxelShapes.combineAndSimplify(Block.makeCuboidShape(4.3, 9, 8, 6.3, 10, 11),
            // VoxelShapes.combineAndSimplify(Block.makeCuboidShape(4.3, 4.2, 10, 6.3, 11.2,
            // 11),
            // VoxelShapes.combineAndSimplify(Block.makeCuboidShape(4.3, 2, 6.67276, 6.3,
            // 2.75, 11.42276),
            // Block.makeCuboidShape(4.3, 1, 12.2, 6.3, 4, 13.2), IBooleanFunction.OR),
            // IBooleanFunction.OR),
            // IBooleanFunction.OR),
            // VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.7, 9, 8, 11.7, 10,
            // 11),
            // VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.7, 4.2, 10, 11.7,
            // 11.2, 11),
            // VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.7, 2, 6.67276, 11.7,
            // 2.75, 11.42276),
            // Block.makeCuboidShape(9.7, 1, 12.2, 11.7, 4, 13.2), IBooleanFunction.OR),
            // IBooleanFunction.OR),
            // IBooleanFunction.OR),
            Block.makeCuboidShape(7.1, 8.5, 3, 9.1, 10, 4), Block.makeCuboidShape(3.75, 9, 3.6, 12.25, 12, 9.1),
            Block.makeCuboidShape(4, 0, 4, 12, 11, 9)).reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
            }).get();

    public BackpackBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_N;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypeInit.BACKPACK.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
            Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof BackpackTileEntity) {
                BackpackTileEntity backpacktileentity = (BackpackTileEntity) tileentity;
                player.openContainer(backpacktileentity);
            } else {
                return ActionResultType.PASS;
            }
        }
        return ActionResultType.SUCCESS;
    }

    // @Override
    // public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state,
    // PlayerEntity player) {
    // TileEntity tileentity = worldIn.getTileEntity(pos);
    // if (tileentity instanceof BackpackTileEntity) {
    // BackpackTileEntity backpacktileentity = (BackpackTileEntity) tileentity;
    // if (!worldIn.isRemote && player.isCreative() &&
    // !backpacktileentity.isEmpty()) {
    // ItemStack itemstack = new ItemStack(BlockInit.BACKPACK.get());
    // CompoundNBT compoundnbt = backpacktileentity.saveToNbt(new CompoundNBT());
    // if (!compoundnbt.isEmpty()) {
    // itemstack.setTagInfo("Items", compoundnbt.getCompound("Items"));
    // }

    // if (backpacktileentity.hasCustomName()) {
    // itemstack.setDisplayName(backpacktileentity.getCustomName());
    // }

    // ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX(), (double)
    // pos.getY(),
    // (double) pos.getZ(), itemstack);
    // itementity.setDefaultPickupDelay();
    // worldIn.addEntity(itementity);
    // } else {
    // backpacktileentity.fillWithLoot(player);
    // }
    // }

    // super.onBlockHarvested(worldIn, pos, state, player);
    // }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof BackpackTileEntity) {
            BackpackTileEntity backpacktileentity = (BackpackTileEntity) tileentity;
            if (!worldIn.isRemote && backpacktileentity != null) {
                BackpackInventory inv = new BackpackInventory(stack);
                BackpackInventory.transferToTileEntity(inv, backpacktileentity);
            }
        }

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }
}