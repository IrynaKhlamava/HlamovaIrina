package com.company.api.service;

import com.company.model.AdditionalServices;
import com.company.model.Guest;

import java.util.Date;

public interface IGuestService {

    Guest addGuest(String name, Integer daysOfStay);

    Date calcDateCheckOut(Guest guest);

    void addServices(Guest guest, AdditionalServices addService);

    void getAllAddServices(Guest guest);

    void getAllAddServicesSortByPrice(Guest guest);
}
