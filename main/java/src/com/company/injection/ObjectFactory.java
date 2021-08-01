package com.company.injection;

import com.company.injection.exeptions.InjectionException;

public class ObjectFactory {

    public static <T> T createBean(Class<T> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new InjectionException("Bean creation failed", e);
        }
    }
}
