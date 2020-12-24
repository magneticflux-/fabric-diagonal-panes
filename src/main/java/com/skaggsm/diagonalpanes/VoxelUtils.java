package com.skaggsm.diagonalpanes;

import net.minecraft.block.Block;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.List;
import java.util.function.Function;

public class VoxelUtils {

    /**
     * 1x1x1 coordinate space
     */
    public static Vec3d flipX(Vec3d in) {
        return new Vec3d(1 - in.x, in.y, in.z);
    }

    /**
     * 1x1x1 coordinate space
     */
    public static Vec3d flipZ(Vec3d in) {
        return new Vec3d(in.x, in.y, 1 - in.z);
    }

    /**
     * 1x1x1 coordinate space
     */
    public static Vec3d flipBoth(Vec3d in) {
        return flipX(flipZ(in));
    }

    /**
     * 16x16x16 coordinate space
     */
    public static Box flipX(Box in) {
        return new Box(16 - in.minX, in.minY, in.minZ, 16 - in.maxX, in.maxY, in.maxZ);
    }

    /**
     * 16x16x16 coordinate space
     */
    public static Box flipZ(Box in) {
        return new Box(in.minX, in.minY, 16 - in.minZ, in.maxX, in.maxY, 16 - in.maxZ);
    }

    /**
     * 16x16x16 coordinate space
     */
    public static Box flipBoth(Box in) {
        return flipX(flipZ(in));
    }

    public static VoxelShape voxelize(Box in) {
        return Block.createCuboidShape(in.minX, in.minY, in.minZ, in.maxX, in.maxY, in.maxZ);
    }

    public static VoxelShape voxelizeAll(List<Box> boxes, Function<Box, Box> modification) {
        if (modification == null) modification = Function.identity();

        return VoxelShapes.union(VoxelShapes.empty(), boxes.stream()
                .map(modification)
                .map(VoxelUtils::voxelize)
                .toArray(VoxelShape[]::new)
        );
    }
}
