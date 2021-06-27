package com.company.ui.actions;

import com.company.exceptions.ServiceException;
import com.company.model.Guest;
import com.company.util.ScannerUtil;

import java.util.List;

public class GetAllGuestServicesSortedByPrice extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        try {
            hotelFacade.getAllGuestServicesSortedByPrice(hotelFacade.getGuest(guestId)).forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Список услуг гостя отсортировать по цене не удалось. Введите другой пунк меню");
        }
    }
}
