package com.company.dao;

import com.company.api.dao.IRoomDao;
import com.company.model.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());

    @Override
    public Room update(Room entity) {
        Room room = getById(entity.getId());
            LOGGER.log(Level.INFO, String.format("Update room"));
            room.setNumber(entity.getNumber());
            room.setPriceRoom(entity.getPriceRoom());
            room.setRoomStatus(entity.getRoomStatus());
            room.setCapacity(entity.getCapacity());
            room.setComfort(entity.getComfort());
            return room;
    }
}
