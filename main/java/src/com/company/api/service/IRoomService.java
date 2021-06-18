package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.RoomComfort;
import com.company.model.RoomStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IRoomService {

    Room addRoom(Integer number, Integer capacity, RoomStatus roomStatus, Double priceRoom, RoomComfort comfort);

    void checkIn(Guest guest, Room room);

    void checkOut(Guest guest, Room room);

    Room getByRoomNumber(Integer roomNumber);

    List<Room> getAllFreeRoom();

    void changeStatus(Integer roomNum, RoomStatus roomStatus);

    void changePrice(Integer roomNum, Double newPrice);

    List<Room> sortRoomByCapacity();

    List<Room> sortRoomByPrice();

    List<Room> sortRoomByComfort();

    List<Room> getFreeRoomSortByPrice();

    List<Room> getFreeRoomSortByCapacity();

    List<Room> getFreeRoomSortByComfort();

    Map<Integer, List<Guest>> getAllGuestsAndRoomsSortByName();

    int availableRooms();

    Map<Integer, List<Guest>> getAllGuestsAndRoomsSortByDeparture();

    List<Room> getFreeRoomsByDate(LocalDate onDate);

    double getBill(Guest guest);

    Map<String, List<LocalDate>> lastGuestsOfRoom(int roomNumber);

}
