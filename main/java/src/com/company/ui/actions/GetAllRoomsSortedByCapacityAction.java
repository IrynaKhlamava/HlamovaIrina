package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllRoomsSortedByCapacityAction extends AbstractAction {

    @Override
    public void execute() {
        try {
            hotelFacade.getAllRoomsSortedByCapacity().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Посмотреть свободные комнаты с сортировкой по вместимости не удалось. Введите другой пункт меню");
        }
    }
}

