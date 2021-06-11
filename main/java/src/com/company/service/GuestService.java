package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.service.IGuestService;

import com.company.model.Guest;
import com.company.model.Service;
import com.company.util.IdCreate;

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
    public List<Service> getAllServices(Guest guest) {
        return guest.getListServices();
    }

}
