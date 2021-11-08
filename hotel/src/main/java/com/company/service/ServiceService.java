package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IServiceDao;
import com.company.api.service.IServiceService;
import com.company.exceptions.DaoException;
import com.company.exceptions.ServiceException;
import com.company.model.Service;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@org.springframework.stereotype.Service
@Transactional
public class ServiceService implements IServiceService {

    private static final Logger LOGGER = Logger.getLogger(ServiceService.class.getName());

    private final IServiceDao serviceDao;

    private final IGuestDao guestDao;

    private final ModelMapper mapper;

    public ServiceService(IServiceDao serviceDao, IGuestDao guestDao, ModelMapper mapper) {
        this.serviceDao = serviceDao;
        this.guestDao = guestDao;
        this.mapper = mapper;
    }

    @Override
    public Service addService(String name, double price, Long guestId) {
        LOGGER.info(String.format("AddService : %s to guest :%s", name, guestId));
        try {
            Service service = new Service(name, price, guestDao.getById(guestId));
            serviceDao.save(service);
            return service;
        } catch (DaoException e) {
            LOGGER.warn("AddService failed", e);
            throw new ServiceException("AddService failed", e);
        }
    }

    public List<Service> getAll(String col) {
        LOGGER.info("Services sorted By " + col);
        try {
        return serviceDao.getAll(col);
        } catch (DaoException e) {
            LOGGER.warn("getAll services Sorted failed", e);
            throw new ServiceException("Services Sorted failed", e);
        }
    }

    public List<Service> getAllGuestServicesSort(Long id, String col) {
        LOGGER.info("All guest's services sorted by " + col);
        try {
            return serviceDao.getAllGuestServicesSort(id, col);
        } catch (DaoException e) {
            LOGGER.warn("getAll Guest's services sorted failed", e);
            throw new ServiceException("getAll guest's services sorted failed", e);
        }
    }

    @Override
    public Service getById(Long id) {
        return serviceDao.getById(id);
    }

    @Override
    public void delete(Long id) {
        serviceDao.delete(getById(id));
    }

    @Override
    public void update(Long id, Service updateData) {
        serviceDao.update(id, updateData);
    }

}
