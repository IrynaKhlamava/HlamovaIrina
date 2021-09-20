package com.company.api.dao;

import com.company.model.Guest;

import com.company.model.Service;

import java.util.List;

public interface IGuestDao extends GenericDao<Guest> {

    int getCountGuestsInRoomById(Long id);

    List<Guest> getLastGuestsOfRoom(Long roomId, Integer numLastGuestFromProperty);

    void addService(Service service, Guest guest);
}
