package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllGuestsSortedByDepartureAction extends AbstractAction {

    @Override
    public void execute() {
        try {
            hotelFacade.getAllGuestsSortedByDeparture().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Не удалось посмотреть гостей отсортированных по дате отправления. Введите другой пункт меню");
        }
    }
}