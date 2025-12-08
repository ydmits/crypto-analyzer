package ru.javarush.ydmits.cryptoanalyzer.alphabet;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum EncodingTable {

    LATINIC(new LatinicAlphabet()),
    CYRILIC(new CyrillicAlphabet()),
    NUMBERS(new NumbersAlphabet()),
    SPECIAL(new SpecialAlphabet());

    Alphabet alphabet;

    EncodingTable(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    private static Character[] getEncodingTable() {
        Character[] symbols = new Character[size()];

        int i = 0;
        for (EncodingTable elem : EncodingTable.values()) {
            Character[] chars = elem.alphabet.getAlphabet();

            for (int j = 0; j < chars.length; j++, i++) {
                symbols[i] = chars[j];
            }
        }

        return symbols;
    }

    public static Map<Character, Integer> getMapEncodingTableChatInt() {
        Map<Character,Integer> map = new HashMap<>();
        Character[] chars = getEncodingTable();

        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], i);
        }

        return Collections.unmodifiableMap(map);
    }

    public static Map<Integer, Character> getMapEncodingTableIntChar() {
        Map<Integer,Character> map = new HashMap<>();
        Character[] chars = getEncodingTable();

        for (int i = 0; i < chars.length; i++) {
            map.put(i, chars[i]);
        }

        return Collections.unmodifiableMap(map);
    }

    public static int size() {
        int size = 0;

        for (EncodingTable elem : EncodingTable.values()) {
            size += elem.alphabet.getSize();
        }

        return size;
    }




}
