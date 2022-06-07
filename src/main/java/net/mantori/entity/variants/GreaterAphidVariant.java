package net.mantori.entity.variants;

import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;

import java.util.Arrays;
import java.util.Comparator;

public enum GreaterAphidVariant {
    NATURAL(0, "natural", true),
    BLUE(1, "blue", false),
    GREEN(2, "green", false),
    ORANGE(3, "orange", false),
    PEACH(4, "peach", false),
    YELLOW(5, "yellow", false),
    RED(6, "red", false);

    public static final GreaterAphidVariant[] VARIANTS = Arrays.stream(GreaterAphidVariant.values()).sorted(Comparator.comparingInt(GreaterAphidVariant::getId)).toArray(GreaterAphidVariant[]::new);

    private final int id;

    private final String name;
    private final boolean natural;
    GreaterAphidVariant(int id, String name, boolean natural) {
        this.id = id;
        this.name = name;
        this.natural = natural;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public static GreaterAphidVariant getRandomNatural(Random random) {
        return GreaterAphidVariant.getRandom(random, true);
    }

    public static GreaterAphidVariant getRandomUnnatural(Random random) {
        return GreaterAphidVariant.getRandom(random, false);
    }

    private static GreaterAphidVariant getRandom(Random random, boolean natural) {
        GreaterAphidVariant[] variants = Arrays.stream(VARIANTS).filter(variant -> variant.natural == natural).toArray(GreaterAphidVariant[]::new);
        return Util.getRandom(variants, random);
    }

}
