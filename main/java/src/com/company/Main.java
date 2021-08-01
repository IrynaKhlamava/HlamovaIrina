package com.company;

import com.company.injection.ApplicationContext;
import com.company.injection.HotelApplication;
import com.company.ui.UiApplication;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        HotelApplication.run(context);
        UiApplication.run(context);
    }

}