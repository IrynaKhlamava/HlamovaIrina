package com.company.ui.actions;

import com.company.facade.HotelFacade;
import com.company.util.ScannerUtil;

public class ChangeRoomPrice extends AbstractAction {

    public ChangeRoomPrice(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer roomNum = ScannerUtil.readInteger();
        System.out.println("введите новую цену");
        Double newPrice = ScannerUtil.readDouble();
        hotelFacade.changeRoomPrice(roomNum, newPrice);
    }

}
