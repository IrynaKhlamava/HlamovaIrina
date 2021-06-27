package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllGuestsAction extends AbstractAction {

    @Override
    public void execute() {
        try {
            hotelFacade.getAllGuests().forEach(System.out::println);
        } catch (
                ServiceException e) {
            System.out.println("Список гостей пуст. Введите другой пунк меню");
        }
    }
}
