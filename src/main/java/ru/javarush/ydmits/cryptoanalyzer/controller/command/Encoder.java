package ru.javarush.ydmits.cryptoanalyzer.controller.command;

import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;

public class Encoder extends AbstractCoder {

    private Property [] properties = {Property.SOURCE_PATH, Property.TARGET_PATH, Property.KEY};

    @Override
    protected int getKey(int key) {
        return key;
    }

    @Override
    public Property[] getProperty() {

        return properties;
    }
}
