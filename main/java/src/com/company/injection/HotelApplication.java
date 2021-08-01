package com.company.injection;

public class HotelApplication {

    public static void run(ApplicationContext context) {
        DependencyInjector.run(HotelApplication.class, context);

    }
}
