package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomDao;
import com.company.api.dao.IServiceDao;
import com.company.api.service.IRoomService;

import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;

import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class RoomService implements IRoomService {

    private static final String GET_BY_DATA_ERROR_MESSAGE = "could not find an entity by data: %s";
    private static final Logger LOGGER = Logger.getLogger(RoomService.class.getName());

    @Autowired
    private IRoomDao roomDao;

    @Autowired
    private IGuestDao guestDao;

    @Autowired
    private IServiceDao serviceDao;

    public RoomService() {
    }

    @Override
    public Room addRoom(Integer number, Integer capacity, Integer roomStatus, Double priceRoom, Integer comfort) {
        try {
            LOGGER.log(Level.INFO, String.format("addRoom number: %s, capacity: %s, roomStatus: %s, priceRoom: %s, comfort: %s", number, capacity, roomStatus, priceRoom, comfort));
            Room room = new Room(number, capacity, roomStatus, priceRoom, comfort);
            roomDao.save(room);
            return room;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "addRoom failed", e);
            throw new ServiceException("addRoom failed", e);
        }
    }

    @Override
    public void checkIn(Guest guest, Room room) {
        int count;
        LOGGER.log(Level.INFO, String.format("checkIn of the guest: %s to the room: %s", guest, room));
        count = guestDao.getCountGuestsInRoomById(room.getId());
        try {
            guest.setDateCheckIn(LocalDate.now());
            guest.setDateCheckOut(guest.getDateCheckIn().plusDays(guest.getDaysOfStay()));
            guest.setRoomId(room.getId());
            if (count < room.getCapacity()) {
                guestDao.update(guest);
            } else {
                LOGGER.log(Level.WARNING, "No free space in the room. CheckIn failed");
            }
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "CheckIn failed", e);
            throw new ServiceException("CheckIn failed", e);
        }
    }

    @Override
    public void checkOut(Guest guest, Room room) {
        LOGGER.log(Level.INFO, String.format("checkOut of the guest: %s from the room: %s", guest, room));
        try {
            guest.setDateCheckOut(LocalDate.now());
            guestDao.update(guest);
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "CheckOut failed", e);
            throw new ServiceException("CheckOut failed", e);
        }
    }

    @Override
    public Room getByRoomNumber(Integer roomNum) {
        LOGGER.log(Level.INFO, String.format("get Room By Number: %s ", roomNum));
        try {
            return roomDao.getRoomByNumber(roomNum);
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, String.format("get Room By Number failed: %s ", roomNum));
            throw new ServiceException("get Room By Number failed");
        }
    }

    @Override
    public List<Room> getAllFreeRoom() {
        try {
            return roomDao.getFreeRooms();
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, String.format("get All FreeRoom failed"), e);
            throw new ServiceException(String.format("get All FreeRoom failed"), e);
        }
    }

    @Override
    public void changeStatusByRoomNumber(Integer roomNum, RoomStatus newStatus) {
        LOGGER.log(Level.INFO, String.format("Status of the room at number: %s. changed on new status: %s", roomNum, newStatus));
        try {
            roomDao.changeRoomStatus(roomNum, newStatus);
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, String.format("change status room: %s failed", roomNum));
            throw new ServiceException(String.format(GET_BY_DATA_ERROR_MESSAGE, roomNum));
        }
    }

    @Override
    public void changePrice(Integer roomNum, Double newPrice) {
        LOGGER.log(Level.INFO, String.format("Price of the room at number: %s. changed on new price: %s", roomNum, newPrice));
        try {
            roomDao.changeRoomPrice(roomNum, newPrice);
        } catch (DaoException e) {
            LOGGER.log(Level.INFO, String.format("no room number: %s. change price failed", roomNum));
            throw new ServiceException(String.format(GET_BY_DATA_ERROR_MESSAGE, roomNum));
        }
    }

    @Override
    public List<Room> sortRoomByCapacity() {
        LOGGER.log(Level.INFO, "sort Room By Capacity");
        try {
            return roomDao.getAllSorted("capacity");
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "sort Room By Capacity failed", e);
            throw new ServiceException("sort Room By Capacity failed", e);
        }
    }

    @Override
    public List<Room> sortRoomByComfort() {
        LOGGER.log(Level.INFO, "sort Room By Comfort");
        try {
            return roomDao.getAllSorted("comfort");
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "sort Room By Comfort failed", e);
            throw new ServiceException("sort Room By Comfort failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomSortByPrice() {
        LOGGER.log(Level.INFO, "Free Room Sort By Price");
        try {
            return roomDao.getFreeRoomsSort("price");
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Free Room Sort By Price failed", e);
            throw new ServiceException("get Free Room Sort By Price failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomSortByCapacity() {
        LOGGER.log(Level.INFO, "Free Room Sort By Capacity");
        try {
            return roomDao.getFreeRoomsSort("capacity");
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Free Room Sort By Capacity failed", e);
            throw new ServiceException("get Free Room Sort By Capacity failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomSortByComfort() {
        LOGGER.log(Level.INFO, "Free Room Sort By Comfort");
        try {
            return roomDao.getFreeRoomsSort("comfort");
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "sort Free Room By Price failed", e);
            throw new ServiceException("sort Room By Price failed", e);
        }
    }

    @Override
    public List<Room> sortRoomByPrice() {
        LOGGER.log(Level.INFO, "All Rooms Sort By Price");
        try {
            return roomDao.getAllSorted("price");
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "sort Rooms By Price failed", e);
            throw new ServiceException("sort Rooms By Price failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomsByDate(String byDate) {
        LOGGER.log(Level.INFO, "get Free Rooms By Date");
        try {
            return roomDao.getFreeRoomsByDate(byDate);
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Free Rooms By Date failed", e);
            throw new ServiceException("get Free Rooms By Date failed", e);
        }
    }

    @Override
    public double getBill(Guest guest) {
        LOGGER.log(Level.INFO, "get Bill of guest");
        try {
            return roomDao.getRoomPrice(guest.getRoomId()) * guest.getDaysOfStay() + serviceDao.getBillForGuestForServices(guest.getId());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Bill of guest failed", e);
            throw new ServiceException("get Bill of guest failed", e);
        }
    }

    public void save(Room room) {
        roomDao.save(room);
    }

    public List<Room> getAll() {
        return roomDao.getAll();
    }

    public RoomStatus getRoomStatusByNumber(Integer num) {
        return RoomStatus.getRoomStatusByNum(num);
    }

    public RoomComfort getRoomComfortByNumber(Integer num) {
        return RoomComfort.getRoomComfortByNum(num);
    }

}
