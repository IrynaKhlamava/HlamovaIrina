package com.company.ui.menu;

import com.company.facade.HotelFacade;
import com.company.util.ScannerUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;

@org.springframework.stereotype.Service
@Transactional
public class MenuController {

    private static final Logger LOGGER = Logger.getLogger(MenuController.class.getName());

    public static Boolean isRunning = true;

    private Builder builder;

    private Navigator navigator;

    public MenuController(Builder builder, Navigator navigator) {
        this.builder = builder;
        this.navigator = navigator;
    }

    public void run(AnnotationConfigApplicationContext context) {
        builder.buildMenu(context.getBean(HotelFacade.class));
        navigator.setCurrentMenu(builder.getRootMenu());
        while (isRunning) {
            try {
                navigator.printMenu();
                navigator.navigate(ScannerUtil.readInteger());
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "last request failed", e);
            }
        }
    }

}
