package io;

import exception.InputException;

import java.util.Scanner;

public class InputSystem {
    private Scanner sc;

    public InputSystem() {
        sc = new Scanner(System.in);
    }

    public String inputUserName() {
        String names = sc.nextLine();
        if (InputException.nameSplitException(names))
            return inputUserName();
        return names;
    }

    public int inputBettingPirce() {
        String stringPrice = sc.next();
        if(InputException.priceStringException(stringPrice))
            return inputBettingPirce();
        int price = Integer.parseInt(stringPrice);
        if(InputException.bettingMoneycheck(price))
            return inputBettingPirce();
        return price;
    }

    public boolean inputBettingAnswer() {
        String answer = sc.next();
        if (answer.equals("y") || answer.equals("Y"))
            return true;
        if (answer.equals("n") || answer.equals("N"))
            return false;
        System.out.println("Y, N로 대답해주세요.");
        return inputBettingAnswer();
    }
}