package com.company.util;

import com.company.exceptions.ServiceException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesHandler {

    private static final String FAILED_READ_PROPERTIES_ERROR_MESSAGE = "Failed to read properties";
    private static final Logger LOGGER = Logger.getLogger(PropertiesHandler.class.getName());
    private static Properties properties;
    private static final String PROPERTIES_FILE_PATH = "main/java/resources/application.properties";

    private PropertiesHandler() {
    }

    public static Optional<String> getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        return Optional.ofNullable(properties.getProperty(key));
    }

    private static void loadProperties() {
        try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.warning(FAILED_READ_PROPERTIES_ERROR_MESSAGE + e.getMessage());
            throw new ServiceException(FAILED_READ_PROPERTIES_ERROR_MESSAGE, e);
        }
    }

    public static Integer getNumOfGuest() {
        Integer num;
        return num = PropertiesHandler.getProperty("guest.change_num_of_last_guests")
                .map(Integer::valueOf)
                .orElse(3);
    }

    public static String getPathToFile() {
        String pathToFile;
        return pathToFile = PropertiesHandler.getProperty("serialization.path_to_file")
                .orElseThrow(() -> new ServiceException("Serialization file not found"));
    }


}
