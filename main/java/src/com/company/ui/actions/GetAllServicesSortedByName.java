package com.company.ui.actions;

public class GetAllServicesSortedByName extends AbstractAction {

    @Override
    public void execute() {
        hotelFacade.getAllServicesSortedByName().forEach(System.out::println);

    }
}
