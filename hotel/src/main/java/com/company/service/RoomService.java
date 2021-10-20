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
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@Transactional
public class RoomService implements IRoomService {

    private static final String GET_BY_DATA_ERROR_MESSAGE = "could not find an entity by data: %s";
    private static final Logger LOGGER = Logger.getLogger(RoomService.class.getName());


    private final IRoomDao roomDao;

    private final IGuestDao guestDao;

    private final IServiceDao serviceDao;

    private final ModelMapper mapper;

    public RoomService(IRoomDao roomDao, IGuestDao guestDao, IServiceDao serviceDao, ModelMapper mapper) {
        this.roomDao = roomDao;
        this.guestDao = guestDao;
        this.serviceDao = serviceDao;
        this.mapper = mapper;
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
    public void checkIn(Long guestId, Long roomId) {
        int count;
        LOGGER.info(String.format("checkIn of the guestId: %s to the roomId: %s", guestId, roomId));
        try {
            count = guestDao.getCountGuestsInRoomById(roomId);
            Guest guest = guestDao.getById(guestId);
            guest.setDateCheckIn(LocalDate.now());
            guest.setDateCheckOut(guest.getDateCheckIn().plusDays(guest.getDaysOfStay()));
            guest.setRoomId(roomId);
            Room room = roomDao.getById(roomId);
            if (count < room.getCapacity()) {
                guestDao.update(guest.getId(), guest);
            } else {
                LOGGER.warn("No Available space in the room. CheckIn failed");
            }
        } catch (DaoException e) {
            LOGGER.warn("CheckIn failed", e);
            throw new ServiceException("CheckIn failed", e);
        }
    }

    @Override
    public void checkOut(Long guestId, Long roomId) {
        LOGGER.info(String.format("checkOut of the guestId: %s from the roomId: %s", guestId, roomId));
        try {
            Guest guest = guestDao.getById(guestId);
            guest.setDateCheckOut(LocalDate.now());
            guestDao.update(guestId, guest);
        } catch (DaoException e) {
            LOGGER.warn("CheckOut failed", e);
            throw new ServiceException("CheckOut failed", e);
        }
    }

    @Override
    public Room getById(Long id) {
        return roomDao.getById(id);
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
    public List<Room> getAvailableRooms(String col) {
        try {
            RoomFilter filter = new RoomFilter();
            filter.setSortField(col);
            return roomDao.getAllAvailableRooms(filter);
        } catch (DaoException e) {
            LOGGER.warn(String.format("get All AvailableRoom failed"), e);
            throw new ServiceException(String.format("get All AvailableRoom failed"), e);
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
    public List<Room> getAvailableRoomsByDate(String byDate) {
        LOGGER.info("get Available Rooms By Date");
        try {
            RoomFilter filter = new RoomFilter();
            filter.setDate(LocalDate.parse(byDate));
            return roomDao.getAllAvailableRooms(filter);
        } catch (DaoException e) {
            LOGGER.warn("get Available Rooms By Date failed", e);
            throw new ServiceException("get Available Rooms By Date failed", e);
        }
    }

    @Override
    public Double getBill(Long idGuest) {
        LOGGER.info("get Bill of guest");
        try {
            Guest guest = guestDao.getById(idGuest);
            Double billForServices = serviceDao.getBillForGuestForServices(idGuest);
            if (billForServices != null) {
                return roomDao.getRoomPrice(guest.getRoomId()) * guest.getDaysOfStay() + billForServices;
            } else {
                return roomDao.getRoomPrice(guest.getRoomId()) * guest.getDaysOfStay();
            }
        } catch (DaoException e) {
            LOGGER.warn("get Bill of guest failed", e);
            throw new ServiceException("get Bill of guest failed", e);
        }
    }

    public void save(Room room) {
        roomDao.save(room);
    }

    public List<Room> getAll(String col) {
        return roomDao.getAll(col);
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
