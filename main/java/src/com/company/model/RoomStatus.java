package com.company.model;

public enum RoomStatus {
    CLEAN,
    OUT_OF_ORDER, // номер, находящийся в вынужденном простое, неисправный, в плохом состоянии (ремонт);
    DIRTY,
    OCCUPY, // занятый, жилой
    DEPARTURE //выездной
}
