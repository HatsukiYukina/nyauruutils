package org.nyauru.nyauruutils.fabric.register;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import org.nyauru.nyauruutils.Nyauruutils;

import java.util.function.Supplier;

public class   FoodItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Nyauruutils.MOD_ID, Registries.ITEM);
    //注册普通物品
    private static RegistrySupplier<Item> registerItem(String id, Supplier<Item> itemSupplier) {
        return ITEMS.register(id, itemSupplier);
    }
    //注册方块物品
    private static RegistrySupplier<Item> registerBlockItem(String id, Supplier<Block> blockSupplier) {
        return ITEMS.register(id, () -> new BlockItem(blockSupplier.get(), new Item.Properties()));
    }

    private static PolymerModelData spicyStripModel;

    // 初始化方法
    public static void initialize() {
        spicyStripModel = PolymerResourcePackUtils.requestModel(
                Items.ROTTEN_FLESH,
                ResourceLocation.fromNamespaceAndPath(Nyauruutils.MOD_ID, "item/spicy_strip")
        ); //辣条物品模型
    }

    private static class SpicyStripItem extends SimplePolymerItem {
        private final PolymerModelData modelData;

        public SpicyStripItem(Properties settings, Item virtualItem, PolymerModelData modelData) { //构造函数
            super(settings, virtualItem);   //父类构造函数
            this.modelData = modelData; //辣条物品模型数据
        }

        @Override
        public int getPolymerCustomModelData(ItemStack itemStack, ServerPlayer player) {
            return this.modelData.value();
        }
    }

    public static final RegistrySupplier<Item> SPICY_STRIP =
            ITEMS.register("spicy_strip", () -> new SpicyStripItem(
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationModifier(0.44F)
                                    .fast()
                                    .alwaysEdible()
                                    .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0f)
                                    .build()
                            )
                            .stacksTo(64)
                    , Items.ROTTEN_FLESH,
                    spicyStripModel
            ));
}
