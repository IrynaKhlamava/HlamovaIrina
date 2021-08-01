package com.company.injection.exeptions;

public class FindClassesException extends RuntimeException {

    public FindClassesException(String message) {
        super(message);
    }

    public FindClassesException(String message, Throwable cause) {
        super(message, cause);
    }
}
