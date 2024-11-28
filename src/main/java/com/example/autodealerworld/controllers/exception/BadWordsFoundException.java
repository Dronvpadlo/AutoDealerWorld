package com.example.autodealerworld.controllers.exception;

public class BadWordsFoundException extends RuntimeException {
    public BadWordsFoundException(String message) {
        super(message);
    }
}