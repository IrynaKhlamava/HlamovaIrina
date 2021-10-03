package com.company;

import com.company.configuration.ContextConfiguration;
import com.company.ui.UiApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        UiApplication.run(context);
    }
}