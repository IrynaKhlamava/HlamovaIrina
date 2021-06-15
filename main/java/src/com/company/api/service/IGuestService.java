package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IGuestService {

    Guest addGuest(String name, Integer daysOfStay);

    List<Service> getAllServices(Guest guest);

    List<Guest> sortGuestsByName();

    List<Guest> sortGuestsByDeparture();


}
