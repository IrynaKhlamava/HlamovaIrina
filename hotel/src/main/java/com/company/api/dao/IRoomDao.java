package com.company.api.dao;

import com.company.model.Room;
import com.company.model.RoomStatus;

import java.time.LocalDate;
import java.util.List;

public interface IRoomDao extends GenericDao<Room> {

    Room getRoomByNumber(Integer roomNum);

    void changeRoomStatus(Integer roomNum, RoomStatus roomStatus);

    void changeRoomPrice(Integer roomNum, Double newPrice);

    List<Room> getFreeRooms();

    List<Room> getFreeRoomsSort(String col);

    List<Room> getFreeRoomsByDate(LocalDate date);

    double getRoomPrice(Long roomId);

}
