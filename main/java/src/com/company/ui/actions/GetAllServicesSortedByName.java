package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllServicesSortedByName extends AbstractAction {

    @Override
    public void execute() {
        try {
            hotelFacade.getAllServicesSortedByName().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Посмотреть услуги отсортированные по названию не удалось. Введите другой пункт меню");
        }
    }
}
