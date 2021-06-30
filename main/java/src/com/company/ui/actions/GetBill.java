package com.company.ui.actions;

import com.company.model.Guest;
import com.company.util.ScannerUtil;

public class GetBill extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        Guest guest = hotelFacade.getGuest(guestId);
        if (guest != null) {
            System.out.println(hotelFacade.getBill(guest));
        } else {
            System.out.println("Получить счет гостя не удалось. Введите другой пункт меню");
        }
    }
}
