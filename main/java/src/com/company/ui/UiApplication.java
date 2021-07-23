package com.company.ui;

import com.company.injection.ApplicationContext;
import com.company.ui.menu.MenuController;

public class UiApplication {
    public static void run(ApplicationContext context) {
        MenuController controller = context.getBean(MenuController.class);
        controller.run();
    }
}
