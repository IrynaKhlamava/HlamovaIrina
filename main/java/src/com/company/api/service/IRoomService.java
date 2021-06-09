package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Room;

import java.util.Date;
import java.util.List;

public interface IRoomService {

    void checkIn(Guest guest, Room room);

    void checkOut(Guest guest, Room room);

    Room getByRoomNumber(Integer roomNumber);

    List<Room> sortRoomByCapacity();

    List<Room> sortRoomByPrice();

    List<Room> sortRoomByComfort();

    List<Room> sortFreeRoomByPrice(List<Room> freeRoom);

    List<Room> sortFreeRoomByCapacity(List<Room> freeRoom);

    List<Room> sortFreeRoomByComfort(List<Room> freeRoom);

    void getAllGuestsAndRooms();

    int availableRooms();

    void getAllGuestsAndRoomsSortByDeparture();

    List<Room> getFreeRoomsByDate(Date onDate);

    double getBill(Guest guest);

    void lastGuestsOfRoom(int roomNumber);


}
