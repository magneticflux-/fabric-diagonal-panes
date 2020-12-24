package com.skaggsm.diagonalpanes.mixin;

import com.skaggsm.diagonalpanes.NotVoxelShape;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.HorizontalConnectingBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        VoxelShape westSouthCollision = VoxelShapes.union(
                Block.createCuboidShape(0, 0, 8, 1, 16, 9),
                Block.createCuboidShape(1, 0, 9, 2, 16, 10),
                Block.createCuboidShape(2, 0, 10, 3, 16, 11),
                Block.createCuboidShape(3, 0, 11, 4, 16, 12),
                Block.createCuboidShape(4, 0, 12, 5, 16, 13),
                Block.createCuboidShape(5, 0, 13, 6, 16, 14),
                Block.createCuboidShape(6, 0, 14, 7, 16, 15),
                Block.createCuboidShape(7, 0, 15, 8, 16, 16)
        );
        VoxelShape westSouthBounds = VoxelShapes.union(
                Block.createCuboidShape(0, 0, 8, 2, 16, 10),
                Block.createCuboidShape(1, 0, 9, 3, 16, 11),
                Block.createCuboidShape(2, 0, 10, 4, 16, 12),
                Block.createCuboidShape(3, 0, 11, 5, 16, 13),
                Block.createCuboidShape(4, 0, 12, 6, 16, 14),
                Block.createCuboidShape(5, 0, 13, 7, 16, 15),
                Block.createCuboidShape(6, 0, 14, 8, 16, 16)
        );

        VoxelShape northWestCollision = VoxelShapes.union(
                Block.createCuboidShape(0, 0, 7, 1, 16, 8),
                Block.createCuboidShape(1, 0, 6, 2, 16, 7),
                Block.createCuboidShape(2, 0, 5, 3, 16, 6),
                Block.createCuboidShape(3, 0, 4, 4, 16, 5),
                Block.createCuboidShape(4, 0, 3, 5, 16, 4),
                Block.createCuboidShape(5, 0, 2, 6, 16, 3),
                Block.createCuboidShape(6, 0, 1, 7, 16, 2),
                Block.createCuboidShape(7, 0, 0, 8, 16, 1)
        );
        VoxelShape northWestBounds = VoxelShapes.union(
                Block.createCuboidShape(0, 0, 6, 2, 16, 8),
                Block.createCuboidShape(1, 0, 5, 3, 16, 7),
                Block.createCuboidShape(2, 0, 4, 4, 16, 6),
                Block.createCuboidShape(3, 0, 3, 5, 16, 5),
                Block.createCuboidShape(4, 0, 2, 6, 16, 4),
                Block.createCuboidShape(5, 0, 1, 7, 16, 3),
                Block.createCuboidShape(6, 0, 0, 8, 16, 2)
        );

        VoxelShape eastSouthCollision = VoxelShapes.union(
                Block.createCuboidShape(15, 0, 8, 16, 16, 9),
                Block.createCuboidShape(14, 0, 9, 15, 16, 10),
                Block.createCuboidShape(13, 0, 10, 14, 16, 11),
                Block.createCuboidShape(12, 0, 11, 13, 16, 12),
                Block.createCuboidShape(11, 0, 12, 12, 16, 13),
                Block.createCuboidShape(10, 0, 13, 11, 16, 14),
                Block.createCuboidShape(9, 0, 14, 10, 16, 15),
                Block.createCuboidShape(8, 0, 15, 9, 16, 16)
        );
        VoxelShape eastSouthBounds = VoxelShapes.union(
                Block.createCuboidShape(14, 0, 8, 16, 16, 10),
                Block.createCuboidShape(13, 0, 9, 15, 16, 11),
                Block.createCuboidShape(12, 0, 10, 14, 16, 12),
                Block.createCuboidShape(11, 0, 11, 13, 16, 13),
                Block.createCuboidShape(10, 0, 12, 12, 16, 14),
                Block.createCuboidShape(9, 0, 13, 11, 16, 15),
                Block.createCuboidShape(8, 0, 14, 10, 16, 16)
        );

        VoxelShape eastNorthCollision = VoxelShapes.union(
                Block.createCuboidShape(15, 0, 7, 16, 16, 8),
                Block.createCuboidShape(14, 0, 6, 15, 16, 7),
                Block.createCuboidShape(13, 0, 5, 14, 16, 6),
                Block.createCuboidShape(12, 0, 4, 13, 16, 5),
                Block.createCuboidShape(11, 0, 3, 12, 16, 4),
                Block.createCuboidShape(10, 0, 2, 11, 16, 3),
                Block.createCuboidShape(9, 0, 1, 10, 16, 2),
                Block.createCuboidShape(8, 0, 0, 9, 16, 1)
        );
        VoxelShape eastNorthBounds = VoxelShapes.union(
                Block.createCuboidShape(14, 0, 6, 16, 16, 8),
                Block.createCuboidShape(13, 0, 5, 15, 16, 7),
                Block.createCuboidShape(12, 0, 4, 14, 16, 6),
                Block.createCuboidShape(11, 0, 3, 13, 16, 5),
                Block.createCuboidShape(10, 0, 2, 12, 16, 4),
                Block.createCuboidShape(9, 0, 1, 11, 16, 3),
                Block.createCuboidShape(8, 0, 0, 10, 16, 2)
        );

        /*
         * Bitmask for each direction
         * 0000
         * ^^^^_ SOUTH
         * |||__ WEST
         * ||___ NORTH
         * |____ EAST
         */

        VoxelShape[] collisionShapes = new VoxelShape[]{
                // 0000
                VoxelShapes.union(center),
                // 0001
                VoxelShapes.union(center, south),
                // 0010
                VoxelShapes.union(center, west),
                // 0011
                westSouthCollision,

                // 0100
                VoxelShapes.union(center, north),
                // 0101
                VoxelShapes.union(center, north, south),
                // 0110
                northWestCollision,
                // 0111
                VoxelShapes.union(center, north, west, south),

                // 1000
                VoxelShapes.union(center, east),
                // 1001
                eastSouthCollision,
                // 1010
                VoxelShapes.union(center, east, west),
                // 1011
                VoxelShapes.union(center, east, west, south),

                // 1100
                eastNorthCollision,
                // 1101
                VoxelShapes.union(center, east, north, south),
                // 1110
                VoxelShapes.union(center, east, north, west),
                // 1111
                VoxelShapes.union(center, east, north, west, south),
        };

        // Non-voxel bounding box setup:

        VoxelShape[] boundingShapes = Arrays.copyOf(collisionShapes, collisionShapes.length);
        // 0.9558058261758 0.0441941738242
        // 0.5441941738242 0.4558058261758
        Vec3d p1 = new Vec3d(-0.0441941738242, 0, 0.5441941738242);
        Vec3d p2 = new Vec3d(0.4558058261758, 0, 1.0441941738242);
        Vec3d p3 = new Vec3d(0.5441941738242, 0, 0.9558058261758);
        Vec3d p4 = new Vec3d(0.0441941738242, 0, 0.4558058261758);
        Vec3d p1u16 = p1.add(0, 1, 0);
        Vec3d p2u16 = p2.add(0, 1, 0);
        Vec3d p3u16 = p3.add(0, 1, 0);
        Vec3d p4u16 = p4.add(0, 1, 0);

        List<Vec3d> westSouthBoundingEdges = Arrays.asList(
                // Bottom
                p1, p2,
                p2, p3,
                p3, p4,
                p4, p1,

                // Sides
                p1, p1u16,
                p2, p2u16,
                p3, p3u16,
                p4, p4u16,

                // Top
                p1u16, p2u16,
                p2u16, p3u16,
                p3u16, p4u16,
                p4u16, p1u16
        );
        List<Vec3d> northWestBoundingEdges = westSouthBoundingEdges.stream().map(PaneBlockMixin::flipZ).collect(Collectors.toList());
        List<Vec3d> eastSouthBoundingEdges = westSouthBoundingEdges.stream().map(PaneBlockMixin::flipX).collect(Collectors.toList());
        List<Vec3d> eastNorthBoundingEdges = westSouthBoundingEdges.stream().map(PaneBlockMixin::flipBoth).collect(Collectors.toList());

        boundingShapes[3] = new NotVoxelShape(westSouthBounds, westSouthBoundingEdges);
        boundingShapes[6] = new NotVoxelShape(northWestBounds, northWestBoundingEdges);
        boundingShapes[9] = new NotVoxelShape(eastSouthBounds, eastSouthBoundingEdges);
        boundingShapes[12] = new NotVoxelShape(eastNorthBounds, eastNorthBoundingEdges);

        this.setCollisionShapes(collisionShapes);
        this.setBoundingShapes(boundingShapes);
    }

    private static Vec3d flipX(Vec3d in) {
        return new Vec3d(1 - in.x, in.y, in.z);
    }

    private static Vec3d flipZ(Vec3d in) {
        return new Vec3d(in.x, in.y, 1 - in.z);
    }

    private static Vec3d flipBoth(Vec3d in) {
        return flipX(flipZ(in));
    }
}
