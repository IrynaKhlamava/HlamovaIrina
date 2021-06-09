package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomService;
import com.company.filter.*;
import com.company.model.Guest;
import com.company.model.Room;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class RoomService implements IRoomService {

    private final IRoomDao roomDao;

    private final IGuestDao guestDao;

    public RoomService(IRoomDao roomDao, IGuestDao guestDao) {
        this.roomDao = roomDao;
        this.guestDao = guestDao;
    }

    @Override
    public void checkIn(Guest guest, Room room) {
        //Room room = roomDao.getByRoomNumber(roomNum);
        guest.setDateCheckIn();
        // guestDao.calcDateCheckOut(guest);
        if (room.getGuests().size() < room.getCapacity()) {
            room.getGuests().add(guest);
        } else {
            System.out.println("Can't add guest. There is no free space in the room. Choose another room");
        }
    }

    @Override
    public void checkOut(Guest guest, Room room) {
        //Room room = roomDao.getByRoomNumber(roomNum);
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
    public List<Room> sortRoomByCapacity() {
        Comparator<Room> sortByCapacity = new SortRoomByCapacity();
        List<Room> roomsSortByCapacity = roomDao.getAll();
        roomsSortByCapacity.sort(sortByCapacity);
        return roomsSortByCapacity;
    }

    @Override
    public List<Room> sortRoomByPrice() {
        Comparator<Room> sortRoomByPrice = new SortRoomByPrice();
        List<Room> roomsSortRoomByPrice = roomDao.getAll();
        roomsSortRoomByPrice.sort(sortRoomByPrice);
        return roomsSortRoomByPrice;

    }

    @Override
    public List<Room> sortRoomByComfort() {
        Comparator<Room> sortRoomByComfort = new SortRoomByComfort();
        List<Room> roomsSortByComfort = roomDao.getAll();
        roomsSortByComfort.sort(sortRoomByComfort);
        return roomsSortByComfort;
    }

    @Override
    public List<Room> sortFreeRoomByPrice(List<Room> freeRoom) {
        Comparator<Room> sortRoomByPrice = new SortRoomByPrice();
        freeRoom.sort(sortRoomByPrice);
        return new ArrayList<>(freeRoom);
    }


    @Override
    public List<Room> sortFreeRoomByCapacity(List<Room> freeRoom) {
        Comparator<Room> sortRoomByCapacity = new SortRoomByCapacity();
        freeRoom.sort(sortRoomByCapacity);
        return new ArrayList<>(freeRoom);
    }

    @Override
    public List<Room> sortFreeRoomByComfort(List<Room> freeRoom) {
        Comparator<Room> sortRoomByComfort = new SortRoomByComfort();
        freeRoom.sort(sortRoomByComfort);
        return new ArrayList<>(freeRoom);
    }

    @Override
    public void getAllGuestsAndRooms() {
        Comparator<Guest> sortGuest = new SortGuestsByName();
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            List<Guest> allGuests = room.getGuests();
            allGuests.sort(sortGuest);
            System.out.println("Room № " + room.getNumber() + ", all guest sort by name " + allGuests);
        }
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
    public void getAllGuestsAndRoomsSortByDeparture() {
        Comparator<Guest> sortGuest = new SortByDeparture();
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            List<Guest> allGuests = room.getGuests();
            if (allGuests.size() > 1) allGuests.sort(sortGuest);
            System.out.println("Room № " + room.getNumber() + ", all guest sort by name " + allGuests);
        }
    }

    @Override
    public List<Room> getFreeRoomsByDate(Date onDate) {
        List<Room> roomsFreeOnDate = new ArrayList<>();
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            List<Guest> allGuests = room.getGuests();
            for (Guest guest : allGuests) {
                if (guest.getDateCheckOut().after(onDate)) {
                    roomsFreeOnDate.add(room);
                }
            }
        }

        return roomsFreeOnDate;
    }

    @Override
    public double getBill(Guest guest) {
        List<Room> rooms = roomDao.getAll();
        double sum = 0, price = 0, addService = 0;
        for (Room room : rooms) {
            List<Guest> allGuests = room.getGuests();
            for (Guest guestRoom : allGuests) {
                if (guestRoom.getId().equals(guest.getId())) {
                    price = room.getPriceRoom();
                    addService = guest.getAddServices();
                }
            }
        }
        sum = price * guest.getDaysOfStay() + addService;

        return sum;
    }

    @Override
    public void lastGuestsOfRoom(int roomNumber) {
        Comparator<Guest> sortGuest = new SortByDeparture();
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms) {
            if (room.getNumber().equals(roomNumber)) {
                List<Guest> allGuests = room.getGuests();
                if (allGuests.size() > 1) allGuests.sort(sortGuest);
                int count = 0;
                for (int i = allGuests.size() - 1; i >= 0 && count < 3; i--) {
                    System.out.println(allGuests.get(i));
                    count++;
                }
                break;
            }

        }

    }


}
