package com.company.api.dao;

import com.company.model.Guest;
import com.company.model.LastGuestsInfo;

import java.util.List;

public interface IGuestDao extends GenericDao<Guest> {

    int getCountGuestsInRoomById(Long id);

    List<LastGuestsInfo> getLastGuestsOfRoom(Long roomId, Integer numLastGuestFromProperty);
}
