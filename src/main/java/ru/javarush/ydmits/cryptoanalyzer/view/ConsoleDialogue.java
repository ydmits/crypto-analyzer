package ru.javarush.ydmits.cryptoanalyzer.view;

import ru.javarush.ydmits.cryptoanalyzer.constant.Constants;
import ru.javarush.ydmits.cryptoanalyzer.controller.Command;
import ru.javarush.ydmits.cryptoanalyzer.controller.MainController;
import ru.javarush.ydmits.cryptoanalyzer.controller.command.Action;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;
import ru.javarush.ydmits.cryptoanalyzer.exception.InvalidUserCommandException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleDialogue implements Dialogue {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private Action command = null;

    private Property[] properties = null;

    private MainController controller = new MainController();

    @Override
    public void run() {
        do {
            printStartMenu();
            setCommand();
            executeCommand();
        } while (command != null);
    }

    private void printStartMenu() {
        System.out.println(Constants.START_CONSOLE_MENU);

    }

    private int getCommandNumber() {
        String strNumber = readString(Constants.REQUEST_COMMAND_NUMBER);
        int number = Integer.parseInt(strNumber);
        int commandNumber = validationNumber(number);

        return commandNumber;
    }

    private int validationNumber(int number) {
        if (number < 0 || number > Constants.HOW_MANY_COMMANDS) {
            throw new InvalidUserCommandException("Invalid command number " + number);
        } else return number;
    }

    private void setCommand() {
        int commandNumber = getCommandNumber();
        command = Command.find(commandNumber);
    }

    private void executeCommand() {
        if (command != null) {
            controller.setCommand(command);
            getProperty();
            setProperty();
            controller.execute();
            clsProperty();
        }
    }

    private void getProperty() {
        properties = controller.getProperty();
    }

    private void setProperty() {
        for(Property property : properties) {
            do {
                String content = readString("Enter the valid property " + property.name() + ": ");
                property.setProperty(content);

            } while (!property.isValidProperty());
        }
    }

    private void clsProperty() {
        properties = null;
    }

    private String readString(String message) {
        try {
            System.out.println(message);
            return reader.readLine();
        } catch (IOException e) {
            System.out.println(String.format("Try again by ", e.getMessage()));
            return readString(message);
        }
    }
}





