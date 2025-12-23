package ru.javarush.ydmits.cryptoanalyzer.chipher;

public interface Chipher {
    String execute(String content, int key);

    int getNumberSymbol(Character symbol);

    Character getSymbolNumber(int number);

    int getTableSize();
}
