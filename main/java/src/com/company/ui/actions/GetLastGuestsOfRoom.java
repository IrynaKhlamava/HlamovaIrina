package com.company.ui.actions;

import com.company.util.ScannerUtil;

public class GetLastGuestsOfRoom extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer roomNum = ScannerUtil.readInteger();
        if (hotelFacade.getLastGuestsOfRoom(roomNum).size() > 0) {
            System.out.println(hotelFacade.getLastGuestsOfRoom(roomNum));
        } else {
            System.out.println("Посмотреть последних гостей комнаты не удалось");
        }
    }
}
