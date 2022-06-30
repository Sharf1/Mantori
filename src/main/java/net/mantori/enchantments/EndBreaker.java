package net.mantori.enchantments;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import ru.bclib.api.tag.CommonBlockTags;

public class EndBreaker extends Enchantment {
    protected EndBreaker(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean isTreasure() {
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

    public static float getDestroySpeedMultiplier(BlockState state, float baseSpeed, float level){
        if ((state.isIn(CommonBlockTags.END_STONES))) {
            float speed = baseSpeed * level * 3;
            return speed;
        }
        return baseSpeed;
    }
}
