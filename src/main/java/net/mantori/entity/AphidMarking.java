package net.mantori.entity;


import java.util.Arrays;
import java.util.Comparator;

public enum AphidMarking {
    BASIC(0);

    private static final AphidMarking[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(AphidMarking::getIndex)).toArray(AphidMarking[]::new);
    private final int index;

    AphidMarking(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static AphidMarking byIndex(int index) {
        return VALUES[index % VALUES.length];
    }
}
