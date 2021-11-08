package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAvailableRoomsSortedByCapacity extends AbstractAction {

    public GetAvailableRoomsSortedByCapacity(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAvailableRoomSortByCapacity();
    }
}