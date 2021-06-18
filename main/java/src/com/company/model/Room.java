package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room extends AEntity {
    private Integer number;
    private Integer capacity;
    private RoomStatus roomStatus;
    private Double priceRoom;
    private RoomComfort comfort;
    private List<Guest> guests;

    public Room(Integer number, Integer capacity, RoomStatus roomStatus, Double priceRoom, RoomComfort comfort) {
        this.number = number;
        this.capacity = capacity;
        this.roomStatus = roomStatus;
        this.priceRoom = priceRoom;
        this.comfort = comfort;
        this.guests = new ArrayList();

    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public Double getPriceRoom() {
        return priceRoom;
    }

    public void setPriceRoom(Double priceRoom) {
        this.priceRoom = priceRoom;
    }

    public RoomComfort getComfort() {
        return comfort;
    }

    public void setComfort(RoomComfort comfort) {
        this.comfort = comfort;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", capacity=" + capacity +
                ", roomStatus=" + roomStatus +
                ", priceRoom=" + priceRoom +
                ", comfort=" + comfort +
                ", guests=" + guests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(number, room.number) &&
                Objects.equals(capacity, room.capacity) &&
                roomStatus == room.roomStatus &&
                Objects.equals(priceRoom, room.priceRoom) &&
                comfort == room.comfort &&
                Objects.equals(guests, room.guests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, capacity, roomStatus, priceRoom, comfort, guests);
    }
}
