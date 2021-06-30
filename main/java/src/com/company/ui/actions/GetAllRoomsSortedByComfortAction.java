package com.company.ui.actions;

public class GetAllRoomsSortedByComfortAction extends AbstractAction {

    @Override
    public void execute() {
        hotelFacade.getAllRoomsSortedByComfort().forEach(System.out::println);
    }
}

