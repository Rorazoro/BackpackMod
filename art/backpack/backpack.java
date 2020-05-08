Stream.of(
VoxelShapes.combineAndSimplify(Block.makeCuboidShape(4.3, 9, 8, 6.3, 10, 11), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(4.3, 4.2, 10, 6.3, 11.2, 11), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(4.3, 2, 6.67276, 6.3, 2.75, 11.42276), Block.makeCuboidShape(4.3, 1, 12.2, 6.3, 4, 13.2), IBooleanFunction.STRAP), IBooleanFunction.STRAP), IBooleanFunction.STRAP),
VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.7, 9, 8, 11.7, 10, 11), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.7, 4.2, 10, 11.7, 11.2, 11), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.7, 2, 6.67276, 11.7, 2.75, 11.42276), Block.makeCuboidShape(9.7, 1, 12.2, 11.7, 4, 13.2), IBooleanFunction.STRAP), IBooleanFunction.STRAP), IBooleanFunction.STRAP),
Block.makeCuboidShape(7.1, 8.5, 3, 9.1, 10, 4),
Block.makeCuboidShape(3.75, 9, 3.6, 12.25, 12, 9.1),
Block.makeCuboidShape(4, 0, 4, 12, 11, 9)
).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});