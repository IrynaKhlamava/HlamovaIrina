package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllFreeRoomsAction extends AbstractAction {

    @Override
    public void execute() {
        try {
            System.out.println("Общее количество свободных комнат: " + hotelFacade.getNumberAllFreeRooms());
            hotelFacade.getAllFreeRooms().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Посмотреть свободные комнаты не удалось. Введите другой пункт меню");
        }
    }
}
