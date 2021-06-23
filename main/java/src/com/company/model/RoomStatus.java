package com.company.model;

public enum RoomStatus {
    CLEAN(1),
    OUT_OF_ORDER(2), // номер, находящийся в вынужденном простое, неисправный, в плохом состоянии (ремонт);
    DIRTY(3),
    OCCUPY(4), // занятый, жилой
    DEPARTURE(5); //выездной

    private int value;

    RoomStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
