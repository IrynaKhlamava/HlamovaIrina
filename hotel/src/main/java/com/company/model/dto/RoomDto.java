package com.company.model.dto;

import com.company.model.RoomComfort;
import com.company.model.RoomStatus;

import java.util.List;

public class RoomDto {

    private Long id;
    private Integer number;
    private Integer capacity;
    private RoomStatus roomStatus;
    private Double priceRoom;
    private RoomComfort comfort;
    private List<GuestDto> guests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<GuestDto> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestDto> guests) {
        this.guests = guests;
    }
}
