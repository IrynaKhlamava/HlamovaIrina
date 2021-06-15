package com.company.facade;


import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomDao;
import com.company.api.dao.IServiceDao;
import com.company.dao.GuestDao;
import com.company.dao.RoomDao;
import com.company.dao.ServiceDao;
import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.Service;
import com.company.service.GuestService;
import com.company.service.RoomService;
import com.company.service.ServiceService;

import java.util.List;

public class HotelFacade {

    private RoomService roomService;
    private GuestService guestService;
    private ServiceService serviceService;

    private HotelFacade() {

    }

    private static HotelFacade INSTANCE;

    public static HotelFacade getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new HotelFacade();
            INSTANCE.init();
        }
        return INSTANCE;
    }

    public void saveRoom(Room room) {
        roomService.save(room);
    }

    public List<Room> getAllRoom() {
        return roomService.getAll();
    }

    private void init() {
        IRoomDao roomDao = new RoomDao();
        IGuestDao guestDao = new GuestDao();
        IServiceDao serviceDao = new ServiceDao();
        roomService = new RoomService(roomDao, guestDao);
        guestService = new GuestService(guestDao);
        serviceService = new ServiceService(serviceDao);
    }

    public List<Room> getAllRoomsSortedByCapacity() {
        return roomService.sortRoomByCapacity();
    }

    public List<Room> getAllRoomsSortedByByPrice() {
        return roomService.sortRoomByPrice();
    }

    public List<Room> getAllRoomsSortedByComfort() {
        return roomService.sortRoomByComfort();
    }

    public void saveGuest(String name, Integer daysOfStay) {
        guestService.addGuest(name, daysOfStay);
    }

    public List<Guest> getAllGuests() {
        return guestService.getAll();
    }

    public List<Guest> getAllGuestsSortedByName() {
        return guestService.sortGuestsByName();
    }

    public List<Guest> getAllGuestsSortedByDeparture() {
        return guestService.sortGuestsByDeparture();
    }

    public List<Service> getAllServices() {
        return serviceService.getAll();
    }

    public void saveService(String name, Double price, Guest guest) {
        //не работает как получить гостя
        serviceService.addService(name, price, guest);
    }

    public List<Service> getAllServicesSortedByPrice(Guest guest) {
        //не работает как получить гостя
        return serviceService.getAllServicesSortByPrice(guest);
    }

}
