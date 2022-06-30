package net.mantori.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.mantori.Mantori;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup MANTORI = FabricItemGroupBuilder.build(new Identifier(Mantori.MOD_ID, "mantori"),
            () -> new ItemStack(ModItems.CHITIN_SHELL));
}
