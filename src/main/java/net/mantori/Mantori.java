package net.mantori;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.mantori.entity.ModEntityTypes;
import net.mantori.item.ModItems;
import net.mantori.sounds.ModSounds;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mantori implements ModInitializer {

    public static final String MOD_ID = "mantori";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModSounds.registerSounds();
        ModEntityTypes.registerModEntities();
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.THEEND), SpawnGroup.CREATURE, ModEntityTypes.GREATER_APHID_ENTITY_TYPE, 80, 2, 4);
    }
}
