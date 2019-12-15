package domain;

import domain.blackjackgame.BlackjackGame;
import domain.user.Users;

import static utility.PlayerUtility.generatePlayers;

public class PlayBlackjackGame {
    public static void main(String[] args) {
        Users users = generatePlayers();
        BlackjackGame blackjackGame = new BlackjackGame(users);
        blackjackGame.execute();

    }

}
