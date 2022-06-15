package net.mantori.mixin;

import net.mantori.enchantments.EndBreaker;
import net.mantori.enchantments.ModEnchantments;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MiningToolItem.class)
public class DiggerItemMixin {
    @Inject(method="getMiningSpeedMultiplier", at=@At(value="RETURN"), cancellable = true)
    void getDestroySpeed(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir){
        final int endLevel = EnchantmentHelper.getLevel(ModEnchantments.END_BREAKER, stack);
        if (endLevel>0) {
            cir.setReturnValue(EndBreaker.getDestroySpeedMultiplier(state, cir.getReturnValue(), endLevel));
        }
    }
}