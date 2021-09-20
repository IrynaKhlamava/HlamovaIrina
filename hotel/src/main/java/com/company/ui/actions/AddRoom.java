package com.company.ui.actions;

import com.company.facade.HotelFacade;

import com.company.util.ScannerUtil;
import org.apache.log4j.Logger;


public class AddRoom extends AbstractAction {

    private static final Logger LOGGER = Logger.getLogger(AddRoom.class.getName());

    public AddRoom(HotelFacade facade) {
        super(facade);
    }

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
        System.out.println("введите комфортность комнаты: 0, 1 или 2 звезды");
        Integer roomComfort = ScannerUtil.readInteger();
        System.out.println("введите цену");
        Double roomPrice = ScannerUtil.readDouble();
       // RoomStatus roomStatusByNum = hotelFacade.getRoomStatusByNumber(roomStatus);
       // RoomComfort roomComfortByNum = hotelFacade.getComfortByNumber(roomComfort);
        if ((roomStatus >= 0 && roomStatus <= 4) && (roomComfort >=0 &&  roomComfort <= 2)) {
            hotelFacade.saveRoom(roomNumber, roomCapacity, roomStatus, roomPrice, roomComfort);
        } else {
            LOGGER.info(String.format("Добавить комнату не удалось"));
        }
    }

}
