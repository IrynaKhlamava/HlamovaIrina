package com.company.api.dao;

import com.company.model.Guest;

import com.company.model.Service;

import java.util.List;
import java.util.Set;

public interface IGuestDao extends GenericDao<Guest> {

    int getCountGuestsInRoomById(Long id);

    List<Guest> getLastGuestsOfRoom(Integer roomNumber, Integer numLastGuestFromProperty);

    Set<Service> getGuestServices(Long guestId);

    void update(Long id, Guest updateData);
}
