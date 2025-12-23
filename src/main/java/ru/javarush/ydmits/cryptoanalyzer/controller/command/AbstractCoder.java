package ru.javarush.ydmits.cryptoanalyzer.controller.command;

import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;

public abstract class AbstractCoder implements Action {

    protected Property[] properties;

    protected String sourcePath;
    protected String targetPath;
    protected int key;

    @Override
    public void execute(){

        setSourcePath();
        setTargetPath();
        setKey();
        doAction();
    }

    public Property[] getProperty() {
        return properties;
    }

    protected void setSourcePath() {
        sourcePath = properties[0].getProperty();
    }

    protected void setTargetPath() {
        targetPath = properties[1].getProperty();
    }

    protected abstract void setKey();

    protected abstract void doAction();

}
