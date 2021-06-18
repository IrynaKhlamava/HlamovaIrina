package com.company.ui.actions;

public class GetAllRoomAction extends AbstractAction {

    @Override
    public void execute() {
        hotelFacade.getAllRoom().forEach(System.out::println);
    }
}
