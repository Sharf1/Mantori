package net.mantori.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.mantori.Mantori;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

	public static final RegistryKey<ItemGroup> MANTORI_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
			new Identifier(Mantori.MOD_ID, "mantori"));
	public static ItemGroup MANTORI;

	public static void registerItemGroup() {
		// MANTORI = FabricItemGroup.builder(new Identifier(Mantori.MOD_ID, "mantori"))
		MANTORI = FabricItemGroup.builder().displayName(Text.literal("Aphid Additions"))
				.icon(() -> new ItemStack(ModItems.CHITIN_SHELL)).build();
		Registry.register(Registries.ITEM_GROUP, MANTORI_KEY, MANTORI);
	}

}
