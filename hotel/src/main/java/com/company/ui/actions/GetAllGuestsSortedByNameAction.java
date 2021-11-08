package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAllGuestsSortedByNameAction extends AbstractAction {

    public GetAllGuestsSortedByNameAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAllGuestsSortedByName();
    }
}
