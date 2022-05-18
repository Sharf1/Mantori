package net.mantori.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.mantori.entity.ModEntities;
import net.mantori.entity.custom.LesserAphidEntity;

public class ModRegistries {
    public static void registerModStuff() {
        registerAttributes();
    }
    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.LESSER_APHID, LesserAphidEntity.setAttributes());
    }
}
