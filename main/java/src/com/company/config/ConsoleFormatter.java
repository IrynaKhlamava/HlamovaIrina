package com.company.config;

import java.time.LocalDate;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ConsoleFormatter extends Formatter {
    public String format(LogRecord record) {
        return LocalDate.now() + ": " + record.getLevel() + ": " + record.getMessage() + ": " + record.getLoggerName() + "\n";

    }
}
