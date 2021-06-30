package com.company.ui.actions;

public class GetAllServices extends AbstractAction {

    @Override
    public void execute() {
        hotelFacade.getAllServices().forEach(System.out::println);
    }
}
