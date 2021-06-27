package com.company.ui.actions;

import com.company.exceptions.ServiceException;

public class GetAllRoomAction extends AbstractAction {

    @Override
    public void execute() {
        try {
            hotelFacade.getAllRoom().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Список комнат пуст. Введите другой пунк меню");
        }
    }
}
