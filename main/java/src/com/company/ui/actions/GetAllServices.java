package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAllServices extends AbstractAction {

    public GetAllServices(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAllServices().forEach(System.out::println);
    }
}
