package net.mantori.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> BEETLEBERRY_BUSH_PLACED = PlacedFeatures.register("beetleberry_bush_placed",
            ModConfiguredFeatures.BEETLEBERRY_BUSH, RarityFilterPlacementModifier.of(384), SquarePlacementModifier.of(),
            PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
}
