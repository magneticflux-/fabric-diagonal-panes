package com.skaggsm.diagonalpanes.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.HorizontalConnectingBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PaneBlock.class)
public abstract class PaneBlockMixin extends HorizontalConnectingBlock implements HorizontalConnectingBlockAccessor {

    protected PaneBlockMixin(float radius1, float radius2, float boundingHeight1, float boundingHeight2, float collisionHeight, Settings settings) {
        super(radius1, radius2, boundingHeight1, boundingHeight2, collisionHeight, settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void onInit(AbstractBlock.Settings settings, CallbackInfo ci) {
        VoxelShape center = Block.createCuboidShape(7, 0, 7, 9, 16, 9);

        VoxelShape south = Block.createCuboidShape(7, 0, 7, 9, 16, 16);
        VoxelShape west = Block.createCuboidShape(0, 0, 7, 9, 16, 9);
        VoxelShape north = Block.createCuboidShape(7, 0, 0, 9, 16, 9);
        VoxelShape east = Block.createCuboidShape(7, 0, 7, 16, 16, 9);

        VoxelShape westSouth = VoxelShapes.union(
                Block.createCuboidShape(7, 0, 15, 9, 16, 16),
                Block.createCuboidShape(6, 0, 14, 8, 16, 15),
                Block.createCuboidShape(5, 0, 13, 7, 16, 14),
                Block.createCuboidShape(4, 0, 12, 6, 16, 13),
                Block.createCuboidShape(3, 0, 11, 5, 16, 12),
                Block.createCuboidShape(2, 0, 10, 4, 16, 11),
                Block.createCuboidShape(1, 0, 9, 3, 16, 10),
                Block.createCuboidShape(0, 0, 8, 2, 16, 9),
                Block.createCuboidShape(0, 0, 7, 1, 16, 8)
        );
        VoxelShape northWest = VoxelShapes.union(
                Block.createCuboidShape(7, 0, 0, 9, 16, 1),
                Block.createCuboidShape(6, 0, 1, 8, 16, 2),
                Block.createCuboidShape(5, 0, 2, 7, 16, 3),
                Block.createCuboidShape(4, 0, 3, 6, 16, 4),
                Block.createCuboidShape(3, 0, 4, 5, 16, 5),
                Block.createCuboidShape(2, 0, 5, 4, 16, 6),
                Block.createCuboidShape(1, 0, 6, 3, 16, 7),
                Block.createCuboidShape(0, 0, 7, 2, 16, 8),
                Block.createCuboidShape(0, 0, 8, 1, 16, 9)
        );
        VoxelShape eastSouth = VoxelShapes.union(
                Block.createCuboidShape(7, 0, 15, 9, 16, 16),
                Block.createCuboidShape(8, 0, 14, 10, 16, 15),
                Block.createCuboidShape(9, 0, 13, 11, 16, 14),
                Block.createCuboidShape(10, 0, 12, 12, 16, 13),
                Block.createCuboidShape(11, 0, 11, 13, 16, 12),
                Block.createCuboidShape(12, 0, 10, 14, 16, 11),
                Block.createCuboidShape(13, 0, 9, 15, 16, 10),
                Block.createCuboidShape(14, 0, 8, 16, 16, 9),
                Block.createCuboidShape(15, 0, 7, 16, 16, 8)
        );
        VoxelShape eastNorth = VoxelShapes.union(
                Block.createCuboidShape(7, 0, 0, 9, 16, 1),
                Block.createCuboidShape(8, 0, 1, 10, 16, 2),
                Block.createCuboidShape(9, 0, 2, 11, 16, 3),
                Block.createCuboidShape(10, 0, 3, 12, 16, 4),
                Block.createCuboidShape(11, 0, 4, 13, 16, 5),
                Block.createCuboidShape(12, 0, 5, 14, 16, 6),
                Block.createCuboidShape(13, 0, 6, 15, 16, 7),
                Block.createCuboidShape(14, 0, 7, 16, 16, 8),
                Block.createCuboidShape(15, 0, 8, 16, 16, 9)
        );

        /*
         * Bitmask for each direction
         * 0000
         * ^^^^_ SOUTH
         * |||__ WEST
         * ||___ NORTH
         * |____ EAST
         */

        VoxelShape[] shapes = new VoxelShape[]{
                // 0000
                VoxelShapes.union(center),
                // 0001
                VoxelShapes.union(center, south),
                // 0010
                VoxelShapes.union(center, west),
                // 0011
                westSouth,

                // 0100
                VoxelShapes.union(center, north),
                // 0101
                VoxelShapes.union(center, north, south),
                // 0110
                northWest,
                // 0111
                VoxelShapes.union(center, north, west, south),

                // 1000
                VoxelShapes.union(center, east),
                // 1001
                eastSouth,
                // 1010
                VoxelShapes.union(center, east, west),
                // 1011
                VoxelShapes.union(center, east, west, south),

                // 1100
                eastNorth,
                // 1101
                VoxelShapes.union(center, east, north, south),
                // 1110
                VoxelShapes.union(center, east, north, west),
                // 1111
                VoxelShapes.union(center, east, north, west, south),
        };

        this.setCollisionShapes(shapes);
        this.setBoundingShapes(shapes);
    }
}
