package net.mantori.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum GreaterAphidColor {
    GREATER_NATURAL(1);

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
