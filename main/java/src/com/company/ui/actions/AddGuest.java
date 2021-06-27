package com.company.ui.actions;

import com.company.exceptions.ServiceException;
import com.company.util.ScannerUtil;

public class AddGuest extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите ФИО гостя");
        String name = ScannerUtil.readString();
        System.out.println("введите планируемое колличество дней пребывания");
        Integer daysOfStay = ScannerUtil.readInteger();
        try {
            hotelFacade.saveGuest(name, daysOfStay);
        } catch (ServiceException e) {
            System.out.println("Добавить гостя не удалось. Введите другой пунк меню");
        }
    }
}
