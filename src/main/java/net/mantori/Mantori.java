package net.mantori;

import net.fabricmc.api.ModInitializer;
import net.mantori.block.ModBlocks;
import net.mantori.enchantments.ModEnchantments;
import net.mantori.item.ModItems;
import net.mantori.sounds.ModSounds;
import net.mantori.util.ModRegistries;
import net.mantori.world.feature.ModConfiguredFeatures;
import net.mantori.world.gen.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mantori implements ModInitializer {

    public static final String MOD_ID = "mantori";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModConfiguredFeatures.registerConfiguredFeatures();

        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModSounds.registerSounds();

        ModEnchantments.registerModEnchantments();

        ModRegistries.registerModStuff();

        ModWorldGen.generateModWorldGen();
    }
}
