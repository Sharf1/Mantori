package net.mantori;

import net.fabricmc.api.ModInitializer;
import net.mantori.entity.ModEntityTypes;
import net.mantori.item.ModItems;
import net.mantori.sounds.ModSounds;
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
    }
}
