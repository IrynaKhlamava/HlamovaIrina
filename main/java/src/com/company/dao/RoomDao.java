package com.company.dao;

import com.company.api.dao.IRoomDao;
import com.company.model.*;


import java.util.*;

public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    @Override
    public Room update(Room entity) {
       Room room = getById(entity.getId());
       room.setNumber(entity.getNumber());
       room.setPriceRoom(entity.getPriceRoom());
       room.setRoomStatus(entity.getRoomStatus());
       room.setCapacity(entity.getCapacity());
       room.setComfort(entity.getComfort());
       return room;
    }
}
