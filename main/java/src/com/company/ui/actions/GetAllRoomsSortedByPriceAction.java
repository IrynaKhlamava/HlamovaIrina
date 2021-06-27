package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllRoomsSortedByPriceAction extends AbstractAction {

    @Override
    public void execute() {
        try {
            hotelFacade.getAllRoomsSortedByByPrice().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Посмотреть свободные комнаты с сортировкой по цене не удалось. Введите другой пункт меню");
        }
    }
}
