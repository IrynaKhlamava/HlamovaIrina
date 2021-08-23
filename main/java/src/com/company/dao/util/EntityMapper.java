package com.company.dao.util;

import com.company.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EntityMapper {

    public static AEntity parseResultSet(ResultSet resultSet, String tableName) {
        try {
            switch (tableName) {
                case "GUESTS":
                    return createGuest(resultSet);
                case "ROOMS":
                    return createRooms(resultSet);
                case "SERVICES":
                    return createServices(resultSet);
                default:
                    throw new RuntimeException("Unknown table " + tableName);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Parsing exception", e);
        }
    }

    private static Service createServices(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getLong("id"));
        service.setName(resultSet.getString("name"));
        service.setPrice(resultSet.getDouble("price"));
        return service;
    }

    private static Room createRooms(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong("id"));
        room.setNumber(resultSet.getInt("number"));
        room.setCapacity(resultSet.getInt("capacity"));
        room.setRoomStatus(RoomStatus.getRoomStatusByNum(resultSet.getInt("status")));
        room.setComfort(RoomComfort.getRoomComfortByNum(resultSet.getInt("comfort")));
        return room;
    }

    private static Guest createGuest(ResultSet resultSet) throws SQLException {
        Guest guest = new Guest();
        guest.setId(resultSet.getLong("id"));
        guest.setName(resultSet.getString("name"));
        guest.setDaysOfStay(resultSet.getInt("days_of_stay"));
        return guest;
    }
}
