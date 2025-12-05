package ru.javarush.ydmits.cryptoanalyzer.alphabet;

import ru.javarush.ydmits.cryptoanalyzer.constant.Constants;

public class NumbersAlphabet extends AbstractAlphabet{

    private Character[] symbols = Constants.NUMBER.toArray(new Character[0]);

    @Override
    public Character[] getAlphabet() {
        return symbols;
    }

    @Override
    public int getSize() {
        return symbols.length;
    }
}
