package com.company.ui.actions;

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
        System.out.println("0 - готова к заселению\n" +
                "1 - неисправна \n" +
                "2 - ждет уборки\n" +
                "3- занята \n" +
                "4 - выездная  ");
        Integer roomStatus = ScannerUtil.readInteger();
        System.out.println("введите комфортность комнаты: 3, 4 или 5 звезд");
        Integer roomComfort = ScannerUtil.readInteger();
        System.out.println("введите цену");
        Double roomPrice = ScannerUtil.readDouble();
        RoomStatus roomStatusByNum = hotelFacade.getRoomStatusByNumber(roomStatus);
        RoomComfort roomComfortByNum = hotelFacade.getComfortByNumber(roomComfort);
        if ((roomStatusByNum != null) && (roomComfortByNum != null)) {
            hotelFacade.saveRoom(roomNumber, roomCapacity, roomStatusByNum, roomPrice, roomComfortByNum);
        } else {
            System.out.println("Добавить комнату не удалось. Введите корректные данные или выберите другой пункт меню");
        }
    }
}
