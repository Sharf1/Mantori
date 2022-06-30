package net.mantori.item.custom;

import net.mantori.enchantments.ModEnchantments;
import net.mantori.interfaces.InitialStackStateProvider;
import net.mantori.item.ModToolMaterial;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

import java.util.HashMap;
import java.util.Map;

public class ModPickaxeItem extends PickaxeItem implements InitialStackStateProvider {
    public ModPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void initializeState(ItemStack stack){
        Map<Enchantment, Integer> defaultEnchants = new HashMap<>();
        int thornLevel = 0;
        int shulkBlessing = 0;
        if (this.getMaterial()== ModToolMaterial.CHITIN) thornLevel = 3;

        if (thornLevel>0) {
            defaultEnchants.put(ModEnchantments.END_BREAKER, thornLevel);
            EnchantmentHelper.set(defaultEnchants, stack);
        }

        if (this.getMaterial()== ModToolMaterial.CHITIN) shulkBlessing = 1;
        if (shulkBlessing>0) {
            defaultEnchants.put(ModEnchantments.SHULKERS_BLESSING, shulkBlessing);
            EnchantmentHelper.set(defaultEnchants, stack);
        }
    }
}
