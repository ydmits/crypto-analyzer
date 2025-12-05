package ru.javarush.ydmits.cryptoanalyzer.chipher;

import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;

public interface Chipher {
    String execute(String content, int key);

    Property[] getProperty();

    int getNumberSymbol(Character symbol);

    Character getSymbolNumber(int number);

    int getTableSize();
}
