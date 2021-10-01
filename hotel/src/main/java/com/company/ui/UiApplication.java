package com.company.ui;

import com.company.ui.menu.MenuController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UiApplication {
    public static void run(AnnotationConfigApplicationContext context) {
        MenuController controller = context.getBean(MenuController.class);
        controller.run(context);
    }

}
