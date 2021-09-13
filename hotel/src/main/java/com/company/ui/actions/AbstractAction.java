package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class AbstractAction implements IAction {

    protected HotelFacade hotelFacade;

    public AbstractAction(HotelFacade facade) {
        this.hotelFacade = facade;
    }

    @Override
    public void execute() {

    }

}