package net.mantori.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.mantori.entity.ModEntities;
import net.mantori.entity.custom.GreaterAphidEntity;
import net.mantori.entity.custom.LesserAphidEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;

public class ModRegistries {
    public static void registerModStuff() {
        registerAttributes();
    }
    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.LESSER_APHID, LesserAphidEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.GREATER_APHID,  MobEntity.createMobAttributes()
                .add(EntityAttributes.HORSE_JUMP_STRENGTH)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 53.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.325f));
    }
}
