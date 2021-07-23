package com.company.config.annotation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class PropertiesConfigurator {

    private static final String PROPERTIES_FILE_PATH = "main/java/resources/application.properties";

    public static Map<String, String> propertiesMap;

    public static Map<String, String> configure() throws FileNotFoundException {
        Stream<String> lines = new BufferedReader(new FileReader(PROPERTIES_FILE_PATH)).lines();
        return propertiesMap = lines.map(line -> line.split(" = ")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }
}
