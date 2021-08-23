package com.company.service;

import com.company.api.dao.IGuestDao;

import com.company.api.service.IGuestService;

import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;
import com.company.filter.SortByDeparture;
import com.company.filter.SortGuestsByName;
import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.model.Guest;

import com.company.model.Service;
import com.company.util.IdCreate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GuestService implements IGuestService {

    private static final Logger LOGGER = Logger.getLogger(GuestService.class.getName());

    @Autowired
    private IGuestDao guestDao;

    public GuestService() {
    }

    public GuestService(IGuestDao guestDao) {
        this.guestDao = guestDao;
    }

    @Override
    public Guest addGuest(String name, Integer daysOfStay) {
        try {
            LOGGER.log(Level.INFO, String.format("addGuest by name: %s from on days: %s", name, daysOfStay));
            Guest guest = new Guest(name, daysOfStay);
           // guest.setId(IdCreate.createGuestId());
            guestDao.save(guest);
            return guest;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "addGuest failed", e);
            throw new ServiceException("addGuest failed", e);
        }
    }

    @Override
    public List<Service> getAllServices(Guest guest) {
        return guest.getListServices();
    }

    public List<Guest> getAll() {
        return guestDao.getAll();
    }

    @Override
    public void saveAll(List<Guest> guests) {
        guestDao.saveAll(guests);
    }

    @Override
    public List<Guest> sortGuestsByName() {
        try {
            return guestDao.getAllSorted(new SortGuestsByName());
        } catch (DaoException e) {
            LOGGER.log(Level.INFO, String.format("sort Guests By Name failed"));
            throw new ServiceException(String.format("sort Guests By Name failed"));
        }
    }

    @Override
    public List<Guest> sortGuestsByDeparture() {
        try {
            return guestDao.getAllSorted(new SortByDeparture());
        } catch (DaoException e) {
            LOGGER.log(Level.INFO, String.format("sort Guests By Departure failed"));
            throw new ServiceException(String.format("sort Guests By Departure failed"));
        }
    }

    public void save(Guest guest) {
        guestDao.save(guest);
    }

    public Guest getGuest(Long guestID) {
        return guestDao.getById(guestID);
    }

}