package net.mantori.enchantments;

import net.mantori.Mantori;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {

    public static Enchantment CURSED_THORNS = register("cursed_thorns",
            new CursedThornsEnchantment(Enchantment.Rarity.VERY_RARE));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(Mantori.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        System.out.println("Registering mod enchantments for " + Mantori.MOD_ID);
    }
}
