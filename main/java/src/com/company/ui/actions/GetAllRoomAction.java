package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAllRoomAction extends AbstractAction {

    public GetAllRoomAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        hotelFacade.getAllRoom().forEach(System.out::println);
    }
}
