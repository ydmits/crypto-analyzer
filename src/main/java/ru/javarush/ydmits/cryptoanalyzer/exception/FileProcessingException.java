package ru.javarush.ydmits.cryptoanalyzer.exception;

public class FileProcessingException extends RuntimeException {

    public FileProcessingException(String message) {
        super(message);
    }

    public FileProcessingException(String message, Exception ex) {
        super(message, ex);
    }
}
