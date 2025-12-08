package ru.javarush.ydmits.cryptoanalyzer.controller.command;


import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;

public interface Action {

    void execute();

    Property[] getProperty();

}
