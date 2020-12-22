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
                VoxelShapes.empty(),

                // 0100
                VoxelShapes.union(center, north),
                // 0101
                VoxelShapes.union(center, north, south),
                // 0110
                VoxelShapes.empty(),
                // 0111
                VoxelShapes.union(center, north, west, south),

                // 1000
                VoxelShapes.union(center, east),
                // 1001
                VoxelShapes.empty(),
                // 1010
                VoxelShapes.union(center, east, west),
                // 1011
                VoxelShapes.union(center, east, west, south),

                // 1100
                VoxelShapes.empty(),
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
