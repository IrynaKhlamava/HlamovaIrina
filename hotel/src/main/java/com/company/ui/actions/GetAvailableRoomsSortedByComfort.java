package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAvailableRoomsSortedByComfort extends AbstractAction {

    public GetAvailableRoomsSortedByComfort(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAvailableRoomSortByComfort().forEach(System.out::println);
    }
}