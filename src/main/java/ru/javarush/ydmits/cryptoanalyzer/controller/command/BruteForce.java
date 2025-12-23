package ru.javarush.ydmits.cryptoanalyzer.controller.command;

import ru.javarush.ydmits.cryptoanalyzer.chipher.CaesarCipher;
import ru.javarush.ydmits.cryptoanalyzer.chipher.Chipher;
import ru.javarush.ydmits.cryptoanalyzer.constant.Constants;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;
import ru.javarush.ydmits.cryptoanalyzer.exception.FileProcessingException;
import ru.javarush.ydmits.cryptoanalyzer.file.FileProcessor;

import java.util.*;
import java.util.stream.Stream;

public class BruteForce extends AbstractCoder {
    private int howManySymbolsToAnalyze;

    public BruteForce() {
        properties = new Property[]{
                Property.SOURCE_PATH,
                Property.TARGET_PATH};

        howManySymbolsToAnalyze = Constants.HOW_MANY_SYMBOLS_TO_ANALYZE;
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
        List<String> content = new ArrayList<>();

        int index = 0;
        try(Stream<String> contentFile = fileProcessor.readFile(sourcePath)) {
            Iterator<String> iterator = contentFile.iterator();

            while (iterator.hasNext() && index < howManySymbolsToAnalyze) {
                String str = iterator.next();
                content.add(str);
                index+= str.length();
            }
        } catch (Exception e) {
            throw new FileProcessingException(e.getMessage(), e);
        }

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
