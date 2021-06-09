package com.company.filter;

import com.company.model.Room;

import java.util.Comparator;

public class SortRoomByCapacity implements Comparator<Room> {


    @Override
    public int compare(Room room1, Room room2) {
        return room1.getCapacity() - room2.getCapacity();
    }
}
