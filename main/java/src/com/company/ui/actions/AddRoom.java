package com.company.ui.actions;

import com.company.model.Room;
import com.company.model.RoomComfort;
import com.company.model.RoomStatus;
import com.company.util.ScannerUtil;

public class AddRoom extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        Integer roomNumber = ScannerUtil.readInteger();
        System.out.println("введите вместимость комнаты");
        Integer roomCapacity = ScannerUtil.readInteger();
        System.out.println("введите статус комнаты");
        System.out.println("1 - готова к заселению\n" +
                "2 - неисправна \n" +
                "3 - ждет уборки\n" +
                "4 - занята \n" +
                "5 - выездная  ");
        Integer roomStatus = ScannerUtil.readInteger();
        System.out.println("введите комфортность комнаты: 3, 4 или 5 звезд");
        Integer roomComfort = ScannerUtil.readInteger();
        System.out.println("введите цену");
        Double roomPrice = ScannerUtil.readDouble();
        hotelFacade.saveRoom(new Room(roomNumber, roomCapacity, getRoomStatusByNum(roomStatus), roomPrice, getRoomComfortByNum(roomComfort)));
    }

    private RoomComfort getRoomComfortByNum(Integer num) {
        for (RoomComfort roomComfort : RoomComfort.values()) {
            if (num == roomComfort.getValue())
                return roomComfort;
        }
        return null;
    }

    private RoomStatus getRoomStatusByNum(Integer num) {
        for (RoomStatus roomStatus : RoomStatus.values()) {
            if (num == roomStatus.getValue())
                return roomStatus;
        }
        return null;
    }
}
