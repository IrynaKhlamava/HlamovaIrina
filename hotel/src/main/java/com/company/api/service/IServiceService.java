package com.company.api.service;

import com.company.model.Service;

import java.util.List;

public interface IServiceService {

    Service addService(String name, double price, Long guestId);

    List<Service> getAllGuestServicesSort(Long id, String col);

    List<Service> getAll(String col);

    Service getById(Long id);

    void delete(Long id);

    void update(Long id, Service updateData);
}


