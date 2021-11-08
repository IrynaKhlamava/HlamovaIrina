package com.company.ui.actions;

import com.company.facade.HotelFacade;
import com.company.util.ScannerUtil;

public class AddGuest extends AbstractAction {

    public AddGuest(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        String name = ScannerUtil.readString();
        Integer daysOfStay = ScannerUtil.readInteger();
        hotelFacade.saveGuest(name, daysOfStay);
    }

}
