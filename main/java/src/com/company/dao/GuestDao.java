package com.company.dao;

import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.model.Guest;
import com.company.api.dao.IGuestDao;
import com.company.service.SerializationService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    private static final Logger LOGGER = Logger.getLogger(GuestDao.class.getName());

    @Override
    public Guest update(Guest entity) {
        Guest guest = getById(entity.getId());
        LOGGER.log(Level.INFO, String.format("Update Guest"));
        guest.setName(entity.getName());
        guest.setDaysOfStay(entity.getDaysOfStay());
        return guest;

    }
}
