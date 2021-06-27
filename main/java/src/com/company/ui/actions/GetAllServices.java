package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllServices extends AbstractAction {

    @Override
    public void execute() {
        try {
            hotelFacade.getAllServices().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Посмотреть услуги не удалось. Введите другой пункт меню");
        }
    }
}
