package com.company.ui.actions;

import com.company.exceptions.ServiceException;
import com.company.util.ScannerUtil;

public class GetAllGuestsServices extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        try {
            hotelFacade.getAllGuestsServices(hotelFacade.getGuest(guestId)).forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Не удалось получить услуги гостя. Введите другой пункт меню");
        }
    }
}
