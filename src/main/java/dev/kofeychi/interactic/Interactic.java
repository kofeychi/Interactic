package dev.kofeychi.interactic;

import net.fabricmc.api.ModInitializer;

public class Interactic implements ModInitializer {

    @Override
    public void onInitialize() {
        FiguraPlugin.init();
    }
}
