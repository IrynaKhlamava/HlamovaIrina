package com.company.ui.actions;

import com.company.filter.SortGuestsByName;
import com.company.model.Guest;
import com.company.model.Room;

import java.util.List;
import java.util.Scanner;

public class GetAllServicesSortedByPrice extends AbstractAction {

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите номер комнаты");
        String name = scanner.next();
        List<Guest> guests = hotelFacade.getAllGuests();
        for (Guest guest : guests) {
            if (guest.getName().equals(name)) {
                hotelFacade.getAllServicesSortedByPrice(guest).forEach(System.out::println);
            }
        }

    }
}
