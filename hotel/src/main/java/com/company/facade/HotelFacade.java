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
        return roomService.getAll("");
    }

    public List<Room> getAllRoomsSortedByCapacity() {
        return roomService.getAll("capacity");
    }

    public List<Room> getAllRoomsSortedByByPrice() {
        return roomService.getAll("price");
    }

    public List<Room> getAllRoomsSortedByComfort() {
        return roomService.getAll("comfort");
    }

    public void saveGuest(String name, Integer daysOfStay) {
        guestService.addGuest(name, daysOfStay);
    }

    public List<Guest> getAllGuests() {
        return guestService.getAll("");
    }

    public List<Guest> getAllGuestsSortedByName() {
        return guestService.getAll("name");
    }

    public List<Guest> getAllGuestsSortedByDeparture() {
        return guestService.getAll("dateCheckOut");
    }

    public void saveService(String name, Double price, Long guestId) {
        serviceService.addService(name, price, guestId);
    }

    public List<Service> getAllGuestServicesSortedByPrice(Long id, String col) {
        return serviceService.getAllGuestServicesSort(id, col);
    }

    public Guest getGuest(Long guestID) {
        return guestService.getGuest(guestID);
    }

    public Room getByRoomNumber(Integer num) {
        return roomService.getByRoomNumber(num);
    }

    public void checkIn(Long guestId, Long roomId) {
        roomService.checkIn(guestId, roomId);
    }

    public void checkOut(Long guestId, Long roomId) {
        roomService.checkOut(guestId, roomId);
    }

    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms("");
    }

    public Integer getNumberAllAvailableRooms() {
        return roomService.getNumOfAvailableRooms();
    }

    public List<Room> getAvailableRoomsByDate(String byDate) {
        return roomService.getAvailableRoomsByDate(byDate);
    }

    public double getBill(Guest guest) {
        return roomService.getBill(guest.getId());
    }

    public List<Guest> getLastGuestsOfRoom(Integer roomNum) {
        return guestService.lastGuestsOfRoom(roomNum);
    }

    public Set<Service> getAllGuestsServices(Guest guest) {
        return guestService.getAllServices(guest.getId());
    }

    public List<Service> getAll(String name) {
        return serviceService.getAll(name);
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

    public List<Room> getAvailableRoomSortByPrice() {
        return roomService.getAvailableRooms("price");
    }

    public List<Room> getAvailableRoomSortByCapacity() {
        return roomService.getAvailableRooms("capacity");
    }

    public List<Room> getAvailableRoomSortByComfort() {
        return roomService.getAvailableRooms("comfort");
    }
}
