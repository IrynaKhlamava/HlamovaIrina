package com.company.ui.actions;

public class GetAllGuestServices extends AbstractAction {
    @Override
    public void execute() {
        hotelFacade.getAllServices().forEach(System.out::println);
    }
}
