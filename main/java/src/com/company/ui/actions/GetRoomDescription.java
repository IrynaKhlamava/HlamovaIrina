package com.company.ui.actions;

import com.company.exceptions.ServiceException;
import com.company.util.ScannerUtil;


public class GetRoomDescription extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer numRoom = ScannerUtil.readInteger();
        try {
            System.out.println(hotelFacade.getByRoomNumber(numRoom));
        } catch (ServiceException e) {
            System.out.println("Посмотреть описание комнаты не удалось. Введите другой пункт меню");
        }
    }
}
