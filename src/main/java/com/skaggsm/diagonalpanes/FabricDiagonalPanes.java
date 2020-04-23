package com.skaggsm.diagonalpanes;

import io.github.cottonmc.cotton.datapack.virtual.VirtualResourcePack;
import io.github.cottonmc.cotton.datapack.virtual.VirtualResourcePackManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.util.HashMap;

import static java.util.Collections.singletonList;

public class FabricDiagonalPanes implements ModInitializer {
    public static final String MODID = "fabric-diagonal-panes";

    @Override
    public void onInitialize() {
        VirtualResourcePackManager.INSTANCE.addPack(new VirtualResourcePack(new Identifier("test", "test"), new HashMap<>()), singletonList(ResourceType.CLIENT_RESOURCES));
    }
}
