package com.company.ui.actions;

import com.company.ui.menu.MenuController;

public class ExitAction extends AbstractAction{

    @Override
    public void execute() {
        hotelFacade.saveToFile();
        MenuController.indexRun = 0;
  }
}
