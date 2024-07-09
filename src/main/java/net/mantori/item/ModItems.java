package net.mantori.item;

import static net.minecraft.item.Items.GLASS_BOTTLE;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.mantori.Mantori;
import net.mantori.block.ModBlocks;
import net.mantori.entity.ModEntities;
import net.mantori.item.custom.JarItem;
import net.mantori.item.custom.ModArmorItem;
import net.mantori.item.custom.ModAxeItem;
import net.mantori.item.custom.ModHoeItem;
import net.mantori.item.custom.ModPickaxeItem;
import net.mantori.item.custom.ModShovelItem;
import net.mantori.item.custom.ModSwordItem;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CHITIN_SHELL = registerItem("chitin",
            new Item(new FabricItemSettings()));
    public static final Item CHITIN_SPIKE = registerItem("chitin_spike",
            new Item(new FabricItemSettings()));

    public static final Item APHID_MEAT = registerItem("aphid_meat",
            new Item(new FabricItemSettings().food(ModFoodComponents.APHID_MEAT)));
    public static final Item COOKED_APHID_MEAT = registerItem("cooked_aphid_meat",
            new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_APHID_MEAT)));

    public static final Item ELYTRA_SHARD = registerItem("elytra_shard",
            new Item(new FabricItemSettings().food(ModFoodComponents.ELYTRA_SHARD)));
    public static final Item END_WING = registerItem("end_wing",
            new Item(new FabricItemSettings().food(ModFoodComponents.END_WING)));
    public static final Item HONEYDEW_BOTTLE = registerItem("honeydew_bottle",
            new HoneyBottleItem(new FabricItemSettings().maxCount(16).food(ModFoodComponents.HONEYDEW_BOTTLE).recipeRemainder(GLASS_BOTTLE)));
    public static final Item BEETLEBERRY_JAM = registerItem("beetleberry_jam",
            new JarItem(new FabricItemSettings().maxCount(16).food(ModFoodComponents.BEETLEBERRY_JAM).recipeRemainder(GLASS_BOTTLE)));
    public static final Item HOT_SAUCE = registerItem("hot_sauce",
            new JarItem(new FabricItemSettings().maxCount(16).food(ModFoodComponents.HOT_SAUCE).recipeRemainder(GLASS_BOTTLE)));
    public static final Item HONEYGLAZED_APHID_MEAT = registerItem("honeyglazed_aphid_meat",
            new Item(new FabricItemSettings().food(ModFoodComponents.HONEYGLAZED_APHID_MEAT)));
    public static final Item GLAZED_WING = registerItem("glazed_wing",
            new Item(new FabricItemSettings().food(ModFoodComponents.GLAZED_WING)));
    public static final Item HOT_WING = registerItem("hot_wing",
            new Item(new FabricItemSettings().food(ModFoodComponents.HOT_WING)));
    public static final Item BEETLEBERRY = registerItem("beetleberry",
            new AliasedBlockItem(ModBlocks.BEETLEBERRY_BUSH, new FabricItemSettings().food(ModFoodComponents.BEETLEBERRY)));
    public static final Item DRAGONS_BREATH = registerItem("dragons_breath",
            new Item(new FabricItemSettings().food(ModFoodComponents.DRAGONS_BREATH)));
    public static final Item DRAGONS_BREATH_SEED = registerItem("dragons_breath_seed",
            new AliasedBlockItem(ModBlocks.DRAGONS_BREATH_BUSH, new FabricItemSettings()));

    public static final Item CHITIN_SWORD = registerItem("chitin_sword",
            new ModSwordItem(ModToolMaterial.CHITIN, 3, -2.4f,
                    new FabricItemSettings()));
    public static final Item CHITIN_AXE = registerItem("chitin_axe",
            new ModAxeItem(ModToolMaterial.CHITIN, 5, -2.9f,
                    new FabricItemSettings()));
    public static final Item CHITIN_SHOVEL = registerItem("chitin_shovel",
            new ModShovelItem(ModToolMaterial.CHITIN, 1.5f, -3f,
                    new FabricItemSettings()));
    public static final Item CHITIN_PICKAXE = registerItem("chitin_pickaxe",
            new ModPickaxeItem(ModToolMaterial.CHITIN, 1, -2.8f,
                    new FabricItemSettings()));
    public static final Item CHITIN_HOE = registerItem("chitin_hoe",
            new ModHoeItem(ModToolMaterial.CHITIN, -4, 2f,
                    new FabricItemSettings()));

    public static final Item CHITIN_HELMET = registerItem("chitin_helmet",
            new ModArmorItem(ModArmorMaterial.CHITIN, Type.HELMET,
                    new FabricItemSettings()));
    public static final Item CHITIN_CHESTPLATE = registerItem("chitin_chestplate",
            new ModArmorItem(ModArmorMaterial.CHITIN, Type.CHESTPLATE,
                    new FabricItemSettings()));
    public static final Item CHITIN_LEGGINGS = registerItem("chitin_leggings",
            new ModArmorItem(ModArmorMaterial.CHITIN, Type.LEGGINGS,
                    new FabricItemSettings()));
    public static final Item CHITIN_BOOTS = registerItem("chitin_boots",
            new ModArmorItem(ModArmorMaterial.CHITIN, Type.BOOTS,
                    new FabricItemSettings()));

    public static final Item GREATER_APHID_SPAWN_EGG = registerItem("greater_aphid_spawn_egg", new SpawnEggItem(ModEntities.GREATER_APHID, 3947078, 6900102,
            new FabricItemSettings()));

    public static final Item LESSER_APHID_SPAWN_EGG = registerItem("lesser_aphid_spawn_egg", new SpawnEggItem(ModEntities.LESSER_APHID, 7690372, 6377329,
            new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Mantori.MOD_ID, name), item);
    }

    public static void addItemsToItemGroups() {
        addToItemGroup(ItemGroups.INGREDIENTS, CHITIN_SHELL);
        addToItemGroup(ItemGroups.INGREDIENTS, CHITIN_SPIKE);
        addToItemGroup(ItemGroups.SPAWN_EGGS, GREATER_APHID_SPAWN_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, LESSER_APHID_SPAWN_EGG);
        addToItemGroup(ItemGroups.COMBAT, CHITIN_SWORD);
        addToItemGroup(ItemGroups.COMBAT, CHITIN_AXE);
        addToItemGroup(ItemGroups.COMBAT, CHITIN_HELMET);
        addToItemGroup(ItemGroups.COMBAT, CHITIN_CHESTPLATE);
        addToItemGroup(ItemGroups.COMBAT, CHITIN_LEGGINGS);
        addToItemGroup(ItemGroups.COMBAT, CHITIN_BOOTS);
        addToItemGroup(ItemGroups.TOOLS, CHITIN_SHOVEL);
        addToItemGroup(ItemGroups.TOOLS, CHITIN_PICKAXE);
        addToItemGroup(ItemGroups.TOOLS, CHITIN_AXE);
        addToItemGroup(ItemGroups.TOOLS, CHITIN_HOE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, APHID_MEAT);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, COOKED_APHID_MEAT);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, HONEYGLAZED_APHID_MEAT);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ELYTRA_SHARD);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, END_WING);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, GLAZED_WING);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, HOT_WING);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, HONEYDEW_BOTTLE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, BEETLEBERRY_JAM);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, HOT_SAUCE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, BEETLEBERRY);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, DRAGONS_BREATH);
        addToItemGroup(ItemGroups.NATURAL, DRAGONS_BREATH_SEED);
        addToItemGroup(ItemGroups.NATURAL, DRAGONS_BREATH);
        addToItemGroup(ItemGroups.NATURAL, BEETLEBERRY);


        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_SHELL);
        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_SPIKE);
        addToItemGroup(ModItemGroup.MANTORI_KEY, GREATER_APHID_SPAWN_EGG);
        addToItemGroup(ModItemGroup.MANTORI_KEY, LESSER_APHID_SPAWN_EGG);
        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_SWORD);
        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_AXE);
        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_HELMET);
        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_CHESTPLATE);
        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_LEGGINGS);
        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_BOOTS);
        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_SHOVEL);
        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_PICKAXE);
        addToItemGroup(ModItemGroup.MANTORI_KEY, CHITIN_HOE);
        addToItemGroup(ModItemGroup.MANTORI_KEY, APHID_MEAT);
        addToItemGroup(ModItemGroup.MANTORI_KEY, COOKED_APHID_MEAT);
        addToItemGroup(ModItemGroup.MANTORI_KEY, HONEYGLAZED_APHID_MEAT);
        addToItemGroup(ModItemGroup.MANTORI_KEY, ELYTRA_SHARD);
        addToItemGroup(ModItemGroup.MANTORI_KEY, END_WING);
        addToItemGroup(ModItemGroup.MANTORI_KEY, GLAZED_WING);
        addToItemGroup(ModItemGroup.MANTORI_KEY, HOT_WING);
        addToItemGroup(ModItemGroup.MANTORI_KEY, HONEYDEW_BOTTLE);
        addToItemGroup(ModItemGroup.MANTORI_KEY, BEETLEBERRY_JAM);
        addToItemGroup(ModItemGroup.MANTORI_KEY, HOT_SAUCE);
        addToItemGroup(ModItemGroup.MANTORI_KEY, DRAGONS_BREATH_SEED);
        addToItemGroup(ModItemGroup.MANTORI_KEY, DRAGONS_BREATH);
        addToItemGroup(ModItemGroup.MANTORI_KEY, BEETLEBERRY);

    }

    public static void addToItemGroup(RegistryKey<ItemGroup> group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }


    public static void registerModItems() {

        Mantori.LOGGER.info("Registering mod items for " + Mantori.MOD_ID);
        addItemsToItemGroups();
    }
}
