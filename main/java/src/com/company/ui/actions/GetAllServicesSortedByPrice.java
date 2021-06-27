package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllServicesSortedByPrice extends AbstractAction {

    @Override
    public void execute() {
        try {
            hotelFacade.getAllServicesSortedByPrice().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Посмотреть услуги с сортировкой по цене не удалось. Введите другой пункт меню");
        }
    }
}
