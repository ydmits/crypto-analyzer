package ru.javarush.ydmits.cryptoanalyzer.controller;

import ru.javarush.ydmits.cryptoanalyzer.controller.command.*;

public enum Command {

    EXIT(null),
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTE_FORCE(new BruteForce()),
    STATISTIC_ANALYZER(new StatisticAnalyzer());

    private final Action action;

    Command(Action action) {
        this.action = action;
    }

    public static Action find(int commandNumber) {
        return Command.values()[commandNumber].action;
    }
}
