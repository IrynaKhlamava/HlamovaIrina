package com.company.ui.actions;

import com.company.facade.HotelFacade;

public class GetAvailableRoomsAction extends AbstractAction {

    public GetAvailableRoomsAction(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        Integer AvailableRooms = hotelFacade.getNumberAllAvailableRooms();
        if (AvailableRooms > 0) {
            System.out.println("Общее количество свободных комнат: " + AvailableRooms);
            hotelFacade.getAvailableRooms().forEach(System.out::println);
        } else {
            System.out.println("Свободных комнат нет");
        }
    }

}