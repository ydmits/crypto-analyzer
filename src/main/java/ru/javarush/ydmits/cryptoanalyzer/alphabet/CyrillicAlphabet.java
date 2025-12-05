package ru.javarush.ydmits.cryptoanalyzer.alphabet;

import ru.javarush.ydmits.cryptoanalyzer.constant.Constants;

public class CyrillicAlphabet extends AbstractAlphabet{

    private Character[] symbols = Constants.CYRILLIC.toArray(new Character[0]);

    @Override
    public Character[] getAlphabet() {
        return symbols;
    }

    @Override
    public int getSize() {
        return symbols.length;
    }
}
