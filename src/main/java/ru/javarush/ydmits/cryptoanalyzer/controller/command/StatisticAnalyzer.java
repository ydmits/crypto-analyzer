package ru.javarush.ydmits.cryptoanalyzer.controller.command;

import ru.javarush.ydmits.cryptoanalyzer.alphabet.EncodingTable;
import ru.javarush.ydmits.cryptoanalyzer.chipher.CaesarCipher;
import ru.javarush.ydmits.cryptoanalyzer.chipher.Chipher;
import ru.javarush.ydmits.cryptoanalyzer.constant.Constants;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;
import ru.javarush.ydmits.cryptoanalyzer.file.FileProcessor;

import java.util.*;

public class StatisticAnalyzer extends AbstractCoder{

    private String analysisPath;
    private int howManyCharsToAnalyze;
    private int howManySymbolsToAnalyze;

    public StatisticAnalyzer() {
        properties = new Property[]{
                Property.SOURCE_PATH,
                Property.TARGET_PATH,
                Property.ANALYSIS_PATH};

        howManyCharsToAnalyze = Constants.HOW_MANY_CHARS_TO_ANALYZE;
        howManySymbolsToAnalyze = Constants.HOW_MANY_SYMBOLS_TO_ANALYZE;
    }



    @Override
    protected void setKey() {
        setAnalysisPath();

        List<Character> exampleText = getText(analysisPath);
        List<Character> encodeText = getText(sourcePath);

        key = frequencyAnalysis(exampleText, encodeText);
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

    private void setAnalysisPath() {
        analysisPath = properties[2].getProperty();
    }

    private List<Character> getText(String path) {
        List<Character> result = new ArrayList<>();

        FileProcessor fileProcessor = new FileProcessor();
        List<String> contentFile = fileProcessor.readFile(path);

        int index = 0;
        for (String str : contentFile) {
            if (index >= howManySymbolsToAnalyze) {
                break;
            }
            char[] chars = str.toCharArray();
            for (char ch : chars) {
                if (index >= howManySymbolsToAnalyze) {
                    break;
                }
                result.add(ch);
                index++;
            }
        }
        return result;
    }

    private int frequencyAnalysis(List<Character> exampleText, List<Character> encodeText) {

        Map<Character, Integer> charsExampleFrequency = getCharsFrequency(exampleText);
        Map<Character, Integer> charsEncodeFrequency = getCharsFrequency(encodeText);

        List<Character> topExampleFrequency = getTopFrequency(charsExampleFrequency);
        List<Character> topEncodeFrequency = getTopFrequency(charsEncodeFrequency);

        List<Integer> possibleExampleKeys = getKeysText(topExampleFrequency);
        List<Integer> possibleEncodeKeys = getKeysText(topEncodeFrequency);

        Map<Integer, String> decodeText = getDecodeText(possibleExampleKeys, possibleEncodeKeys, encodeText);
        Map<Integer, Integer> keysCountStrike = counterStrike(decodeText, exampleText.toString());

        Map.Entry<Integer, Integer> maxEntry =
                Collections.max(keysCountStrike.entrySet(),Map.Entry.comparingByValue());

        int newKey = choiceKey(maxEntry, charsExampleFrequency, charsEncodeFrequency);

        return newKey;
    }

    private Map<Character, Integer> getCharsFrequency(List<Character> content) {

        Map<Character, Integer> result = new HashMap<>();
        Map<Character, Integer> encodingTable = EncodingTable.getMapEncodingTableChatInt();

        for (char ch : content) {
            if (encodingTable.containsKey(ch)) {
                if(result.containsKey(ch)) {
                    result.put(ch, result.get(ch) + 1);
                } else {
                    result.put(ch, 1);
                }
            }
        }

        return result;
    }

        private List<Character> getTopFrequency(Map<Character, Integer> charsFrequency) {
            List<Character> topFrequency = new ArrayList<>();
            Map<Character, Integer> copyCharsFrequency = new HashMap<>(charsFrequency);

        for (int i = 0; i < howManyCharsToAnalyze && !copyCharsFrequency.isEmpty(); i++) {
            Map.Entry<Character, Integer> maxEntry =
                    Collections.max(copyCharsFrequency.entrySet(), Map.Entry.comparingByValue());

            topFrequency.add(maxEntry.getKey());
            copyCharsFrequency.remove(maxEntry.getKey());
        }

        return topFrequency;
    }

    private List<Integer> getKeysText(List<Character> topCharsExampleText) {

        List<Integer> possibleKeys = new ArrayList<>();
        Chipher chipher = new CaesarCipher();

        for (int i = 0; i < topCharsExampleText.size(); i++) {
            possibleKeys.add(chipher.getNumberSymbol(topCharsExampleText.get(i)));
        }

        return possibleKeys;
    }

    private Map<Integer, String> getDecodeText
            (List<Integer> possibleExampleKeys, List<Integer> possibleEncodeKeys, List<Character> encodeText) {

        Map<Integer, String> result = new HashMap<>();

        Chipher chipher = new CaesarCipher();
        String content = encodeText.toString();

        for (int exampleKey : possibleExampleKeys) {
            for (int encodeKey : possibleEncodeKeys) {
                int key = Math.abs((exampleKey - encodeKey) % chipher.getTableSize());

                String decodeText = chipher.execute(content, -key);
                result.put(key, decodeText);
            }
        }
        return result;
    }

    private Map<Integer, Integer> counterStrike(Map<Integer, String> decodeText,String exampleText) {
        Map<Integer, Integer> result = new HashMap<>();

        for(int key: decodeText.keySet()) {
            int counter = 0;

            String decodeStr = decodeText.get(key);
            String[] decodeWords = decodeStr.split(" ");

            for (String word : decodeWords) {
                if (exampleText.contains(word)) {
                    counter++;
                }
            }
            result.put(key, counter);
        }
        return result;
    }

    private int choiceKey(Map.Entry<Integer, Integer> maxEntry,
                  Map<Character, Integer> charsExampleFrequency,
                  Map<Character, Integer> charsEncodeFrequency) {

        if (maxEntry.getValue() >= (howManySymbolsToAnalyze * 0.01)) return maxEntry.getKey();

        Map.Entry<Character, Integer> maxEntryExample =
                Collections.max(charsExampleFrequency.entrySet(),Map.Entry.comparingByValue());
        Map.Entry<Character, Integer> maxEntryEncode =
                Collections.max(charsEncodeFrequency.entrySet(),Map.Entry.comparingByValue());

        char topDecodeSymbol = maxEntryExample.getKey();
        char topEncodeSymbol = maxEntryEncode.getKey();

        Chipher chipher = new CaesarCipher();

        int numberDecodingSymbol = chipher.getNumberSymbol(topDecodeSymbol);
        int numberEncodingSymbol = chipher.getNumberSymbol(topEncodeSymbol);

        return Math.abs((numberDecodingSymbol - numberEncodingSymbol) % chipher.getTableSize());
    }


}
