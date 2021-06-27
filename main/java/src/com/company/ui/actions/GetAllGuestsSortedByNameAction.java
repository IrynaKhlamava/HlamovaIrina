package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllGuestsSortedByNameAction extends AbstractAction {

    @Override
    public void execute() {
        try {
            hotelFacade.getAllGuestsSortedByName().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Не удалось просмотреть гостей отсортированных по имени. Введите другой пункт меню");
        }
    }
}
