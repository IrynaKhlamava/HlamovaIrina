package com.company.ui.actions;

public class GetAllFreeRoomsAction extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("Общее количество свободных комнат: "+hotelFacade.getNumberAllFreeRooms());
        hotelFacade.getAllFreeRooms().forEach(System.out::println);

    }
}
