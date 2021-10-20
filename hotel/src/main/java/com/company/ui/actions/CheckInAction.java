package com.company.ui.actions;

import com.company.facade.HotelFacade;
import com.company.model.Guest;
import com.company.model.Room;
import com.company.util.ScannerUtil;

public class CheckInAction extends AbstractAction {

    public CheckInAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        System.out.println("введите номер комнаты");
        Integer numRoom = ScannerUtil.readInteger();
        Room roomNumber = hotelFacade.getByRoomNumber(numRoom);
        hotelFacade.checkIn(guestId, roomNumber.getId());
    }

}
