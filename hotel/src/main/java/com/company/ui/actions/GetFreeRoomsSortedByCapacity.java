package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetFreeRoomsSortedByCapacity extends AbstractAction {

    public GetFreeRoomsSortedByCapacity(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getFreeRoomSortByCapacity().forEach(System.out::println);
    }
}