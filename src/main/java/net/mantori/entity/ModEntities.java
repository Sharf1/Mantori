package net.mantori.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.mantori.Mantori;
import net.mantori.entity.custom.GreaterAphidEntity;
import net.mantori.entity.custom.LesserAphidEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static final EntityType<LesserAphidEntity> LESSER_APHID = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Mantori.MOD_ID, "lesser_aphid"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LesserAphidEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.25f))
                    .build()

    );

    public static final EntityType<GreaterAphidEntity> GREATER_APHID = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Mantori.MOD_ID, "greater_aphid"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GreaterAphidEntity::new)
                    .dimensions(EntityDimensions.fixed(2f, 2f))
                    .build()

    );
}
