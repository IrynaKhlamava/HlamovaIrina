package com.company.ui.actions;

import com.company.util.ScannerUtil;

public class CheckInAction extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        System.out.println("введите номер комнаты");
        Integer numRoom = ScannerUtil.readInteger();
        hotelFacade.checkIn(hotelFacade.getGuest(guestId), hotelFacade.getByRoomNumber(numRoom));

    }
}
