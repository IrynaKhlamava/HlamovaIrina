package com.company.config;

import java.time.LocalDate;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FileFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return LocalDate.now() + ": " + record.getLevel() + ": " + record.getMessage() + ": " + record.getLoggerName() + "\n";
    }
}
