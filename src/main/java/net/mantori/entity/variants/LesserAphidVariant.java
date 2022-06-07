package net.mantori.entity.variants;

import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;

import java.util.Arrays;
import java.util.Comparator;

public enum LesserAphidVariant {
    NATURAL(0, "natural", true),
    BLUE(1, "blue", false),
    GREEN(2, "green", false),
    ORANGE(3, "orange", false),
    BLACK(4, "black", false),
    BROWN(5, "brown", false),
    CYAN(6, "cyan", false),
    GRAY(7, "gray", false);

    public static final LesserAphidVariant[] VARIANTS = Arrays.stream(LesserAphidVariant.values()).sorted(Comparator.comparingInt(LesserAphidVariant::getId)).toArray(LesserAphidVariant[]::new);

    private final int id;

    private final String name;
    private final boolean natural;
    LesserAphidVariant(int id, String name, boolean natural) {
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

    public static LesserAphidVariant getRandomNatural(Random random) {
        return LesserAphidVariant.getRandom(random, true);
    }

    public static LesserAphidVariant getRandomUnnatural(Random random) {
        return LesserAphidVariant.getRandom(random, false);
    }

    private static LesserAphidVariant getRandom(Random random, boolean natural) {
        LesserAphidVariant[] variants = Arrays.stream(VARIANTS).filter(variant -> variant.natural == natural).toArray(LesserAphidVariant[]::new);
        return Util.getRandom(variants, random);
    }

}
