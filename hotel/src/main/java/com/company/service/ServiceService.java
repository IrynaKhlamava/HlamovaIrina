package com.company.service;

import com.company.api.dao.IServiceDao;
import com.company.api.service.IServiceService;
import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;
import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.model.Guest;
import com.company.model.Service;

import org.apache.log4j.Logger;

import java.util.List;


@Component
public class ServiceService implements IServiceService {

    private static final Logger LOGGER = Logger.getLogger(ServiceService.class.getName());

    @Autowired
    private IServiceDao serviceDao;

    public ServiceService() {
    }

    public ServiceService(IServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public Service addService(String name, double price, Guest guest) {
        LOGGER.info(String.format("AddService : %s to guest :%s", name, guest));
        try {
            Service service = new Service(name, price, guest);
            serviceDao.save(service);
            return service;
        } catch (DaoException e) {
            LOGGER.warn("AddService failed", e);
            throw new ServiceException("AddService failed", e);
        }
    }

    @Override
    public List<Service> getAllServicesSortByPrice(Guest guest) {
        LOGGER.info("Services Sorted By Price");
        try {
            return serviceDao.getAllGuestServices(guest.getId());
        } catch (DaoException e) {
            LOGGER.warn("Services Sorted By Price failed", e);
            throw new ServiceException("Services Sorted By Price failed", e);
        }
    }

    @Override
    public List<Service> getServicesSortByName() {
        LOGGER.info("Services Sorted By Name");
        try {
            return serviceDao.getAllSorted("name");
        } catch (DaoException e) {
            LOGGER.warn("Services Sorted By Name failed", e);
            throw new ServiceException("Services Sorted By Name failed", e);
        }
    }

    @Override
    public List<Service> getServicesSortByPrice() {
        LOGGER.info("Services Sorted By Price");
        try {
            return serviceDao.getAllSorted("price");
        } catch (DaoException e) {
            LOGGER.warn("Services Sorted By Price failed", e);
            throw new ServiceException("Services Sorted By Price failed", e);
        }
    }

    public List<Service> getAll() {
        return serviceDao.getAll();
    }

}
