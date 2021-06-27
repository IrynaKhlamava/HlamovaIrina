package com.company.service;

import com.company.api.dao.IGuestDao;

import com.company.api.service.IGuestService;

import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;
import com.company.filter.SortByDeparture;
import com.company.filter.SortGuestsByName;
import com.company.model.Guest;

import com.company.model.Service;
import com.company.util.IdCreate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuestService implements IGuestService {

    private static final String GET_BY_ID_ERROR_MESSAGE = "could not find guest by id:";
    private static final Logger LOGGER = Logger.getLogger(GuestService.class.getName());

    private final IGuestDao guestDao;

    public GuestService(IGuestDao guestDao) {
        this.guestDao = guestDao;
    }

    @Override
    public Guest addGuest(String name, Integer daysOfStay) {
        try {
            LOGGER.log(Level.INFO, String.format("addGuest by name: %s from on days: %s", name, daysOfStay));
            Guest guest = new Guest(name, daysOfStay);
            guest.setId(IdCreate.createGuestId());
            guestDao.save(guest);
            return guest;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "addGuest failed", e);
            throw new ServiceException("addGuest failed", e);
        }
    }

    @Override
    public List<Service> getAllServices(Guest guest) {
        try {
            return guest.getListServices();
        } catch (DaoException e) {
            LOGGER.log(Level.INFO, String.format("get All Services of guest failed"));
            throw new ServiceException(String.format("get All Services of guest failed"));
        }
    }

    public List<Guest> getAll() {
        try {
            if (guestDao.getAll().size() > 0) {
                return guestDao.getAll();
            }
        } catch (DaoException e) {
            LOGGER.log(Level.INFO, String.format("list of guests is empty"));
            throw new ServiceException(String.format("list of guests is empty"));
        }
        return guestDao.getAll();
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
//        try {
//        List<Guest> guests = guestDao.getAll();
//        return guests.stream()
//                .filter(g -> g.getId().equals(guestID))
//                .findFirst();
//        } catch (DaoException e) {
//            LOGGER.log(Level.WARNING, GET_BY_ID_ERROR_MESSAGE, guestID);
//            throw new ServiceException(String.format(GET_BY_ID_ERROR_MESSAGE, guestID));
//        }
        try {
            LOGGER.log(Level.INFO, String.format("get Guest By ID: %s ", guestID));
            for (Guest guestRoom : guestDao.getAll()) {
                if (guestRoom.getId().equals(guestID)) {
                    return guestRoom;
                }
            }
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, GET_BY_ID_ERROR_MESSAGE, guestID);
            throw new ServiceException(String.format(GET_BY_ID_ERROR_MESSAGE, guestID));
        }
        return null;
    }

}

