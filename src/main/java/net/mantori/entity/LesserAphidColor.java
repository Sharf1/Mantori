package net.mantori.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum LesserAphidColor {
    BLACK(0),
    BLUEGREEN(1),
    CYAN(2),
    GRAY(3),
    GREEN(4),
    NATURAL(5),
    ORANGE(6),
    BROWN(7);

    private static final LesserAphidColor[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(LesserAphidColor::getIndex)).toArray(LesserAphidColor[]::new);
    private final int index;

    LesserAphidColor(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static LesserAphidColor byIndex(int index) {
        return VALUES[index % VALUES.length];
    }
}