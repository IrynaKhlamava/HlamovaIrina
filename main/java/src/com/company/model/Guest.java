package com.company.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class Guest extends AEntity {

    private String name;
    private Long id;
    private Integer daysOfStay;
    private LocalDate dateCheckIn;
    private LocalDate dateCheckOut;
    private List<Service> listServices = new ArrayList<>();

    public Guest(String name, Integer daysOfStay) {
        this.name = name;
        this.daysOfStay = daysOfStay;
    }

    public LocalDate getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(LocalDate dateDateCheckOut) {
        this.dateCheckOut = dateDateCheckOut;
    }

    public List<Service> getListServices() {
        return listServices;
    }

    public void setListServices(List<Service> listServices) {
        this.listServices = listServices;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn() {
        this.dateCheckIn = LocalDate.now();
    }

    public Integer getDaysOfStay() {
        return daysOfStay;
    }

    public void setDaysOfStay(Integer daysOfStay) {
        this.daysOfStay = daysOfStay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return Objects.equals(name, guest.name) &&
                Objects.equals(id, guest.id) &&
                Objects.equals(daysOfStay, guest.daysOfStay) &&
                Objects.equals(dateCheckIn, guest.dateCheckIn) &&
                Objects.equals(dateCheckOut, guest.dateCheckOut) &&
                Objects.equals(listServices, guest.listServices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, daysOfStay, dateCheckIn, dateCheckOut, listServices);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", daysOfStay=" + daysOfStay +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                ", listServices=" + listServices +
                '}';
    }
}
