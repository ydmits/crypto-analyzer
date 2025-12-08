package ru.javarush.ydmits.cryptoanalyzer.alphabet;

import ru.javarush.ydmits.cryptoanalyzer.constant.Constants;

public class SpecialAlphabet extends AbstractAlphabet{

    private final static Character[] symbols = Constants.SPECIAL;

    @Override
    public Character[] getAlphabet() {
        return symbols;
    }

    @Override
    public int getSize() {
        return symbols.length;
    }
}
