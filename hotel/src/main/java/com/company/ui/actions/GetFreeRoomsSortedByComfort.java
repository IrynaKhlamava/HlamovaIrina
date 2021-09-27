package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetFreeRoomsSortedByComfort extends AbstractAction {

    public GetFreeRoomsSortedByComfort(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getFreeRoomSortByComfort().forEach(System.out::println);
    }
}