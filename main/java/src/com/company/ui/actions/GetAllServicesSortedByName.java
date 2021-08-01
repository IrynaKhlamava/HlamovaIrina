package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAllServicesSortedByName extends AbstractAction {

    public GetAllServicesSortedByName(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAllServicesSortedByName().forEach(System.out::println);
    }
}
