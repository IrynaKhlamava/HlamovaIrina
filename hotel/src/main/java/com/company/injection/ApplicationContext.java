package com.company.injection;

import com.company.config.annotation.ConfigProperty;
import com.company.config.annotation.PropertiesConfigurator;
import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.injection.exeptions.InjectionException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.util.stream.Collectors;

public class ApplicationContext {

    private final Map<Class<?>, Object> context;
    private final Map<Class<?>, Class<?>> classInterfaceMap;
    private ObjectFactory factory;

    public ApplicationContext() {
        this.context = new HashMap<>();
        this.classInterfaceMap = new HashMap<>();
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }

    public void createContext(Set<Class<?>> classes) throws IllegalAccessException {
        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(Component.class)) {
                continue;
            }
            Class<?>[] interfaces = clazz.getInterfaces();
            if (interfaces.length == 0) {

                classInterfaceMap.put(clazz, clazz);
                continue;
            }
            for (Class<?> interfaceKey : interfaces) {
                if (!classInterfaceMap.containsKey(interfaceKey)) {
                    classInterfaceMap.put(interfaceKey, clazz);
                }
            }
        }
        fillContext();
    }

    private void fillContext() throws IllegalAccessException {
        for (Class<?> clazz : classInterfaceMap.values()) {
            Object bean = factory.createBean(clazz);
            context.put(clazz, bean);
            injectDependencies(clazz, bean);
        }
    }

    private void injectDependencies(Class<?> clazz, Object bean) throws IllegalAccessException {
        Field[] declaredField = clazz.getDeclaredFields();
        for (Field field : declaredField) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object instance = this.getBean(field.getType());
                field.setAccessible(true);
                field.set(bean, instance);
                injectDependencies(instance.getClass(), instance);
            }
            if (field.isAnnotationPresent(ConfigProperty.class)) {
                String propertyKey = field.getName();
                String propertyValue = PropertiesConfigurator.propertiesMap.entrySet().stream()
                        .filter(entry -> propertyKey.equals(entry.getKey()))
                        .findFirst()
                        .get()
                        .getValue();
                PropertiesConfigurator.setPropertyToField(field, propertyValue, bean);
            }
        }
    }

    public <T> T getBean(Class<T> type) {
        Set<Map.Entry<Class<?>, Class<?>>> classSet = classInterfaceMap.entrySet().stream()
                .filter(entry -> type.equals(entry.getKey()))
                .collect(Collectors.toSet());
        if (classSet.size() != 1) {
            throw new InjectionException("More or less than 1 set");
        }
        Class<?> clazz = classSet.stream()
                .findFirst()
                .get()
                .getValue();

        if (context.containsKey(clazz)) {
            return (T) context.get(clazz);
        }

        Object bean = factory.createBean(clazz);
        context.put(clazz, bean);
        return (T) bean;

    }
}
