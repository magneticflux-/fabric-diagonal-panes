package com.skaggsm.diagonalpanes.mixin;

import net.minecraft.block.HorizontalConnectingBlock;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HorizontalConnectingBlock.class)
public interface HorizontalConnectingBlockAccessor {
    @Accessor
    void setCollisionShapes(VoxelShape[] collisionShapes);

    @Accessor
    void setBoundingShapes(VoxelShape[] boundingShapes);
}
