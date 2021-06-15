package com.company.ui.actions;

public class GetAllGuestsAction extends AbstractAction {
    @Override
    public void execute() {
        hotelFacade.getAllGuests().forEach(System.out::println);
    }
}
