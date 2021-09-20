package com.company.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "guests")
public class Guest extends AEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "days_of_stay")
    private Integer daysOfStay;
    @Column(name = "date_check_in")
    private LocalDate dateCheckIn;
    @Column(name = "date_check_out")
    private LocalDate dateCheckOut;
    @Column(name = "rooms_id")
    private Long roomId;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "guest_services",
            joinColumns = @JoinColumn(name = "guest_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> services;

    public Guest() {
    }

    public Guest(String name, Integer daysOfStay) {
        this.name = name;
        this.daysOfStay = daysOfStay;
    }

    public LocalDate getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(LocalDate dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(LocalDate dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public Integer getDaysOfStay() {
        return daysOfStay;
    }

    public void setDaysOfStay(Integer daysOfStay) {
        this.daysOfStay = daysOfStay;
    }


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return Objects.equals(name, guest.name) &&
                Objects.equals(daysOfStay, guest.daysOfStay) &&
                Objects.equals(dateCheckIn, guest.dateCheckIn) &&
                Objects.equals(dateCheckOut, guest.dateCheckOut) &&
                Objects.equals(services, guest.services);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, daysOfStay, dateCheckIn, dateCheckOut, services);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", id=" + super.getId() +
                ", daysOfStay=" + daysOfStay +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                ", listServices=" + services +
                '}';
    }
}
