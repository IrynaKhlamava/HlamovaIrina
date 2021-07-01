package com.company.service;

import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomService;
import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;
import com.company.filter.*;
import com.company.model.*;
import com.company.util.IdCreate;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.company.util.FilteredListSorted.getFilteredListSorted;

public class RoomService implements IRoomService {

    private static final String GET_BY_DATA_ERROR_MESSAGE = "could not find an entity by data: %s";
    private static final Logger LOGGER = Logger.getLogger(RoomService.class.getName());

    private final IRoomDao roomDao;

    public RoomService(IRoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public Room addRoom(Integer number, Integer capacity, RoomStatus roomStatus, Double priceRoom, RoomComfort comfort) {
        try {
            LOGGER.log(Level.INFO, String.format("addRoom number: %s, capacity: %s, roomStatus: %s, priceRoom: %s, comfort: %s", number, capacity, roomStatus, priceRoom, comfort));
            Room room = new Room(number, capacity, roomStatus, priceRoom, comfort);
            room.setId(IdCreate.createRoomId());
            room.setNumber(NumberRoom.getNewRoomNumber());
            roomDao.save(room);
            return room;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "addRoom failed", e);
            throw new ServiceException("addRoom failed", e);
        }
    }

    @Override
    public void checkIn(Guest guest, Room room) {
        LOGGER.log(Level.INFO, String.format("checkIn of the guest: %s to the room: %s", guest, room));
        try {
            guest.setDateCheckIn(LocalDate.now());
            guest.setDateCheckOut(guest.getDateCheckIn().plusDays(guest.getDaysOfStay()));
            if (room.getGuests().size() < room.getCapacity()) {
                room.getGuests().add(guest);
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
            room.getGuests().remove(guest);
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "CheckOut failed", e);
            throw new ServiceException("CheckOut failed", e);
        }
    }

    @Override
    public Room getByRoomNumber(Integer roomNum) {
        LOGGER.log(Level.INFO, String.format("get Room By Number: %s ", roomNum));
        return roomDao.getAll().stream()
                .filter(room -> room.getNumber()
                        .equals(roomNum))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.log(Level.WARNING, String.format("get Room By Number failed: %s ", roomNum));
                    throw new ServiceException("get Room By Number failed");
                });
    }

    @Override
    public List<Room> getAllFreeRoom() {
        try {
            return getAll().stream()
                    .filter(r -> r.getGuests().size() == 0)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, String.format("get All FreeRoom failed"), e);
            throw new ServiceException(String.format("get All FreeRoom failed"), e);
        }
    }

    @Override
    public void changeStatusByRoomNumber(Integer roomNum, RoomStatus roomStatus) {
        LOGGER.log(Level.INFO, String.format("Status of the room at number: %s. changed on new status: %s", roomNum, roomStatus));
        getAll().stream()
                .filter(r -> r.getNumber().equals(roomNum))
                .peek(r -> {
                    r.setRoomStatus(roomStatus);
                })
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.log(Level.WARNING, String.format("change status room: %s failed", roomNum));
                    throw new ServiceException(String.format(GET_BY_DATA_ERROR_MESSAGE, roomNum));
                });
    }

    @Override
    public void changePrice(Integer roomNum, Double newPrice) {
        LOGGER.log(Level.INFO, String.format("Price of the room at number: %s. changed on new price: %s", roomNum, newPrice));
        getAll().stream()
                .filter(r -> r.getNumber().equals(roomNum))
                .peek(r -> {
                    r.setPriceRoom(newPrice);
                })
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.log(Level.INFO, String.format("no room number: %s. change price failed", roomNum));
                    throw new ServiceException(String.format(GET_BY_DATA_ERROR_MESSAGE, roomNum));
                });
    }

    @Override
    public List<Room> sortRoomByCapacity() {
        LOGGER.log(Level.INFO, "sort Room By Capacity");
        try {
            return roomDao.getAllSorted(new SortRoomByCapacity());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "sort Room By Capacity failed", e);
            throw new ServiceException("sort Room By Capacity failed", e);
        }
    }

    @Override
    public List<Room> sortRoomByComfort() {
        LOGGER.log(Level.INFO, "sort Room By Comfort");
        try {
            return roomDao.getAllSorted(new SortRoomByComfort());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "sort Room By Comfort failed", e);
            throw new ServiceException("sort Room By Comfort failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomSortByPrice() {
        LOGGER.log(Level.INFO, "Free Room Sort By Price");
        try {
            return getFilteredListSorted(getAllFreeRoom(), new SortRoomByPrice());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Free Room Sort By Price failed", e);
            throw new ServiceException("get Free Room Sort By Price failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomSortByCapacity() {
        LOGGER.log(Level.INFO, "Free Room Sort By Capacity");
        try {
            return getFilteredListSorted(getAllFreeRoom(), new SortRoomByCapacity());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Free Room Sort By Capacity failed", e);
            throw new ServiceException("get Free Room Sort By Capacity failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomSortByComfort() {
        LOGGER.log(Level.INFO, "Free Room Sort By Comfort");
        try {
            return getFilteredListSorted(getAllFreeRoom(), new SortRoomByComfort());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "sort Free Room By Price failed", e);
            throw new ServiceException("sort Room By Price failed", e);
        }
    }

    @Override
    public List<Room> sortRoomByPrice() {
        LOGGER.log(Level.INFO, "Free Room Sort By Price");
        try {
            return roomDao.getAllSorted(new SortRoomByPrice());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "sort Room By Price failed", e);
            throw new ServiceException("sort Room By Price failed", e);
        }
    }


    @Override
    public Map<Integer, List<Guest>> getAllGuestsAndRoomsSortedByThisComparator(Comparator comparator) {
        LOGGER.log(Level.INFO, "get Rooms ang Guests Sort By comparator");
        try {
            Map<Integer, List<Guest>> listGuestsAndRooms = new HashMap<>();
            List<Room> rooms = roomDao.getAll();
            for (Room room : rooms) {
                List<Guest> allGuests = room.getGuests();
                if (allGuests.size() > 1) allGuests.sort(comparator);
                listGuestsAndRooms.put(room.getNumber(), allGuests);
            }
            return listGuestsAndRooms;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Rooms ang Guests Sort By comparator failed", e);
            throw new ServiceException("get Rooms ang Guests Sort By comparator failed", e);
        }
    }

    @Override
    public Map<Integer, List<Guest>> getAllGuestsAndRoomsSortByName() {
        LOGGER.log(Level.INFO, "get Rooms ang Guests Sort By Name failed");
        try {
            return getAllGuestsAndRoomsSortedByThisComparator(new SortGuestsByName());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Rooms ang Guests Sort By Name failed", e);
            throw new ServiceException("get Rooms ang Guests Sort By Name failed", e);
        }
    }

    @Override
    public Map<Integer, List<Guest>> getAllGuestsAndRoomsSortByDeparture() {
        LOGGER.log(Level.INFO, "get Rooms ang Guests Sort By Departure failed");
        try {
            return getAllGuestsAndRoomsSortedByThisComparator(new SortByDeparture());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Rooms ang Guests Sort By Departure failed", e);
            throw new ServiceException("get Rooms ang Guests Sort By Departure failed", e);
        }
    }

    @Override
    public int availableRooms() {
        LOGGER.log(Level.INFO, "available Rooms");
        try {
            return (int) roomDao.getAll().stream()
                    .filter(r -> r.getGuests().size() == 0)
                    .count();
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "available Rooms failed", e);
            throw new ServiceException("available Rooms failed", e);
        }
    }

    @Override
    public List<Room> getFreeRoomsByDate(LocalDate onDate) {
        LOGGER.log(Level.INFO, "get Free Rooms By Date");
        try {
            List<Room> roomsFreeOnDate = new ArrayList<>();
            for (Room room : roomDao.getAll()) {
                List<Guest> allGuests = room.getGuests();
                if (allGuests.size() == 0) {
                    roomsFreeOnDate.add(room);
                } else {
                    for (Guest guest : allGuests) {
                        if (guest.getDateCheckOut().isBefore(onDate)) {
                            roomsFreeOnDate.add(room);
                        }
                    }
                }
            }
            return roomsFreeOnDate;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Free Rooms By Date failed", e);
            throw new ServiceException("get Free Rooms By Date failed", e);
        }
    }

    @Override
    public double getBill(Guest guest) {
        LOGGER.log(Level.INFO, "get Bill of guest");
        try {
            double sum = 0, price = 0, sumServices = 0;
            List<Service> addService;
            for (Room room : roomDao.getAll()) {
                for (Guest guestRoom : room.getGuests()) {
                    if (guestRoom.getId().equals(guest.getId())) {
                        price = room.getPriceRoom();
                        addService = guest.getListServices();
                        for (Service service : addService) {
                            sumServices += service.getPrice();
                        }
                    }
                }
            }
            sum = price * guest.getDaysOfStay() + sumServices;
            return sum;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "get Bill of guest failed", e);
            throw new ServiceException("get Bill of guest failed", e);
        }
    }

    @Override
    public List<LastGuestsInfo> lastGuestsOfRoom(int roomNumber) {
        LOGGER.log(Level.INFO, "last Guests Of Room");
        try {
            List<LastGuestsInfo> listLastGuestInfo = new ArrayList<>();
            for (Room room : roomDao.getAll()) {
                if (room.getNumber().equals(roomNumber)) {
                    List<Guest> allGuests = room.getGuests();
                    if (allGuests.size() > 1) allGuests.sort(new SortByDeparture());
                    int count = 0;
                    for (int i = allGuests.size() - 1; i >= 0 && count < 3; i--) {
                        Guest guest = allGuests.get(i);
                        LastGuestsInfo guestsInfo = new LastGuestsInfo();
                        guestsInfo.setName(guest.getName());
                        guestsInfo.setDateCheckIn(guest.getDateCheckIn());
                        guestsInfo.setDateCheckOut(guest.getDateCheckOut());
                        listLastGuestInfo.add(guestsInfo);
                        count++;
                    }
                }
            }
            return listLastGuestInfo;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "last Guests Of Room failed", e);
            throw new ServiceException("last Guests Of Room failed", e);
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
