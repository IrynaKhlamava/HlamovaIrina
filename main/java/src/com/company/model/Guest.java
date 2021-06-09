package com.company.model;

import java.util.*;


public class Guest extends AEntity {

    private String name;
    private Long id;
    private Integer daysOfStay;
    private Date dateCheckIn;
    private Date dateCheckOut;
    private double addServices;
    private List<AdditionalServices> listAddServices = new ArrayList<>();

    public Guest(String name, Integer daysOfStay) {
        this.name = name;
        this.daysOfStay = daysOfStay;
    }

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateDateCheckOut) {
        this.dateCheckOut = dateDateCheckOut;
    }

    public List<AdditionalServices> getListAddServices() {
        return listAddServices;
    }

    public void setListAddServices(List<AdditionalServices> listAddServices) {
        this.listAddServices = listAddServices;
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

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn() {
        this.dateCheckIn = new Date();
    }

    public Integer getDaysOfStay() {
        return daysOfStay;
    }

    public void setDaysOfStay(Integer daysOfStay) {
        this.daysOfStay = daysOfStay;
    }

       /* public void calcDateCheckOut(Guest guest) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(guest.getDateCheckIn()); //устанавливаем дату, с которой будет производить операции
            instance.add(Calendar.DAY_OF_MONTH, guest.getDaysOfStay());// прибавляем 3 дня к установленной дате
            Date dateDateCheckOut = instance.getTime(); // получаем изменен
            guest.setDateCheckOut(dateDateCheckOut);
        }

        */


    public double getAddServices() {
        return addServices;
    }

    public void setAddServices(double addServices) {
        this.addServices += addServices;
    }


    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", daysOfStay=" + daysOfStay +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                ", addServices=" + addServices +
                '}';
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
                Objects.equals(dateCheckOut, guest.dateCheckOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, daysOfStay, dateCheckIn, dateCheckOut);
    }


}
