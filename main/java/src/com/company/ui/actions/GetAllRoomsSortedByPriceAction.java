package com.company.ui.actions;

import org.w3c.dom.ls.LSOutput;

public class GetAllRoomsSortedByPriceAction extends AbstractAction {

    @Override
    public void execute() {

        hotelFacade.getAllRoomsSortedByByPrice().forEach(System.out::println);
    }
}
