package com.company.service;

import com.company.api.dao.IServiceDao;
import com.company.api.service.IServiceService;
import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;
import com.company.filter.SortServicesByName;
import com.company.filter.SortServicesByPrice;
import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.model.Guest;
import com.company.model.Service;
import com.company.util.IdCreate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.company.util.FilteredListSorted.getFilteredListSorted;

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
        LOGGER.log(Level.INFO, String.format("AddService : %s to guest :%s", name, guest));
        try {
            Service service = new Service(name, price);
            service.setId(IdCreate.createServiceId());
            serviceDao.save(service);
            guest.getListServices().add(service);
            return service;
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "AddService failed", e);
            throw new ServiceException("AddService failed", e);
        }
    }

    @Override
    public List<Service> getAllServicesSortByPrice(Guest guest) {
        LOGGER.log(Level.INFO, "Services Sorted By Price");
        try {
            return getFilteredListSorted(guest.getListServices(), new SortServicesByPrice());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "Services Sorted By Price failed", e);
            throw new ServiceException("Services Sorted By Price failed", e);
        }
    }

    @Override
    public List<Service> getServicesSortByName() {
        LOGGER.log(Level.INFO, "Services Sorted By Name");
        try {
            return getFilteredListSorted(serviceDao.getAll(), new SortServicesByName());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "Services Sorted By Name failed", e);
            throw new ServiceException("Services Sorted By Name failed", e);
        }
    }

    @Override
    public List<Service> getServicesSortByPrice() {
        LOGGER.log(Level.INFO, "Services Sorted By Price");
        try {
            return getFilteredListSorted(serviceDao.getAll(), new SortServicesByPrice());
        } catch (DaoException e) {
            LOGGER.log(Level.WARNING, "Services Sorted By Price failed", e);
            throw new ServiceException("Services Sorted By Price failed", e);
        }
    }

    public List<Service> getAll() {
        return serviceDao.getAll();
    }
}
