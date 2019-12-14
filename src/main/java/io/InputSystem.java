package io;

import java.util.Scanner;

public class InputSystem {
    private Scanner sc;

    public InputSystem() {
        sc = new Scanner(System.in);
    }

    public String inputUserName() {
        return sc.nextLine();
    }

    public int inputBettingPirce() {
        return sc.nextInt();
    }

    public String inputBettingAnswer() {
        return sc.nextLine();
    }
}