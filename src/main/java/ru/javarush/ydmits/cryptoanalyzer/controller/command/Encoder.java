package ru.javarush.ydmits.cryptoanalyzer.controller.command;

import ru.javarush.ydmits.cryptoanalyzer.chipher.CaesarCipher;
import ru.javarush.ydmits.cryptoanalyzer.chipher.Chipher;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;
import ru.javarush.ydmits.cryptoanalyzer.file.FileProcessor;

import java.util.List;

public class Encoder extends AbstractCoder {

    public Encoder() {
        properties = new Property[]{
                Property.SOURCE_PATH,
                Property.TARGET_PATH,
                Property.KEY};
    }

    @Override
    protected void setKey() {
        key = Integer.parseInt(properties[2].getProperty());
    }

    @Override
    protected void doAction() {
        FileProcessor fileProcessor = new FileProcessor();

        List<String> content = fileProcessor.readFile(sourcePath);

        Chipher chipher = new CaesarCipher();

        for (String str : content) {
            String result = chipher.execute(str, key);
            fileProcessor.appendToFile(targetPath, result);
        }
    }
}
