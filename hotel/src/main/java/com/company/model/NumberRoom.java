package com.company.model;

public class NumberRoom {

    public static int numRoom = 1;

    public static int getNewRoomNumber() {
        return numRoom++;
    }
}
