package com.company.ui.actions;

import com.company.model.Guest;
import com.company.util.ScannerUtil;

import java.util.List;

public class GetAllGuestServicesSortedByPrice extends AbstractAction {

    @Override
    public void execute() {
        System.out.println("введите номер комнаты");
        String name = ScannerUtil.readString();
        List<Guest> guests = hotelFacade.getAllGuests();
        for (Guest guest : guests) {
            if (guest.getName().equals(name)) {
                hotelFacade.getAllGuestServicesSortedByPrice(guest).forEach(System.out::println);
            }
        }

    }
}
