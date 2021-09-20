package com.company.api.dao;

import com.company.model.Service;

import java.util.List;

public interface IServiceDao extends GenericDao<Service> {

    List<Service> getGuestServices(Long id);

    List<Service> getAllGuestServicesSortByPrice(Long id);

    double getBillForGuestForServices(Long id);
}
