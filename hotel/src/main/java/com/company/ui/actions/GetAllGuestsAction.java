package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAllGuestsAction extends AbstractAction {

    public GetAllGuestsAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAllGuests();
    }

}
