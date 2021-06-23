package com.company.ui.actions;

import com.company.model.RoomStatus;
import com.company.util.ScannerUtil;

public class ChangeRoomStatus extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer roomNum = ScannerUtil.readInteger();
        System.out.println(" изменить статус комнаты\n" +
                "1 - готова к заселению\n" +
                "2 - неисправна \n" +
                "3 - ждет уборки\n" +
                "4 - занята \n" +
                "5 - выездная  ");
        Integer roomStatus = ScannerUtil.readInteger();
        hotelFacade.changeRoomStatus(roomNum, getRoomStatusByNum(roomStatus));
    }

    private RoomStatus getRoomStatusByNum(Integer num) {
        for (RoomStatus roomStatus : RoomStatus.values()) {
            if (num == roomStatus.getValue())
                return roomStatus;
        }
        return null;
    }
}
