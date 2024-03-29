package com.company.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class CustomLogger {

    static {
        try (InputStream configReader = new FileInputStream("main/java/resources/logger.properties")) {
            LogManager.getLogManager().readConfiguration(configReader);
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration:" + e.toString());
        }
    }
}