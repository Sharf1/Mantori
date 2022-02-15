package net.mantori.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum AphidColor {
    BASIC(0);

    private static final AphidColor[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(AphidColor::getIndex)).toArray(AphidColor[]::new);
    private final int index;

    AphidColor(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static AphidColor byIndex(int index) {
        return VALUES[index % VALUES.length];
    }
}
