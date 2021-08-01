package com.company.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Guest extends AEntity implements Serializable {

    private String name;
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

    public void setDateCheckOut(LocalDate dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return Objects.equals(name, guest.name) &&
                Objects.equals(daysOfStay, guest.daysOfStay) &&
                Objects.equals(dateCheckIn, guest.dateCheckIn) &&
                Objects.equals(dateCheckOut, guest.dateCheckOut) &&
                Objects.equals(listServices, guest.listServices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, daysOfStay, dateCheckIn, dateCheckOut, listServices);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", id=" + super.getId() +
                ", daysOfStay=" + daysOfStay +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                ", listServices=" + listServices +
                '}';
    }
}
