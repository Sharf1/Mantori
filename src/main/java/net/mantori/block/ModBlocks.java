package net.mantori.block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mantori.Mantori;
import net.mantori.block.custom.BeetleberryBushBlock;
import net.mantori.block.custom.DragonsBreathBlock;
import net.mantori.block.custom.EndGrass;
import net.mantori.block.custom.JellyBlock;
import net.mantori.item.ModItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block BEETLEBERRY_BUSH = registerBlockWithoutBlockItem("beetleberry_bush",
            new BeetleberryBushBlock(FabricBlockSettings.copy(Blocks.SWEET_BERRY_BUSH).nonOpaque()));
    public static final Block DRAGONS_BREATH_BUSH = registerBlockWithoutBlockItem("dragons_breath_bush",
            new DragonsBreathBlock(FabricBlockSettings.copy(Blocks.BEETROOTS).nonOpaque()));

    public static final Block JELLY = registerBlock("jelly",
            new JellyBlock(FabricBlockSettings.copy(Blocks.SLIME_BLOCK).nonOpaque()), ModItemGroup.MANTORI);
    public static final Block END_GRASS = registerBlock("end_grass",
            new EndGrass(FabricBlockSettings.copy(Blocks.GRASS).nonOpaque()), ModItemGroup.MANTORI);

    private static Block registerBlockWithoutBlockItem(String name, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(Mantori.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registries.BLOCK, new Identifier(Mantori.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        Item item = Registry.register(Registries.ITEM, new Identifier(Mantori.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(item));
        return item;
    }


    public static void registerModBlocks(){
        System.out.println("Registering ModBlocks for " + Mantori.MOD_ID);
    }
}
