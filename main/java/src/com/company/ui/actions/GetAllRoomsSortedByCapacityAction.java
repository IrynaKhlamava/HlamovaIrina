package com.company.ui.actions;

public class GetAllRoomsSortedByCapacityAction extends AbstractAction {

    @Override
    public void execute() {
        hotelFacade.getAllRoomsSortedByCapacity().forEach(System.out::println);
    }
}

