package domain;

import domain.controller.GameManager;

public class Main {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();

        gameManager.run();
    }

}
