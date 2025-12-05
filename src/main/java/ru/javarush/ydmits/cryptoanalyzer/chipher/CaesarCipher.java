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

        String lowerContent = content.toLowerCase();

        for (int i = 0; i < content.length(); i++) {

            Character originalChar = lowerContent.charAt(i);

            if(tableIndex.containsKey(originalChar)) {
                int originalIndex = tableIndex.get(originalChar);

                int newIndex = (tableSize + originalIndex + key) % tableSize;
                Character newChar = indexTable.get(newIndex);

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
}
