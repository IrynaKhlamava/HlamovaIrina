package com.company.api.dao;

import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.RoomComfort;
import com.company.model.RoomStatus;

import java.util.List;

public interface IRoomDao extends GenericDao<Room> {

    Room update(Room entity);

}
