package com.company.ui.actions;

import com.company.exceptions.ServiceException;
import com.company.util.ScannerUtil;

public class GetLastGuestsOfRoom extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer roomNum = ScannerUtil.readInteger();
        try {
            System.out.println(hotelFacade.getLastGuestsOfRoom(roomNum));
        } catch(ServiceException e){
            System.out.println("Посмотреть последних гостей комнаты не удалось. Введите другой пункт меню");
        }
    }
}
