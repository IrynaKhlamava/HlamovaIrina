package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.service.IGuestService;
import com.company.filter.SortAddServicesByPrice;
import com.company.model.AdditionalServices;
import com.company.model.Guest;
import com.company.util.IdCreate;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class GuestService implements IGuestService {

    private final IGuestDao guestDao;

    public GuestService(IGuestDao guestDao) {
        this.guestDao = guestDao;
    }

    @Override
    public Guest addGuest(String name, Integer daysOfStay) {
        Guest guest = new Guest(name, daysOfStay);
        guest.setId(IdCreate.createGuestId());
        guestDao.save(guest);
        return guest;
    }

    @Override
    public Date calcDateCheckOut(Guest guest) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(guest.getDateCheckIn());
        instance.add(Calendar.DAY_OF_MONTH, guest.getDaysOfStay());
        return instance.getTime();
    }

    @Override
    public void addServices(Guest guest, AdditionalServices addService) {
        guest.setAddServices(addService.getPrice());
        guest.getListAddServices().add(addService);
    }

    @Override
    public void getAllAddServices(Guest guest) {
        List<AdditionalServices> listAdd = guest.getListAddServices();
        for (AdditionalServices addServ : listAdd) {
            System.out.println("Additional Service name " + addServ.getName() + " and price " + addServ.getPrice());
        }
    }

    @Override
    public void getAllAddServicesSortByPrice(Guest guest) {
        Comparator<AdditionalServices> sortAddServByPrice = new SortAddServicesByPrice();
        List<AdditionalServices> listAddServSort = guest.getListAddServices();
        listAddServSort.sort(sortAddServByPrice);
        for (AdditionalServices addServ : listAddServSort) {
            System.out.println("Additional Service name " + addServ.getName() + " and price " + addServ.getPrice());
        }
    }

}
