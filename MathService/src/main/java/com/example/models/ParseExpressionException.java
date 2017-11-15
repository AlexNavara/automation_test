package com.example.models;

public class ParseExpressionException extends Exception {

    public ParseExpressionException(final String message) {
        super(message);
    }

    public ParseExpressionException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
