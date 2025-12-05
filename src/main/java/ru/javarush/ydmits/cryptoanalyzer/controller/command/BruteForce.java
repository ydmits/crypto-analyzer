package ru.javarush.ydmits.cryptoanalyzer.controller.command;


import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;

public class BruteForce implements Action {

    private Property [] properties = {Property.SOURCE_PATH, Property.TARGET_PATH};

    @Override
    public void execute(){

    }

    @Override
    public Property[] getProperty() {

        return properties;
    }
}
