package com.company.ui.actions;

import com.company.model.Room;
import com.company.model.RoomComfort;
import com.company.model.RoomStatus;

import java.util.Scanner;

public class AddRoom extends AbstractAction{

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите номер комнаты");
        Integer roomNumber = scanner.nextInt();
        System.out.println("введите вместимость комнаты");
        Integer roomCapacity = scanner.nextInt();
        //System.out.println("введите статус комнаты");
        //Как сделать чтобы статус выбрать из енама?
        RoomStatus roomStatus = RoomStatus.CLEAN ;
        System.out.println("введите комфортность комнаты: 3, 4 или 5 звезд");
        Integer roomComfort = scanner.nextInt();
        System.out.println("введите цену");
        Double roomPrice = scanner.nextDouble();
        hotelFacade.saveRoom(new Room(roomNumber, roomCapacity, roomStatus, roomPrice, roomComfort));
    }
}
