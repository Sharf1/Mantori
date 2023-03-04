package net.mantori.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.mantori.Mantori;
import net.mantori.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static ItemGroup MANTORI;

    public static void registerItemGroup() {
        MANTORI = FabricItemGroup.builder(new Identifier(Mantori.MOD_ID, "mantori"))
                .displayName(Text.literal("Aphid Additions"))
                .icon(() -> new ItemStack(ModItems.CHITIN_SHELL)).build();
    }

}
