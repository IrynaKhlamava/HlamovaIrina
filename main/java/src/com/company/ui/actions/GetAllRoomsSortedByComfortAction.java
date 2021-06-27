package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllRoomsSortedByComfortAction extends AbstractAction {

    @Override
    public void execute() {
        try {
            hotelFacade.getAllRoomsSortedByComfort().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Не удалось просмотреть комнаты отсортированные по комфортности. Введите другой пункт меню");
        }
    }
}

