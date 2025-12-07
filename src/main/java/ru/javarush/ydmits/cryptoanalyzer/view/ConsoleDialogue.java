package ru.javarush.ydmits.cryptoanalyzer.view;

import ru.javarush.ydmits.cryptoanalyzer.constant.Constants;
import ru.javarush.ydmits.cryptoanalyzer.controller.Command;
import ru.javarush.ydmits.cryptoanalyzer.controller.MainController;
import ru.javarush.ydmits.cryptoanalyzer.controller.command.Action;
import ru.javarush.ydmits.cryptoanalyzer.controller.property.Property;
import ru.javarush.ydmits.cryptoanalyzer.exception.FileProcessingException;
import ru.javarush.ydmits.cryptoanalyzer.exception.InvalidUserCommandException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleDialogue implements Dialogue {
    private BufferedReader reader;
    private Action command;
    private Property[] properties;
    private MainController controller;

    public ConsoleDialogue() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        command = null;
        properties = null;
        controller = new MainController();
    }

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
        boolean isTryAgain;
        int commandNumber = 0;

        do {
            isTryAgain = false;
            try {
                String strNumber = readString(Constants.REQUEST_COMMAND_NUMBER);
                int number = Integer.parseInt(strNumber);
                commandNumber = validationNumber(number);

            } catch (InvalidUserCommandException | NumberFormatException ex) {
                System.out.println(ex.getMessage());
                isTryAgain = true;
            }
        } while (isTryAgain);

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
        boolean isTryAgain = false;

        for(Property property : properties) {
            do {
                try {
                    String content = readString("Enter the valid property " + property.name() + ": ");
                    property.setProperty(content);
                    isTryAgain = !property.isValidProperty();
                } catch (FileProcessingException ex) {
                    System.out.println("Invalid path file property: ");
                    System.out.println(ex.getMessage());
                    isTryAgain = true;
                } catch (InvalidUserCommandException ex) {
                    System.out.println(ex.getMessage());
                    isTryAgain = true;
                }
            } while (isTryAgain);
        }
    }

    private void clsProperty() {
        properties = null;
    }

    private String readString(String message) {
        try {
            System.out.print(message);
            return reader.readLine();
        } catch (IOException e) {
            System.out.println(String.format("Try again by ", e.getMessage()));
            return readString(message);
        }
    }
}





