package com.company.api.dao;

import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.RoomComfort;
import com.company.model.RoomStatus;

import java.util.List;

public interface IRoomDao extends GenericDao<Room> {

    Room addRoom(Integer number, Integer capacity, RoomStatus roomStatus, Double priceRoom, RoomComfort comfort, List<Guest> guests);

    List<Room> getAllFreeRoom();

    void changeStatus(Integer roomNum, RoomStatus roomStatus);

    void changePrice(Integer roomNum, Double price);


}
