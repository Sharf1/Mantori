package net.mantori.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum GreaterAphidColor {
    GREATER_NATURAL(1),
    GREATER_BLUE(2),
    GREATER_GREEN(3),
    GREATER_ORANGE(4),
    GREATER_PEACH(5),
    GREATER_RED(6),
    GREATER_YELLOW(7);
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
