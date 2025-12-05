package ru.javarush.ydmits.cryptoanalyzer.controller.property;

import ru.javarush.ydmits.cryptoanalyzer.constant.Constants;
import ru.javarush.ydmits.cryptoanalyzer.file.FileNameValidation;

public enum Property {
    SOURCE_PATH,
    TARGET_PATH,
    KEY,
    CONTENT;

    private String content;

    Property() {
        this.content = null;
    }

    public String getProperty() {
        return content;
    }

    public void setProperty(String content) {
        this.content = content;
    }

    public boolean isValidProperty() {
        FileNameValidation fileNameValidation = new FileNameValidation();

        switch (this) {
            case SOURCE_PATH -> {
                fileNameValidation.validateForReading(content);
                return true;
            }
            case TARGET_PATH -> {
                fileNameValidation.validateForWriting(content);
                return true;
            }
            case KEY -> {
                int key;
                try {
                    key = Integer.parseInt(content);
                } catch (NumberFormatException ex) {
                    return false;
                }
                return (key >= 0 && key <= Constants.SIZE_ENCODING_TABLE);
            }
            case CONTENT -> {return content != null;}
        }

        return true;
    }


}
