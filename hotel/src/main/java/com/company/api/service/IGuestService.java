package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Service;


import java.util.List;
import java.util.Set;

public interface IGuestService {

    Guest addGuest(String name, Integer daysOfStay);

    Set<Service> getAllServices(Long guestID);

    List<Guest> getAll(String col);

    List<Guest> lastGuestsOfRoom(int roomNumber);

    Guest getGuest(Long guestID);

    Guest getById(Long id);

    void delete(Long id);

    void update(Long id, Guest updateData);
}
