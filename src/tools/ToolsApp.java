/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import enums.RunwaysStatus;
import enums.SpaceshipsStatus;
import exceptions.InputException;

/**
 * Class with aux functions to validate data
 *
 * @author yuli
 */
public class ToolsApp {

    private static ToolsApp toolsApp;

    public static ToolsApp getSwingTools() {
        if (toolsApp == null) {
            toolsApp = new ToolsApp();
        }
        return toolsApp;
    }

    /**
     * Parse String to int
     *
     * @param text
     * @return value int of text
     * @throws InputException if String isn't a integer number, data wrong
     */
    public static int convertStringToNumber(String text) throws InputException {
        int num;
        try {
            num = Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            throw new InputException(InputException.WRONG_DATA);
        }
        return num;
    }

    /**
     * Parse int to String
     *
     * @return value text of int
     */
    public static String convertNumberToString(int number) {
        String numberText = Integer.toString(number);
        return numberText;
    }

    /**
     * Parse string to SpaceshipsStatus type enum
     *
     * * @return
     */
    public static SpaceshipsStatus converStringToSpaceshipsStatus(String text) {
        SpaceshipsStatus enumText = SpaceshipsStatus.valueOf(text.toUpperCase());
        return enumText;
    }

    /**
     * Parse string to RunwaysStatus type enum
     *
     * @param text
     * @return
     */
    public static RunwaysStatus converStringToRunwaysStatus(String text) {
        RunwaysStatus enumText = RunwaysStatus.valueOf(text.toUpperCase());
        return enumText;
    }
}
