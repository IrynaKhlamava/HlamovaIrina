package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAllRoomsSortedByCapacityAction extends AbstractAction {

    public GetAllRoomsSortedByCapacityAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAllRoomsSortedByCapacity();
    }
}

