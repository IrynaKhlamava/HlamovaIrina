package com.company.config.annotation;

import com.company.injection.exeptions.InjectionException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class PropertiesConfigurator {

    private static final String PROPERTIES_FILE_PATH = "H:/javadev/HlamovaIrina/hotel/src/main/java/com/company" +
            "/resources/application.properties";
    private static final String FAILED_READ_PROPERTIES_ERROR_MESSAGE = "Failed to read properties";
    private static final Logger LOGGER = Logger.getLogger(PropertiesConfigurator.class.getName());

    public static Map<String, String> propertiesMap;

    public static Map<String, String> configure() throws FileNotFoundException {
        Stream<String> lines = new BufferedReader(new FileReader(PROPERTIES_FILE_PATH)).lines();
        return propertiesMap = lines.map(line -> line.split(" = ")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    static {
        try {
            PropertiesConfigurator.configure();
        } catch (IOException e) {
            LOGGER.warn(FAILED_READ_PROPERTIES_ERROR_MESSAGE + e.getMessage());
            throw new InjectionException(FAILED_READ_PROPERTIES_ERROR_MESSAGE, e);
        }
    }

    public static void setPropertyToField(Field field, String propertyValue, Object bean) throws IllegalAccessException {
        Class type = field.getType();
        field.setAccessible(true);
        if (type.isPrimitive()) {
            int valueProperty = Integer.parseInt(propertyValue.trim());
            field.set(bean, valueProperty);
            return;
        }
        if (type == Boolean.class) {
            Boolean valueProperty = Boolean.parseBoolean(propertyValue);
            field.set(bean, valueProperty);
            return;
        }
        if (type == Integer.class) {
            Integer valueProperty = Integer.parseInt(propertyValue.trim());
            field.set(bean, valueProperty);
            return;
        }
        if (type == String.class) {
            String valueProperty = propertyValue.trim();
            field.set(bean, valueProperty);
            return;
        }
        if (type == Double.class) {
            Double valueProperty = Double.parseDouble(propertyValue.trim());
            field.set(bean, valueProperty);
            return;
        }
        if (type == Long.class) {
            Long valueProperty = Long.parseLong(propertyValue.trim());
            field.set(bean, valueProperty);
            return;
        }
    }
}

