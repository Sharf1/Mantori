package net.mantori.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.mantori.Mantori;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item CHITIN_SHELL = registerItem("chitin",
            new Item(new FabricItemSettings().group(ModItemGroup.MANTORI)));

    public static final Item CHITIN_HELMET = registerItem("chitin_helmet",
            new ArmorItem(ModArmorMaterial.CHITIN, EquipmentSlot.HEAD, new FabricItemSettings().group((ModItemGroup.MANTORI))));
    public static final Item CHITIN_CHESTPLATE = registerItem("chitin_chestplate",
            new ArmorItem(ModArmorMaterial.CHITIN, EquipmentSlot.CHEST, new FabricItemSettings().group((ModItemGroup.MANTORI))));
    public static final Item CHITIN_LEGGINGS = registerItem("chitin_leggings",
            new ArmorItem(ModArmorMaterial.CHITIN, EquipmentSlot.LEGS, new FabricItemSettings().group((ModItemGroup.MANTORI))));
    public static final Item CHITIN_BOOTS = registerItem("chitin_boots",
            new ArmorItem(ModArmorMaterial.CHITIN, EquipmentSlot.FEET, new FabricItemSettings().group((ModItemGroup.MANTORI))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Mantori.MOD_ID, name), item);
    }
    public static void registerModItems() {
        Mantori.LOGGER.info("Registering mod items for " + Mantori.MOD_ID);
    }
}
