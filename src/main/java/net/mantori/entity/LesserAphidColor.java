package net.mantori.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum LesserAphidColor {
    BASIC(0),
    BLACK(1),
    BLUEGREEN(2),
    CYAN(3),
    GRAY(4),
    GREEN(5),
    NATURAL(6),
    ORANGE(7),
    BROWN(8),
    CHILD(9);

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