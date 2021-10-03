package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomDao;
import com.company.api.dao.IServiceDao;
import com.company.api.service.IRoomService;

import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;

import com.company.model.*;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoomService implements IRoomService {

    private static final String GET_BY_DATA_ERROR_MESSAGE = "could not find an entity by data: %s";
    private static final Logger LOGGER = Logger.getLogger(RoomService.class.getName());


    private final IRoomDao roomDao;

    private final IGuestDao guestDao;

    private final IServiceDao serviceDao;

    public RoomService(IRoomDao roomDao, IGuestDao guestDao, IServiceDao serviceDao) {
        this.roomDao = roomDao;
        this.guestDao = guestDao;
        this.serviceDao = serviceDao;
    }

    @Override
    public Room addRoom(Integer number, Integer capacity, Integer roomStatus, Double priceRoom, Integer comfort) {
        try {
            LOGGER.info(String.format("addRoom number: %s, capacity: %s, roomStatus: %s, priceRoom: %s, comfort: %s",
                    number, capacity, roomStatus, priceRoom, comfort));
            Room room = new Room(number, capacity, roomStatus, priceRoom, comfort);
            roomDao.save(room);
            return room;
        } catch (DaoException e) {
            LOGGER.warn("addRoom failed", e);
            throw new ServiceException("addRoom failed", e);
        }
    }

    @Override
    public void checkIn(Guest guest, Room room) {
        int count;
        LOGGER.info(String.format("checkIn of the guest: %s to the room: %s", guest, room));
        count = guestDao.getCountGuestsInRoomById(room.getId());
        try {
            guest.setDateCheckIn(LocalDate.now());
            guest.setDateCheckOut(guest.getDateCheckIn().plusDays(guest.getDaysOfStay()));
            guest.setRoomId(room.getId());
            if (count < room.getCapacity()) {
                guestDao.update(guest);
            } else {
                LOGGER.warn("No free space in the room. CheckIn failed");
            }
        } catch (DaoException e) {
            LOGGER.warn("CheckIn failed", e);
            throw new ServiceException("CheckIn failed", e);
        }
    }

    @Override
    public void checkOut(Guest guest, Room room) {
        LOGGER.info(String.format("checkOut of the guest: %s from the room: %s", guest, room));
        try {
            guest.setDateCheckOut(LocalDate.now());
            guestDao.update(guest);
        } catch (DaoException e) {
            LOGGER.warn("CheckOut failed", e);
            throw new ServiceException("CheckOut failed", e);
        }
    }

    @Override
    public Room getByRoomNumber(Integer roomNum) {
        LOGGER.info(String.format("get Room By Number: %s ", roomNum));
        try {
            return roomDao.getRoomByNumber(roomNum);
        } catch (DaoException e) {
            LOGGER.warn(String.format("get Room By Number failed: %s ", roomNum));
            throw new ServiceException("get Room By Number failed");
        }
    }

    @Override
    public List<Room> getAllFreeRoom() {
        try {
            return roomDao.getAllFreeRooms(new RoomFilter());
        } catch (DaoException e) {
            LOGGER.warn(String.format("get All FreeRoom failed"), e);
            throw new ServiceException(String.format("get All FreeRoom failed"), e);
        }
    }

    @Override
    public void changeStatusByRoomNumber(Integer roomNum, RoomStatus newStatus) {
        if (roomDao.getRoomByNumber(roomNum) != null) {
            LOGGER.info(String.format("Status of the room at number: %s. changed on new status: %s", roomNum, newStatus));
            roomDao.changeRoomStatus(roomNum, newStatus);
        } else {
            LOGGER.warn(String.format("change status room: %s failed", roomNum));
            throw new ServiceException(String.format(GET_BY_DATA_ERROR_MESSAGE, roomNum));
        }
    }

    @Override
    public void changePrice(Integer roomNum, Double newPrice) {
        if (roomDao.getRoomByNumber(roomNum) != null) {
            roomDao.changeRoomPrice(roomNum, newPrice);
            LOGGER.info(String.format("Price of the room at number: %s. changed on new price: %s", roomNum, newPrice));
        } else {
            LOGGER.info(String.format("no room number: %s. change price failed", roomNum));
            throw new ServiceException(String.format(GET_BY_DATA_ERROR_MESSAGE, roomNum));
        }
    }

    @Override
    public List<Room> sortRoomByCapacity() {
        LOGGER.info("sort Room By Capacity");
        try {
            return roomDao.getAllSorted("capacity");
        } catch (DaoException e) {
            LOGGER.warn("sort Room By Capacity failed", e);
            throw new ServiceException("sort Room By Capacity failed", e);
        }
    }

    @Override
    public List<Room> sortRoomByComfort() {
        LOGGER.info("sort Room By Comfort");
        try {
            return roomDao.getAllSorted("comfort");
        } catch (DaoException e) {
            LOGGER.warn("sort Room By Comfort failed", e);
            throw new ServiceException("sort Room By Comfort failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomSortByPrice() {
        LOGGER.info("Free Room Sort By Price");
        try {
            RoomFilter filter = new RoomFilter();
            filter.setSortField("priceRoom");
            return roomDao.getAllFreeRooms(filter);
        } catch (DaoException e) {
            LOGGER.warn("get Free Room Sort By Price failed", e);
            throw new ServiceException("get Free Room Sort By Price failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomSortByCapacity() {
        LOGGER.info("Free Room Sort By Capacity");
        try {
            RoomFilter filter = new RoomFilter();
            filter.setSortField("capacity");
            return roomDao.getAllFreeRooms(filter);
        } catch (DaoException e) {
            LOGGER.warn("get Free Room Sort By Capacity failed", e);
            throw new ServiceException("get Free Room Sort By Capacity failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomSortByComfort() {
        LOGGER.info("Free Room Sort By Comfort");
        try {
            RoomFilter filter = new RoomFilter();
            filter.setSortField("comfort");
            return roomDao.getAllFreeRooms(filter);
        } catch (DaoException e) {
            LOGGER.info("sort Free Room By Price failed", e);
            throw new ServiceException("sort Room By Price failed", e);
        }
    }

    @Override
    public List<Room> sortRoomByPrice() {
        LOGGER.info("All Rooms Sort By Price");
        try {
            return roomDao.getAllSorted("priceRoom");
        } catch (DaoException e) {
            LOGGER.info("sort Rooms By Price failed", e);
            throw new ServiceException("sort Rooms By Price failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomsByDate(String byDate) {
        LOGGER.info("get Free Rooms By Date");
       try {
           RoomFilter filter = new RoomFilter();
           filter.setDate(LocalDate.parse(byDate));
            return roomDao.getAllFreeRooms(filter);
        } catch (DaoException e) {
            LOGGER.warn("get Free Rooms By Date failed", e);
            throw new ServiceException("get Free Rooms By Date failed", e);
        }
    }

    @Override
    public double getBill(Guest guest) {
        LOGGER.info("get Bill of guest");
        try {
            return roomDao.getRoomPrice(guest.getRoomId()) * guest.getDaysOfStay() + serviceDao.getBillForGuestForServices(guest.getId());
        } catch (DaoException e) {
            LOGGER.warn("get Bill of guest failed", e);
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

    @Override
    public Integer getNumOfAvailableRooms() {
        return roomDao.getNumOfAvailableRooms();
    }

}
