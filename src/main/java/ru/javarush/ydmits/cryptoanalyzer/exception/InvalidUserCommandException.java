package ru.javarush.ydmits.cryptoanalyzer.exception;

public class InvalidUserCommandException extends RuntimeException {

    public InvalidUserCommandException(String message) {
        super(message);
    }

    public InvalidUserCommandException(String message, Exception ex) {
        super(message, ex);
    }
}
