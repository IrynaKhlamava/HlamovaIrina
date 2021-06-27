package com.company.ui.menu;

import com.company.util.ScannerUtil;

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
            try {
                navigator.printMenu();
                navigator.navigate(ScannerUtil.readInteger());
            } catch (Exception e) {
                System.out.println("Выбран несуществующий пунк меню. Введите другой");
            }
        }
    }
}
