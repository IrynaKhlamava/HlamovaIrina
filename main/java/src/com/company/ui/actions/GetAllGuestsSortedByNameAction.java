package com.company.ui.actions;

public class GetAllGuestsSortedByNameAction extends AbstractAction {

    @Override
    public void execute() {
        hotelFacade.getAllGuestsSortedByName().forEach(System.out::println);
    }

}
