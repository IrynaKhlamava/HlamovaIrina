package com.company.dao;

import com.company.api.dao.IServiceDao;
import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;
import com.company.model.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceDao extends AbstractDao<Service> implements IServiceDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());

    @Override
    public Service update(Service entity) {
        try {
            LOGGER.log(Level.INFO, String.format("Update Service"));
            Service service = getById(entity.getId());
            service.setName(entity.getName());
            service.setPrice(entity.getPrice());
            return service;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "Update Service failed", e);
            throw new ServiceException("Update Service failed", e);
        }
    }
}
