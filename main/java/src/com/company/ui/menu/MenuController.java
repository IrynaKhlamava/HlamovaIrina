package com.company.ui.menu;

import java.util.Scanner;

public class MenuController {

    private static MenuController instance;
    private Builder builder;
    private Navigator navigator;

    public MenuController(Builder builder, Navigator navigator) {
        this.builder = builder;
        this.navigator = navigator;
    }


    public void run() {
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        Integer index = -1;
        while (!index.equals(0)) {
            navigator.printMenu();
            Scanner scanner = new Scanner(System.in);
            navigator.navigate(scanner.nextInt());
        }
    }
}
