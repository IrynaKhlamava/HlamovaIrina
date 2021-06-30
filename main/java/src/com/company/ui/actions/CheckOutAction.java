package com.company.ui.actions;

import com.company.model.Guest;
import com.company.model.Room;
import com.company.util.ScannerUtil;

public class CheckOutAction extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        System.out.println("введите номер комнаты");
        Integer numRoom = ScannerUtil.readInteger();
        Guest guest = hotelFacade.getGuest(guestId);
        Room roomNumber = hotelFacade.getByRoomNumber(numRoom);
        if ((guest != null) && (roomNumber != null)) {
            hotelFacade.checkOut(guest, roomNumber);
        } else {
            System.out.println("Выселить гостя из комнаты не удалось. Введите другой пункт меню");
        }
    }
}
