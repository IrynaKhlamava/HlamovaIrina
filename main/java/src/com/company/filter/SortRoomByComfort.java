package com.company.filter;

import com.company.model.Room;

import java.util.Comparator;

public class SortRoomByComfort implements Comparator<Room> {

    @Override
    public int compare(Room room1, Room room2) {
        return room1.getComfort().getValue() - room2.getComfort().getValue();
    }
}
