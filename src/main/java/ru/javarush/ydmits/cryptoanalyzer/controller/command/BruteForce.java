package ru.javarush.ydmits.cryptoanalyzer.controller.command;

import ru.javarush.ydmits.cryptoanalyzer.chipher.CaesarCipher;
import ru.javarush.ydmits.cryptoanalyzer.chipher.Chipher;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;
import ru.javarush.ydmits.cryptoanalyzer.file.FileProcessor;

import java.util.*;

public class BruteForce extends AbstractCoder {

    public BruteForce() {
        properties = new Property[]{
                Property.SOURCE_PATH,
                Property.TARGET_PATH};
    }

    @Override
    protected void setKey() {

        Map<Character, Integer> forceAnalysisContent = getForceAnalysisContent();

        Character encodingSpace = ' ';
        Character decodingSpace = ' ';

        int maxCount = 0;
        for(Map.Entry<Character, Integer> entry : forceAnalysisContent.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                encodingSpace = entry.getKey();
            }
        }
        Chipher chipher = new CaesarCipher();
        int numberDecodingSpace = chipher.getNumberSymbol(decodingSpace);
        int numberEncodingSpace = chipher.getNumberSymbol(encodingSpace);

        key = Math.abs((numberDecodingSpace - numberEncodingSpace) % chipher.getTableSize());
    }

    @Override
    protected void doAction() {
        Action decoder = new Decoder();

        Property[] decoderProperty = decoder.getProperty();

        decoderProperty[0].setProperty(sourcePath);
        decoderProperty[1].setProperty(targetPath);
        decoderProperty[2].setProperty(String.valueOf(key));

        decoder.execute();
    }

    private Map<Character, Integer> getForceAnalysisContent() {
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

        return Collections.unmodifiableMap(forceAnalysisContent);
    }
}
