package com.company.service;

import com.company.api.dao.IServiceDao;
import com.company.api.service.IServiceService;
import com.company.filter.SortServicesByPrice;
import com.company.model.Guest;
import com.company.model.Service;
import com.company.util.IdCreate;

import java.util.List;

public class ServiceService implements IServiceService {

    private final IServiceDao serviceDao;

    public ServiceService(IServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public Service addService(String name, double price, Guest guest) {
        Service service = new Service(name, price);
        service.setId(IdCreate.createServiceId());
        serviceDao.save(service);
        guest.getListServices().add(service);
        return service;
    }

    @Override
    public List<Service> getAllServicesSortByPrice(Guest guest) {
        return serviceDao.getFilteredListSorted(guest.getListServices(), new SortServicesByPrice());
    }

    public List<Service> getAll() {
        List<Service> services = serviceDao.getAll();
        return services;
    }

}
