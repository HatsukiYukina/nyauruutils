package org.nyauru.nyauruutils.fabric.register;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.nyauru.nyauruutils.Nyauruutils;

public class Tabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Nyauruutils.MOD_ID, Registries.CREATIVE_MODE_TAB);
    //创造模式 主物品栏
    public static final RegistrySupplier<CreativeModeTab> CREATIVE_TAB_MAIN = TABS.register(
            "main_tab",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("tab.nyauruutils.main"))
                    .icon(() -> new ItemStack(FoodItems.SPICY_STRIP.get()))
                    .displayItems((parameters, output) -> {
                        //在这填充物品
                        output.accept(FoodItems.SPICY_STRIP.get()); //辣椒
                    })
                    .build()
    );
}
