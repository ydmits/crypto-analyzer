package ru.javarush.ydmits.cryptoanalyzer.controller;

import ru.javarush.ydmits.cryptoanalyzer.controller.command.Action;
import ru.javarush.ydmits.cryptoanalyzer.controller.command.Decoder;
import ru.javarush.ydmits.cryptoanalyzer.controller.command.Encoder;

public enum Command {

    EXIT(null),
    ENCODE(new Encoder()),
    DECODE(new Decoder());


    private final Action action;

    Command(Action action) {
        this.action = action;
    }

    public static Action find(int commandNumber) {
        return Command.values()[commandNumber].action;
    }
}
