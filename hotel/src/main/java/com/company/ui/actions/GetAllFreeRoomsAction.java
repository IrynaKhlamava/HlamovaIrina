package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAllFreeRoomsAction extends AbstractAction {

    public GetAllFreeRoomsAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        Integer freeRooms = hotelFacade.getNumberAllFreeRooms();
        if (freeRooms > 0) {
            System.out.println("Общее количество свободных комнат: " + freeRooms);
            hotelFacade.getAllFreeRooms().forEach(System.out::println);
        } else {
            System.out.println("Свободных комнат нет");
        }
    }

}