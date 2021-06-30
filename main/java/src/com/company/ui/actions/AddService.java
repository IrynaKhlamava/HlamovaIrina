package com.company.ui.actions;

import com.company.model.Guest;
import com.company.util.ScannerUtil;

public class AddService extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите название услуги");
        String name = ScannerUtil.readString();
        System.out.println("введите цену услуги");
        Double price = ScannerUtil.readDouble();
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        Guest guest = hotelFacade.getGuest(guestId);
        if (guest != null) {
            hotelFacade.saveService(name, price, guest);
        } else {
            System.out.println("Нет такого гостя. Добавить услугу не удалось. Введите другой пунк меню");
        }
    }
}