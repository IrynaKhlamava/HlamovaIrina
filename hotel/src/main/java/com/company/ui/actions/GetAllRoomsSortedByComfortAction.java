package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAllRoomsSortedByComfortAction extends AbstractAction {

    public GetAllRoomsSortedByComfortAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAllRoomsSortedByComfort().forEach(System.out::println);
    }
}

