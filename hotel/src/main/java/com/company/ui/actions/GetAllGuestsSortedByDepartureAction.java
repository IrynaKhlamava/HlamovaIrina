package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAllGuestsSortedByDepartureAction extends AbstractAction {

    public GetAllGuestsSortedByDepartureAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAllGuestsSortedByDeparture().forEach(System.out::println);
    }
}