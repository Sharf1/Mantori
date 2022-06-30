package net.mantori.world.feature;

import net.mantori.Mantori;
import net.mantori.block.ModBlocks;
import net.mantori.block.custom.BeetleberryBushBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> BEETLEBERRY_BUSH =
            ConfiguredFeatures.register("beetleberry_bush", Feature.RANDOM_PATCH,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.BEETLEBERRY_BUSH.getDefaultState().with(BeetleberryBushBlock.AGE, 3))), List.of(Blocks.END_STONE), 8));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> END_GRASS =
            ConfiguredFeatures.register("end_grass", Feature.RANDOM_PATCH,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.END_GRASS)), List.of(Blocks.END_STONE), 32));


    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + Mantori.MOD_ID);
    }
}
