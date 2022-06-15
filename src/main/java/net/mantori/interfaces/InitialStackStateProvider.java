package net.mantori.interfaces;

import net.minecraft.item.ItemStack;

public interface InitialStackStateProvider {
    public void initializeState(ItemStack stack);
}
