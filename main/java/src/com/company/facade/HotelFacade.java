package com.company.facade;

import com.company.api.service.IGuestService;
import com.company.api.service.IRoomService;
import com.company.api.service.IServiceService;

import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.model.*;

import com.company.service.SerializationService;

import java.time.LocalDate;
import java.util.List;

@Component
public class HotelFacade {

    @Autowired
    private IRoomService roomService;
    @Autowired
    private IGuestService guestService;
    @Autowired
    private IServiceService serviceService;

    public void saveRoom(Integer number, Integer capacity, Integer roomStatus, Double priceRoom, Integer comfort) {
        roomService.addRoom(number, capacity, roomStatus, priceRoom, comfort);
    }

    public List<Room> getAllRoom() {
        return roomService.getAll();
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
        serviceService.addService(name, price, guest);
    }

    public List<Service> getAllGuestServicesSortedByPrice(Guest guest) {
        return serviceService.getAllServicesSortByPrice(guest);
    }

    public Guest getGuest(Long guestID) {
        return guestService.getGuest(guestID);
    }

    public Room getByRoomNumber(Integer num) {
        return roomService.getByRoomNumber(num);
    }

    public void checkIn(Guest guest, Room room) {
        roomService.checkIn(guest, room);
    }

    public void checkOut(Guest guest, Room room) {
        roomService.checkOut(guest, room);
    }

    public List<Room> getAllFreeRooms() {
        return roomService.getAllFreeRoom();
    }

    public Integer getNumberAllFreeRooms() {
        return roomService.getAllFreeRoom().size();
    }

    public List<Room> getFreeRoomsByDate(LocalDate onDate) {
        return roomService.getFreeRoomsByDate(onDate);
    }

    public double getBill(Guest guest) {
        return roomService.getBill(guest);
    }

    public List<LastGuestsInfo> getLastGuestsOfRoom(Integer roomNum) {
        return roomService.lastGuestsOfRoom(roomNum);
    }

    public List<Service> getAllGuestsServices(Guest guest) {
        return guestService.getAllServices(guest);
    }

    public List<Service> getAllServicesSortedByPrice() {
        return serviceService.getServicesSortByPrice();
    }

    public List<Service> getAllServicesSortedByName() {
        return serviceService.getServicesSortByName();
    }

    public void changeStatusByRoomNumber(Integer roomNum, RoomStatus roomStatusByNum) {
        roomService.changeStatusByRoomNumber(roomNum, roomStatusByNum);
    }

    public void changeRoomPrice(Integer roomNum, Double newPrice) {
        roomService.changePrice(roomNum, newPrice);
    }

    public RoomStatus getRoomStatusByNumber(Integer num) {
        return roomService.getRoomStatusByNumber(num);
    }

    public RoomComfort getComfortByNumber(Integer num) {
        return roomService.getRoomComfortByNumber(num);
    }

    public void saveToFile() {
        SerializationService serializationService = new SerializationService();
        serializationService.serializeToFile(roomService.getAll(), guestService.getAll(), serviceService.getAll());
    }

    public void loadFromFile() {
        SerializationService serializationService = new SerializationService();
        roomService.saveAll(serializationService.deserializeRoomFromFile());
        guestService.saveAll(serializationService.deserializeGuestFromFile());
        serviceService.saveAll(serializationService.deserializeServiceFromFile());
    }
}
