package com.company.filter;

import com.company.model.Room;

import java.util.Comparator;

public class SortRoomByPrice implements Comparator<Room> {

    @Override
    public int compare(Room room1, Room room2) {
        return (int) (room1.getPriceRoom() - room2.getPriceRoom());
    }
}
