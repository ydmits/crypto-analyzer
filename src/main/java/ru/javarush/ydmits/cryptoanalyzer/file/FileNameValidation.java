package ru.javarush.ydmits.cryptoanalyzer.file;

import ru.javarush.ydmits.cryptoanalyzer.exception.FileProcessingException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class FileNameValidation {

    public void validateForWriting(String fileName) {
        Path path = validatePath(fileName);

        if (Files.exists(path)){

            if (Files.isDirectory(path)) {
                throw new FileProcessingException("File " + path + " is directory");

            }

            if (!Files.isWritable(path)) {
                throw new FileProcessingException("File " + path + " is not writing");
            }
        }
    }

    public void validateForReading(String fileName) {
        Path path = validatePath(fileName);

        if (Files.notExists(path)){
            throw new FileProcessingException("File " + path + " is not exist");

        }

        if (Files.isDirectory(path)) {
            throw new FileProcessingException("File " + path + " is directory");

        }

        if (!Files.isReadable(path)) {
            throw new FileProcessingException("File " + path + " is not reading");

        }
    }


    private Path validatePath(String fileName) {

        try {
            Path path = Path.of(fileName);
            return path;
        } catch (InvalidPathException ex) {
            throw new FileProcessingException(ex.getMessage(), ex);
        }
    }
}
