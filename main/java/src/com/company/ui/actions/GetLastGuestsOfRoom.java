package com.company.ui.actions;

import com.company.util.ScannerUtil;

public class GetLastGuestsOfRoom extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer roomNum = ScannerUtil.readInteger();
        System.out.println(hotelFacade.getLastGuestsOfRoom(roomNum));
    }
}
