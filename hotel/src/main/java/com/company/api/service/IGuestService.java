package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Service;

import java.util.List;
import java.util.Set;

public interface IGuestService {

    Guest addGuest(String name, Integer daysOfStay);

    Set<Service> getAllServices(Guest guest);

    List<Guest> sortGuestsByName();

    List<Guest> sortGuestsByDeparture();

    Guest getGuest(Long guestID);

    List<Guest> getAll();

    List<Guest> lastGuestsOfRoom(int roomNumber);

}
