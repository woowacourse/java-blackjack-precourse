package domain;

import domain.user.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameMc Mc = new GameMc();
        Mc.gameMc();
        Mc.gameStart();
        Mc.isBlackJack();
        Mc.askHit();

        Mc.endGame();
    }
}
