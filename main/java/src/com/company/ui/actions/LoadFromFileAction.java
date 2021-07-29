package com.company.ui.actions;

import com.company.facade.HotelFacade;
import com.company.ui.menu.Builder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadFromFileAction extends AbstractAction {

    private static final Logger LOGGER = Logger.getLogger(LoadFromFileAction.class.getName());

    public LoadFromFileAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if (!Builder.isDataLoaded) {
            hotelFacade.loadFromFile();
            Builder.isDataLoaded = true;
            LOGGER.log(Level.INFO, "Data from file loaded successfully");
        } else {
            LOGGER.log(Level.INFO, String.format("Reloading is not possible. Data from file has already been loaded"));
        }
    }

}
