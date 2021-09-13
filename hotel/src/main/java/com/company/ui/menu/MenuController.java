package com.company.ui.menu;

import com.company.facade.HotelFacade;
import com.company.injection.ApplicationContext;
import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.util.ScannerUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MenuController {

    private static final Logger LOGGER = Logger.getLogger(MenuController.class.getName());

    public static Boolean isRunning = true;

    @Autowired
    private Builder builder;
    @Autowired
    private Navigator navigator;

    public MenuController() {
    }

    public MenuController(Builder builder, Navigator navigator) {
        this.builder = builder;
        this.navigator = navigator;
    }

    public void run(ApplicationContext context) {
        builder.buildMenu(context.getBean(HotelFacade.class));
        //builder.buildMenu(context.getBean(HotelFacade.class));
        navigator.setCurrentMenu(builder.getRootMenu());
        while (isRunning) {
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
