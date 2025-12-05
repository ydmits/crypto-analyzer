package ru.javarush.ydmits.cryptoanalyzer.controller;

import ru.javarush.ydmits.cryptoanalyzer.controller.command.Action;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;

public class MainController {
    private Action command;

    public void execute(){
        if (this.command != null) {
            this.command.execute();
        }
    }

    public Property[] getProperty() {
        return this.command.getProperty();
    }

    public void setCommand(Action command) {
        this.command = command;
    }


}


