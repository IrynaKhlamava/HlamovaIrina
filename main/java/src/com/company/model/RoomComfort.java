package com.company.model;

public enum RoomComfort {
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5);

    private int value;

    RoomComfort(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

