package net.mantori.data;

import java.util.List;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.mantori.block.ModBlocks;
import net.mantori.block.custom.BeetleberryBushBlock;
import net.mantori.world.feature.ModConfiguredFeatures;
import net.mantori.world.feature.ModPlacedFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModWorldGenerator::new);

    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
//        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModPlacedFeatures::bootstrap);
    	registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, context -> {
    		ModConfiguredFeatures.register(context, ModConfiguredFeatures.BEETLEBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.BEETLEBERRY_BUSH.getDefaultState().with(BeetleberryBushBlock.AGE, 3))), List.of(Blocks.END_STONE), 32));
    		ModConfiguredFeatures.register(context, ModConfiguredFeatures.END_GRASS_KEY, Feature.RANDOM_PATCH,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.END_GRASS)), List.of(Blocks.END_STONE), 32));
    	});
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
    }
}
