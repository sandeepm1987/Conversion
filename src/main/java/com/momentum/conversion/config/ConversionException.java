package com.momentum.conversion.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConversionException extends Exception {

    public ConversionException() {
        super();
    }

    public ConversionException(String message, Exception e) {
        super(message, e);
    }

    public ConversionException(String message) {
        super(message);
    }
}
