package net.mantori.world.gen;

public class ModWorldGen {
    public static void generateModWorldGen() {

        ModFeatureGeneration.generateBushes();
        ModFeatureGeneration.generateGrass();
        ModEntitySpawn.addEntitySpawn();
    }
}
