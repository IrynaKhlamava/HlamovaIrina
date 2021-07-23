package com.company.dao;

import com.company.api.dao.IRoomDao;
import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.model.*;
import com.company.service.SerializationService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());

    @Autowired
    private static RoomDao INSTANCE;

    public static RoomDao getRoomDao() {
        INSTANCE.init();
        return INSTANCE;
    }

    private void init() {
        SerializationService serializationService = new SerializationService();
        this.saveAll(serializationService.deserializeRoomFromFile());
    }

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
