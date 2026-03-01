package org.nyauru.nyauruutils.fabric.register;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
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
import net.minecraft.world.level.block.state.BlockState;
import org.nyauru.nyauruutils.Nyauruutils;

import java.util.function.Supplier;

public class   FoodItems {
    /*++++++++++++++++++++++++++++++++++*/
    /*           此为注册机，勿动          */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Nyauruutils.MOD_ID, Registries.ITEM);
    //注册普通物品
    private static RegistrySupplier<Item> registerItem(String id, Supplier<Item> itemSupplier) {
        return ITEMS.register(id, itemSupplier);
    }
    //注册方块物品
    private static RegistrySupplier<Item> registerBlockItem(String id, Supplier<Block> blockSupplier) {
        return ITEMS.register(id, () -> new BlockItem(blockSupplier.get(), new Item.Properties()));
    }/*           注册机模块结束          */

    /*++++++++++++++++++++++++++++++++++*/

    /*           资源数据定义区           */
    private static PolymerModelData spicyStripModel; //辣条物品模型数据
    private static PolymerModelData ChocolateModel; //巧克力物品模型数据
    private static BlockState westlakeFishBlockState;//西湖醋鱼方块状态
    /*           资源数据定义区结束          */
    /*++++++++++++++++++++++++++++++++++*/
    /*           polymer物品初始化函数         */
    public static void initialize() {
        spicyStripModel = PolymerResourcePackUtils.requestModel( //获取模型数据
                Items.ROTTEN_FLESH,//虚拟物品
                ResourceLocation.fromNamespaceAndPath(Nyauruutils.MOD_ID, "item/spicy_strip") //模型文件路径
        ); //辣条物品模型

        ChocolateModel = PolymerResourcePackUtils.requestModel( //获取模型数据
                Items.NETHER_BRICK,//虚拟物品
                ResourceLocation.fromNamespaceAndPath(Nyauruutils.MOD_ID, "item/chocolate") //模型文件路径
        ); //巧克力物品模型

        PolymerBlockModel fishBlockModel = PolymerBlockModel.of(
                ResourceLocation.fromNamespaceAndPath(Nyauruutils.MOD_ID, "block/westlake_fish")
        );//西湖醋鱼模型
        westlakeFishBlockState = PolymerBlockResourceUtils.requestBlock(// 请求一个完整方块模型
                BlockModelType.FULL_BLOCK, //方块模型类型
                fishBlockModel //模型数据
        );
    }/*           polymer物品初始化函数结束          */
    /*++++++++++++++++++++++++++++++++++*/

    /*           下面为具体实现区          */

    /*++++++++++++++++++++++++++++++++++*/
    /*           辣条          */
    private static class SpicyStripItem extends SimplePolymerItem { //辣条物品
        private final PolymerModelData modelData; //辣条物品模型数据

        public SpicyStripItem(Properties settings, Item virtualItem, PolymerModelData modelData) { //构造函数
            super(settings, virtualItem);   //父类构造函数
            this.modelData = modelData; //辣条物品模型数据
        }
        @Override
        public int getPolymerCustomModelData(ItemStack itemStack, ServerPlayer player) { //获取模型数据
            return this.modelData.value(); //返回模型数据
        }
    }

    public static final RegistrySupplier<Item> SPICY_STRIP =  //辣条
            ITEMS.register("spicy_strip", () -> new SpicyStripItem(
                    new Item.Properties() //属性
                            .food(new FoodProperties.Builder() //食物属性
                                    .nutrition(6)   //饱食度
                                    .saturationModifier(0.44F)  //营养价值
                                    .fast() //快速食用
                                    .alwaysEdible() //永久能吃，无视已饱
                                    .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0f) //添加效果
                                    .build()  //食物属性结束
                            )
                            .stacksTo(64)  //堆叠数
                    , Items.ROTTEN_FLESH, //虚拟物品
                    spicyStripModel //模型数据
            ){
                @Override //获取模型数据
                public int getPolymerCustomModelData(ItemStack itemStack, ServerPlayer player) {
                    return 2; //返回模型数据id
                }
            });
    /*           辣条结束          */

    /*           巧克力          */
    private static class ChocolateItem extends SimplePolymerItem { //辣条物品
        private final PolymerModelData modelData;

        public ChocolateItem(Properties settings, Item virtualItem, PolymerModelData modelData) { //构造函数
            super(settings, virtualItem);   //父类构造函数
            this.modelData = modelData;
        }
        @Override
        public int getPolymerCustomModelData(ItemStack itemStack, ServerPlayer player) { //获取模型数据
            return this.modelData.value(); //返回模型数据
        }
    }

    public static final RegistrySupplier<Item> CHOCOLATE =
            ITEMS.register("chocolate", () -> new ChocolateItem(
                    new Item.Properties() //属性
                            .food(new FoodProperties.Builder() //食物属性
                                    .nutrition(4)   //饱食度
                                    .saturationModifier(0.75F)  //营养价值
                                    .fast() //快速食用
                                    .alwaysEdible() //永久能吃，无视已饱
                                    //.effect() //添加效果
                                    .build()  //食物属性结束
                            )
                            .stacksTo(64)  //堆叠数
                    , Items.NETHER_BRICK, //虚拟物品
                    ChocolateModel //模型数据
            ){
                @Override //获取模型数据
                public int getPolymerCustomModelData(ItemStack itemStack, ServerPlayer player) {
                    return 3; //返回模型数据id
                }
            });
    /*           巧克力结束          */

    //
    //西湖醋鱼
    //
}
