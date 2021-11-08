package com.company.api.dao;

import com.company.model.Service;

import java.util.List;

public interface IServiceDao extends GenericDao<Service> {

    List<Service> getAllGuestServicesSort(Long id, String col);

    Double getBillForGuestForServices(Long id);

    void update(Long id, Service updateData);

}
