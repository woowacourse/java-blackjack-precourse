package domain.game;

import java.util.*;

public class BlackJack {
    public static void main(String[] args) {
        System.out.println(receiveInput("입력"));
    }

    private static String receiveInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }
}