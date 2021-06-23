package com.company.ui.actions;

import com.company.facade.HotelFacade;

public abstract class AbstractAction implements IAction {

    protected HotelFacade hotelFacade = HotelFacade.getINSTANCE();

}
