package com.company.ui.actions;

public class GetAllFreeRoomsAction extends AbstractAction {

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