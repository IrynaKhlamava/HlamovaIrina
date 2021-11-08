package com.company.ui.actions;


import com.company.facade.HotelFacade;

public class GetAllRoomsSortedByPriceAction extends AbstractAction {

    public GetAllRoomsSortedByPriceAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAllRoomsSortedByByPrice();
    }
}
