package com.company.ui.menu;

import com.company.service.RoomService;
import com.company.util.ScannerUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController {

    private static final Logger LOGGER = Logger.getLogger(MenuController.class.getName());

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
                LOGGER.log(Level.WARNING, "last request failed", e);
                System.out.println("Введены некорректные данные");
            }
        }
    }
}
