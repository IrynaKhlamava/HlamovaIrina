package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomService;
import com.company.filter.*;
import com.company.model.*;
import com.company.util.IdCreate;

import java.time.LocalDate;
import java.util.*;

public class RoomService implements IRoomService {

    private final IRoomDao roomDao;

    private final IGuestDao guestDao;

    public RoomService(IRoomDao roomDao, IGuestDao guestDao) {
        this.roomDao = roomDao;
        this.guestDao = guestDao;
    }

    @Override
    public Room addRoom(Integer number, Integer capacity, RoomStatus roomStatus, Double priceRoom, RoomComfort comfort) {
        Room room = new Room(number, capacity, roomStatus, priceRoom, comfort);
        room.setId(IdCreate.createRoomId());
        room.setNumber(NumberRoom.getNewRoomNumber());
        roomDao.save(room);
        return room;
    }

    @Override
    public void checkIn(Guest guest, Room room) {
        guest.setDateCheckIn();
        guest.setDateCheckOut(guest.getDateCheckIn().plusDays(guest.getDaysOfStay()));
        if (room.getGuests().size() < room.getCapacity()) {
            room.getGuests().add(guest);
        } else {
            System.out.println("Can't add guest. There is no free space in the room. Choose another room");
        }
    }

    @Override
    public void checkOut(Guest guest, Room room) {
        guest.setDateCheckOut(LocalDate.now());
        room.getGuests().remove(guest);
    }

    @Override
    public Room getByRoomNumber(Integer roomNum) {
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            if (room.getNumber().equals(roomNum)) {
                return room;
            }
        }
        return null;
    }

    @Override
    public List<Room> getAllFreeRoom() {
        List<Room> roomFree = new ArrayList<>();
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            if (room.getGuests().size() == 0) {
                roomFree.add(room);
            }
        }
        return roomFree;

    }

    @Override
    public List<Room> sortRoomByCapacity() {
        return roomDao.getAllSorted(new SortRoomByCapacity());
    }

    @Override
    public List<Room> sortRoomByComfort() {
        return roomDao.getAllSorted(new SortRoomByComfort());
    }

    @Override
    public List<Room> getFreeRoomSortByPrice() {
        return roomDao.getFilteredListSorted(getAllFreeRoom(), new SortRoomByPrice());
    }

    @Override
    public List<Room> getFreeRoomSortByCapacity() {
        return roomDao.getFilteredListSorted(getAllFreeRoom(), new SortRoomByCapacity());
    }

    @Override
    public List<Room> getFreeRoomSortByComfort() {
        return roomDao.getFilteredListSorted(getAllFreeRoom(), new SortRoomByComfort());
    }

    @Override
    public List<Room> sortRoomByPrice() {
        return roomDao.getAllSorted(new SortRoomByPrice());
    }

    @Override
    public Map<Integer, List<Guest>> getAllGuestsAndRoomsSortByName() {
        Map<Integer, List<Guest>> listGuestsAndRooms = new HashMap<>();
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            List<Guest> allGuests = room.getGuests();
            if (allGuests.size() > 1) allGuests.sort(new SortGuestsByName());
            listGuestsAndRooms.put(room.getNumber(), allGuests);
        }
        return listGuestsAndRooms;
    }

    @Override
    public Map<Integer, List<Guest>> getAllGuestsAndRoomsSortByDeparture() {
        Map<Integer, List<Guest>> listGuestsAndRooms = new HashMap<>();
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            List<Guest> allGuests = room.getGuests();
            if (allGuests.size() > 1) allGuests.sort(new SortByDeparture());
            listGuestsAndRooms.put(room.getNumber(), allGuests);
        }
        return listGuestsAndRooms;
    }

    @Override
    public int availableRooms() {
        int count = 0;
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            if (room.getGuests().size() == 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Room> getFreeRoomsByDate(LocalDate onDate) {
        List<Room> roomsFreeOnDate = new ArrayList<>();
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            List<Guest> allGuests = room.getGuests();
            for (Guest guest : allGuests) {
                if (guest.getDateCheckOut().isBefore(onDate)) {
                    roomsFreeOnDate.add(room);
                }
            }
        }
        return roomsFreeOnDate;
    }

    @Override
    public double getBill(Guest guest) {
        List<Room> rooms = roomDao.getAll();
        double sum = 0, price = 0, sumServices = 0;
        List<Service> addService;
        for (Room room : rooms) {
            List<Guest> allGuests = room.getGuests();
            for (Guest guestRoom : allGuests) {
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
    }

    @Override
    public Map<String, List<LocalDate>> lastGuestsOfRoom(int roomNumber) {
        Map<String, List<LocalDate>> listGuestsAndDate = new HashMap<>();
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            if (room.getNumber().equals(roomNumber)) {
                List<Guest> allGuests = room.getGuests();
                if (allGuests.size() > 1) allGuests.sort(new SortByDeparture());
                int count = 0;
                for (int i = allGuests.size() - 1; i >= 0 && count < 3; i--) {
                    Guest guest = allGuests.get(i);
                    List<LocalDate> dateGuest = new ArrayList<>();
                    dateGuest.add(guest.getDateCheckIn());
                    dateGuest.add(guest.getDateCheckOut());
                    listGuestsAndDate.put(guest.getName(), dateGuest);
                    count++;
                }
            }
        }
        return listGuestsAndDate;
    }

}
