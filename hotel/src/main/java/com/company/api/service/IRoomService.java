package com.company.api.service;

import com.company.model.*;

import java.util.List;

public interface IRoomService {

    Room addRoom(Integer number, Integer capacity, Integer roomStatus, Double priceRoom, Integer comfort);

    void checkIn(Long guestId, Long roomId);

    void checkOut(Long guestId, Long roomId);

    Room getById(Long id);

    Room getByRoomNumber(Integer roomNumber);

    List<Room> getAvailableRooms(String col);

    void changeStatusByRoomNumber(Integer roomNum, RoomStatus roomStatus);

    void changePrice(Integer roomNum, Double newPrice);

    List<Room> getAvailableRoomsByDate(String byDate);

    Double getBill(Long idGuest);

    List<Room> getAll(String col);

    RoomStatus getRoomStatusByNumber(Integer num);

    RoomComfort getRoomComfortByNumber(Integer num);

    Integer getNumOfAvailableRooms();

    void save(Room room);
}
