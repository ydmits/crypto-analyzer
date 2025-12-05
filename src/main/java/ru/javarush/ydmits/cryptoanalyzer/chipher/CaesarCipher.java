package ru.javarush.ydmits.cryptoanalyzer.chipher;

import ru.javarush.ydmits.cryptoanalyzer.alphabet.EncodingTable;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Propertyable;

import java.util.Map;

public class CaesarCipher implements Chipher, Propertyable {

    Property [] properties = {Property.CONTENT, Property.KEY};

    Map<Character, Integer> tableIndex = EncodingTable.getMapEncodingTableChatInt();
    Map<Integer, Character> indexTable = EncodingTable.getMapEncodingTableIntChar();
    int tableSize = EncodingTable.size();

    @Override
    public String execute(String content, int key) {


        StringBuilder result = new StringBuilder();

        char[] lowerContent = content.toLowerCase().toCharArray();

        for (int i = 0; i < content.length(); i++) {

            char originalChar = lowerContent[i];

            if(tableIndex.containsKey(originalChar)) {
                int originalIndex = tableIndex.get(originalChar);

                int newIndex = (originalIndex + key) % tableSize;

                if (newIndex < 0) {
                    newIndex += tableSize;
                }

                char newChar = indexTable.get(newIndex);

                result.append(newChar);
            } else {
                result.append(originalChar);
            }
        }

        result.append('\n');

        return result.toString();
    }

    @Override
    public Property[] getProperty() {

        return properties;
    }

    public int getNumberSymbol(Character symbol) {
        return tableIndex.get(symbol);
    }

    public Character getSymbolNumber(int number) {
        return indexTable.get(number);
    }

    public int getTableSize() {
        return tableSize;
    }
}
