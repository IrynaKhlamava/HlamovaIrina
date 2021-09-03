package com.company.api.service;

import com.company.model.*;

import java.util.List;

public interface IRoomService {

    Room addRoom(Integer number, Integer capacity, Integer roomStatus, Double priceRoom, Integer comfort);

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

    List<Room> getFreeRoomsByDate(String byDate);

    double getBill(Guest guest);

    List<Room> getAll();

    RoomStatus getRoomStatusByNumber(Integer num);

    RoomComfort getRoomComfortByNumber(Integer num);

}
