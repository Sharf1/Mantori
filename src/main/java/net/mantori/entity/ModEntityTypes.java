package net.mantori.entity;

import net.mantori.Mantori;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntityTypes {

    public static final EntityType<GreaterAphid> GREATER_APHID_ENTITY_TYPE = registerEntityType("greater_aphid", EntityType.Builder.create(GreaterAphid::new, SpawnGroup.CREATURE).setDimensions(1.3964844F, 1.6F).maxTrackingRange(10));

    private static <T extends Entity>EntityType<T> registerEntityType(String name, EntityType.Builder<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Mantori.MOD_ID, name), entityType.build(name));
    }
    public static void registerModEntities() {
        Mantori.LOGGER.info("Registering mod entities for " + Mantori.MOD_ID);
    }
}
