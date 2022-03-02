package net.mantori.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum GreaterAphidColor {
    BASIC(0),
    LESSER_BLACK(1),
    LESSER_BLUEGREEN(2),
    LESSER_CYAN(3),
    LESSER_GRAY(4),
    LESSER_GREEN(5),
    LESSER_NATURAL(6),
    LESSER_ORANGE(7),
    LESSER_BROWN(8),
    LESSER_CHILD(9),
    GREATER_CHILD(10);

    private static final GreaterAphidColor[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(GreaterAphidColor::getIndex)).toArray(GreaterAphidColor[]::new);
    private final int index;

    GreaterAphidColor(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static GreaterAphidColor byIndex(int index) {
        return VALUES[index % VALUES.length];
    }
}
