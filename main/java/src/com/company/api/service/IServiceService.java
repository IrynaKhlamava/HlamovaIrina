package com.company.api.service;

import com.company.model.Guest;
import com.company.model.RoomStatus;
import com.company.model.Service;

import java.util.List;

public interface IServiceService {

    Service addService(String name, double price, Guest guest);

    List<Service> getAllServicesSortByPrice(Guest guest);

    List<Service> getServicesSortByName();

    List<Service> getServicesSortByPrice();

}


