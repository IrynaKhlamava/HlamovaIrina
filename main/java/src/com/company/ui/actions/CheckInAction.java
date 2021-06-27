package com.company.ui.actions;

import com.company.exceptions.ServiceException;
import com.company.model.Guest;
import com.company.model.Room;
import com.company.util.ScannerUtil;

import java.io.BufferedReader;

public class CheckInAction extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        System.out.println("введите номер комнаты");
        Integer numRoom = ScannerUtil.readInteger();
        try {
            Guest guest = hotelFacade.getGuest(guestId);
            Room roomNumber = hotelFacade.getByRoomNumber(numRoom);
            hotelFacade.checkIn(guest, roomNumber);
        } catch (ServiceException e) {
            System.out.println("Заселить гостя в комнату не удалось. Введите другой пункт меню");
        }

    }
}
