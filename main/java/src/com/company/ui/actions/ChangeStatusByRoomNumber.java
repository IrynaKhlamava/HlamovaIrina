package com.company.ui.actions;

import com.company.exceptions.ServiceException;
import com.company.model.RoomStatus;
import com.company.util.ScannerUtil;

public class ChangeStatusByRoomNumber extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer roomNum = ScannerUtil.readInteger();
        System.out.println(" изменить статус комнаты\n" +
                "0 - готова к заселению\n" +
                "1 - неисправна \n" +
                "2 - ждет уборки\n" +
                "3 - занята \n" +
                "4 - выездная  ");
        Integer roomStatus = ScannerUtil.readInteger();
        try {
            RoomStatus roomStatusByNum = hotelFacade.getRoomStatusByNumber(roomStatus);
            hotelFacade.changeStatusByRoomNumber(roomNum, roomStatusByNum);
        } catch (ServiceException e) {
            System.out.println("Введены некорректные данные. Введите соответствующие данные либо выберите другой пункт меню");
        }
    }
}
