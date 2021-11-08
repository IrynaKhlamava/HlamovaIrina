package com.company.api.dao;

import com.company.model.Room;
import com.company.model.RoomFilter;
import com.company.model.RoomStatus;

import java.util.List;

public interface IRoomDao extends GenericDao<Room> {

    Room getRoomByNumber(Integer roomNum);

    void changeRoomStatus(Integer roomNum, RoomStatus roomStatus);

    void changeRoomPrice(Integer roomNum, Double newPrice);

    List<Room> getAllAvailableRooms(RoomFilter filter);

    Double getRoomPrice(Long roomId);

    Integer getNumOfAvailableRooms();
}
