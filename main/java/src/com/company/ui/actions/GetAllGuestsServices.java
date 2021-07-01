package com.company.ui.actions;

import com.company.model.Guest;
import com.company.util.ScannerUtil;

public class GetAllGuestsServices extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        hotelFacade.getAllGuestsServices(hotelFacade.getGuest(guestId)).forEach(System.out::println);
    }
}
