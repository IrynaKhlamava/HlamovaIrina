package com.company.ui.actions;

import com.company.model.Guest;
import com.company.util.ScannerUtil;

public class GetAllGuestsServices extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        Guest guest = hotelFacade.getGuest(guestId);
        if (guest != null) {
            hotelFacade.getAllGuestsServices(guest).forEach(System.out::println);
        } else {
            System.out.println("Не удалось получить услуги гостя. Введите другой пункт меню");
        }
    }
}
