package com.company.dao;

import com.company.model.Guest;
import com.company.api.dao.IGuestDao;

public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    @Override
    public Guest update(Guest entity) {
        Guest guest = getById(entity.getId());
        guest.setName(entity.getName());
        guest.setDaysOfStay(entity.getDaysOfStay());
        return guest;
    }
}
