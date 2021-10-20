package com.company.ui.actions;


import com.company.facade.HotelFacade;

public class GetAvailableRoomsSortedByPrice extends AbstractAction {

    public GetAvailableRoomsSortedByPrice(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAvailableRoomSortByPrice().forEach(System.out::println);
    }
}
