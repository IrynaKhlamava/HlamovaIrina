package com.company.ui.actions;

import com.company.model.Guest;
import com.company.model.Room;
import com.company.util.ScannerUtil;

public class CheckInAction extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        System.out.println("введите номер комнаты");
        Integer numRoom = ScannerUtil.readInteger();
        Guest guest = hotelFacade.getGuest(guestId);
        Room roomNumber = hotelFacade.getByRoomNumber(numRoom);
        hotelFacade.checkIn(guest, roomNumber);
        if ((guest != null) && (roomNumber != null)) {
            hotelFacade.checkIn(guest, roomNumber);
        } else {
            System.out.println("Заселить гостя в комнату не удалось. Введите другой пункт меню");
        }
    }
}
