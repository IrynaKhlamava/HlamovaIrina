package com.company.ui.actions;

import com.company.exceptions.ServiceException;
import com.company.util.ScannerUtil;

public class ChangeRoomPrice extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer roomNum = ScannerUtil.readInteger();
        System.out.println("введите новую цену");
        Double newPrice = ScannerUtil.readDouble();
        try {
            hotelFacade.changeRoomPrice(roomNum, newPrice);
        } catch (ServiceException e) {
            System.out.println("Изменить цену комнаты не удалось. Введите другой пунк меню");
        }
    }
}
