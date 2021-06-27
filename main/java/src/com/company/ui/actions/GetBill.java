package com.company.ui.actions;

import com.company.exceptions.ServiceException;
import com.company.util.ScannerUtil;

public class GetBill extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        try {
            System.out.println(hotelFacade.getBill(hotelFacade.getGuest(guestId)));
        } catch (ServiceException e) {
            System.out.println("Получить счет гостя не удалось. Введите другой пункт меню");
        }
    }
}
