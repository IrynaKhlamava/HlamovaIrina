package com.company.injection;

import com.company.injection.exeptions.InjectionException;

import java.util.logging.Logger;

public class DependencyInjector {

    private static final Logger LOGGER = Logger.getLogger(DependencyInjector.class.getName());

    public static void run(Class<?> startClass, ApplicationContext applicationContext) {
        try {
            ClassScanner scanner = new ClassScanner();
            ObjectFactory factory = new ObjectFactory();
            applicationContext.setFactory(factory);
            applicationContext.createContext(scanner.findClasses(startClass));
        } catch (IllegalAccessException e) {
            LOGGER.warning("Access to the class is denied " + e.getMessage());
            throw new InjectionException("Message", e);

        }
    }
}
