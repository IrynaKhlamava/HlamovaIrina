package com.company.dao.util;

import com.company.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EntityMapper {

    public static AEntity parseResultSet(ResultSet resultSet, TableEnum tableName) {
        try {
            switch (tableName) {
                case GUESTS:
                    return createGuest(resultSet);
                case ROOMS:
                    return createRooms(resultSet);
                case SERVICES:
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
        service.setGuestId(resultSet.getLong("guest_id"));
        return service;
    }

    public static Room createRooms(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong("id"));
        room.setNumber(resultSet.getInt("number"));
        room.setCapacity(resultSet.getInt("capacity"));
        room.setRoomStatus(RoomStatus.getRoomStatusByNum(resultSet.getInt("status")));
        room.setComfort(RoomComfort.getRoomComfortByNum(resultSet.getInt("comfort")));
        room.setPriceRoom(resultSet.getDouble("price"));
        return room;
    }

    private static Guest createGuest(ResultSet resultSet) throws SQLException {
        Guest guest = new Guest();
        guest.setId(resultSet.getLong("id"));
        guest.setName(resultSet.getString("name"));
        guest.setDaysOfStay(resultSet.getInt("days_of_stay"));
        if (resultSet.getDate("date_check_in") != null) {
            guest.setDateCheckIn(resultSet.getDate("date_check_in").toLocalDate());
        } else {
            guest.setDateCheckIn(null);
        }
        if (resultSet.getDate("date_check_out") != null) {
            guest.setDateCheckOut(resultSet.getDate("date_check_out").toLocalDate());
        } else {
            guest.setDateCheckOut(null);
        }
        if (resultSet.getLong("room_id") != 0) {
            guest.setRoomId(resultSet.getLong("room_id"));
        } else {
            guest.setRoomId(null);
        }
        return guest;
    }

    public static LastGuestsInfo createLastGuestsInfo(ResultSet resultSet) throws SQLException {
        LastGuestsInfo guest = new LastGuestsInfo();
        guest.setName(resultSet.getString("name"));
        guest.setDateCheckIn(resultSet.getDate("date_check_in").toLocalDate());
        guest.setDateCheckOut(resultSet.getDate("date_check_out").toLocalDate());
        return guest;
    }
}
