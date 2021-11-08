package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAllServicesSortedByPrice extends AbstractAction {

    public GetAllServicesSortedByPrice(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAll("price");
    }
}
