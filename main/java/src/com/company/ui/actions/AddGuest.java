package com.company.ui.actions;

import java.util.Scanner;

public class AddGuest extends AbstractAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите ФИО гостя");
        String name = scanner.nextLine();
        System.out.println("введите планируемое колличество дней пребывания");
        Integer daysOfStay = scanner.nextInt();
        hotelFacade.saveGuest(name, daysOfStay);

    }
}
