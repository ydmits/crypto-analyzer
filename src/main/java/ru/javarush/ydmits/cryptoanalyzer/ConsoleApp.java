package ru.javarush.ydmits.cryptoanalyzer;

import ru.javarush.ydmits.cryptoanalyzer.controller.MainController;
import ru.javarush.ydmits.cryptoanalyzer.view.ConsoleDialogue;
import ru.javarush.ydmits.cryptoanalyzer.view.Dialogue;

public class ConsoleApp {


    public static void main(String[] args) {

        Dialogue dialogue = new ConsoleDialogue();
        dialogue.run();


    }
}