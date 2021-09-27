package com.company.ui.actions;


import com.company.facade.HotelFacade;

public class GetFreeRoomsSortedByPrice extends AbstractAction {

    public GetFreeRoomsSortedByPrice(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getFreeRoomSortByPrice().forEach(System.out::println);
    }
}
