package net.mantori.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.mantori.Mantori;
import net.mantori.item.custom.ModAxeItem;
import net.mantori.item.custom.ModHoeItem;
import net.mantori.item.custom.ModPickaxeItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item CHITIN_SHELL = registerItem("chitin",
            new Item(new FabricItemSettings().group(ModItemGroup.MANTORI)));
    public static final Item CHITIN_SPIKE = registerItem("chitin_spike",
            new Item(new FabricItemSettings().group(ModItemGroup.MANTORI)));

    public static final Item CHITIN_SWORD = registerItem("chitin_sword",
            new SwordItem(ModToolMaterial.CHITIN, 4, -2.4f,
                    new FabricItemSettings().group(ModItemGroup.MANTORI)));
    public static final Item CHITIN_AXE = registerItem("chitin_axe",
            new ModAxeItem(ModToolMaterial.CHITIN, 6, -2.9f,
                    new FabricItemSettings().group(ModItemGroup.MANTORI)));
    public static final Item CHITIN_SHOVEL = registerItem("chitin_shovel",
            new ShovelItem(ModToolMaterial.CHITIN, 1, -3f,
                    new FabricItemSettings().group(ModItemGroup.MANTORI)));
    public static final Item CHITIN_PICKAXE = registerItem("chitin_pickaxe",
            new ModPickaxeItem(ModToolMaterial.CHITIN, 0, -2.8f,
                    new FabricItemSettings().group(ModItemGroup.MANTORI)));
    public static final Item CHITIN_HOE = registerItem("chitin_hoe",
            new ModHoeItem(ModToolMaterial.CHITIN, -6, 2f,
                    new FabricItemSettings().group(ModItemGroup.MANTORI)));

    public static final Item CHITIN_HELMET = registerItem("chitin_helmet",
            new ArmorItem(ModArmorMaterial.CHITIN, EquipmentSlot.HEAD,
                    new FabricItemSettings().group((ModItemGroup.MANTORI))));
    public static final Item CHITIN_CHESTPLATE = registerItem("chitin_chestplate",
            new ArmorItem(ModArmorMaterial.CHITIN, EquipmentSlot.CHEST,
                    new FabricItemSettings().group((ModItemGroup.MANTORI))));
    public static final Item CHITIN_LEGGINGS = registerItem("chitin_leggings",
            new ArmorItem(ModArmorMaterial.CHITIN, EquipmentSlot.LEGS,
                    new FabricItemSettings().group((ModItemGroup.MANTORI))));
    public static final Item CHITIN_BOOTS = registerItem("chitin_boots",
            new ArmorItem(ModArmorMaterial.CHITIN, EquipmentSlot.FEET,
                    new FabricItemSettings().group((ModItemGroup.MANTORI))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Mantori.MOD_ID, name), item);
    }
    public static void registerModItems() {
        Mantori.LOGGER.info("Registering mod items for " + Mantori.MOD_ID);
    }
}
