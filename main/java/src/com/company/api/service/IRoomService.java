package com.company.api.service;

import com.company.model.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface IRoomService {

    Room addRoom(Integer number, Integer capacity, RoomStatus roomStatus, Double priceRoom, RoomComfort comfort);

    void checkIn(Guest guest, Room room);

    void checkOut(Guest guest, Room room);

    Room getByRoomNumber(Integer roomNumber);

    List<Room> getAllFreeRoom();

    void changeStatusByRoomNumber(Integer roomNum, RoomStatus roomStatus);

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

    List<LastGuestsInfo> lastGuestsOfRoom(int roomNumber);

    Map<Integer, List<Guest>> getAllGuestsAndRoomsSortedByThisComparator(Comparator comparator);

    List<Room> getAll();

    RoomStatus getRoomStatusByNumber(Integer num);

    RoomComfort getRoomComfortByNumber(Integer num);

    void saveAll(List<Room> rooms);

}
