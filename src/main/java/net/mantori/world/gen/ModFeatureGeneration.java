package net.mantori.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.mantori.world.feature.ModPlacedFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;

public class ModFeatureGeneration {
    public static void generateBushes() {
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.THEEND),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.BEETLEBERRY_BUSH_PLACED.getKey().get());
    }
}
