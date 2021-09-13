package com.company.ui.actions;

import com.company.facade.HotelFacade;
import com.company.ui.menu.MenuController;

public class ExitAction extends AbstractAction {

    public ExitAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        MenuController.isRunning = false;
    }

}
