package net.mantori.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mantori.Mantori;
import net.mantori.block.custom.BeetleberryBushBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block BEETLEBERRY_BUSH = registerBlockWithoutBlockItem("beetleberry_bush",
            new BeetleberryBushBlock(FabricBlockSettings.copy(Blocks.SWEET_BERRY_BUSH).nonOpaque()));

    private static Block registerBlockWithoutBlockItem(String name, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(Mantori.MOD_ID, name), block);
}

    public static void registerModBlocks(){
        System.out.println("Registering ModBlocks for" + Mantori.MOD_ID);
    }
}
