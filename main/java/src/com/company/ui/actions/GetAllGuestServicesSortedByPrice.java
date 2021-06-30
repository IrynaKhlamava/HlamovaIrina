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
        Guest guest = hotelFacade.getGuest(guestId);
        if (guest != null) {
            hotelFacade.getAllGuestServicesSortedByPrice(guest).forEach(System.out::println);
        } else {
            System.out.println("Список услуг гостя отсортировать по цене не удалось. Введите другой пункт меню");
        }
    }
}
