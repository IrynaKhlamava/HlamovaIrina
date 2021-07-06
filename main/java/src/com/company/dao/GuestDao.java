package com.company.dao;

import com.company.model.Guest;
import com.company.api.dao.IGuestDao;
import com.company.util.IdCreate;
import com.company.util.SerializationHandler;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    private static final Logger LOGGER = Logger.getLogger(GuestDao.class.getName());

    private static GuestDao INSTANCE;

    private GuestDao() {
    }

    public static GuestDao getGuestDao() {
        if (INSTANCE == null) {
            INSTANCE = new GuestDao();
            INSTANCE.init();
        }
        return INSTANCE;
    }

    private void init() {
        List<Guest> guests = SerializationHandler.deserialize(Guest.class);
        this.saveAll(guests);
        IdCreate.setGuestId(guests.size()+1L);
    }

    @Override
    public Guest update(Guest entity) {
        Guest guest = getById(entity.getId());
        LOGGER.log(Level.INFO, String.format("Update Guest"));
        guest.setName(entity.getName());
        guest.setDaysOfStay(entity.getDaysOfStay());
        return guest;

    }
}
