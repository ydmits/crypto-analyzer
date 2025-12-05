package ru.javarush.ydmits.cryptoanalyzer.alphabet;

import ru.javarush.ydmits.cryptoanalyzer.constant.Constants;

public class SpecialAlphabet extends AbstractAlphabet{

    private Character[] symbols = Constants.SPECIAL.toArray(new Character[0]);

    @Override
    public Character[] getAlphabet() {
        return symbols;
    }

    @Override
    public int getSize() {
        return symbols.length;
    }
}
