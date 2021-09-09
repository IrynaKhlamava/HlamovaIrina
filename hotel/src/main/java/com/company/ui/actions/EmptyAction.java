package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class EmptyAction extends AbstractAction {

    public EmptyAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {

    }

}
