package com.company.ui.actions;

import com.company.model.Room;
import com.company.util.ScannerUtil;


public class GetRoomDescription extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer numRoom = ScannerUtil.readInteger();
        Room room = hotelFacade.getByRoomNumber(numRoom);
        if (room != null) {
            System.out.println(room);
        } else {
            System.out.println("Комнаты с таким номером не существует");
        }
    }
}
