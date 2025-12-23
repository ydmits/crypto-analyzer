package ru.javarush.ydmits.cryptoanalyzer.alphabet;

public abstract class AbstractAlphabet implements Alphabet{

    @Override
    public abstract Character[] getAlphabet();

    @Override
    public abstract int getSize();
}
