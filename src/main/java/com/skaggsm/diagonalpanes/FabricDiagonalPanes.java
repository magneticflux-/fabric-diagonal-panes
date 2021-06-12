package com.skaggsm.diagonalpanes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FabricDiagonalPanes implements ModInitializer {

    public static final String MODID = "fabric-diagonal-panes";
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        ModContainer modContainer = FabricLoader.getInstance().getModContainer(MODID).orElseThrow(RuntimeException::new);
        boolean success = ResourceManagerHelper.registerBuiltinResourcePack(
                new Identifier(MODID, "default"),
                modContainer,
                ResourcePackActivationType.ALWAYS_ENABLED
        );
        if (!success)
            LOGGER.warn("Failed to register built-in resource pack!");
    }
}
