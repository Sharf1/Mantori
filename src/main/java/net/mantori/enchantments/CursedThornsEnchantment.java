package net.mantori.enchantments;

import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class CursedThornsEnchantment extends ThornsEnchantment {
    public CursedThornsEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, slotTypes);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }
}
