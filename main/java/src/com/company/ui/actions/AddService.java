package com.company.ui.actions;

import java.util.Scanner;

public class AddService extends AbstractAction {

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите название услуги");
        String name = scanner.next();
        System.out.println("введите цену услуги");
        Double price = scanner.nextDouble();
        System.out.println("введите ID гостя");
        Long guestId = scanner.nextLong();
        hotelFacade.saveService(name, price, hotelFacade.getGuest(guestId));
    }
}
