package org.nyauru.nyauruutils.fabric;

import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import org.nyauru.nyauruutils.Nyauruutils;
import net.fabricmc.api.ModInitializer;
import org.nyauru.nyauruutils.fabric.register.FoodItems;
import org.nyauru.nyauruutils.fabric.register.Tabs;

import static org.nyauru.nyauruutils.Nyauruutils.MOD_ID;

public final class NyauruutilsFabric implements ModInitializer {

    public static PolymerModelData SPICY_STRIP_MODEL;
    @Override
    public void onInitialize() {
        boolean success = PolymerResourcePackUtils.addModAssets(MOD_ID);
        PolymerResourcePackUtils.RESOURCE_PACK_CREATION_EVENT.register((builder) -> {
        });
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        // Run our common setup.
        FoodItems.initialize();
        FoodItems.ITEMS.register();
        Tabs.TABS.register();
        Nyauruutils.init();
    }
}
