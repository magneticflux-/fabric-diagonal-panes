package com.skaggsm.diagonalpanes;

import io.github.cottonmc.cotton.datapack.virtual.InputStreamProvider;
import io.github.cottonmc.cotton.datapack.virtual.VirtualResourcePack;
import io.github.cottonmc.cotton.datapack.virtual.VirtualResourcePackManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.util.HashMap;

import static java.util.Collections.singletonList;

public class FabricDiagonalPanes implements ModInitializer {

    public static final String MODID = "fabric-diagonal-panes";

    private static final String[] COLORS = new String[]{
            "black",
            "blue",
            "brown",
            "cyan",
            "gray",
            "green",
            "light_blue",
            "light_gray",
            "lime",
            "magenta",
            "orange",
            "pink",
            "purple",
            "red",
            "white",
            "yellow"
    };

    private static void registerPath(String path, HashMap<String, InputStreamProvider> map) {
        map.put(path, () -> FabricDiagonalPanes.class.getResourceAsStream(String.format("/virtual-%s", path)));
    }

    @Override
    public void onInitialize() {
        HashMap<String, InputStreamProvider> map = new HashMap<>();

        registerPath("assets/minecraft/models/block/template_glass_pane_diagonal.json", map);

        registerPath("assets/minecraft/models/block/glass_pane_diagonal.json", map);
        registerPath("assets/minecraft/blockstates/glass_pane.json", map);

        registerPath("assets/minecraft/models/block/iron_bars_diagonal.json", map);
        registerPath("assets/minecraft/blockstates/iron_bars.json", map);

        for (String color : COLORS) {
            registerPath(String.format("assets/minecraft/models/block/%s_stained_glass_pane_diagonal.json", color), map);
            registerPath(String.format("assets/minecraft/blockstates/%s_stained_glass_pane.json", color), map);
        }

        VirtualResourcePackManager.INSTANCE.addPack(
                new VirtualResourcePack(new Identifier(MODID, "models"), map),
                singletonList(ResourceType.CLIENT_RESOURCES)
        );
    }
}
