package net.mantori.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.mantori.entity.ModEntities;
import net.mantori.entity.custom.GreaterAphidEntity;
import net.mantori.entity.custom.LesserAphidEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawn {
    public static void addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), SpawnGroup.CREATURE,
                ModEntities.LESSER_APHID, 80, 4, 10);
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), SpawnGroup.CREATURE,
                ModEntities.GREATER_APHID, 70, 2, 4);

        SpawnRestriction.register(ModEntities.LESSER_APHID, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LesserAphidEntity::canSpawn);
        SpawnRestriction.register(ModEntities.GREATER_APHID, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GreaterAphidEntity::canSpawn);
    }


}
