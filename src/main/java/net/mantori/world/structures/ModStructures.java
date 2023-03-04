package net.mantori.world.structures;

import net.mantori.Mantori;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;

public class ModStructures {

    public static StructureType<EndStructure> END_STRUCTURE;

    public static void registerStructureFeatures() {
        END_STRUCTURE = Registry.register(Registries.STRUCTURE_TYPE, new Identifier(Mantori.MOD_ID, "end_structure"), () -> EndStructure.CODEC);
    }
}
