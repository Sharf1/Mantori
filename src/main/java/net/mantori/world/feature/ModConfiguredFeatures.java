package net.mantori.world.feature;

import net.mantori.Mantori;
import net.mantori.block.ModBlocks;
import net.mantori.block.custom.BeetleberryBushBlock;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?,?>> BEETLEBERRY_BUSH_KEY = registerKey("beetlberry_bush");
    public static final RegistryKey<ConfiguredFeature<?,?>> END_GRASS_KEY = registerKey("end_grass");

    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> context) {

        register(context, BEETLEBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.BEETLEBERRY_BUSH.getDefaultState().with(BeetleberryBushBlock.AGE, 3))), List.of(Blocks.END_STONE), 8));
        register(context, END_GRASS_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.END_GRASS)), List.of(Blocks.END_STONE), 32));
    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Mantori.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
