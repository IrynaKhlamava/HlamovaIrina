package com.company.ui.actions;

import com.company.util.ScannerUtil;

public class GetBill extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        System.out.println(hotelFacade.getBill(hotelFacade.getGuest(guestId)));

    }
}
