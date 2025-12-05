package ru.javarush.ydmits.cryptoanalyzer.controller.command;


import ru.javarush.ydmits.cryptoanalyzer.chipher.CaesarCipher;
import ru.javarush.ydmits.cryptoanalyzer.chipher.Chipher;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;
import ru.javarush.ydmits.cryptoanalyzer.file.FileProcessor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForce implements Action {

    private Property [] properties = {Property.SOURCE_PATH, Property.TARGET_PATH};

    @Override
    public void execute(){
        Property[] properties = getProperty();

        String sourcePath = properties[0].getProperty();
        String targetPath = properties[1].getProperty();

        Chipher chipher = new CaesarCipher();

        FileProcessor fileProcessor = new FileProcessor();
        List<String> content = fileProcessor.readFile(sourcePath);

        Map<Character, Integer> forceAnalysisContent = new HashMap<>();

        for(String str : content) {
            char [] chars = str.toCharArray();

            for(Character ch : chars) {
                if(forceAnalysisContent.containsKey(ch)) {
                    forceAnalysisContent.put(ch, forceAnalysisContent.get(ch) + 1);
                } else {
                    forceAnalysisContent.put(ch, 1);
                }
            }
        }

        Character encodingSpace = ' ';
        Character decodingSpace = ' ';

        int maxCount = 0;
        for(Map.Entry<Character, Integer> entry : forceAnalysisContent.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                encodingSpace = entry.getKey();
            }
        }


        int numberDecodingSpace = chipher.getNumberSymbol(decodingSpace);
        int numberEncodingSpace = chipher.getNumberSymbol(encodingSpace);

        int key = Math.abs((numberDecodingSpace - numberEncodingSpace) % chipher.getTableSize());

        Action decoder = new Decoder();

        Property[] decoderProperty = decoder.getProperty();

        decoderProperty[0].setProperty(sourcePath);
        decoderProperty[1].setProperty(targetPath);
        decoderProperty[2].setProperty(String.valueOf(key));

        decoder.execute();



    }

    @Override
    public Property[] getProperty() {

        return properties;
    }
}
