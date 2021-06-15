package com.company.ui.actions;

public class GetAllGuestsSortedByDepartureAction extends AbstractAction {
    @Override
    public void execute() {
        hotelFacade.getAllGuestsSortedByDeparture().forEach(System.out::println);
    }

}
