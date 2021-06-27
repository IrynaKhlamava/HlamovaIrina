package com.company.dao;

import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;
import com.company.model.Guest;
import com.company.api.dao.IGuestDao;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    private static final Logger LOGGER = Logger.getLogger(GuestDao.class.getName());


    @Override
    public Guest update(Guest entity) {
        try {
            LOGGER.log(Level.INFO, String.format("Update Guest"));
            Guest guest = getById(entity.getId());
            guest.setName(entity.getName());
            guest.setDaysOfStay(entity.getDaysOfStay());
            return guest;
        } catch (
                DaoException e) {
            LOGGER.log(Level.WARNING, "Update guest failed", e);
            throw new ServiceException("Update guest failed", e);
        }
    }
}
