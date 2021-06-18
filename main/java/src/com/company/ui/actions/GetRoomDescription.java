package com.company.ui.actions;

import com.company.util.ScannerUtil;


public class GetRoomDescription extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer numRoom = ScannerUtil.readInteger();
        System.out.println(hotelFacade.getByRoomNumber(numRoom));
    }
}
