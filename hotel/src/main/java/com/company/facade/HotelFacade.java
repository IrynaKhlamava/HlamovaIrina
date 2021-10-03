package com.company.facade;

import com.company.api.service.IGuestService;
import com.company.api.service.IRoomService;
import com.company.api.service.IServiceService;

import com.company.model.*;

import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
public class HotelFacade {

    private final IRoomService roomService;

    private final IGuestService guestService;

    private final IServiceService serviceService;

    public HotelFacade(IRoomService roomService, IGuestService guestService, IServiceService serviceService) {
        this.roomService = roomService;
        this.guestService = guestService;
        this.serviceService = serviceService;
    }

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
        return roomService.getNumOfAvailableRooms();
    }

    public List<Room> getFreeRoomsByDate(String byDate) {
        return roomService.getFreeRoomsByDate(byDate);
    }

    public double getBill(Guest guest) {
        return roomService.getBill(guest);
    }

    public List<Guest> getLastGuestsOfRoom(Integer roomNum) {
        return guestService.lastGuestsOfRoom(roomNum);
    }

    public Set<Service> getAllGuestsServices(Guest guest) {
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

    public List<Room> getFreeRoomSortByPrice() {
        return roomService.getFreeRoomSortByPrice();
    }

    public List<Room> getFreeRoomSortByCapacity() {
        return roomService.getFreeRoomSortByCapacity();
    }

    public List<Room> getFreeRoomSortByComfort() {
        return roomService.getFreeRoomSortByComfort();
    }
}
