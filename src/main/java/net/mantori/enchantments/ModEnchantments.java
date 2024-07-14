package net.mantori.enchantments;

import net.mantori.Mantori;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    public static Enchantment CURSED_THORNS = register("cursed_thorns",
            new CursedThornsEnchantment(Enchantment.Rarity.VERY_RARE));
    public static Enchantment END_BREAKER = register("end_breaker",
            new EndBreaker(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static Enchantment SHULKERS_BLESSING = register("shulkers_blessing",
            new ShulkersBlessing(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(Mantori.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        System.out.println("Registering mod enchantments for " + Mantori.MOD_ID);
    }
}
