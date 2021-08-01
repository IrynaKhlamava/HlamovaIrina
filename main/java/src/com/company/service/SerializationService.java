package com.company.service;

import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.Service;
import com.company.util.SerializationHandler;

import java.util.List;

public class SerializationService {

    private List<Guest> guests;
    private List<Room> rooms;
    private List<Service> services;

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void serializeToFile(List<Room> rooms, List<Guest> guests, List<Service> services) {
        SerializationHandler.serialize(rooms, guests, services);
    }

    public List<Guest> deserializeGuestFromFile() {
        setGuests(SerializationHandler.deserialize(Guest.class));
        guests.addAll(guests);
        return guests;
    }

    public List<Room> deserializeRoomFromFile() {
        setRooms(SerializationHandler.deserialize(Room.class));
        return rooms;
    }

    public List<Service> deserializeServiceFromFile() {
        setServices(SerializationHandler.deserialize(Service.class));
        return services;
    }

}
