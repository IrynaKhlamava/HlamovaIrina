package com.company.dao;

import com.company.api.dao.IRoomDao;
import com.company.model.*;
import com.company.util.IdCreate;

import java.util.*;

public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    @Override
    public Room addRoom(Integer number, Integer capacity, RoomStatus roomStatus, Double priceRoom, RoomComfort comfort, List<Guest> guests) {
        Room room = new Room(number, capacity, roomStatus, priceRoom, comfort, guests);
        room.setId(IdCreate.createRoomId());
        room.setNumber(NumberRoom.getNewRoomNumber());
        save(room);
        return room;
    }

    @Override
    public List<Room> getAllFreeRoom() {
        List<Room> roomFree = new ArrayList<>();
        List<Room> rooms = getAll();
        for (Room room : rooms) {
            if (room.getGuests().size() == 0) {
                roomFree.add(room);
            }
        }
        return roomFree;

    }

    @Override
    public void changeStatus(Integer roomNum, RoomStatus roomStatus) {
        List<Room> rooms = getAll();
        for (Room room : rooms) {
            if (room.getNumber().equals(roomNum)) {
                room.setRoomStatus(roomStatus);
            }
        }
    }

    @Override
    public void changePrice(Integer roomNum, Double newPrice) {
        List<Room> rooms = getAll();
        for (Room room : rooms) {
            if (room.getNumber().equals(roomNum)) {
                room.setPriceRoom(newPrice);
            }
        }
    }


}
