package com.company.service;

import com.company.api.dao.IGuestDao;

import com.company.api.dao.IRoomDao;
import com.company.api.dao.IServiceDao;
import com.company.api.service.IGuestService;

import com.company.config.annotation.ConfigProperty;
import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;

import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.model.Guest;

import com.company.model.LastGuestsInfo;
import com.company.model.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GuestService implements IGuestService {

    private static final Logger LOGGER = Logger.getLogger(GuestService.class.getName());

    @Autowired
    private IGuestDao guestDao;

    @Autowired
    private IRoomDao roomDao;

    @Autowired
    private IServiceDao serviceDao;

    @ConfigProperty
    private Integer numLastGuestFromProperty;

    public GuestService() {
    }

    @Override
    public Guest addGuest(String name, Integer daysOfStay) {
        try {
            LOGGER.log(Level.INFO, String.format("addGuest by name: %s from on days: %s", name, daysOfStay));
            Guest guest = new Guest(name, daysOfStay);
            guestDao.save(guest);
            return guest;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "addGuest failed", e);
            throw new ServiceException("addGuest failed", e);
        }
    }

    @Override
    public List<Service> getAllServices(Guest guest) {
        return serviceDao.getGuestServices(guest.getId());
    }

    public List<Guest> getAll() {
        return guestDao.getAll();
    }

    @Override
    public List<Guest> sortGuestsByName() {
        try {
            return guestDao.getAllSorted("name");
        } catch (DaoException e) {
            LOGGER.log(Level.INFO, String.format("sort Guests By Name failed"));
            throw new ServiceException(String.format("sort Guests By Name failed"));
        }
    }

    @Override
    public List<Guest> sortGuestsByDeparture() {
        try {
            return guestDao.getAllSorted("date_check_out");
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

    public List<LastGuestsInfo> lastGuestsOfRoom(int roomNumber) {
        LOGGER.log(Level.INFO, "last Guests Of Room");
        try {
            return guestDao.getLastGuestsOfRoom(roomDao.getRoomByNumber(roomNumber).getId(), numLastGuestFromProperty);
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "last Guests Of Room failed", e);
            throw new ServiceException("last Guests Of Room failed", e);
        }
    }

}