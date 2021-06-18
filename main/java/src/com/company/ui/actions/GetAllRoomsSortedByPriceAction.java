package com.company.ui.actions;

public class GetAllRoomsSortedByPriceAction extends AbstractAction {

    @Override
    public void execute() {
        hotelFacade.getAllRoomsSortedByByPrice().forEach(System.out::println);
    }
}
