package application.view;

import java.io.IOException;
import java.util.Scanner;

public class Input {
    private static Scanner SC = new Scanner(System.in);
    private static String YES = "y";
    private static String NO = "n";

    public static String getNamesFromConsole() {
        return "a,b,c,d,e";
    }

    public static double getBettingMoney() {
        return 10000;
    }

    public static boolean isAddingCardFlag() {
        try {
            return checkInputFlag();
        } catch(IOException e) {
            isAddingCardFlag();
        }
        return false;
    }

    private static boolean checkInputFlag() throws IOException{
        String value = SC.nextLine();
        if (!value.equals(YES) && !value.equals(NO)) {
            throw new IOException();
        }
        return value.equals(YES);
    }
}
