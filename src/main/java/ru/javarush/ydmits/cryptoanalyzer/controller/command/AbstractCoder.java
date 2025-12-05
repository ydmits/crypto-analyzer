package ru.javarush.ydmits.cryptoanalyzer.controller.command;

import ru.javarush.ydmits.cryptoanalyzer.chipher.CaesarCipher;
import ru.javarush.ydmits.cryptoanalyzer.chipher.Chipher;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Propertyable;
import ru.javarush.ydmits.cryptoanalyzer.file.FileProcessor;

import java.util.List;

public abstract class AbstractCoder implements Action, Propertyable {

    @Override
    public void execute(){
        Property[] properties = getProperty();

        String sourcePath = properties[0].getProperty();
        String targetPath = properties[1].getProperty();
        int key = Integer.parseInt(properties[2].getProperty());
        key = getKey(key);

        FileProcessor fileProcessor = new FileProcessor();

        List<String> content = fileProcessor.readFile(sourcePath);

        Chipher chipher = new CaesarCipher();

        for (String str : content) {
            String result = chipher.execute(str, key);
            fileProcessor.appendToFile(targetPath, result);
        }

    }


    protected abstract int getKey(int key);

    @Override
    public abstract Property[] getProperty();

}
