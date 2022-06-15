package net.mantori.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.mantori.entity.ModEntities;
import net.mantori.entity.custom.GreaterAphidEntity;
import net.mantori.entity.custom.LesserAphidEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawn {
    public static void addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.THE_END), SpawnGroup.CREATURE,
                ModEntities.LESSER_APHID, 80, 2, 6);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.THE_END), SpawnGroup.CREATURE,
                ModEntities.GREATER_APHID, 70, 2, 4);

        SpawnRestrictionAccessor.callRegister(ModEntities.LESSER_APHID, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LesserAphidEntity::canSpawnIgnoreLightLevel);
        SpawnRestrictionAccessor.callRegister(ModEntities.GREATER_APHID, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GreaterAphidEntity::canSpawnIgnoreLightLevel);
    }


}
