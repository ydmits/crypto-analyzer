package ru.javarush.ydmits.cryptoanalyzer.constant;

import ru.javarush.ydmits.cryptoanalyzer.alphabet.EncodingTable;
import ru.javarush.ydmits.cryptoanalyzer.controller.Command;

public class Constants {

    public final static Character[] LATIN = {
            'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
            'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z',
            'x', 'c', 'v', 'b', 'n', 'm'
    };

    public final static Character[] CYRILLIC = {
            'й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з',
            'х', 'ъ', 'ф', 'ы', 'в', 'а', 'п', 'р', 'о', 'л',
            'д', 'ж', 'э', 'я', 'ч', 'с', 'м', 'и', 'т', 'ь',
            'б', 'ю'
    };

    public final static Character[] NUMBER = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public final static Character[] SPECIAL = {
            ' ', '!', '@', '#', '$', '%', '^', '&', '*', '(',
            ')', '-', '=', '_', '+', '/', '\\', '|', '<', '>',
            '?', '.', ',', '{', '}', '[', ']', '\'', '\"', ';',
            ':', '`', '~'
    };

    public final static String START_CONSOLE_MENU = """
                Caesar's cipher                
                1. Encode text
                2. Decode text
                3. Brute Force
                4. Statistic analyzer
                0. Exit
                """;
    public static final int HOW_MANY_COMMANDS = Command.values().length;
    public static final int SIZE_ENCODING_TABLE = EncodingTable.size();
    public static final int HOW_MANY_CHARS_TO_ANALYZE = 10;
    public static final int HOW_MANY_SYMBOLS_TO_ANALYZE = 5_000;

    public static final String REQUEST_COMMAND_NUMBER = "Enter the command number: ";
    public static final String REQUEST_SOURCE_PATH = "Enter the source path file: ";
    public static final String REQUEST_TARGET_PATH = "Enter the target path file: ";
    public static final String REQUEST_KEY = "Enter the key: ";

    private Constants() {}
}
