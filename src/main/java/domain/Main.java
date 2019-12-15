package domain;

import domain.card.*;
import domain.user.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        PrepareGame prepareGame = new PrepareGame();
        prepareGame.prepare();

        PlayGame playGame = new PlayGame(prepareGame.getPlayers());
        playGame.play();
    }
}
