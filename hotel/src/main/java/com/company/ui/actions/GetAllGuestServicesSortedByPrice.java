package com.company.ui.actions;

import com.company.facade.HotelFacade;

import com.company.util.ScannerUtil;

public class GetAllGuestServicesSortedByPrice extends AbstractAction {

    public GetAllGuestServicesSortedByPrice(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        hotelFacade.getAllGuestServicesSortedByPrice(guestId, "price");
    }

}
