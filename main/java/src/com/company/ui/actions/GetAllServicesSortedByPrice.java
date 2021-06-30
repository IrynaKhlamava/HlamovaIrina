package com.company.ui.actions;

public class GetAllServicesSortedByPrice extends AbstractAction {

    @Override
    public void execute() {
        hotelFacade.getAllServicesSortedByPrice().forEach(System.out::println);
    }
}
