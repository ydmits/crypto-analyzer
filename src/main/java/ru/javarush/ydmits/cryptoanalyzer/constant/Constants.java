package ru.javarush.ydmits.cryptoanalyzer.constant;

import ru.javarush.ydmits.cryptoanalyzer.alphabet.EncodingTable;
import ru.javarush.ydmits.cryptoanalyzer.controller.Command;

import java.util.Set;

public class Constants {

    public final static Set<Character> LATIN = Set.of(
            'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
            'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z',
            'x', 'c', 'v', 'b', 'n', 'm'
    );

    public final static Set<Character> CYRILLIC = Set.of(
            'й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з',
            'х', 'ъ', 'ф', 'ы', 'в', 'а', 'п', 'р', 'о', 'л',
            'д', 'ж', 'э', 'я', 'ч', 'с', 'м', 'и', 'т', 'ь',
            'б', 'ю'
    );

    public final static Set<Character> NUMBER = Set.of(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    );

    public final static Set<Character> SPECIAL = Set.of(
            ' ', '!', '@', '#', '$', '%', '^', '&', '*', '(',
            ')', '-', '=', '_', '+', '/', '\\', '|', '<', '>',
            '?', '.', ',', '{', '}', '[', ']', '\'', '\"', ';',
            ':', '`', '~'
    );

    public final static String START_CONSOLE_MENU = """
                Caesar's cipher
                
                1. Encode text
                2. Decode text
                0. Exit
                """;
    public static final int HOW_MANY_COMMANDS = Command.values().length;
    public static final int SIZE_ENCODING_TABLE = EncodingTable.size();

    public static final String REQUEST_COMMAND_NUMBER = "Enter the command number: ";
    public static final String REQUEST_SOURCE_PATH = "Enter the source path file: ";
    public static final String REQUEST_TARGET_PATH = "Enter the target path file: ";
    public static final String REQUEST_KEY = "Enter the key: ";

    private Constants() {}
}
